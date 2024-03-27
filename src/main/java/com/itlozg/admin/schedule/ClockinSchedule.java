package com.itlozg.admin.schedule;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itlozg.admin.entity.*;
import com.itlozg.admin.entity.dto.SignNextDto;
import com.itlozg.admin.entity.vo.SignNextVO;
import com.itlozg.admin.model.response.SignFileResponse;
import com.itlozg.admin.model.response.SignNextAllResponse;
import com.itlozg.admin.model.response.SignRuleResponse;
import com.itlozg.admin.service.*;
import com.itlozg.admin.util.StringUtils;
import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 沃考勤定时任务
 */

//@Component
public class ClockinSchedule {
    @Autowired
    private ISignNextService iSignNextService;

    @Autowired
    private ISignFileService iSignFileService;

    @Autowired
    private ISignRuleService iSignRuleService;

    @Autowired
    private ISignGroupService iSignGroupService;

    @Autowired
    private ISignNextAllService iSignNextAllService;

    @Autowired
    private SignTemplateService signTemplateService;

    /**
     * 执行时间 周一到周六，每隔十分钟执行一次
     * 执行内容 查询在接下来的10分钟内上班，但还未打卡的员工的工号，发送HiWork打卡提醒信息
     */
    //@Scheduled(cron = "0 0,10,20,30,40,50 * * * 1-6")
    public void ClockinTip() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = sdf.format(new Date());
        String userIds = iSignGroupService.getMsgNos(dateNow);
        System.out.println(userIds);
        if (userIds == null) {
            return;
        }

        String authForm = "{" + "\"sendBy\":\"" + "501905df0b924bea868011f70a6f4dc3" + "\"," + "\"userIds\":\"" + userIds + "\"," + "\"title\":\"" + "打卡提醒" + "\"," + "\"content\":\"" + "距离上班时间还有10分钟，请您记得及时打卡" + "\"," + "\"msgType\":\"" + "card" + "\"," + "\"mediaId\":\"" + "https://img.tukuppt.com/png_preview/00/06/87/K8Il2IsTtm.jpg%21/fw/780" + "\"," + "\"url\":\"" + "http://221.215.96.250:8082/#/inside" + "\"," + "}";

        System.out.println("lhw");

        //System.out.println("--------接口参数--------" + authForm);
        //钉钉JSAPI鉴权接口, 接口说明见下文
        HttpPost httpPost = new HttpPost("https://dingding.weichai.com/dingding/pushNotify");
        httpPost.setEntity(new StringEntity(authForm, "utf-8"));
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String authInfo = EntityUtils.toString(response.getEntity());
        try {
            response.close();
            httpPost.releaseConnection();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(JSON.parseObject(authInfo, Map.class));
    }

    /**
     * 01：00
     * 生成当日打卡计划
     */
    @Scheduled(cron = "0 0 1 * * ? ")
    public void SignNextSave() {
        // 区分使用组模板的固定打卡人员、使用个人模板的固定打卡人员和自由打卡人员
        List<SignNextVO> normal = iSignNextService.getSureUser().stream()
                .filter(signNextDto -> (signNextDto.getTemplateGroup() != null && signNextDto.getTemplateUser() == null))
                .collect(Collectors.toList());
        List<SignNextVO> special = iSignNextService.getSureUser().stream()
                .filter(signNextDto -> signNextDto.getTemplateUser() != null)
                .collect(Collectors.toList());
        List<SignNextVO> freeUsers = iSignNextService.getFreeUser();

        // 根据模板查询规则，分别拼装普通用户、特殊用户和自由打卡用户的打卡计划，最后合并
        List<SignNext> signNexts = normal.stream().map(signNextVO -> {
            String template = signTemplateService.getNormalTemplate(signNextVO.getTemplateGroup());
            return saveRuleInfo(template, signNextVO);
        }).filter(next -> next != null).collect(Collectors.toList());
        special.stream().forEach(signNextVO -> {
            String template = signTemplateService.getSpecialTemplate(signNextVO.getUserId(), signNextVO.getGroupId());
            SignNext signNext = saveRuleInfo(template, signNextVO);
            if (signNext != null) {
                signNexts.add(signNext);
            }
        });
        freeUsers.stream().forEach(signNextVO -> {
            SignNext signNext = new SignNext();
            String ruleId = "5"; // 自由打卡规则
            SignRuleResponse ruleResponse = iSignRuleService.getByRuleId(ruleId);
            signNextVO.setComeMustTime(ruleResponse.getWorkTime()); // 填充上班时间
            signNextVO.setLeaveMustTime(ruleResponse.getOffTime()); // 填充下班时间
            signNextVO.setSignType(ruleResponse.getRuleType()); // 填充规则类型（固定打卡/自由打卡）
            signNextVO.setRuleId(ruleId); // 填充规则id
            BeanUtils.copyProperties(signNextVO, signNext);
            signNexts.add(signNext);
        });

        // 计划集合入库
        if (signNexts.size() > 0) {
            iSignNextService.saveBatch(signNexts);
        }
    }

    /**
     * 00：30
     * 统计昨天打卡情况（不跨天）
     */
    @SneakyThrows
    @Scheduled(cron = "0 30 0 * * ?")
    public void executeTask() {
        // 每个考勤组分别统计
        List<SignGroup> list = iSignGroupService.getList();
        for (SignGroup signGroup : list) {
            // 如果当前考勤组昨天没有打卡计划，跳过统计
            List<SignNextVO> allLastday = iSignNextService.getSignInfoAllLastday(signGroup.getId());
            if (allLastday == null) continue;
            // 筛选出不是跨天的打卡计划
            List<SignNextVO> dealList = allLastday.stream()
                    .filter(SignNextVO -> SignNextVO.getCrossDay().equals("0"))
                    .collect(Collectors.toList());
            if (dealList == null) return;

            LocalDate today = LocalDate.now();
            LocalDate yesterday = today.minusDays(1);

            List<SignFile> fileList = dealList.stream().map(signNextVO -> {
                // 如果是请假的情况会提前生成统计信息，如果已经存在，就不再插入统计
                SignFileResponse file = iSignFileService.getSignFileByUserDay(yesterday.toString(), signNextVO.getUserId());
                if (file != null) {
                    return null;
                }

                // 根据考勤计划查询考勤规则
                String ruleId = "1";
                if (signNextVO.getSignType().equals("1")) {
                    ruleId = "5"; // 自由打卡设置默认规则
                } else if (signNextVO.getTemplateUser() != null) {
                    String template = signTemplateService.getSpecialTemplate(signNextVO.getUserId(), signNextVO.getGroupId());
                    ruleId = findYesDayIndex(template);
                } else if (signNextVO.getTemplateGroup() != null && signNextVO.getTemplateUser() == null) {
                    String template = signTemplateService.getNormalTemplate(signNextVO.getTemplateGroup());
                    ruleId = findYesDayIndex(template);
                }
                SignRuleResponse rule = iSignRuleService.getByRuleId(ruleId);

                // 填充打卡计划中的信息
                SignFile signFile = new SignFile();
                BeanUtils.copyProperties(signNextVO, signFile);
                signFile.setWorkHours(rule.getWorkHours());
                signFile.setStatus("1"); // 暂未启用，不知含义？

                // 外勤计划会提前设置signFlag，而非外勤计划的signFlag就是空
                if (signNextVO.getSignFlag() == null) {
                    signFile.setIsLeave("0"); // is_leave 0 正常；1 请假；2 出差；3 外勤；4 调休；
                } else {
                    signFile.setIsLeave("2"); // 外勤计划会提前设置signFlag为3，这里暂时把外勤定义为出差
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formatDate = dateFormat.format(signNextVO.getSignDate());
                if (signNextVO.getSignType().equals("0")) {
                    // 固定打卡
                    long workHours = countWorkTime(signNextVO.getUserId(), formatDate, ruleId);
                    signFile.setWorkHoursReal(new BigDecimal(workHours));
                    if (workHours >= 7.5) {
                        signFile.setSituation("0"); // 工时大于8小时，设置为正常
                    } else {
                        signFile.setSituation("1"); // 工时小于8小时，设置为异常
                    }
                } else {
                    // 自由打卡
                    long workHours = countFreeWorkTime(signNextVO.getUserId(), formatDate, ruleId);
                    signFile.setWorkHoursReal(new BigDecimal(workHours));
                    if (workHours >= 7.5) {
                        signFile.setSituation("0"); // 工时大于8小时，设置为正常
                    } else {
                        signFile.setSituation("1"); // 工时小于8小时，设置为异常
                    }
                }

                return signFile;
            }).collect(Collectors.toList());

            // 统计集合入库
            iSignFileService.saveBatch(fileList);
        }
    }

    /**
     * 12：00
     * 统计昨天打卡情况（跨天）
     */
    @SneakyThrows
    @Scheduled(cron = "0 0 12 * * ? ")
    public void executeTaskCross() {
        // 每个考勤组分别统计
        List<SignGroup> list = iSignGroupService.getList();
        for (SignGroup signGroup : list) {
            // 如果当前考勤组昨天没有打卡计划，跳过统计
            List<SignNextVO> allLastday = iSignNextService.getSignInfoAllLastday(signGroup.getId());
            if (allLastday == null) continue;
            // 筛选出不是跨天的打卡计划
            List<SignNextVO> dealList = allLastday.stream()
                    .filter(SignNextVO -> SignNextVO.getCrossDay().equals("1"))
                    .collect(Collectors.toList());
            if (dealList == null) return;

            LocalDate today = LocalDate.now();
            LocalDate yesterday = today.minusDays(1);

            List<SignFile> fileList = dealList.stream().map(signNextVO -> {
                // 如果是请假的情况会提前生成统计信息，如果已经存在，就不再插入统计
                SignFileResponse file = iSignFileService.getSignFileByUserDay(yesterday.toString(), signNextVO.getUserId());
                if (file != null) {
                    return null;
                }

                // 根据考勤计划查询考勤规则
                String ruleId = "1";
                if (signNextVO.getSignType().equals("1")) {
                    ruleId = "5"; // 自由打卡设置默认规则
                } else if (signNextVO.getTemplateUser() != null) {
                    String template = signTemplateService.getSpecialTemplate(signNextVO.getUserId(), signNextVO.getGroupId());
                    ruleId = findYesDayIndex(template);
                } else {
                    String template = signTemplateService.getNormalTemplate(signNextVO.getTemplateGroup());
                    ruleId = findYesDayIndex(template);
                }
                SignRuleResponse rule = iSignRuleService.getByRuleId(ruleId);

                // 填充打卡计划中的信息
                SignFile signFile = new SignFile();
                BeanUtils.copyProperties(signNextVO, signFile);
                signFile.setWorkHours(rule.getWorkHours());
                signFile.setStatus("1"); // 暂未启用，不知含义？

                // 外勤计划会提前设置signFlag，而非外勤计划的signFlag就是空
                if (signNextVO.getSignFlag() == null) {
                    signFile.setIsLeave("0"); // is_leave 0 正常；1 请假；2 出差；3 外勤；4 调休；
                } else {
                    signFile.setIsLeave("2"); // 外勤计划会提前设置signFlag为3，这里暂时把外勤定义为出差
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formatDate = dateFormat.format(signNextVO.getSignDate());
                long workHours = countWorkTimeCross(signNextVO.getUserId(), formatDate, ruleId);
                signFile.setWorkHoursReal(new BigDecimal(workHours));
                if (workHours >= 7.5) {
                    signFile.setSituation("0"); // 工时大于8小时，设置为正常
                } else {
                    signFile.setSituation("1"); // 工时小于8小时，设置为异常
                }

                return signFile;
            }).collect(Collectors.toList());

            // 统计入库
            iSignFileService.saveBatch(fileList);
        }
    }

    /**
     * 06：00 ~ 09：00，每隔20秒执行一次
     * 执行车辆通过闸机进场的打卡处理
     */
    @Scheduled(cron = "0,20,40 * 6,7,8 * * ?")
    public void CarAutoClock() {
        // 调用车辆信息的接口，获取车辆信息
        String url_GET = "http://172.16.162.252:9091/carautoclockin/carinfo/getCarInfo";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url_GET, String.class);
        if (result != null && result != "") {
            List<SignNextDto> signNextDtos = JSON.parseArray(result, SignNextDto.class);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            List<SignNext> signNexts = new ArrayList<>(signNextDtos.size());
            //根据工号，日期，查询当天计划
            signNextDtos.stream().forEach(signNextDto -> {
                SignNext signNext = iSignNextService.getOne(new LambdaQueryWrapper<SignNext>().eq(StringUtils.isNotBlank(signNextDto.getIdcard()), SignNext::getUserId, signNextDto.getIdcard()).eq(SignNext::getSignDate, LocalDate.now().format(formatter)));
                if (signNext != null) {
                    signNext.setComeTime(signNextDto.getInTime());
                    signNexts.add(signNext);
                }
            });
            List<String> ids = new ArrayList<>();
            for (SignNext signNext : signNexts) {
                System.out.println("添加了工号" + signNext.getUserId());

                ids.add(signNext.getUserId());
                signNext.setComeAddress("青岛市黄岛区黄河东路75号雷沃重工集团有限公司");
                signNext.setComeAddressName("雷沃重工");
                signNext.setComeLongitude("120.190358");
                signNext.setComeLatitude("36.034634");
                Boolean flag = iSignNextService.autoSaveCarInfo(signNext);
                System.out.println(flag);
                if (flag) {
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
                    signNextAll.setSignType("0");
                    signNextAll.setRuleType(signNext.getSignType());
                    signNextAll.setSignWay(signNext.getSignWay());
                    iSignNextAllService.save(signNextAll);
                }
            }
            if (ids.size() > 0) {
                String jsonString = JSON.toJSONString(ids);
                String url_POST = "http://172.16.162.252:9091/carautoclockin/carinfo/updateCarInfo?userIds=" + jsonString;
                restTemplate.postForObject(url_POST, null, String.class);
            }
        }
    }


    //--------------------定时任务子方法--------------------


    /**
     * 根据对应的考勤规则，抽取信息，拼装考勤计划
     */
    private SignNext saveRuleInfo(String template, SignNextVO signNextVO) {
        SignNext signNext = new SignNext();
        String ruleId = findDayIndex(template);
        if ("0".equals(ruleId) || ruleId == "") {
            return null;
        }
        SignRuleResponse ruleResponse = iSignRuleService.getByRuleId(ruleId);
        signNextVO.setComeMustTime(ruleResponse.getWorkTime()); // 填充上班时间
        signNextVO.setLeaveMustTime(ruleResponse.getOffTime()); // 填充下班时间
        signNextVO.setSignType(ruleResponse.getRuleType()); // 填充规则类型（固定打卡/自由打卡）
        signNextVO.setRuleId(ruleId); // 填充规则id

        // 判断上下班时间确定是否跨天
        LocalTime workTime = LocalTime.parse(ruleResponse.getWorkTime());
        LocalTime offTime = LocalTime.parse(ruleResponse.getOffTime());
        if(workTime.isAfter(offTime)){
            signNextVO.setCrossDay("1"); // 跨天
        }else{
            signNextVO.setCrossDay("0"); // 不跨天
        }

        BeanUtils.copyProperties(signNextVO, signNext);
        return signNext;
    }

    /**
     * 根据今天是这个月的第几天，返回对应的考勤规则
     */
    private String findDayIndex(String template) {
        String[] split = template.split(",");
        Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // 获取当日是当月的第几天
        return split[dayOfMonth - 1];
    }

    /**
     * 根据昨天是这个月的第几天，返回对应的考勤规则
     */
    private String findYesDayIndex(String template) {
        String[] split = template.split(",");
        Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // 获取当日是当月的第几天
        int[] array = new int[calendar.getActualMaximum(Calendar.DAY_OF_MONTH)]; // 获取当月天数
        // 将当天下标赋值为1
        array[dayOfMonth - 1] = 1;
        return split[dayOfMonth - 2];
    }

    /**
     * 判断是否正常
     */
    public String calculateFlag(SignRuleResponse signRuleResponse, SignNext next) {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        DateFormat dateTimeFormat = new SimpleDateFormat("HH:mm:ss");
        // 上下班打卡时间不为空
        if (StringUtils.isNotBlank(next.getComeTime()) && StringUtils.isNotBlank(next.getLeaveTime())) {
            try {
                Date workStartTime = timeFormat.parse(signRuleResponse.getWorkTime()); // 上班时间
                Date come = dateTimeFormat.parse(next.getComeTime()); // 实际上班时间
                Date offTime = timeFormat.parse(signRuleResponse.getOffTime()); // 下班时间
                Date leave = dateTimeFormat.parse(next.getLeaveTime()); // 实际下班时间
                // 如果来的比上班时间早，并且走的时间比下班时间晚，并且下班时间在11点半之前
                if (come.before(workStartTime) && (leave.after(offTime) && leave.before(dateTimeFormat.parse("23:30:00")))) {
                    return "0"; // 打卡状态正常
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            return "1"; //否则异常
        } else {
            return "1";
        }
    }

    /**
     * 判断打卡状况
     * 0正常；1迟到；2早退；10全天缺卡；11上午缺卡；12下午缺卡；
     */
    public String signStatusFlag(SignRuleResponse signRuleResponse, SignNext next) {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        DateFormat dateTimeFormat = new SimpleDateFormat("HH:mm:ss");

        if (StringUtils.isNotBlank(next.getComeTime()) && StringUtils.isNotBlank(next.getLeaveTime())) {
            try {
                Date workStartTime = timeFormat.parse(signRuleResponse.getWorkTime()); // 上班时间
                Date come = dateTimeFormat.parse(next.getComeTime()); // 实际上班时间
                Date offTime = timeFormat.parse(signRuleResponse.getOffTime()); // 下班时间
                Date leave = dateTimeFormat.parse(next.getLeaveTime()); // 实际下班时间
                // 如果来的比上班时间早，并且走的时间比下班时间晚，并且下班时间在11点半之前
                if (come.before(workStartTime) && (leave.after(offTime) && leave.before(dateTimeFormat.parse("23:30:00")))) {
                    return "0"; // 打卡状态正常
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }

        if (!StringUtils.isNotBlank(next.getComeTime()) && StringUtils.isNotBlank(next.getLeaveTime())) {
            return "11"; //上午缺卡
        }

        if (StringUtils.isNotBlank(next.getComeTime())) {
            try {
                Date workStartTime = timeFormat.parse(signRuleResponse.getWorkTime()); // 上班时间
                Date come = dateTimeFormat.parse(next.getComeTime()); // 实际上班时间
                if (come.after(workStartTime)) {
                    return "1"; // 迟到
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }

        if (StringUtils.isNotBlank(next.getComeTime()) && !StringUtils.isNotBlank(next.getLeaveTime())) {
            return "12"; //下午缺卡
        }

        if (StringUtils.isNotBlank(next.getLeaveTime())) {
            try {
                Date offTime = timeFormat.parse(signRuleResponse.getOffTime()); // 下班时间
                Date leave = dateTimeFormat.parse(next.getLeaveTime()); // 实际下班时间
                if (leave.before(offTime)) {
                    return "2"; // 早退
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }

        if (!StringUtils.isNotBlank(next.getComeTime()) && !StringUtils.isNotBlank(next.getLeaveTime())) {
            return "10"; // 全天缺卡
        }
        return null;
    }

    /**
     * 统计固定打卡的有效时长，不跨天
     */
    private long countWorkTime(String id, String date, String ruleId) {
        SignRuleResponse rule = iSignRuleService.getByRuleId(ruleId);

        // 四个时间节点
        LocalTime workTime = LocalTime.parse(rule.getWorkTime());
        LocalTime amOffTime = null;
        if(StringUtils.isNotBlank(rule.getAmOffTime())){
            amOffTime = LocalTime.parse(rule.getAmOffTime());
        }
        LocalTime pmWorkTime = null;
        if(StringUtils.isNotBlank(rule.getPmWorkTime())){
            pmWorkTime = LocalTime.parse(rule.getPmWorkTime());
        }
        LocalTime offTime = LocalTime.parse(rule.getOffTime());

        // 查询昨天的打卡记录
        List<SignNextAllResponse> details = iSignNextAllService.getSignDetailByUserDay(id, date);
        if(details == null){
            return 0;
        }

        LocalTime start = null;
        LocalTime end = null;
        long time = 0;

        for (int i = 0; i < details.size(); i++) {
            if (details.get(i).getSignType().equals("0")) { // 打上班卡
                LocalTime signTime = LocalTime.parse(details.get(i).getSignTime());
                // 计算有效开始时间
                if (StringUtils.isNotBlank(rule.getAmOffTime()) && StringUtils.isNotBlank(rule.getPmWorkTime())) { // 有上午下班时间和下午上班时间
                    if (signTime.isBefore(workTime)) {
                        start = workTime;
                    } else if (signTime.isAfter(workTime) && signTime.isBefore(amOffTime)) {
                        start = signTime;
                    } else if (signTime.isAfter(amOffTime) && signTime.isBefore(pmWorkTime)) {
                        start = pmWorkTime;
                    } else if (signTime.isAfter(pmWorkTime) && signTime.isBefore(offTime)) {
                        start = signTime;
                    } else if (signTime.isAfter(offTime)) {
                        start = null;
                    }
                } else { // 只有上下班时间
                    if (signTime.isBefore(workTime)) {
                        start = workTime;
                    } else if (signTime.isAfter(workTime) && signTime.isBefore(offTime)) {
                        start = signTime;
                    } else if (signTime.isAfter(offTime)) {
                        start = null;
                    }
                }

                // 如果最后一次打卡，只打了上班卡，而没有打下班卡，那么跳过这次统计
                if((i+1) >= details.size()){
                    continue;
                }

                // 计算有效结束时间
                LocalTime signTime1 = LocalTime.parse(details.get(i + 1).getSignTime());
                if (StringUtils.isNotBlank(rule.getAmOffTime()) && StringUtils.isNotBlank(rule.getPmWorkTime())) {
                    if (signTime1.isBefore(workTime)) {
                        end = null;
                    } else if (signTime1.isAfter(workTime) && signTime1.isBefore(amOffTime)) {
                        end = signTime1;
                    } else if (signTime1.isAfter(amOffTime) && signTime1.isBefore(pmWorkTime)) {
                        end = amOffTime;
                    } else if (signTime1.isAfter(pmWorkTime) && signTime1.isBefore(offTime)) {
                        end = signTime1;
                    } else if (signTime1.isAfter(offTime)) {
                        end = offTime;
                    }
                } else {
                    if (signTime1.isBefore(workTime)) {
                        end = null;
                    } else if (signTime1.isAfter(workTime) && signTime1.isBefore(amOffTime)) {
                        end = signTime1;
                    } else if (signTime1.isAfter(offTime)) {
                        end = offTime;
                    }
                }

                // 叠加本次打卡有效工时
                if (start != null && end != null) {
                    Duration duration = Duration.between(start, end);
                    long second = duration.getSeconds();
                    time += second;
                }
            }
        }

        return time / 3600;
    }

    /**
     * 统计自由打卡的有效时长，不跨天
     */
    private long countFreeWorkTime(String id, String date, String ruleId) {
        List<SignNextAllResponse> details = iSignNextAllService.getSignDetailByUserDay(id, date);
        if(details == null){
            return 0;
        }

        LocalTime freeStart = LocalTime.of(12, 0, 0);
        LocalTime freeEnd = LocalTime.of(13, 30, 0);

        LocalTime start = null;
        LocalTime end = null;
        long time = 0;

        for (int i = 0; i < details.size(); i++) {
            if (details.get(i).getSignType().equals("0")) {
                LocalTime signTime = LocalTime.parse(details.get(i).getSignTime());
                // 计算有效开始时间
                if (signTime.isBefore(freeStart)) {
                    start = signTime;
                } else if (signTime.isAfter(freeStart) && signTime.isBefore(freeEnd)) {
                    start = freeEnd;
                } else if (signTime.isAfter(freeEnd)) {
                    start = signTime;
                }

                // 如果最后一次打卡，只打了上班卡，而没有打下班卡，那么跳过这次统计
                if((i+1) >= details.size()){
                    continue;
                }

                LocalTime signTime1 = LocalTime.parse(details.get(i + 1).getSignTime());
                // 计算有效结束时间
                if (signTime.isBefore(freeStart)) {
                    end = signTime1;
                } else if (signTime.isAfter(freeStart) && signTime.isBefore(freeEnd)) {
                    end = freeStart;
                } else if (signTime.isAfter(freeEnd)) {
                    end = signTime1;
                }

                // 叠加本次打卡有效工时
                if (start != null && end != null) {
                    Duration duration = Duration.between(start, end);
                    long second = duration.getSeconds();
                    time += second;
                }
            }
        }
        return time / 3600;
    }

    // 统计跨天的打卡时长，取昨天的最后一条打卡记录，和今天的第一条打卡记录，计算时长
    private long countWorkTimeCross(String id, String date, String ruleId) {
        // 昨晚的最后一条打卡记录的打卡时间到24点的时间
        List<SignNextAllResponse> details = iSignNextAllService.getSignDetailByUserDay(id, date);
        SignNextAllResponse lastRecord = details.get(details.size() - 1);
        LocalTime start = LocalTime.parse(lastRecord.getSignTime());
        LocalTime startEnd = LocalTime.of(23, 59, 59);
        Duration duration1 = Duration.between(start, startEnd);
        long second1 = duration1.getSeconds();

        // 0点到今天的第一天打卡记录的打卡时间
        LocalDate yesterday = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        LocalDate today = yesterday.plusDays(1);
        String todayString = today.format(DateTimeFormatter.ISO_DATE);
        List<SignNextAllResponse> todayDetails = iSignNextAllService.getSignDetailByUserDay(id, todayString);
        SignNextAllResponse firstRecord = todayDetails.get(0);
        LocalTime endStart = LocalTime.of(0, 0, 0);
        LocalTime end = LocalTime.parse(firstRecord.getSignTime());
        Duration duration2 = Duration.between(endStart, end);
        long second2 = duration2.getSeconds();

        return (second1+second2) / 3600;
    }
}
