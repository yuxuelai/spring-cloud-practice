package com.lavender.service.impl;


import com.lavender.mapper.NewsMapper;
import com.lavender.pojo.NewsModel;
import com.lavender.pojo.NewsTypeModel;
import com.lavender.pojo.ResultModel;
import com.lavender.service.NewsService;
import com.lavender.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private NewsService newsService;

    public void setNewsMapper(NewsMapper newsMapper) {

        this.newsMapper = newsMapper;

    }

    @Override
    public ResultModel enable(NewsModel newsModel) {

        return null;

    }

    @Override
    public ResultModel add(NewsModel newsModel) {
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker ();
        System.out.println (snowflakeIdWorker.nextId ());
        newsModel.setId (snowflakeIdWorker.nextId ());
        return ResultModel.getResult (newsMapper.add (newsModel));
    }

    @Override
    public ResultModel update(NewsModel newsModel) {
        return ResultModel.getResult (newsMapper.update (newsModel));
    }

    @Override
    public ResultModel delete(NewsModel newsModel) {
        return ResultModel.getResult (newsMapper.delete (newsModel));
    }

    @Override
    public ResultModel findById(NewsModel newsModel) {

        return ResultModel.getResult (newsMapper.findById (newsModel));
    }

    @Override
    public ResultModel findAll(NewsModel newsModel) {
        return ResultModel.getResult (newsMapper.findAll (newsModel));
    }

    @Override
    public ResultModel count(NewsModel newsModel) {
        return null;
    }



    @Override
    public ResultModel find(NewsModel newsModel) {
        return ResultModel.getResult (newsMapper.find(newsModel));
    }

    @Override
    public ResultModel findNewsTypeAll(NewsTypeModel newsTypeModel) {
        return newsService.findNewsTypeAll (newsTypeModel);
    }
}
