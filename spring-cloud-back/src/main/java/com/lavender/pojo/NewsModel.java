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
public class NewsModel {

    @JSONField(serializeUsing = com.alibaba.fastjson.serializer.ToStringSerializer.class)
    private Long id;
    private  Integer isShow;
    private  Integer enable;
    private Integer whereShow;
    private String updatetime;
    private String remark;
    private String createTime;
    private String name;
    private String newstitle;
    private String content;
    private String newsdate;
    private String newsStyle;
    private String imghref;
    private String displaycontent;



}
