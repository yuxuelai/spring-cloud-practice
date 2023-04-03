package com.lavender.pojo;


//import com.lavender.utils.IdJsonSerialize;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsTypeModel {

    @JSONField(serializeUsing = com.alibaba.fastjson.serializer.ToStringSerializer.class)
    private Long id;

    private  Integer isShow;

    private  Integer enable;

    private String newsStyle;




}
