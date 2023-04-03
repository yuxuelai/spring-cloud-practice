package com.lavender.service;


import com.lavender.pojo.NewsModel;
import com.lavender.pojo.NewsTypeModel;
import com.lavender.pojo.ResultModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@FeignClient(value = "SERVICE-PROVIDER")
public interface NewsService extends BaseService<NewsModel>{

    @RequestMapping("/back/newsType/findById")
    ResultModel findNewsTypeAll(NewsTypeModel newsTypeModel);



}
