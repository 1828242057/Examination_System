package com.system.mapper;

import com.system.po.Scores;
import com.system.po.ScoresExample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScoresMapperTest {
	private ApplicationContext applicationContext;
	
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
    }

    public void insert() throws Exception {
        ScoresMapper scoresMapper = (ScoresMapper) applicationContext.getBean("scoresMapper");
        Scores scores = new Scores();
        scores.setSelectedcourseid(51);
        scores.setBoardscores(4);
        scores.setHomeworkscores(4);
        scores.setAttendancescores(4);
        scores.setExperimentalscores(4);
        scoresMapper.insert(scores);
    }
    
    public void updateByExample() throws Exception {
        ScoresMapper scoresMapper = (ScoresMapper) applicationContext.getBean("scoresMapper");
        Scores scores = new Scores();
        scores.setSelectedcourseid(51);
        scores.setBoardscores(3);
        scores.setHomeworkscores(7);
        scores.setAttendancescores(7);
        scores.setExperimentalscores(7);
        
        ScoresExample example = new ScoresExample();
		ScoresExample.Criteria criteria = example.createCriteria();
        criteria.andSelectedcourseIDEqualTo(51);
        
        scoresMapper.updateByExample(scores, example);
    }
}
