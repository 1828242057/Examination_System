package com.system.mapper;

import org.apache.ibatis.annotations.Param;

import com.system.po.Scores;
import com.system.po.ScoresExample;

public interface ScoresMapper {
	int updateByExample(@Param("record") Scores record, @Param("example") ScoresExample example);
	int insert(Scores record);
	Scores selectByID(Integer id);
	int deleteByID(Integer id);
}
