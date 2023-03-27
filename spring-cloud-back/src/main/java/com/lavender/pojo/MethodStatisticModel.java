package com.lavender.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MethodStatisticModel {

    private String method;
    private String spendTime;
    private String createTime;
    private String describe;

}
