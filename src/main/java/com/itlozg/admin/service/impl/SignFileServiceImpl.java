package com.itlozg.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itlozg.admin.entity.SignFile;
import com.itlozg.admin.entity.dto.GetSignInfoToPageDto;
import com.itlozg.admin.mapper.SignFileMapper;
import com.itlozg.admin.model.response.SignFileResponse;
import com.itlozg.admin.service.ISignFileService;
import com.itlozg.admin.util.BeanCopier;
import com.itlozg.admin.util.CommonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class SignFileServiceImpl extends BaseServiceImpl<SignFileMapper, SignFile> implements ISignFileService {

    /**
     * 补卡申请
     * @param userId 用户工号
     * @param date yyyy-mm-dd
     * @return
     */
    @Override
    public int applyRepairSign(String userId, String date, String type) throws ParseException {
        System.out.println("--------------------------------------");
        SignFile file = baseMapper.getSignInfoByUserDay(date, userId);
        System.out.println("file------------"+file);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(file == null){
            System.out.println("jjjjjjjjjjjjjjjjjjjjj");
            SignFile signFile = new SignFile();
            signFile.setUserId(userId);
            signFile.setSignDate(sdf.parse(date));
            signFile.setStatus("1");
            signFile.setComeMustTime("8:00");
            signFile.setLeaveMustTime("17:30");
            signFile.setSignType("0");
            signFile.setIsMust("0");
            signFile.setIsLeave("1");
            signFile.setRuleId("1");
            signFile.setDelFlag(0);
            signFile.setSignWay("0");
            signFile.setSignStatus("1");
            signFile.setSituation("6");
            signFile.setNeedCheck("0");
            signFile.setCrossDay("0");
            baseMapper.insert(signFile);
        }

        int row = 0;
        System.out.println("////////////////////"+type);
        if(type.equals("abnormal")){
            System.out.println("11111111111111111111");
            row = baseMapper.applyRepairSign(userId, date);
            System.out.println("222222222222222222222222");
        }else if(type.equals("nothing")){
            row = baseMapper.applyLeaveSign(userId, date);
        }

        return row;
    }

    /**
     * 获取用户月度打卡统计信息
     * @param userId 用户工号
     * @param month yyyy-mm
     * @return
     */
    @Override
    public List<SignFileResponse> getSignInfoByUserMonth(String userId, String month) {
        int monthVal = Integer.parseInt(month.substring(5));
        int yearVal = Integer.parseInt(month.substring(0, 4));
        String dateVal = getMonth(month); // yyyy-mm
        List<SignFile> list = baseMapper.getSignInfoByUserMonth(userId, dateVal);
        Calendar c = Calendar.getInstance();
        c.set(yearVal, monthVal, 0);
        int days = c.get(Calendar.DAY_OF_MONTH); // 获取当月一共有多少天
        List<SignFileResponse> signlist = new ArrayList<>();
        for (int a = 0; a < days; a++) {
            String day = "";
            int b = a + 1;
            if (b < 10) {
                day = "0" + b;
            } else {
                day = "" + b;
            }
            SignFileResponse signfile = new SignFileResponse();
            signfile.setSignMonth(month + '-' + day);
            for (SignFile sf : list) {
                String date = sf.getSignMonth().substring(8);
                if (day.equals(date)) {
                    signfile.setStatus(sf.getStatus());
                    //设置打卡情况 0正常 1异常
                    signfile.setSituation(sf.getSituation());
                    //设置是否必须打卡:0是1否
                    signfile.setIsMust(sf.getIsMust());
                    //设置备注
                    signfile.setComeRemarks("over"); //标识 在归档数据中存在
                    //设置是否请假 0正常 1请假 2出差 3外勤 4调休 ??? 不确定是否正确
                    signfile.setIsLeave(sf.getIsLeave());
                    if (StringUtils.isNotEmpty(signfile.getIsLeave())) {
                        if (signfile.getIsLeave().equals("4")) {
                            //如果是调休，那么也是‘正常’的状态
                            signfile.setIsLeave("0");
                            //打卡情况标识设置正常
                            signfile.setSituation("0");
                        } else if (signfile.getIsLeave().equals("5")) {
                            //暂时未知状态5对应什么，但可以看出，这也是个被认为正常的状态
                            signfile.setIsLeave("0");
                            signfile.setSituation("0");
                        }
                    }
                    //设置打卡状态
                    signfile.setSignStatus(sf.getSignStatus());
                    break;
                }
            }
            //2020-04-07 如果数据不存在 默认非工作日  默认正常 isMust=1
            //打卡信息中，上班备注存在且值为“over”
            if (StringUtils.isNotEmpty(signfile.getComeRemarks()) && signfile.getComeRemarks().equals("over")) {

            } else {
                signfile.setIsMust("1");
                signfile.setSituation("0");
            }
            signlist.add(signfile);
        }
        return signlist;
    }

    /**
     * 获取用户打卡信息
     *
     * @param userId
     * @param dayVal
     * @return
     */
    @Override
    public SignFileResponse getSignInfoByUserDay(String userId, String dayVal) {
        SignFile result = baseMapper.getSignInfoByUserDay(dayVal, userId);
        if (result != null) {
            return BeanCopier.copy(result, SignFileResponse.class);
        } else {
            return null;
        }
    }

    public SignFileResponse getSignFileByUserDay(String userId, String dayVal) {
        SignFile result = baseMapper.getSignFileByUserDay(dayVal, userId);
        System.out.println("////////////-----------"+result);
        if (result != null) {
            return BeanCopier.copy(result, SignFileResponse.class);
        } else {
            return null;
        }
    }

    @Override
    public CommonResult getSignInfoToPage(GetSignInfoToPageDto getSignInfoToPageDto) {
        Page<SignFile> signFilePage = new Page<>();
        if(StringUtils.isNotEmpty(getSignInfoToPageDto.getPageNum()) && StringUtils.isNotEmpty(getSignInfoToPageDto.getPageSize())){
            int pageNum = Integer.valueOf(getSignInfoToPageDto.getPageNum());
            int pageSize = Integer.valueOf(getSignInfoToPageDto.getPageSize());
            signFilePage.setCurrent((pageNum-1)*pageSize);
            signFilePage.setSize(pageSize);
        }
        String username = null;
        if(StringUtils.isNotBlank(getSignInfoToPageDto.getUsername())){
            username = getSignInfoToPageDto.getUsername();
        }
        String deptname = null;
        if(StringUtils.isNotBlank(getSignInfoToPageDto.getDeptname())){
            deptname = getSignInfoToPageDto.getDeptname();
        }
        String startTime = null;
        if(StringUtils.isNotBlank(getSignInfoToPageDto.getStartTime())){
            startTime = getSignInfoToPageDto.getStartTime();
//            System.out.println(startTime+"===============");
        }
        String endTime = null;
        if(StringUtils.isNotBlank(getSignInfoToPageDto.getEndTime())){
            endTime = getSignInfoToPageDto.getEndTime();
//            System.out.println(endTime+"===============");
        }
        Page<SignFile> resultPage = baseMapper.getSignInfoToPage(signFilePage,username,deptname,startTime,endTime);
//        System.out.println(resultPage.getTotal());
//        System.out.println(resultPage.getPages());
        System.out.println(resultPage.getRecords());
        System.out.println(resultPage.getTotal());
        return CommonResult.success(resultPage);
    }

    public String getMonth(String month){
        int yearVal = Integer.parseInt(month.substring(0, 4));
        int monthVal = Integer.parseInt(month.substring(5));
        //定义一个字符串接传进来的月份
        String dateVal = month;
        //如果月份小于10拼接一个“-0”
        if (monthVal < 10) {
            dateVal = yearVal + "-0" + monthVal;
        } else {
            dateVal = yearVal + "-" + monthVal;
        }
        return dateVal;
    }

}
