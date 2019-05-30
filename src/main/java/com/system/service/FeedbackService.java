package com.system.service;

import com.system.po.Feedback;
import java.util.List;

public interface FeedbackService {
	void update(Feedback feedback) throws Exception;
	void save(Feedback feedback) throws Exception;
	Feedback findByID(Integer id) throws Exception;
	List<Feedback> findByTeacherID(Integer id) throws Exception;
	void remove(Integer id) throws Exception;
}