package com.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.mapper.FeedbackMapper;
import com.system.po.*;
import com.system.service.FeedbackService;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
    private FeedbackMapper feedbackMapper;
	
	public void update(Feedback feedback) throws Exception{
        feedbackMapper.update(feedback);
    }
	
	public void save(Feedback feedback) throws Exception{
		feedbackMapper.insert(feedback);
	}
	
	public Feedback findByID(Integer id) throws Exception{
		return feedbackMapper.selectByID(id);
	}
	
	public List<Feedback> findByTeacherID(Integer teacherid) throws Exception{
		return feedbackMapper.selectByTeacherID(teacherid);
	}
	
	public void remove(Integer id) throws Exception{
		feedbackMapper.deleteByID(id);
	}
}