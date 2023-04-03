package com.lavender.service;


import com.lavender.pojo.ResultModel;


public interface BaseService<T> {
    ResultModel enable(T t);

    ResultModel add(T t);

    ResultModel update(T t);

    ResultModel delete(T t);

    ResultModel findById(T t);

    ResultModel findAll(T t);

    ResultModel count(T t);

    ResultModel find(T t);
}
