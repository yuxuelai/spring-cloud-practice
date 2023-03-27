package com.lavender.mapper;

import com.lavender.pojo.NavigateModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NavMapper {
    int delete(NavigateModel navigateModel);

    List<NavigateModel> find(NavigateModel navigateModel);
}
