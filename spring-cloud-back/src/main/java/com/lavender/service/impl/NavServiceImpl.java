package com.lavender.service.impl;


import com.lavender.mapper.NavMapper;
import com.lavender.pojo.NavigateModel;
import com.lavender.pojo.ResultModel;
import com.lavender.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NavServiceImpl implements NavService {

    @Autowired
    private NavMapper navMapper;

    public void setNavMapper(NavMapper navMapper) {
        this.navMapper = navMapper;
    }

    @Override
    public ResultModel enable(NavigateModel navigateModel) {
        return null;
    }

    @Override
    public ResultModel add(NavigateModel navigateModel) {
        return null;
    }

    @Override
    public ResultModel update(NavigateModel navigateModel) {
        return null;
    }

    @Override
    public ResultModel delete(NavigateModel navigateModel) {
        return ResultModel.getResult (navMapper.delete (navigateModel));
    }

    @Override
    public ResultModel findById(NavigateModel navigateModel) {
        return null;
    }

    @Override
    public ResultModel findAll(NavigateModel navigateModel) {
        return null;
    }

    @Override
    public ResultModel count(NavigateModel navigateModel) {
        return null;
    }

    @Override
    public ResultModel find(NavigateModel navigateModel) {
        return ResultModel.getResult (navMapper.find (navigateModel));
    }
}
