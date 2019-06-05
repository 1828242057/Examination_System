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
import com.system.po.TeacherCustom;
import com.system.service.CourseService;
import com.system.service.ExcelInfo;
import com.system.service.ScoresService;
import com.system.service.SelectedCourseService;
import com.system.service.StudentService;
import com.system.service.TeacherService;
import com.system.util.ExcelUtil;

@Service
public class TeacherExportCourseStatistics implements ExcelInfo{
	@Resource(name = "courseServiceImpl")
    private CourseService courseService;
	@Resource(name = "studentServiceImpl")
    private StudentService studentService;
	@Resource(name = "scoresServiceImpl")
    private ScoresService scoresService;
	@Resource(name = "selectedCourseServiceImpl")
	private SelectedCourseService selectedCourseService;
	@Resource(name = "teacherServiceImpl")
	private TeacherService teacherService;
	
	@Override
	public XSSFWorkbook exportExcelInfoWithId(Integer id) throws Exception{

		List<Map<String,Object>> courseStatistics=new ArrayList<Map<String,Object>>();
		List<CourseCustom> courseList = courseService.findByTeacherID(id);
		Map<String,Object> courseStatisticsMap;
        Scores scores;
        List<SelectedCourseCustom> selectedCourselist;
        Integer range1,range2,range3,range4,range5,range6,range7;
        Integer good,excellent,total;
        for(CourseCustom cc:courseList) {
        	range1=range2=range3=range4=range5=range6=range7=good=excellent=total=0;
        	selectedCourselist = selectedCourseService.findByCourseID(cc.getCourseid());
        	for(SelectedCourseCustom scc:selectedCourselist) {
        		total++;
        		if(scc.getMark()<20)range1++;
        		else if(scc.getMark()<40)range2++;
        		else if(scc.getMark()<60)range3++;
        		else if(scc.getMark()<70)range4++;
        		else if(scc.getMark()<80){range5++;good++;}
        		else if(scc.getMark()<90){range6++;if(scc.getMark()<85)good++;else excellent++;}
        		else {range7++;excellent++;}
        	}
        	courseStatisticsMap = new LinkedHashMap<>();
        	courseStatisticsMap.put("courseName", cc.getCoursename());
        	courseStatisticsMap.put("range1", range1);
        	courseStatisticsMap.put("range2", range2);
        	courseStatisticsMap.put("range3", range3);
        	courseStatisticsMap.put("range4", range4);
        	courseStatisticsMap.put("range5", range5);
        	courseStatisticsMap.put("range6", range6);
        	courseStatisticsMap.put("range7", range7);
        	courseStatisticsMap.put("fail", range1+range2+range3);
        	courseStatisticsMap.put("pass", range4+range5+range6+range7);
        	courseStatisticsMap.put("good", good);
        	courseStatisticsMap.put("excellent", excellent);
        	courseStatisticsMap.put("total", total);
        	courseStatisticsMap.put("passRate", (range4+range5+range6+range7)/total.doubleValue());
        	courseStatisticsMap.put("excellentRate", excellent/total.doubleValue());
			courseStatistics.add(courseStatisticsMap);
        }
        
		List<ExcelBean> excel = new ArrayList<>();
		Map<Integer,List<ExcelBean>> map = new LinkedHashMap<>();
		//设置标题栏
		excel.add(new ExcelBean("课程名","courseName",0));
		excel.add(new ExcelBean("0~19分","range1",0));
		excel.add(new ExcelBean("20~39分", "range2", 0));
		excel.add(new ExcelBean("40~59分","range3",0));
		excel.add(new ExcelBean("60~69分","range4",0));
		excel.add(new ExcelBean("70~79分","range5",0));
		excel.add(new ExcelBean("80~89分","range6",0));
		excel.add(new ExcelBean("90~100分","range7",0));
		excel.add(new ExcelBean("不及格(<60)","fail",0));
		excel.add(new ExcelBean("及格(60~69)","pass",0));
		excel.add(new ExcelBean("良好(70~84)","good",0));
		excel.add(new ExcelBean("优秀(>=85)","excellent",0));
		excel.add(new ExcelBean("总人数","total",0));
		excel.add(new ExcelBean("及格率","passRate",0));
		excel.add(new ExcelBean("优秀率","excellentRate",0));
		map.put(0,excel);
		TeacherCustom teacherCustom=teacherService.findById(id);
		String sheetName = teacherCustom.getUsername()+"的所有课程统计分析表";
		//调用ExcelUtil方法
		XSSFWorkbook xssfWorkbook = ExcelUtil.createExcelFile(null, courseStatistics, map, sheetName);
		return xssfWorkbook;
	}
	
	@Override
	public XSSFWorkbook exportExcelInfo() throws Exception{
		return null;
	}
}
