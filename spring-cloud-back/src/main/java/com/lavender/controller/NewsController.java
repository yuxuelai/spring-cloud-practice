package com.lavender.controller;


import com.alibaba.fastjson.JSONObject;
import com.lavender.pojo.NewsModel;
import com.lavender.pojo.NewsTypeModel;
import com.lavender.pojo.ResultModel;
import com.lavender.service.NewsService;
import io.swagger.annotations.Api;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import static java.time.LocalTime.now;


@Api(tags = "the arrangement of news")
@Controller
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class NewsController {

    private static String TOPIC_NAME = "lavender";

    String time=now()+"";
    String message="";

    @Autowired
    @Qualifier("newsServiceImpl")
    private NewsService newsService;

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     *
     * 查询全部商品
     * @param newsModel
     * @return
     */
    @RequestMapping("/back/news/all")
    @ResponseBody
    public String newsFindAll(NewsModel newsModel){
        NewsTypeModel newsTypeModel = new NewsTypeModel ();
        ResultModel all = newsService.findAll (newsModel);

        ResultModel newsTypeAll = newsService.findNewsTypeAll (newsTypeModel);

        Object data = newsTypeAll.getData ();

        return JSONObject.toJSONString (all);


    }

    /**
     *
     * 通过id查询商品
     *
     */

    @RequestMapping("/back/news/findById")
    @ResponseBody
    public String newsFindById(NewsModel newsModel){

        ResultModel byId = newsService.findById (newsModel);

        return JSONObject.toJSONString (byId);


    }

    /**
     * 新增新闻
     * @param newsModel
     * @return
     */
    @RequestMapping("/back/news/add")
    @ResponseBody
    public String newsAdd(NewsModel newsModel){

        ResultModel add = newsService.add (newsModel);
        rabbitTemplate.convertAndSend (TOPIC_NAME,"lavender.admin.*",message="北京时间："+time+"  超级管理员-->新增了一条新闻");

        return JSONObject.toJSONString (add);

    }


    /**
     *
     * find product
     * @param newsModel
     * @return
     */

    @RequestMapping("/back/news/findAll")
    @ResponseBody
    public String newsFind(NewsModel newsModel){

        ResultModel resultModel = newsService.find (newsModel);

        return JSONObject.toJSONString (resultModel);
    }


    /**
     * 更新新闻内容
     * @param newsModel
     * @return
     */
    @RequestMapping("/back/news/update")
    @ResponseBody
    public String newsUpdate(NewsModel newsModel){

        ResultModel update = newsService.update (newsModel);
        rabbitTemplate.convertAndSend (TOPIC_NAME,"lavender.admin.*",message="北京时间："+time+"  超级管理员-->更新了产品id为"+newsModel.getId ()+"的信息！！~~");

        return JSONObject.toJSONString (update);

    }


    /**
     *
     * 删除新闻
     * @param newsModel
     * @return
     */
    @RequestMapping("/back/news/delete")
    @ResponseBody
    public String newsDelete(NewsModel newsModel){
        ResultModel delete = newsService.delete (newsModel);
        rabbitTemplate.convertAndSend (TOPIC_NAME,"lavender.admin.*",message="北京时间："+time+"  超级管理员-->删除了一条新闻");

        return JSONObject.toJSONString (delete);

    }





}
