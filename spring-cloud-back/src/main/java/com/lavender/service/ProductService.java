package com.lavender.service;


import com.lavender.pojo.ProductModel;
import com.lavender.pojo.ResultModel;
import org.springframework.stereotype.Service;


public interface ProductService extends BaseService<ProductModel> {
    @Override
    ResultModel find(ProductModel productModel);


}
