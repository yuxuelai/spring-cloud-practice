package com.lavender.listener;

import com.lavender.mapper.OperatingMapper;
import com.lavender.pojo.OperatingModel;
import com.lavender.utils.EmailRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class RabbitReceiveListener {

    @Autowired
    OperatingMapper operatingMapper;

    @RabbitListener(queues = "adminDelivery")
    public void reportAndReceive(String message) {

        log.info ("receive message:==》" +message);
        OperatingModel operatingModel = new OperatingModel ();
        operatingModel.setMessage (message);
        operatingMapper.add (operatingModel);

    }
}
