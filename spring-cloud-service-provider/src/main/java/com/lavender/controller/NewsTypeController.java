package com.lavender.controller;

import com.alibaba.fastjson.JSONObject;
import com.lavender.pojo.NewsTypeModel;
import com.lavender.pojo.ResultModel;
import com.lavender.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsTypeController {

    @Qualifier("newsStypeServiceImpl")
    @Autowired
    NewsTypeService newsTypeService;

    @RequestMapping("/back/newsType/findById")
    @ResponseBody
    public String newsFindAll(NewsTypeModel newsTypeModel){

        ResultModel newsType = newsTypeService.findAll (newsTypeModel);

        return JSONObject.toJSONString (newsType);


    }
}
