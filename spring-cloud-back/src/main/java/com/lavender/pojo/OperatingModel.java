package com.lavender.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;


import static java.time.LocalTime.now;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperatingModel {

    private String message;
}
