package com.lavender.service;


import com.lavender.pojo.NewsTypeModel;
import com.lavender.pojo.ResultModel;
import org.springframework.cloud.openfeign.FeignClient;



public interface NewsTypeService extends BaseService<NewsTypeModel>{

    @Override
    ResultModel findAll(NewsTypeModel newsTypeModel);

}
