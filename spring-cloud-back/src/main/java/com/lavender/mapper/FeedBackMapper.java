package com.lavender.mapper;

import com.lavender.pojo.FeedbackModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface FeedBackMapper {
    List<FeedbackModel> find(FeedbackModel feedbackModel);

    int delete(FeedbackModel feedbackModel);
}
