package com.lavender.pojo;

import lombok.Data;

/**
 *
 * 封装返回的结果集
 * resultModel  返回前端数据确认
 * 前端Ajax 进行验证 如果成功就进行后续的操作 失败返回失败的信息
 *
 */
@Data
public class ResultModel {
    private String msg;
    private Integer code;
    private Integer count;
    private Object data;


    private ResultModel() {


    }

    private ResultModel(String msg, Integer code, Integer count, Object data) {
        this.msg = msg;
        this.code = code;
        this.count = count;
        this.data = data;
    }



    // 返回结果集
    public static ResultModel getResult(int count) {

        return getResult(null, count, 0, "");
    }

    // 返回成功的结果集
    public static ResultModel success() {
        return getResult(null, 1, 0, "success");
    }

    // 返回错误的结果集
    public static ResultModel error() {
        return getResult(null, 0, 0, "error");
    }


    //
    public static ResultModel getResult(Object data) {

        return getResult(data, 1, 0, "success");

    }


    public static ResultModel getResult(Object data, int count) {

        return getResult(data, count, 0, "");
    }

    private static ResultModel getResult(Object data, int count, int code, String msg) {

        return new ResultModel(msg, code, count, data);

    }

}

