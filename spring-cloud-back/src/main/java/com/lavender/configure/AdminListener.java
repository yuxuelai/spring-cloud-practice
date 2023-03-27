package com.lavender.configure;//package com.lavender.configure;
//
//import com.lavender.mapper.OperatingMapper;
//import com.lavender.pojo.OperatingModel;
//import com.lavender.service.OperatingService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//
//@Slf4j
//@Component
//public class AdminListener {
//
//
//    OperatingModel operatingModel = new OperatingModel ();
//    @Autowired
//    OperatingMapper operatingMapper;
//
//
//    @RabbitListener(queues = "lavender.admin.*")
//    public void receive(String message){
//        operatingModel.setMessage (message);
//        operatingMapper.add (operatingModel);
//        log.info("收到消息，消息内容是: {}",message);
//
//    }
//}
