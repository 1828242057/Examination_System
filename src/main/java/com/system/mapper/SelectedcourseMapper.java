package com.system.mapper;

import com.system.po.CourseCustom;
import com.system.po.SelectedCourseCustom;
import com.system.po.Selectedcourse;
import com.system.po.SelectedcourseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectedcourseMapper {
    int countByExample(SelectedcourseExample example);

    int deleteByExample(SelectedcourseExample example);
    
    int deleteByStudentID(int id);
    
    int deleteByCourseID(int id);

    int insert(Selectedcourse record);

    int insertSelective(Selectedcourse record);

    List<Selectedcourse> selectByExample(SelectedcourseExample example);

    int updateByExampleSelective(@Param("record") Selectedcourse record, @Param("example") SelectedcourseExample example);

    int updateByExample(@Param("record") Selectedcourse record, @Param("example") SelectedcourseExample example);
    
    int deleteByID(Integer id);
    
    Selectedcourse selectById(Integer id);
    
    List<Selectedcourse> selectByCourseIDAndSession(CourseCustom courseCustom);
}