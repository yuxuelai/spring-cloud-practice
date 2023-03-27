package com.lavender.service.impl;

import com.lavender.mapper.OperatingMapper;
import com.lavender.pojo.OperatingModel;
import com.lavender.service.OperatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatingServiceImpl implements OperatingService {

    @Autowired
    OperatingMapper operatingMapper;

    @Override
    public int add(OperatingModel operatingModel) {
        return operatingMapper.add (operatingModel);
    }
}
