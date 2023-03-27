package com.lavender.mapper;

import com.lavender.pojo.NewsModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {


    int add(NewsModel newsModel);

    int update(NewsModel newsModel);

    int delete(NewsModel newsModel);

    NewsModel findById(NewsModel newsModel);

    List<NewsModel> findAll(NewsModel newsModel);

    List<NewsModel> find(NewsModel newsModel);
}
