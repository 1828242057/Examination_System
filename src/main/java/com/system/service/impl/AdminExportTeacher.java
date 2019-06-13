package com.system.service.impl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.system.po.ExcelBean;
import com.system.po.TeacherCustom;
import com.system.service.CourseService;
import com.system.service.ExcelInfo;
import com.system.service.ScoresService;
import com.system.service.StudentService;
import com.system.service.TeacherService;
import com.system.util.ExcelUtil;

@Service
public class AdminExportTeacher implements ExcelInfo{
	@Resource(name = "courseServiceImpl")
    private CourseService courseService;
	@Resource(name = "studentServiceImpl")
    private StudentService studentService;
	@Resource(name = "scoresServiceImpl")
    private ScoresService scoresService;
	@Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;
	
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
        List<Map<String,Object>> teachers=new ArrayList<Map<String,Object>>();
        List<TeacherCustom> list = teacherService.findAll();
        Map<String,Object> teacherMap;
        for(TeacherCustom tc:list) {
        	teacherMap = new LinkedHashMap<>();
        	teacherMap.put("userID", tc.getUserid());
        	teacherMap.put("userName", tc.getUsername());
        	teacherMap.put("sex", tc.getSex());
        	teacherMap.put("birthYear", tc.getBirthyear());
        	teacherMap.put("degree", tc.getDegree());
        	teacherMap.put("title", tc.getTitle());
        	teacherMap.put("grade", tc.getGrade());
        	teacherMap.put("college", tc.getcollegeName());
        	teachers.add(teacherMap);
        }
        
		List<ExcelBean> excel = new ArrayList<>();
		Map<Integer,List<ExcelBean>> map = new LinkedHashMap<>();
		//设置标题栏
		excel.add(new ExcelBean("教工号","userID",0));
		excel.add(new ExcelBean("姓名","userName",0));
		excel.add(new ExcelBean("性别", "sex", 0));
		excel.add(new ExcelBean("生日","birthYear",0));
		excel.add(new ExcelBean("学位","degree",0));
		excel.add(new ExcelBean("职称","title", 0));
		excel.add(new ExcelBean("入职时间","grade",0));
		excel.add(new ExcelBean("学院","college",0));
		map.put(0,excel);
		String sheetName =  "教师花名册";
		//调用ExcelUtil方法
		XSSFWorkbook xssfWorkbook = ExcelUtil.createExcelFile(null, teachers, map, sheetName);
		return xssfWorkbook;
	}
}
