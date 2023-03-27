package com.lavender.service.impl;


import com.lavender.mapper.ProductMapper;
import com.lavender.pojo.ProductModel;
import com.lavender.pojo.ResultModel;
import com.lavender.service.ProductService;
import com.lavender.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public ResultModel enable(ProductModel productModel) {
        return null;
    }

    @Override
    public ResultModel add(ProductModel productModel) {
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker ();
        productModel.setId (snowflakeIdWorker.nextId ());
        return ResultModel.getResult (productMapper.add (productModel));
    }

    @Override
    public ResultModel update(ProductModel productModel) {

        return ResultModel.getResult (productMapper.update (productModel));

    }

    @Override
    public ResultModel delete(ProductModel productModel) {
        return ResultModel.getResult (productMapper.delete (productModel));
    }

    @Override
    public ResultModel findById(ProductModel productModel) {

        return ResultModel.getResult (productMapper.findById (productModel));

    }

    @Override
    public ResultModel findAll(ProductModel productModel) {
        return ResultModel.getResult ( productMapper.findAll ());
    }

    @Override
    public ResultModel count(ProductModel productModel) {
        return null;
    }

    @Override
    public ResultModel find(ProductModel productModel) {

        return ResultModel.getResult (productMapper.find ());

    }
}
