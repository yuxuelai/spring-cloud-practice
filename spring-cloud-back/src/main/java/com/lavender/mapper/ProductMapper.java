package com.lavender.mapper;

import com.lavender.pojo.ProductModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    int add(ProductModel productModel);


    int update(ProductModel productModel);

    int delete(ProductModel productModel);

    ProductModel findById(ProductModel productModel);

    List<ProductModel> findAll();

    List<ProductModel> find();
}
