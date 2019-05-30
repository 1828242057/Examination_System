package com.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.mapper.ScoresMapper;
import com.system.po.*;
import com.system.service.ScoresService;

@Service
public class ScoresServiceImpl implements ScoresService{
	
	@Autowired
    private ScoresMapper scoresMapper;
	
	public void updateOne(Scores scores) throws Exception{
		ScoresExample example = new ScoresExample();
		ScoresExample.Criteria criteria = example.createCriteria();

        criteria.andSelectedcourseIDEqualTo(scores.getSelectedcourseid());

        scoresMapper.updateByExample(scores, example);

    }
	
	public void save(Scores scores) throws Exception{
		scoresMapper.insert(scores);
	}
	
	public Scores findByID(Integer id) throws Exception{
		return scoresMapper.selectByID(id);
	}
	
	public void remove(Integer id) throws Exception{
		scoresMapper.deleteByID(id);
	}
}
