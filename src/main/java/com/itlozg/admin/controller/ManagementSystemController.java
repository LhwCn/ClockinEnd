package com.itlozg.admin.controller;

import com.itlozg.admin.entity.SignFile;
import com.itlozg.admin.entity.dto.GetSignInfoToPageDto;
import com.itlozg.admin.entity.dto.SelectRulePageDto;
import com.itlozg.admin.service.ISignFileService;
import com.itlozg.admin.service.ISignRuleService;
import com.itlozg.admin.service.UserInfoService;
import com.itlozg.admin.service.impl.UserInfoServiceImpl;
import com.itlozg.admin.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@ResponseBody
//@RequestMapping("/api/user")
@RequestMapping("/bosys")
public class ManagementSystemController {

    @Autowired
    private ISignFileService iSignFileService;

    @Autowired
    private ISignRuleService iSignRuleService;

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/getSignInfo")
    public CommonResult getSignInfoByMonth(@RequestBody GetSignInfoToPageDto getSignInfoToPageDto) {
        //获取某个月份的打卡记录
        CommonResult result = iSignFileService.getSignInfoToPage(getSignInfoToPageDto);
        return result;
    }

    @PostMapping("/selectRuleList")
    public CommonResult selectRuleList(@RequestBody SelectRulePageDto selectRulePageDto) {
        //获取某个月份的打卡记录
        CommonResult result = iSignRuleService.selectRuleList(selectRulePageDto);
        return result;
    }

    @GetMapping("/getEmployeeInfo")
    public CommonResult getEmployeeInfo(){
        return userInfoService.getList();
    }
}
