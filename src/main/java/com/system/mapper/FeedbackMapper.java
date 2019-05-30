package com.system.mapper;

import com.system.po.Feedback;
import java.util.List;

public interface FeedbackMapper {
	int update(Feedback feedback);
	int insert(Feedback record);
	Feedback selectByID(Integer id);
	List<Feedback> selectByTeacherID(Integer teacherid);
	int deleteByID(Integer id);
}