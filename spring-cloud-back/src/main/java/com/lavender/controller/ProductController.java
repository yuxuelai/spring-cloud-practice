package com.lavender.controller;

import com.alibaba.fastjson.JSONObject;
import com.lavender.pojo.ProductModel;
import com.lavender.pojo.ResultModel;
import com.lavender.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static java.time.LocalTime.now;


@Api(tags = "the arrangement of product")
@Controller
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class ProductController {

    private static String TOPIC_NAME = "lavender";

    String time=now()+"";
    String message="";

    @Autowired
    @Qualifier("productServiceImpl")
    private ProductService productService;

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     *
     * 查询全部商品
     * @return
     */
    @RequestMapping("/back/product/findAll")
    @ResponseBody
    public ResultModel productFindAll(ProductModel productModel){

        ResultModel all = productService.find (productModel);

        return all;
    }

    /**
     * 通过ID查询商品
     * @param productModel
     * @return
     */
    @RequestMapping("/back/product/findById")
    @ResponseBody
    public String productFindById(ProductModel productModel){
        ResultModel byId = productService.findById (productModel);

        return JSONObject.toJSONString (byId);



    }

    @RequestMapping("/back/product/add")
    @ResponseBody
    public String productAdd(ProductModel productModel){

        ResultModel add = productService.add (productModel);
        rabbitTemplate.convertAndSend (TOPIC_NAME,"lavender.admin.*",message="北京时间："+time+"  超级管理员-->新增了产品");


        return JSONObject.toJSONString (add);


    }

    @RequestMapping("/back/product/all")
    @ResponseBody
    public String productFind(ProductModel productModel){
        ResultModel all = productService.findAll (productModel);

        return JSONObject.toJSONString (all);

    }


    /**
     *
     * 删除商品
     * @param productModel
     * @return
     */
    @RequestMapping("/back/product/delete")
    @ResponseBody
    public String productDelete(ProductModel productModel){
        ResultModel delete = productService.delete (productModel);
        rabbitTemplate.convertAndSend (TOPIC_NAME,"lavender.admin.*",message="北京时间："+time+"  超级管理员-->删除了一条产品");

        return JSONObject.toJSONString (delete);

    }


    /**
     *
     * 更新商品
     * @param productModel
     * @return
     */
    @RequestMapping("/back/product/update")
    @ResponseBody
    public String productUpdate(ProductModel productModel){

        ResultModel update = productService.update (productModel);
        rabbitTemplate.convertAndSend (TOPIC_NAME,"lavender.admin.*",message="北京时间："+time+"  超级管理员-->更新了产品id为"+productModel.getId ()+"的信息！！~~");

        return JSONObject.toJSONString (update);



    }


}
