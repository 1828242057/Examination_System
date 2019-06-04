package com.system.service.impl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.system.po.CourseCustom;
import com.system.po.ExcelBean;
import com.system.service.CourseService;
import com.system.service.ExcelInfo;
import com.system.service.ScoresService;
import com.system.service.StudentService;
import com.system.util.ExcelUtil;

@Service
public class AdminExportCourse implements ExcelInfo{
	@Resource(name = "courseServiceImpl")
    private CourseService courseService;
	@Resource(name = "studentServiceImpl")
    private StudentService studentService;
	@Resource(name = "scoresServiceImpl")
    private ScoresService scoresService;
	
	@Override
	public XSSFWorkbook exportExcelInfoWithId(Integer id) throws Exception{
		return null;
	}
	
	@Override
	public XSSFWorkbook exportExcelInfo() throws Exception{
		//根据条件查询数据
        List<Map<String,Object>> courses=new ArrayList<Map<String,Object>>();
        List<CourseCustom> list = courseService.findAll();
        Map<String,Object> courseMap;
        for(CourseCustom cc:list) {
        	courseMap = new LinkedHashMap<>();
        	courseMap.put("courseID", cc.getCourseid());
        	courseMap.put("courseName", cc.getCoursename());
        	courseMap.put("teacherName", cc.getTeachername());
        	courseMap.put("courseTime", cc.getCoursetime());
        	courseMap.put("classRoom", cc.getClassroom());
        	courseMap.put("courseWeek", cc.getCourseweek());
        	courseMap.put("courseType", cc.getCoursetype());
        	courseMap.put("college", cc.getcollegeName());
        	courseMap.put("score", cc.getScore());
        	courseMap.put("boardScores", cc.getBoardscores());
        	courseMap.put("homeworkScores", cc.getHomeworkscores());
        	courseMap.put("attendanceScores", cc.getAttendancescores());
        	courseMap.put("experimentalScores", cc.getExperimentalscores());
        	courses.add(courseMap);
        }
        
		List<ExcelBean> excel = new ArrayList<>();
		Map<Integer,List<ExcelBean>> map = new LinkedHashMap<>();
		//设置标题栏
		excel.add(new ExcelBean("课程号","courseID",0));
		excel.add(new ExcelBean("课程名","courseName",0));
		excel.add(new ExcelBean("授课教师", "teacherName", 0));
		excel.add(new ExcelBean("上课时间","courseTime",0));
		excel.add(new ExcelBean("上课教室","classRoom",0));
		excel.add(new ExcelBean("周数","courseWeek", 0));
		excel.add(new ExcelBean("课程类型","courseType",0));
		excel.add(new ExcelBean("学院","college",0));
		excel.add(new ExcelBean("学分", "score", 0));
		excel.add(new ExcelBean("考试成绩","boardScores",0));
		excel.add(new ExcelBean("作业成绩","homeworkScores",0));
		excel.add(new ExcelBean("出勤成绩","attendanceScores", 0));
		excel.add(new ExcelBean("实验成绩","experimentalScores", 0));
		map.put(0,excel);
		String sheetName =  "课程花名册";
		//调用ExcelUtil方法
		XSSFWorkbook xssfWorkbook = ExcelUtil.createExcelFile(null, courses, map, sheetName);
		return xssfWorkbook;
	}
}
