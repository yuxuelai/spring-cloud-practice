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
public class NavigateModel {

    @JSONField(serializeUsing = com.alibaba.fastjson.serializer.ToStringSerializer.class)
    private long id;
    private String title;
    private String href;
    private Integer isShow;
    private Integer whereShow;
    private Integer enable;

}
