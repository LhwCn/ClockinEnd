//package com.itlozg.admin.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.itlozg.admin.enums.StatusCode;
//import com.itlozg.admin.exception.ApplicationException;
//import com.itlozg.admin.model.request.ConfigRequest;
//import com.itlozg.admin.model.response.ConfigResponse;
//import com.itlozg.admin.service.IConfigService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
///**
// * @author ysc
// * @create 2017-12-16
// */
//@RestController
//@RequestMapping(value = "/sys/config")
//public class ConfigController extends BaseController {
//
//    @Autowired
//    private IConfigService iConfigService;
//
////    /**
////     * 查询单个
////     */
////    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
////    public ConfigResponse getById(@PathVariable("id") String id) {
////        return iConfigService.getById(id);
////    }
//
//
////    /**
////     * 新增
////     *
////     * @param request
////     * @return
////     */
////    @RequestMapping(value = "/add", method = RequestMethod.POST)
////    public ConfigResponse add(@Valid ConfigRequest request, BindingResult bindingResult) {
////        if (bindingResult.hasErrors()) {
////            throw new ApplicationException(StatusCode.BAD_REQUEST.getCode(), getValidateErrorMessage(bindingResult));
////        }
////        return iConfigService.save(request);
////    }
//
////    /**
////     * 更新
////     *
////     * @param request
////     * @return
////     */
////    @RequestMapping(value = "/update", method = RequestMethod.POST)
////    @ResponseBody
////    public ConfigResponse update(ConfigRequest request) {
////        return iConfigService.update(request);
////    }
//
//
////    /**
////     * 删除
////     *
////     * @param id
////     * @return
////     */
////    @RequestMapping(value = "/delete", method = RequestMethod.POST)
////    public int delete(@RequestParam("id") String id) {
////        return iConfigService.deleteData(id);
////    }
//
////    /**
////     * 查询分页
////     *
////     * @param request
////     * @return
////     */
////    @RequestMapping(value = "/page", method = RequestMethod.GET)
////    public DataGrid getPage(ConfigRequest request) {
////        Page<ConfigResponse> page = iConfigService.getPage(getPagination(request), request);
////        return super.buildDataGrid(page);
////    }
//
//    /**
//     * 获取上下班时间
//     */
//    @RequestMapping(value = "/getTime", method = RequestMethod.GET)
//    public JSONObject getTime() {
//        ConfigRequest configRequest = new ConfigRequest();
//        configRequest.setCategory(ConfigRequest.START_WORK_TIME_AM);
//        //获取上午上班时间
//        ConfigResponse start = iConfigService.getByType(configRequest);
//        configRequest.setCategory(ConfigRequest.END_WORK_TIME_AM);
//        //获取上午下班时间
//        ConfigResponse amEnd = iConfigService.getByType(configRequest);
//        configRequest.setCategory(ConfigRequest.START_WORK_TIME_PM);
//        //获取下午上班时间
//        ConfigResponse pmStart = iConfigService.getByType(configRequest);
//        configRequest.setCategory(ConfigRequest.END_WORK_TIME_PM);
//        //获取下午下班时间
//        ConfigResponse end = iConfigService.getByType(configRequest);
//
//        JSONObject object = new JSONObject();
//        object.put("start", start.getContent());
//        object.put("amEnd", amEnd.getContent());
//        object.put("pmStart", pmStart.getContent());
//        object.put("end", end.getContent());
//        return object;
//    }
//}
