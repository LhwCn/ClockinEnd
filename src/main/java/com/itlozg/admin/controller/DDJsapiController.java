package com.itlozg.admin.controller;

import cn.hutool.json.JSON;
import com.itlozg.admin.entity.dto.DDJsApiDto;
import com.itlozg.admin.util.CommonResult;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/dingding")
public class DDJsapiController {
    @RequestMapping(value = "/jsapi", method = RequestMethod.POST)
    public CommonResult DDJsApi(@RequestBody DDJsApiDto ddJsApiDto) throws Exception {
        String authForm = "{" +
                "\"url\":\"" + ddJsApiDto.getUrl() + "\"," +
                "\"sendBy\":\""+ ddJsApiDto.getSendBy() +"\"" +
                "}";

        //钉钉JSAPI鉴权接口, 接口说明见下文
        HttpPost httpPost = new HttpPost("https://dingding.weichai.com/dingding/jsapi");
        httpPost.setEntity(new StringEntity(authForm, "utf-8"));
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String authInfo = EntityUtils.toString(response.getEntity());
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+authInfo);
        try {
            response.close();
            httpPost.releaseConnection();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResult.success(authInfo);
    }
}
