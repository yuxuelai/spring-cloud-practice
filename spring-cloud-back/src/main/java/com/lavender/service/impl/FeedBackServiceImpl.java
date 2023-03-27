package com.lavender.service.impl;


import com.lavender.mapper.FeedBackMapper;
import com.lavender.pojo.FeedbackModel;
import com.lavender.pojo.ResultModel;
import com.lavender.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedBackServiceImpl implements FeedbackService {

    @Autowired
    private FeedBackMapper feedBackMapper;

    public void setFeedBackMapper(FeedBackMapper feedBackMapper) {
        this.feedBackMapper = feedBackMapper;
    }

    @Override
    public ResultModel enable(FeedbackModel feedbackModel) {
        return null;
    }

    @Override
    public ResultModel add(FeedbackModel feedbackModel) {
        return null;
    }

    @Override
    public ResultModel update(FeedbackModel feedbackModel) {
        return null;
    }

    @Override
    public ResultModel delete(FeedbackModel feedbackModel) {
        return ResultModel.getResult (feedBackMapper.delete (feedbackModel));
    }

    @Override
    public ResultModel findById(FeedbackModel feedbackModel) {
        return null;
    }

    @Override
    public ResultModel findAll(FeedbackModel feedbackModel) {
        return null;
    }

    @Override
    public ResultModel count(FeedbackModel feedbackModel) {
        return null;
    }

    @Override
    public ResultModel find(FeedbackModel feedbackModel) {
        return ResultModel.getResult (feedBackMapper.find (feedbackModel));
    }
}
