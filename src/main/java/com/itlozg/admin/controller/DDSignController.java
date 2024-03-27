package com.itlozg.admin.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.itlozg.admin.entity.*;
import com.itlozg.admin.entity.dto.LoginDto;
import com.itlozg.admin.entity.vo.SignRepairApply;
import com.itlozg.admin.enums.StatusCode;
import com.itlozg.admin.exception.ApplicationException;
import com.itlozg.admin.mapper.SignRepairApplyMapper;
import com.itlozg.admin.model.request.ConfigRequest;
import com.itlozg.admin.model.request.SignCheckRequest;
import com.itlozg.admin.model.request.SignLegworkRequest;
import com.itlozg.admin.model.request.SignNextRequest;
import com.itlozg.admin.model.response.*;
import com.itlozg.admin.service.*;
import com.itlozg.admin.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@CrossOrigin(origins = "*")
//@RequestMapping(value = "/api/sign")
@RequestMapping(value = "/sign")
@Api(tags = "打卡Controller")
public class DDSignController {

    private static Logger logger = LoggerFactory.getLogger(DDSignController.class);
    @Autowired
    private ISignFinalService iSignFinalService;

    @Autowired
    private ISignNextService iSignNextService;

    @Autowired
    private ISignRuleService iSignRuleService;

    @Autowired
    private ISignLongitudeLatitudeService iSignLongitudeLatitudeService;

    @Autowired
    private ISignRangeService iSignRangeService;

    @Autowired
    private ISignLegworkService iSignLegworkService;

    @Autowired
    private ISignFileService iSignFileService;

    @Autowired
    private ISignNextAllService iSignNextAllService;

    @Autowired
    private SignRepairApplyMapper signRepairApplyMapper;

    /**
     * 获取月度打卡统计信息
     *
     * @param month yyyy-mm
     * @return
     */
    @RequestMapping(value = "/getSignInfoByMonth", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("获取某个月打卡信息")
    public List<SignFileResponse> getSignInfoByMonth(String month) {
        LoginDto user = ThreadLocalUtil.get();
        List<SignFileResponse> list = iSignFileService.getSignInfoByUserMonth(user.getId(), month);
        return list;
    }

    /**
     * 补卡申请
     */
    @RequestMapping(value = "/applyRepairSign", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("补卡或请假申请")
    public int applyRepairSign(String date, String type) throws ParseException {
        LoginDto user = ThreadLocalUtil.get();
        int row = iSignFileService.applyRepairSign(user.getId(), date, type); //更新sign_file的situation为2 申请补卡
        // 新增一条补卡记录
        SignRepairApply sra = new SignRepairApply();
        sra.setApplyNo(user.getId());
        sra.setApplyName(user.getUsername());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sra.setApplyTime(sdf.format(System.currentTimeMillis()));
        sra.setApplyRepairDate(date);
        sra.setCheckStatus("2"); // 状态待审批
        // 补卡
        if (type.equals("abnormal")) {
            sra.setRemarks("1");
        } else if (type.equals("nothing")) { // 请假
            sra.setRemarks("2");
        }
        signRepairApplyMapper.insert(sra);
        return row;
    }

    @RequestMapping(value = "/getSignDayHistoryNew", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取当日的打卡情况")
    public SignFileResponse getSignDayHistoryNew(String signDay, String nextId) throws ParseException {
        DateTimeFormatter queka = DateTimeFormatter.ofPattern("HH:mm:ss");
        LoginDto user = ThreadLocalUtil.get();

        // 填充一条用户当日打卡统计，并根据打卡计划给打卡统计记录赋初始值
        SignFileResponse response = null;

        //默认不传递nextid，所以下面这个判断暂时用不到
        if (StringUtils.isNotEmpty(nextId)) {
            try {
                SignNextResponse nextResponse = iSignNextService.getSignNextById(nextId);
                if (nextResponse != null) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    signDay = formatter.format(nextResponse.getSignDate());
                    response = BeanCopier.copy(nextResponse, SignFileResponse.class);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // --------------------------执行此处的代码-----------------------------

            //判断传入的值与今天的相差天数，传入当天，结果是0，传入今天以前的时间，值是正数，传入今天以后的时间，值为负数
            int days = getDaySub(signDay);
            //如果差值<0说明传入的日期比当前日期大，所以并没有打卡记录，所以返回一个空的SignFileResponse
            if (days < 0) {
                // 今天以后的时间
                return new SignFileResponse();
                // throw new ApplicationException(StatusCode.NOT_FOUND.getCode(), "未找到打卡信息");
                //如果差值<=2那么查询这两天之内的打卡信息，并将打卡信息转换成SignFileResponse
            } else if (days <= 2) {
                //从今天开始，以前的两天之内
                //1、两天之内 查询sign_next
                SignNextResponse nextResponse = iSignNextService.getSignInfoByUserDay(user.getId(), signDay);

                response = BeanCopier.copy(nextResponse, SignFileResponse.class);

                //下面就是查询差值大于两天的
            } else {
                //2、两天以前的查询sign_file
                response = iSignFileService.getSignInfoByUserDay(user.getId(), signDay);
            }
        }

        //sign_next_all信息，内勤打卡
        List<SignNextAllResponse> details = iSignNextAllService.getSignDetailByUserDay(user.getId(), signDay);

        //获取外勤打卡记录
        List<SignLegworkResponse> legWorkList = iSignLegworkService.getListByUserDay(user.getId(), signDay);

        /**
         * 如果经过上面的操作后response为null，那么创建一个新的SignFileResponse
         * 这样做可以防止空指针异常，又不影响结果
         */
        if (response == null) {
            response = new SignFileResponse();
        }

        SignRuleResponse signRuleResponse = null;
        try {
            //如果response中的ruleId不为null根据ruleId从redis中获取signRuleResponse
            if (StringUtils.isNotEmpty(response.getRuleId())) {
                signRuleResponse = getSignInfoFromRedis(response.getRuleId());
            } else {
                //如果response中的ruleId为null，根据userid获取SignFinal，然后再根据其中的ruleId获取signRuleResponse
                SignFinal signFinal = getUserRuleInfo(user.getId());
                if (signFinal != null && StringUtils.isNotEmpty(signFinal.getRuleId())) {
                    //如果获取到的规则不为空，则保存到Redis中
                    signRuleResponse = getSignInfoFromRedis(signFinal.getRuleId());
                }
            }
        } catch (Exception e) {
            signRuleResponse = null;
        }

        if (response != null && StringUtils.isEmpty(response.getId()) && signRuleResponse != null) {
            // 为统计记录设置打卡类型，自由还是固定，设置规则id，设置规则名称
            response.setSignType(signRuleResponse.getRuleType());
            response.setRuleId(signRuleResponse.getId());
            response.setRuleName(signRuleResponse.getName());
        }
        //赋值rule的结束时间
        if (signRuleResponse != null && StringUtils.isNotBlank(signRuleResponse.getEndTime())) {
            //如果有跨天打卡结束时间，就赋值
            response.setEndTime(signRuleResponse.getEndTime());
        }

        //如果有外勤打卡，也将外勤打卡设置到response中
        if (legWorkList != null && legWorkList.size() > 0) {
            response.setLegWorkDetails(legWorkList);
        }

        // TODO 在这个位置判断打卡时间，然后设置details的flag属性判断打卡情况的类别，后续如果有请假的情况也能够在这里增加判断条件

        //如果response不为空，details(内勤打卡明细)不为null
        if (response != null && details != null && details.size() > 0) {
            if (StringUtils.isNotEmpty(response.getComeAddress())) {
                response.setComeAddress(response.getComeAddress());
            } else {
                //如果response中没有ComeAddress就从signRuleResponse中获取ComeAddress
                if (signRuleResponse != null && StringUtils.isNotEmpty(signRuleResponse.getAddressName())) {
                    response.setComeAddress(signRuleResponse.getAddressName());
                }
            }

            //如果response中LeaveAddressName不为null那就直接用，否则就根据signRuleResponse中获取LeaveAddress
            if (StringUtils.isNotEmpty(response.getLeaveAddress())) {
                response.setLeaveAddress(response.getLeaveAddress());
            } else {
                if (signRuleResponse != null && StringUtils.isNotEmpty(signRuleResponse.getAddressName())) {
                    response.setLeaveAddress(signRuleResponse.getAddressName());
                }
            }

            //循环打卡信息明细  向打卡明细中设置打卡地点
            for (int i = 0; i < details.size(); i++) {
                if (StringUtils.isNotEmpty(details.get(i).getSignAddressName())) {
                    details.get(i).setSignAddress(details.get(i).getSignAddressName());
                } else {
                    if (signRuleResponse != null && StringUtils.isNotEmpty(signRuleResponse.getAddressName())) {
                        details.get(i).setSignAddress(signRuleResponse.getAddressName());
                    }
                }
            }

            if (details.size() > 0) {
                for (SignNextAllResponse detail : details) {
                    //判断打卡的情况，是否早退、迟到、缺卡等
                    calculateFlag(signRuleResponse, response, detail);
                }
            }

            //将内勤打卡明细设置到response中
            response.setDetails(details);
        }

        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        // 固定打卡，如果不打卡，自动再页面展示一个缺卡记录
        if(response.getSignType().equals("0")){
            // 当前时间晚于上班时间之后的三小时，并且没有内勤打卡记录，添加一条缺卡记录，这个缺卡只是页面展示使用，并不会入库
            if (time.toLocalTime().isAfter(LocalTime.parse(signRuleResponse.getWorkTime(), formatter).plusHours(3)) && details.size() == 0) {
                //如果没有打卡，系统自动添加一条缺卡记录
//                missingClockin(signDay, queka, user);
                SignNextAllResponse detail = new SignNextAllResponse();
                detail.setSignAddress("雷沃重工");
                /**
                 * 这个flag最终会映射到页面，并根据flag的值展示不同的信息，和不同的图片
                 * 0 正常 1 缺卡 2 迟到 3 早退 4 外勤
                 */
                detail.setFlag(1);
                detail.setSignType("0"); // sign_type 0 上班 1 下班
//                details.add(detail);
                response.setDetails(details);
            }
        }

        if (signRuleResponse != null) {
            //设置是否四次打卡，是否开启上班人脸识别，是否开启下班人脸识
            response.setIsQuartic(signRuleResponse.getIsQuartic());
            response.setComeVeriface(signRuleResponse.getComeVeriface());
            response.setLeaveVeriface(signRuleResponse.getLeaveVeriface());
        }

        response.setCode(200);
        return response;
    }

    private void missingClockin(String signDay, DateTimeFormatter queka, LoginDto user) {
        SignNextResponse nextResponse = iSignNextService.getSignInfoByUserDay(user.getId(), signDay);
        if (StringUtils.isBlank(nextResponse.getComeTime())) {
            // 如果实际上班打卡实际为空
            // 更新打卡计划来的上班信息为缺卡
            nextResponse.setComeTime(LocalTime.now().format(queka));
            nextResponse.setComeAddress("缺卡");
            nextResponse.setComeAddressName("缺卡");
            SignNext signNext = new SignNext();
            BeanUtils.copyProperties(nextResponse, signNext);
            iSignNextService.updateById(signNext);

            // 添加一条，内容为缺卡的内勤打卡记录
            SignNextAll signNextAll = new SignNextAll();
            signNextAll.setUserId(signNext.getUserId());
            signNextAll.setSignDate(signNext.getSignDate());
            signNextAll.setSignTime(signNext.getComeTime());
            signNextAll.setRuleId(signNext.getRuleId());
            signNextAll.setSignLongitude(signNext.getComeLongitude());
            signNextAll.setSignLatitude(signNext.getComeLatitude());
            signNextAll.setSignAddress(signNext.getComeAddress());
            signNextAll.setSignAddressName(signNext.getComeAddressName());
            signNextAll.setSignId(signNext.getId());
            signNextAll.setUniqueCode(UUID.randomUUID().toString());
            signNextAll.setDelFlag(0);
            signNextAll.setSignType("0"); // 上班
            signNextAll.setRuleType(signNext.getSignType());
            signNextAll.setSignWay(signNext.getSignWay());
            iSignNextAllService.save(signNextAll);
        }
    }

    // @Authorization
    @ApiOperation("保存外勤打卡记录（sign_legwork）")
    @RequestMapping(value = "/saveSignLegwork", method = RequestMethod.POST)
    @ResponseBody
    public Boolean saveSignLegwork(SignLegworkRequest signLegworkRequest, List<MultipartFile> files, HttpServletRequest request) {
        //设置id
        signLegworkRequest.setId(UUID.randomUUID().toString());
        LoginDto user = ThreadLocalUtil.get();

        //取消重复打卡限制

//        try {
//            if (JedisUtils.getObject(JedisUtils.KEY_PREFIX + "signover" + user.getId()) != null) {
//                // throw new ApplicationException(StatusCode.DATA_INTEGRITY_VIOLATION_EXCEPTION.getCode(), "打卡频繁，请稍后重试");
//                return false;
//                // return new SignNextResponse();
//            }
//        } catch (Exception ex) {
//            throw new ApplicationException(StatusCode.DATA_INTEGRITY_VIOLATION_EXCEPTION.getCode(), ex.getMessage());
//        }

        Boolean success = false;
        if (files != null && files.size() > 0) {
            Map<String, String> infos;
            infos = new UploadUtils().upload(files.get(0), request, "signPhoto");
            //保存图片路径
            if ("ok".equals(infos.get("status"))) {
                signLegworkRequest.setSignPhoto(infos.get("saveUrl"));
            }
        }

        //设置用户工号
        signLegworkRequest.setUserId(user.getId());

        // 如果外勤打卡传入的加密时间参数不为空
        if (StringUtils.isNotEmpty(signLegworkRequest.getParamVal())) {
            //验证时间是否准确
            boolean isTimeOk = checkTimeVal(signLegworkRequest.getSignTime());
            if (isTimeOk) {
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String dateNowStr = sdf.format(d);
                try {
                    //解密真实时间
                    String realTime = EncryptEUtils.DecryptDoNet(signLegworkRequest.getParamVal(), dateNowStr);
                    //如果外部打卡的签到时间不为null并且真实时间和外部打卡签到时间替换：为无是一样的（没有问题）否则报签名验证异常的错误
                    // 说人话就是加密的打卡时间和未加密的打卡时间一致，防止篡改时间
                    if ((StringUtils.isNotEmpty(signLegworkRequest.getSignTime()) && realTime.equals(signLegworkRequest.getSignTime().replaceAll(":", "")))) {

                    } else {
                        throw new ApplicationException(StatusCode.DATA_INTEGRITY_VIOLATION_EXCEPTION.getCode(), "打卡失败，签名验证异常");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ApplicationException(StatusCode.DATA_INTEGRITY_VIOLATION_EXCEPTION.getCode(), "打卡失败，签名验证异常");
                }

                //校验没有问题调用外部打卡service保存打卡信息
                //signLegworkRequest 这里除了有前台封装传递过来的参数之外，还设置了id和用户工号
                success = iSignLegworkService.saveSignInfo(signLegworkRequest);
                if (success) {
                    //这个应该时设置了十秒缓存时间  十秒内重复打卡会爆出上面的异常
                    JedisUtils.setObject(JedisUtils.KEY_PREFIX + "signover" + user.getId(), "1", JedisUtils.REDIS_CACH_SECOND_PCQRCODE_Min);
                }
                return success;
            } else {
                throw new ApplicationException(StatusCode.DATA_INTEGRITY_VIOLATION_EXCEPTION.getCode(), "时间信息异常，请校准时间");
            }
        } else {
            throw new ApplicationException(StatusCode.DATA_INTEGRITY_VIOLATION_EXCEPTION.getCode(), "打卡数据异常，请重新尝试");
        }
    }

    // @Authorization
    @ApiOperation("保存内勤打卡记录信息")
    @RequestMapping(value = "/saveSignInfo", method = RequestMethod.POST)
    @ResponseBody
    public SignNextResponse saveSignInfo(SignNextRequest signNextRequest, List<MultipartFile> files, HttpServletRequest request) {
        LoginDto user = ThreadLocalUtil.get();
        logger.error("saveSignInfo-{}-{}", JSON.toJSONString(user), JSON.toJSONString(signNextRequest));

        //取消重复打卡限制
        try {
            // redis中根据用户id获取信息
            if (JedisUtils.getObject(JedisUtils.KEY_PREFIX + "signover" + user.getId()) != null) {
                // throw new ApplicationException(StatusCode.DATA_INTEGRITY_VIOLATION_EXCEPTION.getCode(), "打卡频繁，请稍后重试");
                SignNextResponse signNextResponse = new SignNextResponse();
                signNextResponse.setCode(500);
                return signNextResponse;
            }
        } catch (Exception ex) {
            throw new ApplicationException(StatusCode.DATA_INTEGRITY_VIOLATION_EXCEPTION.getCode(), ex.getMessage());
        }

        // 上班时间不为空就设置上班时间，下班时间不为空就设置下班时间
        String timeVal = "";
        if (StringUtils.isNotBlank(signNextRequest.getComeTime())) {
            timeVal = signNextRequest.getComeTime();
        } else {
            timeVal = signNextRequest.getLeaveTime();
        }

        // 如果前端传递的加密时间参数不为空
        if (StringUtils.isNotEmpty(signNextRequest.getParamVal())) {
            // 判断加密的时间和传递的打卡时间是否一致，校验准确性
            //boolean isTimeOk = checkTimeVal(timeVal);

//            if (isTimeOk) {//校验时间信息
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateNowStr = sdf.format(d);
            try {
                //通过加密工具，对时间进行解密
                String realTime = EncryptEUtils.DecryptDoNet(signNextRequest.getParamVal(), dateNowStr);
                //（判断请求中打卡上班时间不为空  并且  真实时间和上班打卡时间相同）    或者  （请求中的下班时间不为null  并且真实时间和下班时间相同）
                if ((StringUtils.isNotEmpty(signNextRequest.getComeTime()) && realTime.equals(signNextRequest.getComeTime().replaceAll(":", ""))) || (StringUtils.isNotEmpty(signNextRequest.getLeaveTime()) && realTime.equals(signNextRequest.getLeaveTime().replaceAll(":", "")))) {

                } else {
                    throw new ApplicationException(StatusCode.DATA_INTEGRITY_VIOLATION_EXCEPTION.getCode(), "打卡失败，签名验证异常");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new ApplicationException(StatusCode.DATA_INTEGRITY_VIOLATION_EXCEPTION.getCode(), "打卡失败，签名验证异常");
            }

            /**
             * signWay 暂未使用，默认就是正常打卡
             * 打卡方式 0：正常打卡 1：自动打卡
             */
            if (StringUtils.isEmpty(signNextRequest.getSignWay())) {
                signNextRequest.setSignWay("0");
            }

            SignRuleResponse signRuleResponse = new SignRuleResponse();
            // 根据传递的参数，设置上班或下班地址
            if (StringUtils.isNotEmpty(signNextRequest.getRuleId())) {
                // 基本传递的规则ID都不为空，所以默认执行这个
                String addressName = "";
                signRuleResponse = getSignInfoFromRedis(signNextRequest.getRuleId());
                if (StringUtils.isNotBlank(signNextRequest.getComeTime())) {
                    addressName = iSignRuleService.getById(signNextRequest.getRuleId()).getAddressName();
                    signNextRequest.setComeAddressName(addressName);
                } else {
                    addressName = iSignRuleService.getById(signNextRequest.getRuleId()).getAddressName();
                    signNextRequest.setLeaveAddressName(addressName);
                }
            } else {
                SignFinal signFinal = getUserRuleInfo(user.getId());
                if (signFinal != null && StringUtils.isNotEmpty(signFinal.getRuleId())) {
                    signRuleResponse = getSignInfoFromRedis(signFinal.getRuleId());
                    String addressName = "";
                    if (StringUtils.isNotBlank(signNextRequest.getComeTime())) {
                        addressName = iSignRuleService.getById(signFinal.getRuleId()).getAddressName();
                        signNextRequest.setComeAddressName(addressName);
                    } else {
                        addressName = iSignRuleService.getById(signFinal.getRuleId()).getAddressName();
                        signNextRequest.setLeaveAddressName(addressName);
                    }
                    if (StringUtils.isEmpty(signNextRequest.getRuleId())) {
                        signNextRequest.setRuleId(signFinal.getRuleId());
                    }
                }
            }

            System.out.println("+++++"+signRuleResponse);

//            try {
//                if (signRuleResponse != null && StringUtils.isBlank(signRuleResponse.getWorkTime())) {
//                    ConfigResponse startPm = getConfigInfoFromRedis(ConfigRequest.START_WORK_TIME_AM);
//                    signRuleResponse.setWorkTime(startPm.getContent());
//                }
//                if (signRuleResponse != null && StringUtils.isBlank(signRuleResponse.getOffTime())) {
//                    ConfigResponse endPm = getConfigInfoFromRedis(ConfigRequest.END_WORK_TIME_PM);
//                    signRuleResponse.setOffTime(endPm.getContent());
//                }
//            } catch (Exception ex) {
//                //如果出现异常默认的打卡时间设置为上午八点半和下午五点半
//                signRuleResponse.setWorkTime("08:30");
//                signRuleResponse.setOffTime("17:30");
//            }

            //endregion
            SignNextResponse response = iSignNextService.saveSignInfoNew(user.getId(), signNextRequest, signRuleResponse);
            if (response == null) {
                //今日不需要打卡
                //response = iSignNextService.saveSignInfoNoWorkNew(user.getId(), signNextRequest, signRuleResponse);
                throw new ApplicationException(StatusCode.DATA_INTEGRITY_VIOLATION_EXCEPTION.getCode(), "昨日未打上班卡，无法进行昨日打卡");
            } else {
                //这个应该时设置了十秒缓存时间  十秒内重复打卡会爆出上面的异常
                JedisUtils.setObject(JedisUtils.KEY_PREFIX + "signover" + user.getId(), "1", JedisUtils.REDIS_CACH_SECOND_PCQRCODE_Min);
            }
            return response;

//            } else {
//                throw new ApplicationException(StatusCode.DATA_INTEGRITY_VIOLATION_EXCEPTION.getCode(), "时间信息异常，请校准时间");
//            }
        } else {
            throw new ApplicationException(StatusCode.DATA_INTEGRITY_VIOLATION_EXCEPTION.getCode(), "打卡数据异常，请重新尝试");
        }
    }


    /**
     * 检测打卡签到信息是否正确（主要校验位置） (新)
     * 这个应该是公司内部打卡会有的
     *
     * @param //user
     * @param request
     * @return 前端传入一个SignCheckRequest对象  包含打卡经纬度，打卡时间，打卡规则
     * 后端返回一个SignCheckResponse对象 包含打卡经纬度，打卡时间，打卡规则，是否可以打卡，警告信息
     */
//    @Authorization
    @ApiOperation("检测打卡签到信息是否正确（主要校验位置）")
    @RequestMapping(value = "/checkSignLocations", method = RequestMethod.POST)
    @ResponseBody
    public SignCheckResponse checkSignLocations(SignCheckRequest request) {
        //这里后续还要修改成真正的用户对象，这里只是用来做一个测试
        LoginDto user = ThreadLocalUtil.get();
        //创建一个对象 默认设置允许打卡
        SignCheckResponse result = new SignCheckResponse();
        //result.setCanSign(true);
        result.setCanSign(false);
        if (StringUtils.isBlank(request.getSignRuleId())) {//如果规则为空  取到最终规则    （根据用户id取到最终规则 并设置到请求中）
            SignFinal signFinal = iSignFinalService.getByUserId(user.getId());
            request.setSignRuleId(signFinal.getRuleId());
        }
        if (StringUtils.isNotBlank(request.getSignRuleId())) {//如果规则不为空
            //1、获取对应规则
            SignRuleResponse signRuleResponse = getSignInfoFromRedis(request.getSignRuleId());// iSignRuleService.getByRuleId(request.getSignRuleId());
            if (signRuleResponse != null && signRuleResponse.getLocationClock().equals("0")) {//启用位置打卡
                //2、获取对应规则的电子围栏信息（前提开启位置打卡）
                if (StringUtils.isNotEmpty(request.getSignRuleId())) {
                    SignLonLatInfoResponse response = getSignRangeFromRedis(request.getSignRuleId());//iSignLongitudeLatitudeService.findFenceByRuleIdGroupBy(request.getSignRuleId());

                    boolean isInArea = false;
                    for (int i = 0; i < response.getCircleData().size(); i++) {
                        //3、校验是否在电子围栏范围内
                        AreaPoint areaPoint = new AreaPoint();
                        ArrayList<Double> polygonXA = new ArrayList<>();
                        ArrayList<Double> polygonYA = new ArrayList<>();

//                        //获取到每一个多边形的所有经纬度
//                        for (int j = 0; j < response.getPolygonData().get(i).size(); j++) {
//                            polygonXA.add(Double.valueOf(response.getPolygonData().get(i).get(j).getLatitude()));
//                            polygonYA.add(Double.valueOf(response.getPolygonData().get(i).get(j).getLongitude()));
//                        }
                        //如果每个多边形中只有一个经纬度，采用下面的方式便利
//                        polygonXA.add(Double.valueOf(response.getPolygonData().get(i).get(0).getLatitude()));
//                        polygonYA.add(Double.valueOf(response.getPolygonData().get(i).get(0).getLongitude()));
                        double radius = Double.valueOf(response.getCircleData().get(0).getRadius());
                        polygonXA.add(Double.valueOf(response.getCircleData().get(0).getLatitude()));
                        polygonYA.add(Double.valueOf(response.getCircleData().get(0).getLongitude()));
                        //判断打卡地点在不在规定的位置内  在的话返回结果
                        //isInArea = areaPoint.isPointInPolygon(request.getLatitude(), request.getLongitude(), polygonXA, polygonYA);
                        isInArea = areaPoint.isPointInCircle(request.getLatitude(), request.getLongitude(), polygonXA, polygonYA, radius);
                        if (isInArea) {
                            result.setCanSign(true);
                            System.out.println(true);
                            return result;
                        }
                    }
                    if (!isInArea) {//如果不在范围内
                        result.setCanSign(false);
                        result.setWarnInfo("不在打卡范围");
                        logger.error("checkSignLocation-{}{}", JSON.toJSONString(user), JSON.toJSONString(request));
                    }
                } else {
                    result.setCanSign(false);
                    result.setWarnInfo("不在打卡范围");
                    System.out.println(false);
                }
            }
            return result;
        } else {//无对应打卡规则 不受限制  直接可以打卡
            return result;
        }
    }


    //@Authorization
    @ApiOperation("获取考勤范围")
    @RequestMapping(value = "/getSignRanges", method = RequestMethod.GET)
    @ResponseBody
    public SignLonLatInfoResponse getSignRanges(String ruleId) {
        LoginDto user = ThreadLocalUtil.get();
        String ruleIdVal = ""; //打卡规则ID
        if (StringUtils.isNotEmpty(ruleId)) {
            ruleIdVal = ruleId;
        } else {
            // -----------------------------这个请求前端默认是空参的，从这里开始执行-----------------------------------------
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            //根据用户工号和当日日期从redis中获取当日的打卡计划信息
            SignNextResponse nextResponse = getSignNextResponseFromRedis(user.getId(), df.format(new Date()));

            //iSignNextService.getSignInfoByUserToday(user.getId());
            //if (nextResponse != null) {
            //System.out.println(nextResponse.getRuleId());

            if (nextResponse != null && StringUtils.isNotEmpty(nextResponse.getRuleId())) {
                // 如果查询到用户当日的打卡计划信息，就把打卡规则的id赋值给ruleVal
                ruleIdVal = nextResponse.getRuleId();
            } else {
                //**********如果没有打卡计划应该不允许打卡，这里以后要处理**********
                //在考勤组人员表查询当前用户是否有考勤规则，此处意义不明后续处理
                SignFinal signFinal = getUserRuleInfo(user.getId());
                if (signFinal != null && StringUtils.isNotEmpty(signFinal.getRuleId())) {
                    ruleIdVal = signFinal.getRuleId();
                }
            }
        }

        //如果打卡规则不会空，根据打卡规则从redis中获取打卡范围
        if (StringUtils.isNotBlank(ruleIdVal)) {
            SignLonLatInfoResponse infoResponse = getSignRangeFromRedis(ruleIdVal);
            //iSignLongitudeLatitudeService.findFenceByRuleIdGroupBy(ruleIdVal);
            return infoResponse;
        } else {
            throw new ApplicationException(StatusCode.NOT_FOUND.getCode(), "未设置打卡规则");
        }
    }


    //目前判断的逻辑只有0正常打卡、1无打卡规则、3节假日三种情况启用
    //今天是否需要打卡：0 正常打卡；1 无打卡规则；2 在白名单；3 节假日；4 有打卡规则但还未生成打卡信息
    @ApiOperation("当日是否需要打卡")
    @RequestMapping(value = "/isNeedSign", method = RequestMethod.GET)
    @ResponseBody
    public int isNeedSign() {
        LoginDto user = ThreadLocalUtil.get();
        boolean isNeedDeal = false; //特殊时段标志
        try {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            //判断时间是否在特殊时段内
            isNeedDeal = hourMinuteBetween(sdf.format(d), "00:00", "01:30");
        } catch (Exception ex) {
        }

        // SignNextResponse signNextResponse = iSignNextService.getSignInfoByUserTodayOnlyInfo(user.getId());
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        //根据员工编号和当前日期获取打卡计划信息
        SignNextResponse signNextResponse = getSignNextResponseFromRedis(user.getId(), df.format(new Date()));
        //SignNextResponse signNextResponse = null;
        if (signNextResponse == null && isNeedDeal) {
            //没有打卡计划，但是在特殊时段，判断是不是夜班存在跨天打卡的情况，就是昨天的计划是跨天的，今天凌晨打卡的情况
            signNextResponse = iSignNextService.getSignInfoByUserToday(user.getId(), isNeedDeal);
        }

        if (signNextResponse != null) {
            //打卡计划不等于空，有计划那就是正常打卡
            return 0;
        } else {
            SignFinal signFinal = getUserRuleInfo(user.getId()); // iSignFinalService.getByUserId(user.getId());
            //查询考勤组人员表，看当前用户有没有考勤规则
            if (signFinal == null) {
                //如果也没有考勤规则，那就是1 无考勤规则
                return 1;
            } else {
                // 如果考勤组人员表中，当前用户也没有对应的考勤规则，那么就认为是节假日
                //节假日
                return 3;
            }
        }
    }

    //@Authorization
    @ApiOperation("获取当日打卡计划信息")
    @RequestMapping(value = "/getSignInfo", method = RequestMethod.GET)
    @ResponseBody
    public SignNextResponse getSignInfo() {
        LoginDto user = ThreadLocalUtil.get();
        boolean isNeedDeal = false; //是否特殊时段标志
        try {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            //判断时间是否在指定的时间段内
            isNeedDeal = hourMinuteBetween(sdf.format(d), "00:00", "01:30");
        } catch (Exception ex) {
        }

        //获取用户打卡计划的响应对象，就是打卡计划对象，加上状态值
        SignNextResponse response = iSignNextService.getSignInfoByUserToday(user.getId());
        if (response == null && isNeedDeal) {
            //没有打卡计划但是在特殊时段内，夜班的情况
            response = iSignNextService.getSignInfoByUserToday(user.getId(), isNeedDeal);
        }
        if (response != null) {
            //iSignRuleService.get(response.getRuleId());
            //从redis中获取打卡规则的响应
            SignRuleResponse signRuleResponse = getSignInfoFromRedis(response.getRuleId());
            //上午下班时间为空  默认取全局设定
//            if (StringUtils.isBlank(signRuleResponse.getAmOffTime())) {
//                //从redis中获取config  主要包含的是上午下班时间  这个config具体指什么？？？
//                ConfigResponse endAm = getConfigInfoFromRedis(ConfigRequest.END_WORK_TIME_AM);// configService.getByType(ConfigRequest.END_WORK_TIME_AM);
//                //将上午的下班时间设置在响应中
//                response.setAmOffTime(endAm.getContent());
//            } else {
//                //如果缓存中存在上午下班时间，那么直接设置下午下班时间到响应中
//                response.setAmOffTime(signRuleResponse.getAmOffTime());
//            }
//            //下午上班时间为空  默认取全局设定
//            //获取下午上班时间  并且设置到响应中返回
//            if (StringUtils.isBlank(signRuleResponse.getPmWorkTime())) {
//                ConfigResponse startPm = getConfigInfoFromRedis(ConfigRequest.START_WORK_TIME_PM);// configService.getByType(ConfigRequest.START_WORK_TIME_PM);
//                response.setPmWorkTime(startPm.getContent());
//            } else {
//                response.setPmWorkTime(signRuleResponse.getPmWorkTime());
//            }
//            //是否四次打卡 0 否 1 是
//            response.setIsQuartic(signRuleResponse.getIsQuartic());
//            //是否开启上班打卡人脸识别  0：否 1：是
//            response.setComeVeriface(signRuleResponse.getComeVeriface());
//            是否开启下班打卡人脸识别  0：否 1：是
//            response.setLeaveVeriface(signRuleResponse.getLeaveVeriface());

            //endtime 跨天打卡结束时间
            //ruletype 规则类型：0 固定打卡；1 自由打卡
            if (StringUtils.isNotBlank(signRuleResponse.getEndTime()) && "1".equals(signRuleResponse.getRuleType())) {
                //如果跨天打卡结束时间不为空，不包含空白字符，并且打卡规则是自由打卡时，把打卡结束时间设置为查询到的规则中的跨天打卡结束时间
                response.setEndTime(signRuleResponse.getEndTime());
            }
            response.setCode(200);
            return response;
        } else {
            //没有打卡计划
            throw new ApplicationException(StatusCode.NOT_FOUND.getCode(), "未找到打卡计划信息，无需打卡");
        }
    }


    /**
     * @param nowDate   要比较的时间
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return true在时间段内，false不在时间段内
     * @throws Exception
     */
    public boolean hourMinuteBetween(String nowDate, String startDate, String endDate) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date now = format.parse(nowDate);
        Date start = format.parse(startDate);
        Date end = format.parse(endDate);
        long nowTime = now.getTime();
        long startTime = start.getTime();
        long endTime = end.getTime();
        return nowTime >= startTime && nowTime <= endTime;
    }


    /*
     * 获取用户当日打卡计划信息
     * */
    private SignNextResponse getSignNextResponseFromRedis(String userId, String dayVal) {
        try {
            String jsonStr = "";
            if (JedisUtils.getObject(JedisUtils.KEY_PREFIX + "signnext" + userId + dayVal) != null) {
                jsonStr = (String) JedisUtils.getObject(JedisUtils.KEY_PREFIX + "signnext" + userId + dayVal);
                SignNextResponse infoResponse = JSONObject.parseObject(jsonStr, new TypeReference<SignNextResponse>() {
                });
                if (infoResponse != null) {
                    return infoResponse;
                }
            }
            //redis中没有打卡计划的响应对象，就要从数据库中查询并且存储到redis中
            SignNextResponse infoResponse = iSignNextService.getSignInfoByUserToday(userId);
            if (infoResponse != null) {
                JedisUtils.setObject(JedisUtils.KEY_PREFIX + "signnext" + userId + dayVal, JSONObject.toJSONString(infoResponse), JedisUtils.REDIS_CACH_3H);
                return infoResponse;
            } else {
                JedisUtils.setObject(JedisUtils.KEY_PREFIX + "signnext" + userId + dayVal, "", JedisUtils.REDIS_CACH_3H);
                return infoResponse;
            }

        } catch (Exception ex) {
            SignNextResponse infoResponse = iSignNextService.getSignInfoByUserToday(userId);
            return infoResponse;
        }
    }

    private SignFinal getUserRuleInfo(String userId) {
        try {
            String jsonStr = "";
            if (JedisUtils.getObject(JedisUtils.KEY_PREFIX + "signfinal" + userId) != null) {
                jsonStr = (String) JedisUtils.getObject(JedisUtils.KEY_PREFIX + "signfinal" + userId);
                SignFinal signFinal = JSONObject.parseObject(jsonStr, new TypeReference<SignFinal>() {
                });
                if (signFinal != null) {
                    return signFinal;
                }
            }
            SignFinal signFinal = iSignFinalService.getByUserId(userId);
            if (signFinal != null) {
                JedisUtils.setObject(JedisUtils.KEY_PREFIX + "signfinal" + userId, JSONObject.toJSONString(signFinal), JedisUtils.REDIS_CACH_3H);
                return signFinal;
            } else {
                return null;
            }
        } catch (Exception ex) {
            SignFinal signFinal = iSignFinalService.getByUserId(userId);
            return signFinal;
        }
    }

    /**
     * 缓存获取打卡信息
     *
     * @param ruleIdVal
     * @return
     */
    private SignRuleResponse getSignInfoFromRedis(String ruleIdVal) {
        try {
            String jsonStr = "";
            if (JedisUtils.getObject(JedisUtils.KEY_PREFIX + "signrule" + ruleIdVal) != null) {
                jsonStr = (String) JedisUtils.getObject(JedisUtils.KEY_PREFIX + "signrule" + ruleIdVal);
                SignRuleResponse infoResponse = JSONObject.parseObject(jsonStr, new TypeReference<SignRuleResponse>() {
                });
                if (infoResponse != null) {
                    return infoResponse;
                }
            }
            SignRuleResponse infoResponse = iSignRuleService.getByRuleId(ruleIdVal);
            JedisUtils.setObject(JedisUtils.KEY_PREFIX + "signrule" + ruleIdVal, JSONObject.toJSONString(infoResponse), JedisUtils.REDIS_CACH_48H);
            return infoResponse;

        } catch (Exception ex) {
            SignRuleResponse infoResponse = iSignRuleService.getByRuleId(ruleIdVal);
            return infoResponse;
        }
    }

    /*
     * 从redis缓存中获取配置信息
     * */
    private ConfigResponse getConfigInfoFromRedis(int infoVal) {
        try {
            String jsonStr = "";
            //如果从redis中获取到config，转换成对象之后返回
//            if (JedisUtils.getObject(JedisUtils.KEY_PREFIX + "configinfo" + infoVal) != null) {
//                jsonStr = (String) JedisUtils.getObject(JedisUtils.KEY_PREFIX + "configinfo" + infoVal);
//                ConfigResponse infoResponse = JSONObject.parseObject(jsonStr, new TypeReference<ConfigResponse>() {
//                });
//                if (infoResponse != null) {
//                    return infoResponse;
//                }
//            }
            //没有的话就从数据库中寻找，并且保存到redis中，返回cofig
            //ConfigResponse infoResponse = configService.getByType(infoVal);
            ConfigResponse infoResponse = null;
//            JedisUtils.setObject(JedisUtils.KEY_PREFIX + "configinfo" + infoVal, JSONObject.toJSONString(infoResponse), JedisUtils.REDIS_CACH_48H);
            return infoResponse;

        } catch (Exception ex) {
            //ConfigResponse infoResponse = configService.getByType(infoVal);
            ConfigResponse infoResponse = null;
            return infoResponse;
        }
    }

    /**
     * 缓存获取打卡范围信息
     *
     * @param ruleIdVal
     * @return
     */
    private SignLonLatInfoResponse getSignRangeFromRedis(String ruleIdVal) {
        try {
            String jsonStr = "";
            if (JedisUtils.getObject(JedisUtils.KEY_PREFIX + "signranges" + ruleIdVal) != null) {
                jsonStr = (String) JedisUtils.getObject(JedisUtils.KEY_PREFIX + "signranges" + ruleIdVal);
                SignLonLatInfoResponse infoResponse = JSONObject.parseObject(jsonStr, new TypeReference<SignLonLatInfoResponse>() {
                });
                if (infoResponse != null) {
                    return infoResponse;
                }
            }
            //如果redis中没有信息，从数据库中查询设置到redis中
            //SignLonLatInfoResponse infoResponse = iSignLongitudeLatitudeService.findFenceByRuleIdGroupBy(ruleIdVal);
            SignLonLatInfoResponse infoResponse = iSignRangeService.findFenceByRuleIdGroupBy(ruleIdVal);
            JedisUtils.setObject(JedisUtils.KEY_PREFIX + "signranges" + ruleIdVal, JSONObject.toJSONString(infoResponse), JedisUtils.REDIS_CACH_48H);
            return infoResponse;

        } catch (Exception ex) {
            //SignLonLatInfoResponse infoResponse = iSignLongitudeLatitudeService.findFenceByRuleIdGroupBy(ruleIdVal);
            SignLonLatInfoResponse infoResponse = iSignRangeService.findFenceByRuleIdGroupBy(ruleIdVal);
            return infoResponse;
        }
    }


    /**
     * 与服务器时间校验时间准确性(默认返回不符合)
     *
     * @param realTime 真实时间
     * @return
     */
    public static boolean checkTimeVal(String realTime) {
        try {
            //日期格式化
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            String nowTime = format.format(date);
            if (nowTime.indexOf(":") < 0 || realTime.indexOf(":") < 0) {
                System.out.println("格式不正确");
                return false;
            } else {
                //将时间转换为int数据类型，做减法运算判断是否在180s内完成打卡操作
                String[] array1 = nowTime.split(":");
                int total1 = Integer.valueOf(array1[0]) * 3600 + Integer.valueOf(array1[1]) * 60;
                if (array1.length == 3) {
                    total1 += +Integer.valueOf(array1[2]);
                }
                String[] array2 = realTime.split(":");
                int total2 = Integer.valueOf(array2[0]) * 3600 + Integer.valueOf(array2[1]) * 60;
                if (array2.length == 3) {
                    total2 += +Integer.valueOf(array2[2]);
                }
                int timeVal = total1 - total2;
                return timeVal > 180 || timeVal < -180 ? false : true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
     * 获取打卡位置名称
     * */
    private Map<Boolean, String> getSignAddressName(String ruleId, String latValStr, String longValStr) {
        String addressName = "";
        boolean isInArea = false;
        Map<Boolean, String> result = new HashMap<Boolean, String>();
        try {
            //定义经纬度
            double latVal = Double.valueOf(latValStr);
            double longVal = Double.valueOf(longValStr);
            //从缓存中获取经纬度信息
            SignLonLatInfoResponse response = getSignRangeFromRedis(ruleId);// iSignLongitudeLatitudeService.findFenceByRuleIdGroupBy(ruleId);
            for (int i = 0; i < response.getCircleData().size(); i++) {
                //3、校验是否在电子围栏范围内
                AreaPoint areaPoint = new AreaPoint();
                ArrayList<Double> polygonXA = new ArrayList<>();
                ArrayList<Double> polygonYA = new ArrayList<>();

//               //获取到每一个多边形的所有经纬度
//               for (int j = 0; j < response.getPolygonData().get(i).size(); j++) {
//                   polygonXA.add(Double.valueOf(response.getPolygonData().get(i).get(j).getLatitude()));
//                   polygonYA.add(Double.valueOf(response.getPolygonData().get(i).get(j).getLongitude()));
//
                //如果每个多边形中只有一个经纬度，采用下面的方式便利
//               polygonXA.add(Double.valueOf(response.getPolygonData().get(i).get(0).getLatitude()));
//               polygonYA.add(Double.valueOf(response.getPolygonData().get(i).get(0).getLongitude()));
                double radius = Double.valueOf(response.getCircleData().get(0).getRadius());
                polygonXA.add(Double.valueOf(response.getCircleData().get(0).getLatitude()));
                polygonYA.add(Double.valueOf(response.getCircleData().get(0).getLongitude()));
                //判断打卡地点在不在规定的位置内  在的话返回结果
                //isInArea = areaPoint.isPointInPolygon(request.getLatitude(), request.getLongitude(), polygonXA, polygonYA);
                isInArea = areaPoint.isPointInCircle(latVal, longVal, polygonXA, polygonYA, radius);
                if (isInArea) {
                    addressName = response.getCircleData().get(0).getRemarks();
                    break;
                }

//            for (int i = 0; i < response.getPolygonData().size(); i++) {
//                //3、校验是否在电子围栏范围内
//                AreaPoint areaPoint = new AreaPoint();
//                ArrayList<Double> polygonXA = new ArrayList<>();
//                ArrayList<Double> polygonYA = new ArrayList<>();
//
//                for (int j = 0; j < response.getPolygonData().get(i).size(); j++) {
//                    polygonXA.add(Double.valueOf(response.getPolygonData().get(i).get(j).getLatitude()));
//                    polygonYA.add(Double.valueOf(response.getPolygonData().get(i).get(j).getLongitude()));
//                }
//                polygonXA.add(Double.valueOf(response.getPolygonData().get(i).get(0).getLatitude()));
//                polygonYA.add(Double.valueOf(response.getPolygonData().get(i).get(0).getLongitude()));
//                isInArea = areaPoint.isPointInPolygon(latVal, longVal, polygonXA, polygonYA);
//                if (isInArea) {
//                    addressName = response.getPolygonData().get(i).get(i).getRemarks();
//                    break;
//                }
            }
        } catch (Exception e) {

        }
        result.put(isInArea, addressName);
        return result;
        //return addressName;
    }


    /**
     * 功能描述：计算时间与当前相差天数
     *
     * @param dayVal
     * @return long
     * @author xqz
     */
    private int getDaySub(String dayVal) {
        int day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate;
        Date dayDate;
        try {
            //获取当前时间 并将当前时间转换成指定格式，再将传入时间转换成指定格式，两者相减得到一个时间再除去一天的时间，得出有多少天。
            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String nowDateStr = formatter.format(now);
            nowDate = format.parse(nowDateStr);
            dayDate = format.parse(dayVal);
            day = (int) ((nowDate.getTime() - dayDate.getTime()) / (24 * 60 * 60 * 1000));
        } catch (ParseException e) {
            //自动生成 catch 块
            e.printStackTrace();
        }
        return day;
    }

    public void calculateFlag(SignRuleResponse signRuleResponse, SignFileResponse response, SignNextAllResponse detail) {
        /**
         * 上午上班时间之前打卡视为正常打卡
         * 上午上班之后一个小时视为迟到打卡
         * 上午下班之前未打卡视为缺卡
         * 下午上班到下午下班之间打卡视为早退
         * 下午下班之后到22:30之间打卡视为正常打卡
         * 22:30之后打卡视为缺卡
         * 0：正常  1：缺卡  2：迟到  3：早退  4：外勤
         */
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        DateFormat dateTimeFormat = new SimpleDateFormat("HH:mm:ss");
        switch (detail.getSignType()) {
            case "0": { // 上班
                try {
                    Date workStartTime = timeFormat.parse(signRuleResponse.getWorkTime());
                    Date come = dateTimeFormat.parse(response.getComeTime());

                    if (come.before(workStartTime)) {
                        detail.setFlag(0);
                    } else {
                        long minutesLate = (come.getTime() - workStartTime.getTime()) / (1000 * 60);
                        if (minutesLate <= 60 && minutesLate >= 0) {
                            detail.setFlag(2);
//                        } else if (minutesLate > 60 && amEndTime >0){
                        } else {
                            detail.setFlag(1);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "1": { // 下班
                try {
                    Date offTime = timeFormat.parse(signRuleResponse.getOffTime());
                    Date leave = dateTimeFormat.parse(response.getLeaveTime());

                    if ((leave.after(offTime) && leave.before(dateTimeFormat.parse("22:30:00")))) {
                        detail.setFlag(0);
                    } else if (leave.after(dateTimeFormat.parse("22:30:00"))) {
                        detail.setFlag(1);
                    } else if (leave.before(offTime)) {
                        detail.setFlag(3);
                    } else {
                        detail.setFlag(1);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
