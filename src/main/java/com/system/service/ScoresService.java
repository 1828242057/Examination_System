package com.system.service;

import com.system.po.Scores;

public interface ScoresService {
	void updateOne(Scores scores) throws Exception;
	void save(Scores scores) throws Exception;
	Scores findByID(Integer id) throws Exception;
	void remove(Integer id) throws Exception;
}
