package com.itlozg.admin.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;

/**
 * @program: CarAutoClockin
 * @description:
 * @author: lilu
 * @create: 2023-07-28 11:37
 **/
public class RabbitmqConfig {
    public static final String ITEM_TOPIC_EXCHANGE = "item_topic_exchange";
    public static final String CarInfo_TOPIC_EXCHANGE = "Auto";
    // 队列名称
    public static final String ITEM_QUEUE = "simple.queue";
    public static final String CarInfo_QUEUE = "CarInfo_queue";
    //声明交换机
    @Bean
    public Exchange exchange(){
        return ExchangeBuilder.topicExchange(ITEM_TOPIC_EXCHANGE).durable(true).build();
    }
    //声明队列
    @Bean
    public Queue queue(){
        return QueueBuilder.durable(ITEM_QUEUE).build();
    }
    //队列绑定到交换机
    @Bean
    public Binding binding(Queue queue, Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("item.#").noargs();
    }

    @Bean
    public TopicExchange AutoExchange(){
        return ExchangeBuilder.topicExchange(CarInfo_TOPIC_EXCHANGE).durable(true).build();
    }

    @Bean
    public Queue CarInfo(){
        return QueueBuilder.durable(CarInfo_QUEUE).build();
    }

    @Bean
    public Binding CarInfoExchang(Queue queue,Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("CarInfo.#").noargs();
    }
}
