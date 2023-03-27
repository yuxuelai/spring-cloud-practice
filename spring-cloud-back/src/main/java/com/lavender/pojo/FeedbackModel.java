package com.lavender.pojo;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackModel {

    @JSONField(serializeUsing = com.alibaba.fastjson.serializer.ToStringSerializer.class)
    private long id;
    private String name;
    private String phone;
    private String content;
    private Integer isShow;
    private Integer whereShow;
    private Integer enable;
    private String createTime;
    private String updateTime;





}
