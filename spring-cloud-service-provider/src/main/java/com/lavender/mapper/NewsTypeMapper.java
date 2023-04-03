package com.lavender.mapper;

import com.lavender.pojo.NewsTypeModel;
import com.lavender.pojo.ResultModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsTypeMapper {

//    NewsTypeModel findById(NewsTypeModel newsTypeModel);


    List<NewsTypeModel> findAll(NewsTypeModel newsTypeModel);
}
