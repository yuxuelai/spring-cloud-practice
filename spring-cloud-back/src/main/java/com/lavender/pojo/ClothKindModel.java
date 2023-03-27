package com.lavender.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClothKindModel {

    private Integer id;
    private String createTime;
    private String updateTime;
    private Integer enbale;
    private Integer whereShow;
    private Integer isShow;
    private String remark;
    private String clothType;

}
