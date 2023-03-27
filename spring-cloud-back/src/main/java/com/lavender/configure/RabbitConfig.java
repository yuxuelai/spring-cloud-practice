package com.lavender.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitConfig {

    private static String TOPIC_EXCHANGE ="lavender";

    @Bean
    public Queue adminQueue(){

        log.info ("adminDelivery 实例化成功");
        return new Queue("adminDelivery",true);

    }

    @Bean
    public Queue userQueue(){
        log.info("userDelivery 实例化成功");
        // 如果rabbit里面没有这个队列 会创建该队列
        //创建的前提是：你要向mq里的这个队列发消息或接收消息
        return new Queue("userDelivery",true);

    }

//    public Queue memberQueue(){
//        log.info ("memberDelivery 实例化成功");
//
//        return new Queue ("memberDelivery",true);
//
//    }

    @Bean
    TopicExchange lavender (){

        log.info ("Topic 实例化成功。。 name={}",TOPIC_EXCHANGE);
        return new TopicExchange (TOPIC_EXCHANGE,true,false);

    }

    @Bean
    Binding adminQueueBindMessageBroker(){
        log.info ("adminDelivery 绑定成功" );
        return BindingBuilder.bind (adminQueue()).to (lavender()).with ("lavender.admin.*");

    }

    @Bean
    Binding userQueueBindMessageBroker(){
        log.info ("userDelivery 绑定成功" );
        return BindingBuilder.bind (userQueue()).to (lavender()).with ("lavender.user.*");

    }

//    @Bean
//    Binding memberQueueBindMessageBroker(){
//        log.info ("memberDelivery 绑定成功" );
//        return BindingBuilder.bind (memberQueue ()).to (lavender()).with ("lavender.user.*");
//
//    }




}
