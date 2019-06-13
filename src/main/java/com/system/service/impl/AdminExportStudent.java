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
public class AdminExportStudent implements ExcelInfo{
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
	public XSSFWorkbook exportExcelInfoWithIdAndSession(Integer id, Integer session) throws Exception{
		return null;
	}
	
	@Override
	public XSSFWorkbook exportExcelInfo() throws Exception{
		
		//根据条件查询数据
        List<Map<String,Object>> students=new ArrayList<Map<String,Object>>();
        List<StudentCustom> list = studentService.findAll();
        Map<String,Object> studentMap;
        for(StudentCustom sc:list) {
        	studentMap = new LinkedHashMap<>();
        	studentMap.put("userID", sc.getUserid());
        	studentMap.put("userName", sc.getUsername());
        	studentMap.put("sex", sc.getSex());
        	studentMap.put("birthYear", sc.getBirthyear());
        	studentMap.put("grade", sc.getGrade());
        	studentMap.put("college", sc.getcollegeName());
        	students.add(studentMap);
        }
        
		List<ExcelBean> excel = new ArrayList<>();
		Map<Integer,List<ExcelBean>> map = new LinkedHashMap<>();
		//设置标题栏
		excel.add(new ExcelBean("学生号","userID",0));
		excel.add(new ExcelBean("姓名","userName",0));
		excel.add(new ExcelBean("性别", "sex", 0));
		excel.add(new ExcelBean("生日","birthYear",0));
		excel.add(new ExcelBean("入学时间","grade",0));
		excel.add(new ExcelBean("学院","college", 0));
		map.put(0,excel);
		String sheetName =  "学生花名册";
		//调用ExcelUtil方法
		XSSFWorkbook xssfWorkbook = ExcelUtil.createExcelFile(null, students, map, sheetName);
		return xssfWorkbook;
	}
}
