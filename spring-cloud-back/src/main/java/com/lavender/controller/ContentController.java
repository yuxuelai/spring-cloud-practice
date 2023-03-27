package com.lavender.controller;

import com.alibaba.fastjson.JSONObject;
import com.lavender.service.impl.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/parse/{keyword}")
    public Boolean parse(@PathVariable("keyword") String keyword) throws IOException {

        return contentService.parseContent (keyword);


    }

    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    public String search(@PathVariable("keyword") String keyword,
                                           @PathVariable("pageNo") int pageNo,
                                           @PathVariable("pageSize") int pageSize) throws IOException {

        return JSONObject.toJSONString (contentService.searchContentHighLighter (keyword, pageNo, pageSize));


    }
}
