package com.itlozg.admin.service;

import com.itlozg.admin.entity.SignFile;
import com.itlozg.admin.entity.dto.GetSignInfoToPageDto;
import com.itlozg.admin.model.response.SignFileResponse;
import com.itlozg.admin.util.CommonResult;

import java.text.ParseException;
import java.util.List;

public interface ISignFileService extends IBaseService<SignFile> {


    /**
     * 获取用户月度打卡统计信息
     * @param userId 工号
     * @param month yyyy-mm
     * @return
     */
    public List<SignFileResponse> getSignInfoByUserMonth(String userId, String month);

    /**
     * 补卡申请
     * @param userId 工号
     * @param date yyyy-mm-dd
     * @return
     */
    public int applyRepairSign(String userId, String date, String type) throws ParseException;

    /**
     * 获取用户打卡信息
     *
     * @param userId
     * @param dayVal
     * @return
     */
    public SignFileResponse getSignInfoByUserDay(String userId, String dayVal);
    public SignFileResponse getSignFileByUserDay(String userId, String dayVal);

    CommonResult getSignInfoToPage(GetSignInfoToPageDto getSignInfoToPageDto);
}
