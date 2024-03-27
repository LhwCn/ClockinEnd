package com.itlozg.admin.rabbitmq;

import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.json.JSONString;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itlozg.admin.entity.SignFinal;
import com.itlozg.admin.entity.SignNext;
import com.itlozg.admin.entity.dto.LoginDto;
import com.itlozg.admin.entity.dto.SignNextDto;
import com.itlozg.admin.enums.StatusCode;
import com.itlozg.admin.exception.ApplicationException;
import com.itlozg.admin.model.request.ConfigRequest;
import com.itlozg.admin.model.response.ConfigResponse;
import com.itlozg.admin.model.response.SignNextResponse;
import com.itlozg.admin.model.response.SignRuleResponse;
import com.itlozg.admin.service.ISignNextService;
import com.itlozg.admin.util.EncryptEUtils;
import com.itlozg.admin.util.JedisUtils;
import com.itlozg.admin.util.ThreadLocalUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: LOVOL-Clock-End
 * @description:
 * @author: lilu
 * @create: 2023-07-28 13:45
 **/
//@Component
@Slf4j
public class CarInfoListener {
    @Autowired
    private ISignNextService iSignNextService;

    /*@SneakyThrows
    @RabbitListener(bindings =@QueueBinding(
            value = @Queue(RabbitmqConfig.CarInfo_QUEUE),
            exchange = @Exchange(RabbitmqConfig.CarInfo_TOPIC_EXCHANGE),
            key = "CarInfo.#"
    ))
    public void ReceiveCarInfo(String string){
        System.out.println("本次自动打卡车辆信息是："+string);
        List<SignNextDto> signNextDtos = JSON.parseArray(string, SignNextDto.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 将日期转换为指定格式的字符串
        String formattedDate = LocalDate.now().format(formatter);
        List<SignNext> signNexts = signNextDtos.stream().map(signNextDto -> {
            SignNext signNext = iSignNextService.getOne(new LambdaQueryWrapper<SignNext>()
                    .eq(StringUtils.isNotBlank(signNextDto.getIdcard()), SignNext::getUserId, signNextDto.getIdcard())
                    .eq(SignNext::getSignDate,formattedDate));
            signNext.setComeTime(signNextDto.getInTime());
            return signNext;
        }).collect(Collectors.toList());
        for (SignNext signNext : signNexts) {
            signNext.setComeAddress("山东省青岛市黄岛区千山南路55号靠近康美滋营养烹饪厨师学院");
            signNext.setComeAddressName("雷沃重工");
            signNext.setComeLongitude("120.19542709038717");
            signNext.setComeLatitude("36.03729726807991");
            iSignNextService.update(signNext,new LambdaQueryWrapper<SignNext>()
                    .eq(SignNext::getUserId,signNext.getUserId()));
        }
    }*/
}
