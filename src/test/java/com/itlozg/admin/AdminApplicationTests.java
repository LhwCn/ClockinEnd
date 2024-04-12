//package com.itlozg.admin;
//
//import com.itlozg.admin.entity.*;
//import com.itlozg.admin.entity.vo.SignNextVO;
//import com.itlozg.admin.model.response.SignNextAllResponse;
//import com.itlozg.admin.model.response.SignRuleResponse;
//import com.itlozg.admin.service.*;
//import com.itlozg.admin.util.HttpClient;
//import com.itlozg.admin.util.StringUtils;
//import lombok.extern.slf4j.Slf4j;
//import net.sf.json.JSONArray;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.Duration;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.Period;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//import java.util.stream.Collectors;
//
//import net.sf.json.JSONObject;
//import weaver.rsa.security.RSA;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Slf4j
//@SpringBootTest
//class AdminApplicationTests {
//    @Autowired
//    private ISignNextService iSignNextService;
//
//    @Autowired
//    private ISignFileService iSignFileService;
//
//    @Autowired
//    private ISignRuleService iSignRuleService;
//
//    @Autowired
//    private ISignGroupService iSignGroupService;
//
//    @Autowired
//    private ISignNextAllService iSignNextAllService;
//
//    @Autowired
//    private ISignLegworkService iSignLegworkService;
//
//    @Autowired
//    private SignTemplateService signTemplateService;
//
//    @Test
//    void contextLoads() {
//        iSignNextService.autoSave();
//    }
//
//    //---------------     OA     ---------------
//
//    /**
//     * 注册接口
//     */
//    public static JSONObject getRegist() {
//        Map<String, String> heads = new HashMap<String, String>();
//        String cpk = new RSA().getRSA_PUB();
//        heads.put("appid", "5583bc0e-220e-4a44-8e14-d838d47ad9b6");
//        heads.put("cpk", cpk);
//        String data = HttpClient.httpPostForm("http://10.3.61.223:8080/api/ec/dev/auth/regist", null, heads, "utf-8");
//        JSONObject registerObject = JSONObject.fromObject(data);
//        return registerObject;
//    }
//
//    /**
//     * 获取token接口
//     */
//    public static String applytoken(String secrit, String spk) {
//        Map<String, String> heads = new HashMap<String, String>();
//        RSA rsa = new RSA();
//        String secret_2 = rsa.encrypt(null, secrit, null, "utf-8", spk, false);
//        heads.put("appid", "5583bc0e-220e-4a44-8e14-d838d47ad9b6");
//        heads.put("secret", secret_2);
//        String data = HttpClient.httpPostForm("http://10.3.61.223:8080/api/ec/dev/auth/applytoken", null, heads, "utf-8");
//        JSONObject objectToken = JSONObject.fromObject(data);
//        String token = objectToken.getString("token");
//        return token;
//    }
//
//    /**
//     * 获取人员ID接口
//     */
//    public static String getUserId(String loginId, String token, String spk) {
//        Map paramsMap = new HashMap<>();
//        Map tempMap = new HashMap<>();
//        tempMap.put("loginid", loginId);
//        JSONObject tempObject = JSONObject.fromObject(tempMap);
//        paramsMap.put("params", tempObject.toString());
//        Map<String, String> heads = new HashMap<String, String>();
//        RSA rsa = new RSA();
//        String userid = rsa.encrypt(null, "1", null, "utf8", spk, false);
//        heads.put("token", token);
//        heads.put("appid", "5583bc0e-220e-4a44-8e14-d838d47ad9b6");
//        heads.put("userid", userid);
//        String userBack = HttpClient.httpPostForm("http://10.3.61.223:8080/api/hrm/resful/getHrmUserInfoWithPage", paramsMap, heads,"utf-8");
//        JSONObject userObject = JSONObject.fromObject(userBack);
//        String id = userObject.getJSONObject("data").getJSONArray("dataList").getJSONObject(0).getString("id");
//        return id;
//    }
//
//    /**
//     * 将文件进行base64加密
//     */
//    public static String encryptToBase64(String filePath) {
//        if (filePath == null) {
//            return null;
//        }
//        try {
//            byte[] b = Files.readAllBytes(Paths.get(filePath));
//            return Base64.getEncoder().encodeToString(b);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Test
//    public void askForLeave() {
//        // 注册接口
//        JSONObject registerObject = getRegist();
//        String secrit = registerObject.getString("secrit");
//        String spk = registerObject.getString("spk");
//
//        // 获取token
//        String token = applytoken(secrit, spk);
//
//        // 创建流程
//        Map<String, String> createheads = new HashMap<String, String>();
//        String id = getUserId("zhangxiaogang-l", token, spk); // 获取人员ID
//        String createUserid = new RSA().encrypt(null, id, null, "utf8", spk, false);
//        createheads.put("token", token);
//        createheads.put("appid", "5583bc0e-220e-4a44-8e14-d838d47ad9b6");
//        createheads.put("userid", createUserid);
//        String url = "http://10.3.61.223:8080/api/workflow/paService/doCreateRequest";
//        Map inMap = new HashMap();
//
//        //主表参数
//        List list = new ArrayList();
//        Map fieldmap = new HashMap();
//        fieldmap.put("fieldName", "sqr");
//        String sqr = getUserId("zhangxiaogang-l", token, spk); // 获取人员ID
//        fieldmap.put("fieldValue", sqr);
//        list.add(fieldmap);
//
//        Map fieldmap2 = new HashMap();
//        fieldmap2.put("fieldName", "sqrq");
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String formattedDate = sdf.format(date);
//        fieldmap2.put("fieldValue", formattedDate);
//        list.add(fieldmap2);
//
//        Map fieldmap3 = new HashMap();
//        fieldmap3.put("fieldName", "sqbm");
//        fieldmap3.put("fieldValue", "8449");
//        list.add(fieldmap3);
//
//        Map fieldmap4 = new HashMap();
//        fieldmap4.put("fieldName", "lxdh");
//        fieldmap4.put("fieldValue", "15588620709");
//        list.add(fieldmap4);
//
//        Map fieldmap5 = new HashMap();
//        fieldmap5.put("fieldName", "qjry");
//        String qjry = getUserId("yuanxiaobo", token, spk); // 获取人员ID
//        fieldmap5.put("fieldValue", qjry);
//        list.add(fieldmap5);
//
//        Map fieldmap6 = new HashMap();
//        fieldmap6.put("fieldName", "qjrygh");
//        fieldmap6.put("fieldValue", "6207359");
//        list.add(fieldmap6);
//
//        Map fieldmap7 = new HashMap();
//        fieldmap7.put("fieldName", "qjryszbm");
//        fieldmap7.put("fieldValue", "8449");
//        list.add(fieldmap7);
//
//        Map fieldmap8 = new HashMap();
//        fieldmap8.put("fieldName", "rylb");
//        fieldmap8.put("fieldValue", "1");
//        list.add(fieldmap8);
//
//        Map fieldmap9 = new HashMap();
//        fieldmap9.put("fieldName", "qjrygw");
//        fieldmap9.put("fieldValue", "系统开发岗");
//        list.add(fieldmap9);
//
//        Map fieldmap10 = new HashMap();
//        fieldmap10.put("fieldName", "qjrylxfs");
//        fieldmap10.put("fieldValue", "15588620709");
//        list.add(fieldmap10);
//
//        Map fieldmap11 = new HashMap();
//        fieldmap11.put("fieldName", "qjryzj");
//        fieldmap11.put("fieldValue", "科级");
//        list.add(fieldmap11);
//
//        Map fieldmap12 = new HashMap();
//        fieldmap12.put("fieldName", "qjksrq");
//        fieldmap12.put("fieldValue", "2023-11-15");
//        list.add(fieldmap12);
//
//        Map fieldmap13 = new HashMap();
//        fieldmap13.put("fieldName", "qjkssj");
//        fieldmap13.put("fieldValue", "08:00");
//        list.add(fieldmap13);
//
//        Map fieldmap14 = new HashMap();
//        fieldmap14.put("fieldName", "qjjsrq");
//        fieldmap14.put("fieldValue", "2023-11-15");
//        list.add(fieldmap14);
//
//        Map fieldmap15 = new HashMap();
//        fieldmap15.put("fieldName", "qjjssj");
//        fieldmap15.put("fieldValue", "17:30");
//        list.add(fieldmap15);
//
//        Map fieldmap16 = new HashMap();
//        fieldmap16.put("fieldName", "qjts");
//        fieldmap16.put("fieldValue", "1");
//        list.add(fieldmap16);
//
//        Map fieldmap17 = new HashMap();
//        fieldmap17.put("fieldName", "qjsy");
//        fieldmap17.put("fieldValue", "有事情");
//        list.add(fieldmap17);
//
//        //附件
////        Map fj = new HashMap();
////        fj.put("fieldName", "scfj");
////        List fjList = new ArrayList();
////        Map fjmx = new HashMap();
////        fjmx.put("filePath", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577426926378&di=0084fc19f5cb90fc2286aab5ca8c343e&imgtype=0&src=http%3A%2F%2Fpic.51yuansu.com%2Fpic2%2Fcover%2F00%2F41%2F80%2F581369c715701_610.jpg");
////        fjmx.put("fileName", "图片.jpg");
////        fjList.add(fjmx);
////        Map fjmx2 = new HashMap();
////        //  FileUtil fu = new FileUtil();
////        //  System.out.println(fu.encryptToBase64("G://测试上传.txt"));
////        //一定要以base64 开头 ，还需要关注下产品那边
////        fjmx2.put("filePath", "base64:" + encryptToBase64("D://1.txt"));
////        fjmx2.put("fileName", "测试上传.txt");
////        fjList.add(fjmx2);
////        fj.put("fieldValue", fjList);
////        list.add(fj);
//
//        Map fieldmap18 = new HashMap();
//        fieldmap18.put("fieldName", "bz");
//        fieldmap18.put("fieldValue", "备注");
//        list.add(fieldmap18);
//
//        Map fieldmap19 = new HashMap();
//        fieldmap19.put("fieldName", "bh");
//        fieldmap19.put("fieldValue", "LOVOL/雷沃重工/YGQJLC");
//        list.add(fieldmap19);
//
//        Map fieldmap20 = new HashMap();
//        fieldmap20.put("fieldName", "bbmkqgly");
//        String bbmkqgly = getUserId("zhangxiaogang-l", token, spk); // 获取人员ID
//
//        fieldmap20.put("fieldValue", bbmkqgly);
//        list.add(fieldmap20);
//
//        Map fieldmap21 = new HashMap();
//        fieldmap21.put("fieldName", "qjlx");
//        fieldmap21.put("fieldValue", "0");
//        list.add(fieldmap21);
//
//        Map fieldmap22 = new HashMap();
//        fieldmap22.put("fieldName", "sh");
//        fieldmap22.put("fieldValue", "审核");
//        list.add(fieldmap22);
//
//        Map fieldmap23 = new HashMap();
//        fieldmap23.put("fieldName", "ywxgldhq");
//        fieldmap23.put("fieldValue", "业务相关领导会签");
//        list.add(fieldmap23);
//
//        Map fieldmap24 = new HashMap();
//        fieldmap24.put("fieldName", "jrqysj");
//        fieldmap24.put("fieldValue", "2023-07-05");
//        list.add(fieldmap24);
//
//        Map fieldmap25 = new HashMap();
//        fieldmap25.put("fieldName", "tqdx");
//        fieldmap25.put("fieldValue", "父母");
//        list.add(fieldmap25);
//
//        Map fieldmap26 = new HashMap();
//        fieldmap26.put("fieldName", "cxfs");
//        fieldmap26.put("fieldValue", "自驾");
//        list.add(fieldmap26);
//
//        Map fieldmap27 = new HashMap();
//        fieldmap27.put("fieldName", "cfdssqjdxq");
//        fieldmap27.put("fieldValue", "家");
//        list.add(fieldmap27);
//
//        Map fieldmap28 = new HashMap();
//        fieldmap28.put("fieldName", "mddssqjdxq");
//        fieldmap28.put("fieldValue", "家");
//        list.add(fieldmap28);
//
//        Map fieldmap29 = new HashMap();
//        fieldmap29.put("fieldName", "qjlx1");
//        fieldmap29.put("fieldValue", "事假");
//        list.add(fieldmap29);
//
//        JSONArray arr = JSONArray.fromObject(list);
//        inMap.put("mainData", arr.toString());
//        inMap.put("requestName", "测试请假流程" + System.currentTimeMillis());
//        inMap.put("workflowId", "6159959");
//        Map otherParams = new HashMap();
//        otherParams.put("isnextflow ", "1");
//        otherParams.put("delReqFlowFaild ", "1");
//        JSONObject otherObject = JSONObject.fromObject(otherParams);
//        inMap.put("otherParams", otherObject.toString());
//
//        JSONObject jsonObject = JSONObject.fromObject(inMap);
//        System.out.println("请求参数-->" + jsonObject.toString());
//        String back2 = HttpClient.httpPostForm(url, inMap, createheads, "utf-8");
//        System.out.println("返回值-->" + back2);
//    }
//
//    @Test
//    public void testAskForBuka() {
//        // 注册接口
//        JSONObject registerObject = getRegist();
//        String secrit = registerObject.getString("secrit");
//        String spk = registerObject.getString("spk");
//
//        // 获取token
//        String token = applytoken(secrit, spk);
//
//        // 创建流程
//        Map<String, String> createheads = new HashMap<String, String>();
//        String id = getUserId("zhangxiaogang-l", token, spk); // 获取人员ID
//        String createUserid = new RSA().encrypt(null, id, null, "utf8", spk, false);
//        createheads.put("token", token);
//        createheads.put("appid", "5583bc0e-220e-4a44-8e14-d838d47ad9b6");
//        createheads.put("userid", createUserid);
//        String url = "http://10.3.61.223:8080/api/workflow/paService/doCreateRequest";
//        Map inMap = new HashMap();
//
//        // 主表参数
//        List list = new ArrayList();
//        Map fieldmap1 = new HashMap();
//        fieldmap1.put("fieldName", "bt");
//        fieldmap1.put("fieldValue", "LOVOL/雷沃重工/YGBKLC");
//        list.add(fieldmap1);
//
//        // 流程编号 lcbh
//
//        Map fieldmap3 = new HashMap();
//        fieldmap3.put("fieldName", "sqrxm");
//        String sqr = getUserId("zhangxiaogang-l", token, spk); // 获取人员ID
//        fieldmap3.put("fieldValue", sqr);
//        list.add(fieldmap3);
//
//        Map fieldmap4 = new HashMap();
//        fieldmap4.put("fieldName", "ssbm");
//        fieldmap4.put("fieldValue", "8449");
//        list.add(fieldmap4);
//
//        Map fieldmap5 = new HashMap();
//        fieldmap5.put("fieldName", "sqrq");
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String formattedDate = sdf.format(date);
//        fieldmap5.put("fieldValue", formattedDate);
//        list.add(fieldmap5);
//
//        Map fieldmap6 = new HashMap();
//        fieldmap6.put("fieldName", "ksrq");
//        fieldmap6.put("fieldValue", "2023-11-15");
//        list.add(fieldmap6);
//
//        Map fieldmap7 = new HashMap();
//        fieldmap7.put("fieldName", "jsrq");
//        fieldmap7.put("fieldValue", "2023-11-15");
//        list.add(fieldmap7);
//
//        Map fieldmap8 = new HashMap();
//        fieldmap8.put("fieldName", "sqbkyy");
//        fieldmap8.put("fieldValue", "调休补卡");
//        list.add(fieldmap8);
//
//        Map fieldmap9 = new HashMap();
//        fieldmap9.put("fieldName", "bz");
//        fieldmap9.put("fieldValue", "补卡备注");
//        list.add(fieldmap9);
//
//        // 附件 fj
//
//        Map fieldmap11 = new HashMap();
//        fieldmap11.put("fieldName", "kqgly");
//        String kqgly = getUserId("yuanxiaobo", token, spk); // 获取人员ID
//        fieldmap11.put("fieldValue", kqgly);
//        list.add(fieldmap11);
//
//        Map fieldmap12 = new HashMap();
//        fieldmap12.put("fieldName", "lxfs");
//        fieldmap12.put("fieldValue", "15588620709");
//        list.add(fieldmap12);
//
//        Map fieldmap13 = new HashMap();
//        fieldmap13.put("fieldName", "zgld");
//        String zgld = getUserId("jiaoxiuqing", token, spk); // 获取人员ID
//        fieldmap13.put("fieldValue", zgld);
//        list.add(fieldmap13);
//
//        // 补卡明细...
//
//        JSONArray arr = JSONArray.fromObject(list);
//        inMap.put("mainData", arr.toString());
//        inMap.put("requestName", "测试补卡流程" + System.currentTimeMillis());
//        inMap.put("workflowId", "6159961");
//        Map otherParams = new HashMap();
//        otherParams.put("isnextflow ", "1");
//        otherParams.put("delReqFlowFaild ", "1");
//        JSONObject otherObject = JSONObject.fromObject(otherParams);
//        inMap.put("otherParams", otherObject.toString());
//
//        JSONObject jsonObject = JSONObject.fromObject(inMap);
//        System.out.println("请求参数-->" + jsonObject.toString());
//        String back2 = HttpClient.httpPostForm(url, inMap, createheads, "utf-8");
//        System.out.println("返回值-->" + back2);
//    }
//
//    public void AskForBuka(String applyUser) {
//        // 注册接口
//        JSONObject registerObject = getRegist();
//        String secrit = registerObject.getString("secrit");
//        String spk = registerObject.getString("spk");
//
//        // 获取token
//        String token = applytoken(secrit, spk);
//
//        // 创建流程
//        Map<String, String> createheads = new HashMap<String, String>();
//        String id = getUserId(applyUser, token, spk); // 获取人员ID
//        String createUserid = new RSA().encrypt(null, id, null, "utf8", spk, false);
//        createheads.put("token", token);
//        createheads.put("appid", "5583bc0e-220e-4a44-8e14-d838d47ad9b6");
//        createheads.put("userid", createUserid);
//        String url = "http://10.3.61.223:8080/api/workflow/paService/doCreateRequest";
//        Map inMap = new HashMap();
//
//        // 主表参数
//        List list = new ArrayList();
//        Map fieldmap1 = new HashMap();
//        fieldmap1.put("fieldName", "bt");
//        fieldmap1.put("fieldValue", "LOVOL/雷沃重工/YGBKLC");
//        list.add(fieldmap1);
//
//        // 流程编号 lcbh ...
//
//        Map fieldmap3 = new HashMap();
//        fieldmap3.put("fieldName", "sqrxm");
//        String sqr = getUserId(applyUser, token, spk); // 获取人员ID
//        fieldmap3.put("fieldValue", sqr);
//        list.add(fieldmap3);
//
//        Map fieldmap4 = new HashMap();
//        fieldmap4.put("fieldName", "ssbm");
//        fieldmap4.put("fieldValue", "8449");
//        list.add(fieldmap4);
//
//        Map fieldmap5 = new HashMap();
//        fieldmap5.put("fieldName", "sqrq");
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String formattedDate = sdf.format(date);
//        fieldmap5.put("fieldValue", formattedDate);
//        list.add(fieldmap5);
//
//        Map fieldmap6 = new HashMap();
//        fieldmap6.put("fieldName", "ksrq");
//        fieldmap6.put("fieldValue", "2023-11-15");
//        list.add(fieldmap6);
//
//        Map fieldmap7 = new HashMap();
//        fieldmap7.put("fieldName", "jsrq");
//        fieldmap7.put("fieldValue", "2023-11-15");
//        list.add(fieldmap7);
//
//        Map fieldmap8 = new HashMap();
//        fieldmap8.put("fieldName", "sqbkyy");
//        fieldmap8.put("fieldValue", "调休补卡");
//        list.add(fieldmap8);
//
//        Map fieldmap9 = new HashMap();
//        fieldmap9.put("fieldName", "bz");
//        fieldmap9.put("fieldValue", "补卡备注");
//        list.add(fieldmap9);
//
//        // 附件 fj ...
//
//        Map fieldmap11 = new HashMap();
//        fieldmap11.put("fieldName", "kqgly");
//        String kqgly = getUserId("yuanxiaobo", token, spk); // 获取人员ID
//        fieldmap11.put("fieldValue", kqgly);
//        list.add(fieldmap11);
//
//        Map fieldmap12 = new HashMap();
//        fieldmap12.put("fieldName", "lxfs");
//        fieldmap12.put("fieldValue", "15588620709");
//        list.add(fieldmap12);
//
//        Map fieldmap13 = new HashMap();
//        fieldmap13.put("fieldName", "zgld");
//        String zgld = getUserId("jiaoxiuqing", token, spk); // 获取人员ID
//        fieldmap13.put("fieldValue", zgld);
//        list.add(fieldmap13);
//
//        // 补卡明细...
//
//        JSONArray arr = JSONArray.fromObject(list);
//        inMap.put("mainData", arr.toString());
//        inMap.put("requestName", "测试补卡流程" + System.currentTimeMillis());
//        inMap.put("workflowId", "6159961");
//        Map otherParams = new HashMap();
//        otherParams.put("isnextflow ", "1");
//        otherParams.put("delReqFlowFaild ", "1");
//        JSONObject otherObject = JSONObject.fromObject(otherParams);
//        inMap.put("otherParams", otherObject.toString());
//
//        JSONObject jsonObject = JSONObject.fromObject(inMap);
//        System.out.println("请求参数-->" + jsonObject.toString());
//        String back2 = HttpClient.httpPostForm(url, inMap, createheads, "utf-8");
//        System.out.println("返回值-->" + back2);
//    }
//
//    /**
//     * OA系统推送请假消息到沃考勤系统
//     */
//    @Test
//    public void sendLeaveMsg() {
//        Map paramsMap = new HashMap<>();
//        paramsMap.put("userId", "6207359"); // 人员工号
//        paramsMap.put("startDate", "2025-01-01"); // 请假开始日期
//        paramsMap.put("startTime", "13:30"); // 请假开始时间
//        paramsMap.put("endDate", "2025-01-01"); // 请假结束日期
//        paramsMap.put("endTime", "12:00"); // 请假结束时间
//        paramsMap.put("leaveType", "1"); // 请假类型
//        paramsMap.put("leaveTime", "1"); // 请假时长（天）
//        JSONObject paramsObject = JSONObject.fromObject(paramsMap);
//
//        Map<String, String> heads = new HashMap<String, String>();
//        heads.put("Content-Type", "application/json");
//
//        String url = "http://10.203.114.20:8080/jeecg-boot/clockin/OA/leave";
//        String back = HttpClient.httpPostRaw(url, paramsObject.toString(), heads,"utf-8");
//        System.out.println("-->" + back);
//    }
//
//    //---------------     OA     ---------------
//
//
//
//
//    //---------------     打卡     ---------------
//
//    @Test
//    public void executeTask() {
//        // 每个考勤组分别统计
//        List<SignGroup> list = iSignGroupService.getList();
//        for (SignGroup signGroup : list) {
//            // 如果当前考勤组昨天没有打卡计划，跳过统计
//            List<SignNextVO> allLastday = iSignNextService.getSignInfoAllLastday(signGroup.getId());
//            if (allLastday == null) continue;
//            // 筛选出不是跨天的打卡计划
//            List<SignNextVO> dealList = allLastday.stream()
//                    .filter(SignNextVO -> SignNextVO.getCrossDay().equals("0"))
//                    .collect(Collectors.toList());
//            if (dealList == null) return;
//
//            List<SignFile> fileList = dealList.stream().map(signNextVO -> {
//                // 根据考勤计划查询考勤规则
//                String ruleId = "1";
//                if (signNextVO.getSignType().equals("1")) {
//                    ruleId = "5"; // 自由打卡设置默认规则
//                } else if (signNextVO.getTemplateUser() != null) {
//                    String template = signTemplateService.getSpecialTemplate(signNextVO.getUserId(), signNextVO.getGroupId());
//                    ruleId = findYesDayIndex(template);
//                } else if (signNextVO.getTemplateGroup() != null && signNextVO.getTemplateUser() == null) {
//                    String template = signTemplateService.getNormalTemplate(signNextVO.getTemplateGroup());
//                    ruleId = findYesDayIndex(template);
//                }
//                SignRuleResponse rule = iSignRuleService.getByRuleId(ruleId);
//
//                // 填充打卡计划中的信息
//                SignFile signFile = new SignFile();
//                BeanUtils.copyProperties(signNextVO, signFile);
//                signFile.setWorkHours(rule.getWorkHours());
//                signFile.setStatus("1"); // 暂未启用，不知含义？
//
//                // 外勤计划会提前设置signFlag，而非外勤计划的signFlag就是空
//                if (signNextVO.getSignFlag() == null) {
//                    signFile.setIsLeave("0"); // is_leave 0 正常；1 请假；2 出差；3 外勤；4 调休；
//                } else {
//                    signFile.setIsLeave("2"); // 外勤计划会提前设置signFlag为3，这里暂时把外勤定义为出差
//                }
//
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                String formatDate = dateFormat.format(signNextVO.getSignDate());
//                if (signNextVO.getSignType().equals("0")) {
//                    // 固定打卡
//                    long workHours = countWorkTime(signNextVO.getUserId(), formatDate, ruleId);
//                    signFile.setWorkHoursReal(new BigDecimal(workHours));
//                    if (workHours >= 7.5) {
//                        signFile.setSituation("0"); // 工时大于8小时，设置为正常
//                    } else {
//                        signFile.setSituation("1"); // 工时小于8小时，设置为异常
//                    }
//                } else {
//                    // 自由打卡
//                    long workHours = countFreeWorkTime(signNextVO.getUserId(), formatDate, ruleId);
//                    signFile.setWorkHoursReal(new BigDecimal(workHours));
//                    if (workHours >= 7.5) {
//                        signFile.setSituation("0"); // 工时大于8小时，设置为正常
//                    } else {
//                        signFile.setSituation("1"); // 工时小于8小时，设置为异常
//                    }
//                }
//
//                return signFile;
//            }).collect(Collectors.toList());
//
//            // 统计集合入库
//            iSignFileService.saveBatch(fileList);
//        }
//    }
//
//    private String findYesDayIndex(String template) {
//        String[] split = template.split(",");
//        Calendar calendar = Calendar.getInstance();
//        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // 获取当日是当月的第几天
//        int[] array = new int[calendar.getActualMaximum(Calendar.DAY_OF_MONTH)]; // 获取当月天数
//        // 将当天下标赋值为1
//        array[dayOfMonth - 1] = 1;
//        return split[dayOfMonth - 2];
//    }
//
//    /**
//     * 统计固定打卡的有效时长，不跨天
//     */
//    private long countWorkTime(String id, String date, String ruleId) {
//        SignRuleResponse rule = iSignRuleService.getByRuleId(ruleId);
//
//        // 四个时间节点
//        LocalTime workTime = LocalTime.parse(rule.getWorkTime());
//        LocalTime amOffTime = null;
//        if(StringUtils.isNotBlank(rule.getAmOffTime())){
//            amOffTime = LocalTime.parse(rule.getAmOffTime());
//        }
//        LocalTime pmWorkTime = null;
//        if(StringUtils.isNotBlank(rule.getPmWorkTime())){
//            pmWorkTime = LocalTime.parse(rule.getPmWorkTime());
//        }
//        LocalTime offTime = LocalTime.parse(rule.getOffTime());
//
//        // 查询昨天的打卡记录
//        List<SignNextAllResponse> details = iSignNextAllService.getSignDetailByUserDay(id, date);
//        if(details == null){
//            return 0;
//        }
//
//        LocalTime start = null;
//        LocalTime end = null;
//        long time = 0;
//
//        for (int i = 0; i < details.size(); i++) {
//            if (details.get(i).getSignType().equals("0")) { // 打上班卡
//                LocalTime signTime = LocalTime.parse(details.get(i).getSignTime());
//                // 计算有效开始时间
//                if (StringUtils.isNotBlank(rule.getAmOffTime()) && StringUtils.isNotBlank(rule.getPmWorkTime())) { // 有上午下班时间和下午上班时间
//                    if (signTime.isBefore(workTime)) {
//                        start = workTime;
//                    } else if (signTime.isAfter(workTime) && signTime.isBefore(amOffTime)) {
//                        start = signTime;
//                    } else if (signTime.isAfter(amOffTime) && signTime.isBefore(pmWorkTime)) {
//                        start = pmWorkTime;
//                    } else if (signTime.isAfter(pmWorkTime) && signTime.isBefore(offTime)) {
//                        start = signTime;
//                    } else if (signTime.isAfter(offTime)) {
//                        start = null;
//                    }
//                } else { // 只有上下班时间
//                    if (signTime.isBefore(workTime)) {
//                        start = workTime;
//                    } else if (signTime.isAfter(workTime) && signTime.isBefore(offTime)) {
//                        start = signTime;
//                    } else if (signTime.isAfter(offTime)) {
//                        start = null;
//                    }
//                }
//
//                // 如果最后一次打卡，只打了上班卡，而没有打下班卡，那么跳过这次统计
//                if((i+1) >= details.size()){
//                    continue;
//                }
//
//                // 计算有效结束时间
//                LocalTime signTime1 = LocalTime.parse(details.get(i + 1).getSignTime());
//                if (StringUtils.isNotBlank(rule.getAmOffTime()) && StringUtils.isNotBlank(rule.getPmWorkTime())) {
//                    if (signTime1.isBefore(workTime)) {
//                        end = null;
//                    } else if (signTime1.isAfter(workTime) && signTime1.isBefore(amOffTime)) {
//                        end = signTime1;
//                    } else if (signTime1.isAfter(amOffTime) && signTime1.isBefore(pmWorkTime)) {
//                        end = amOffTime;
//                    } else if (signTime1.isAfter(pmWorkTime) && signTime1.isBefore(offTime)) {
//                        end = signTime1;
//                    } else if (signTime1.isAfter(offTime)) {
//                        end = offTime;
//                    }
//                } else {
//                    if (signTime1.isBefore(workTime)) {
//                        end = null;
//                    } else if (signTime1.isAfter(workTime) && signTime1.isBefore(amOffTime)) {
//                        end = signTime1;
//                    } else if (signTime1.isAfter(offTime)) {
//                        end = offTime;
//                    }
//                }
//
//                // 叠加本次打卡有效工时
//                if (start != null && end != null) {
//                    Duration duration = Duration.between(start, end);
//                    long second = duration.getSeconds();
//                    time += second;
//                }
//            }
//        }
//
//        return time / 3600;
//    }
//
//    /**
//     * 统计自由打卡的有效时长，不跨天
//     */
//    private long countFreeWorkTime(String id, String date, String ruleId) {
//        List<SignNextAllResponse> details = iSignNextAllService.getSignDetailByUserDay(id, date);
//        if(details == null){
//            return 0;
//        }
//
//        LocalTime freeStart = LocalTime.of(12, 0, 0);
//        LocalTime freeEnd = LocalTime.of(13, 30, 0);
//
//        LocalTime start = null;
//        LocalTime end = null;
//        long time = 0;
//
//        for (int i = 0; i < details.size(); i++) {
//            if (details.get(i).getSignType().equals("0")) {
//                LocalTime signTime = LocalTime.parse(details.get(i).getSignTime());
//                // 计算有效开始时间
//                if (signTime.isBefore(freeStart)) {
//                    start = signTime;
//                } else if (signTime.isAfter(freeStart) && signTime.isBefore(freeEnd)) {
//                    start = freeEnd;
//                } else if (signTime.isAfter(freeEnd)) {
//                    start = signTime;
//                }
//
//                // 如果最后一次打卡，只打了上班卡，而没有打下班卡，那么跳过这次统计
//                if((i+1) >= details.size()){
//                    continue;
//                }
//
//                LocalTime signTime1 = LocalTime.parse(details.get(i + 1).getSignTime());
//                // 计算有效结束时间
//                if (signTime.isBefore(freeStart)) {
//                    end = signTime1;
//                } else if (signTime.isAfter(freeStart) && signTime.isBefore(freeEnd)) {
//                    end = freeStart;
//                } else if (signTime.isAfter(freeEnd)) {
//                    end = signTime1;
//                }
//
//                // 叠加本次打卡有效工时
//                if (start != null && end != null) {
//                    Duration duration = Duration.between(start, end);
//                    long second = duration.getSeconds();
//                    time += second;
//                }
//            }
//        }
//        return time / 3600;
//    }
//
//    @Test
//    public void executeTaskCross() {
//        // 每个考勤组分别统计
//        List<SignGroup> list = iSignGroupService.getList();
//        for (SignGroup signGroup : list) {
//            // 如果当前考勤组昨天没有打卡计划，跳过统计
//            List<SignNextVO> allLastday = iSignNextService.getSignInfoAllLastday(signGroup.getId());
//            if (allLastday == null) continue;
//            // 筛选出不是跨天的打卡计划
//            List<SignNextVO> dealList = allLastday.stream()
//                    .filter(SignNextVO -> SignNextVO.getCrossDay().equals("1"))
//                    .collect(Collectors.toList());
//            if (dealList == null) return;
//
//            List<SignFile> fileList = dealList.stream().map(signNextVO -> {
//                // 根据考勤计划查询考勤规则
//                String ruleId = "1";
//                if (signNextVO.getSignType().equals("1")) {
//                    ruleId = "5"; // 自由打卡设置默认规则
//                } else if (signNextVO.getTemplateUser() != null) {
//                    String template = signTemplateService.getSpecialTemplate(signNextVO.getUserId(), signNextVO.getGroupId());
//                    ruleId = findYesDayIndex(template);
//                } else {
//                    String template = signTemplateService.getNormalTemplate(signNextVO.getTemplateGroup());
//                    ruleId = findYesDayIndex(template);
//                }
//                SignRuleResponse rule = iSignRuleService.getByRuleId(ruleId);
//
//                // 填充打卡计划中的信息
//                SignFile signFile = new SignFile();
//                BeanUtils.copyProperties(signNextVO, signFile);
//                signFile.setWorkHours(rule.getWorkHours());
//                signFile.setStatus("1"); // 暂未启用，不知含义？
//
//                // 外勤计划会提前设置signFlag，而非外勤计划的signFlag就是空
//                if (signNextVO.getSignFlag() == null) {
//                    signFile.setIsLeave("0"); // is_leave 0 正常；1 请假；2 出差；3 外勤；4 调休；
//                } else {
//                    signFile.setIsLeave("2"); // 外勤计划会提前设置signFlag为3，这里暂时把外勤定义为出差
//                }
//
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                String formatDate = dateFormat.format(signNextVO.getSignDate());
//                long workHours = countWorkTimeCross(signNextVO.getUserId(), formatDate, ruleId);
//                signFile.setWorkHoursReal(new BigDecimal(workHours));
//                if (workHours >= 7.5) {
//                    signFile.setSituation("0"); // 工时大于8小时，设置为正常
//                } else {
//                    signFile.setSituation("1"); // 工时小于8小时，设置为异常
//                }
//
//                return signFile;
//            }).collect(Collectors.toList());
//
//            // 统计入库
//            iSignFileService.saveBatch(fileList);
//        }
//    }
//
//    // 统计跨天的打卡时长，取昨天的最后一条打卡记录，和今天的第一条打卡记录，计算时长
//    private long countWorkTimeCross(String id, String date, String ruleId) {
//        // 昨晚的最后一条打卡记录的打卡时间到24点的时间
//        List<SignNextAllResponse> details = iSignNextAllService.getSignDetailByUserDay(id, date);
//        SignNextAllResponse lastRecord = details.get(details.size() - 1);
//        LocalTime start = LocalTime.parse(lastRecord.getSignTime());
//        LocalTime startEnd = LocalTime.of(23, 59, 59);
//        Duration duration1 = Duration.between(start, startEnd);
//        long second1 = duration1.getSeconds();
//
//        // 0点到今天的第一天打卡记录的打卡时间
//        LocalDate yesterday = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
//        LocalDate today = yesterday.plusDays(1);
//        String todayString = today.format(DateTimeFormatter.ISO_DATE);
//        List<SignNextAllResponse> todayDetails = iSignNextAllService.getSignDetailByUserDay(id, todayString);
//        SignNextAllResponse firstRecord = todayDetails.get(0);
//        LocalTime endStart = LocalTime.of(0, 0, 0);
//        LocalTime end = LocalTime.parse(firstRecord.getSignTime());
//        Duration duration2 = Duration.between(endStart, end);
//        long second2 = duration2.getSeconds();
//
//        return (second1+second2) / 3600;
//    }
//
//    //---------------     打卡     ---------------
//
//    @Test
//    public void j(){
//        String startDate = "2022-01-01";
//        String endDate = "2022-01-02";
//
//        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
//        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
//
//        Period period = Period.between(start, end);
//
//        int days = period.getDays();
//
//        for (int i = 1; i < days; i++) {
//            LocalDate date = start.plusDays(i);
//            System.out.println(date);
//        }
//    }
//
//}
