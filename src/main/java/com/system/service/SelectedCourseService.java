package com.system.service;

import com.system.po.CourseCustom;
import com.system.po.SelectedCourseCustom;

import java.util.List;

/**
 * 选课表servic层
 */
public interface SelectedCourseService {

    //根据课程ID查询课程
    List<SelectedCourseCustom> findByCourseID(Integer id) throws Exception;
    
    //查询课程的当前届选课列表
    List<SelectedCourseCustom> findPresentSessionCourse(CourseCustom courseCustom) throws Exception;

    //根据课程id分页查询课程
    List<SelectedCourseCustom> findByCourseIDPaging(Integer page, Integer id) throws Exception;
    
    //根据选课id查询选课
    SelectedCourseCustom findById(Integer id) throws Exception;

    //获取该课程学生数
    Integer countByCourseID(Integer id) throws Exception;

    //查询指定学生成绩
    SelectedCourseCustom findOne(SelectedCourseCustom selectedCourseCustom) throws Exception;

    //打分
    void updataOne(SelectedCourseCustom selectedCourseCustom) throws Exception;

    //选课
    void save(SelectedCourseCustom selectedCourseCustom) throws Exception;

    //根据学生id查找课程
    List<SelectedCourseCustom> findByStudentID(Integer id) throws Exception;

    //退课
    void remove(Integer id) throws Exception;



}
