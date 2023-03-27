package com.lavender.controller;

import com.alibaba.fastjson2.JSONObject;
import com.lavender.pojo.FeedbackModel;
import com.lavender.pojo.ResultModel;
import com.lavender.service.FeedbackService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Api(tags = "the arrangement of feedback")
@RestController
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class FeedBackController {

    @Qualifier("feedBackServiceImpl")
    @Autowired
    FeedbackService feedbackService;


    @RequestMapping("/back/feedback/all")
    public ResultModel FeedBackFind(FeedbackModel feedbackModel){

        ResultModel resultModel = feedbackService.find (feedbackModel);

        return resultModel;


    }

    @RequestMapping("/back/feedback/delete")
    @ResponseBody
    public String FeedBackDelete(FeedbackModel feedbackModel){

        ResultModel delete = feedbackService.delete (feedbackModel);

        return JSONObject.toJSONString (delete);


    }



}
