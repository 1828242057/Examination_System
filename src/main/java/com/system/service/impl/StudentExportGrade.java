package com.system.service.impl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.system.po.ExcelBean;
import com.system.po.Scores;
import com.system.po.SelectedCourseCustom;
import com.system.po.StudentCustom;
import com.system.service.CourseService;
import com.system.service.ExcelInfo;
import com.system.service.ScoresService;
import com.system.service.StudentService;
import com.system.util.ExcelUtil;

@Service
public class StudentExportGrade implements ExcelInfo{   //学生导出课程信息  未实现 缺少数据库支持
	
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
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());

        List<Map<String,Object>> grades=new ArrayList<Map<String,Object>>();
        if(studentCustom!=null) {
        	List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();
        	Map<String,Object> gradeMap;
        	Scores scores;
        	for(SelectedCourseCustom sc:list) {
        		if(sc.getMark()!=null && !sc.getPassed().equals(0)) {
        			gradeMap = new LinkedHashMap<>();
        			scores=scoresService.findByID(sc.getId());
        			gradeMap.put("courseid", sc.getCourseid());
        			gradeMap.put("coursename", sc.getCouseCustom().getCoursename());
        			gradeMap.put("teachername", sc.getCouseCustom().getTeachername());
        			gradeMap.put("coursetype", sc.getCouseCustom().getCoursetype());
        			gradeMap.put("score", sc.getCouseCustom().getScore());
        			gradeMap.put("boardscores", scores.getBoardscores());
        			gradeMap.put("homeworkscores", scores.getHomeworkscores());
        			gradeMap.put("attendancescores", scores.getAttendancescores());
        			gradeMap.put("experimentalscores", scores.getExperimentalscores());
        			gradeMap.put("mark", sc.getMark());
        			grades.add(gradeMap);
        		}
        	}
        }
        
		List<ExcelBean> excel = new ArrayList<>();
		Map<Integer,List<ExcelBean>> map = new LinkedHashMap<>();
		//设置标题栏
		excel.add(new ExcelBean("课程号","courseid",0));
		excel.add(new ExcelBean("课程名称","coursename",0));
		excel.add(new ExcelBean("授课老师", "teachername", 0));
		excel.add(new ExcelBean("课程类型","coursetype",0));
		excel.add(new ExcelBean("学分","score",0));
		excel.add(new ExcelBean("考试成绩","boardscores", 0));
		excel.add(new ExcelBean("作业成绩","homeworkscores",0));
		excel.add(new ExcelBean("出勤成绩","attendancescores",0));
		excel.add(new ExcelBean("实验成绩","experimentalscores",0));
		excel.add(new ExcelBean("总成绩","mark",0));
		map.put(0,excel);
		String sheetName = studentCustom.getUsername() + "的成绩表";
		//调用ExcelUtil方法
		XSSFWorkbook xssfWorkbook = ExcelUtil.createExcelFile(null, grades, map, sheetName);
		return xssfWorkbook;
	}
}
