package com.lavender.controller;


import com.alibaba.fastjson.JSONObject;
import com.lavender.pojo.NavigateModel;
import com.lavender.pojo.ResultModel;
import com.lavender.service.NavService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags = "the arrangement of navigate")
@RestController
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class NavController {

    @Autowired
    @Qualifier("navServiceImpl")
    private NavService navService;


    @RequestMapping("/back/navigate/all")
    @ResponseBody
    public String navFind(NavigateModel navigateModel){
        ResultModel resultModel = navService.find (navigateModel);

        return JSONObject.toJSONString (resultModel);



    }

    @RequestMapping("/back/navigate/delete")
    @ResponseBody
    public String navDelete(NavigateModel navigateModel){
        ResultModel delete = navService.delete (navigateModel);

        return JSONObject.toJSONString (delete);

    }




}
