package com.lavender.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {


    @JSONField(serializeUsing = com.alibaba.fastjson.serializer.ToStringSerializer.class)
    private Long id;
    private String createdate;
    private String file;
    private String createdatestart;
    private String createdateend;
    private String updatetime;
    private String updatetimestart;
    private String updatetimeend;
    private Integer enable;
    private String remark;

    private Integer page;
    private Integer limit;

    private String name;
    private String clothType;
    private BigDecimal startPrice;

    private BigDecimal normalprice;
    private BigDecimal marketprice;
    private BigDecimal startmarketprice;
    private BigDecimal endmarketprice;
    private String imgHref;

    private String content;
    private Integer isshow;
    private Integer isrecommend;
    private Integer clothid;
    private String clothName;
    private Integer whereshow;






}
