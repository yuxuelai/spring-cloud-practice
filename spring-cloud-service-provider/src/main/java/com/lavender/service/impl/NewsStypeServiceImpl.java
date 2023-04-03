package com.lavender.service.impl;


import com.lavender.mapper.NewsTypeMapper;
import com.lavender.pojo.NewsTypeModel;
import com.lavender.pojo.ResultModel;
import com.lavender.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsStypeServiceImpl implements NewsTypeService {

    @Autowired
    private NewsTypeMapper newsTypeMapper;


    @Override
    public ResultModel enable(NewsTypeModel newsTypeModel) {
        return null;
    }

    @Override
    public ResultModel add(NewsTypeModel newsTypeModel) {
        return null;
    }

    @Override
    public ResultModel update(NewsTypeModel newsTypeModel) {
        return null;
    }

    @Override
    public ResultModel delete(NewsTypeModel newsTypeModel) {
        return null;
    }

    @Override
    public ResultModel findById(NewsTypeModel newsTypeModel) {
        return null;

    }

    @Override
    public ResultModel findAll(NewsTypeModel newsTypeModel) {
        return ResultModel.getResult (newsTypeMapper.findAll (newsTypeModel));
    }

    @Override
    public ResultModel count(NewsTypeModel newsTypeModel) {
        return null;
    }

    @Override
    public ResultModel find(NewsTypeModel newsTypeModel) {
        return null;
    }
}
