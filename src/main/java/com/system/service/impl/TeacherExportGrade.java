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
import com.system.po.Scores;
import com.system.po.SelectedCourseCustom;
import com.system.service.CourseService;
import com.system.service.ExcelInfo;
import com.system.service.ScoresService;
import com.system.service.SelectedCourseService;
import com.system.service.StudentService;
import com.system.util.ExcelUtil;

@Service
public class TeacherExportGrade implements ExcelInfo{
	@Resource(name = "courseServiceImpl")
    private CourseService courseService;
	@Resource(name = "studentServiceImpl")
    private StudentService studentService;
	@Resource(name = "scoresServiceImpl")
    private ScoresService scoresService;
	@Resource(name = "selectedCourseServiceImpl")
	private SelectedCourseService selectedCourseService;
	
	@Override
	public XSSFWorkbook exportExcelInfoWithId(Integer id) throws Exception{

		List<Map<String,Object>> grades=new ArrayList<Map<String,Object>>();
		List<SelectedCourseCustom> list = selectedCourseService.findByCourseID(id);
		Map<String,Object> gradeMap;
        Scores scores;
        for(SelectedCourseCustom scc:list) {
        	if(!scc.getPassed().equals(0)) {
        		gradeMap = new LinkedHashMap<>();
        		scores=scoresService.findByID(scc.getId());
        		gradeMap.put("studentid", scc.getStudentid());
        		gradeMap.put("username", scc.getStudentCustom().getUsername());
        		gradeMap.put("boardscores", scores.getBoardscores());
        		gradeMap.put("homeworkscores", scores.getHomeworkscores());
        		gradeMap.put("attendancescores", scores.getAttendancescores());
        		gradeMap.put("experimentalscores", scores.getExperimentalscores());
        		gradeMap.put("mark", scc.getMark());
        		grades.add(gradeMap);
        	}
        }
        
		List<ExcelBean> excel = new ArrayList<>();
		Map<Integer,List<ExcelBean>> map = new LinkedHashMap<>();
		//设置标题栏
		excel.add(new ExcelBean("学号","studentid",0));
		excel.add(new ExcelBean("姓名","username",0));
		excel.add(new ExcelBean("考试成绩", "boardscores", 0));
		excel.add(new ExcelBean("作业成绩","homeworkscores",0));
		excel.add(new ExcelBean("出勤成绩","attendancescores",0));
		excel.add(new ExcelBean("实验成绩","experimentalscores", 0));
		excel.add(new ExcelBean("总成绩","mark",0));
		map.put(0,excel);
		CourseCustom courseCustom=courseService.findById(id);
		String sheetName = courseCustom.getCoursename();
		//调用ExcelUtil方法
		XSSFWorkbook xssfWorkbook = ExcelUtil.createExcelFile(null, grades, map, sheetName);
		return xssfWorkbook;
	}
	
	@Override
	public XSSFWorkbook exportExcelInfo() throws Exception{
		return null;
	}
}
