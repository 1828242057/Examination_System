package com.system.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.system.service.ExcelInfo;

@Controller
public class ExportExcelController {
	@Resource(name = "studentExportGrade")
	private ExcelInfo studentExportGrade;
	
	@Resource(name = "adminExportCourse")
	private ExcelInfo adminExportCourse;
	
	@Resource(name = "adminExportTeacher")
	private ExcelInfo adminExportTeacher;
	
	@Resource(name = "adminExportStudent")
	private ExcelInfo adminExportStudent;
	
	@Resource(name = "teacherExportGrade")
	private ExcelInfo teacherExportGrade;
	
	@Resource(name = "teacherExportCourseStatistics")
	private ExcelInfo teacherExportCourseStatistics;
	
	@RequestMapping("exportStudentGrade")
	public String exportStudentGrade(HttpServletRequest request,HttpServletResponse response) throws Exception{
	        response.reset(); //清除buffer缓存  
	        //Map<String,Object> map=new HashMap<String,Object>();  
	        // 指定下载的文件名  
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
	        Subject subject = SecurityUtils.getSubject();
	        response.setHeader("Content-Disposition","attachment;filename="+new String("学生成绩表.xls".getBytes(),"iso-8859-1"));
	        //导出Excel对象  
	        XSSFWorkbook workbook = studentExportGrade.exportExcelInfo();
	        OutputStream output;  
	        try {  
	            output = response.getOutputStream(); 
	            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);  
	            bufferedOutput.flush();  
	            workbook.write(bufferedOutput);  
	            bufferedOutput.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }
	        return "redirect:/student/overCourse";
	}
	
	@RequestMapping("exportCourse")
	public String exportCourse(HttpServletRequest request,HttpServletResponse response) throws Exception{
	        response.reset(); //清除buffer缓存  
	        //Map<String,Object> map=new HashMap<String,Object>();  
	        // 指定下载的文件名  
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
	        response.setHeader("Content-Disposition","attachment;filename="+new String("课程花名册.xls".getBytes(),"iso-8859-1"));
	        //导出Excel对象  
	        XSSFWorkbook workbook = adminExportCourse.exportExcelInfo();
	        OutputStream output;  
	        try {  
	            output = response.getOutputStream(); 
	            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);  
	            bufferedOutput.flush();  
	            workbook.write(bufferedOutput);  
	            bufferedOutput.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }
	        return "redirect:/admin/showCourse";
	}
	
	@RequestMapping("exportTeacher")
	public String exportTeacher(HttpServletRequest request,HttpServletResponse response) throws Exception{
	        response.reset(); //清除buffer缓存  
	        //Map<String,Object> map=new HashMap<String,Object>();  
	        // 指定下载的文件名  
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
	        response.setHeader("Content-Disposition","attachment;filename="+new String("教师花名册.xls".getBytes(),"iso-8859-1"));
	        //导出Excel对象  
	        XSSFWorkbook workbook = adminExportTeacher.exportExcelInfo();
	        OutputStream output;  
	        try {  
	            output = response.getOutputStream(); 
	            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);  
	            bufferedOutput.flush();  
	            workbook.write(bufferedOutput);  
	            bufferedOutput.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }
	        return "redirect:/admin/showTeacher";
	}
	
	@RequestMapping("exportStudent")
	public String exportStudent(HttpServletRequest request,HttpServletResponse response) throws Exception{
	        response.reset(); //清除buffer缓存  
	        //Map<String,Object> map=new HashMap<String,Object>();  
	        // 指定下载的文件名  
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
	        response.setHeader("Content-Disposition","attachment;filename="+new String("学生花名册.xls".getBytes(),"iso-8859-1"));
	        //导出Excel对象  
	        XSSFWorkbook workbook = adminExportStudent.exportExcelInfo();
	        OutputStream output;  
	        try {  
	            output = response.getOutputStream(); 
	            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);  
	            bufferedOutput.flush();  
	            workbook.write(bufferedOutput);  
	            bufferedOutput.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }
	        return "redirect:/admin/showStudent";
	}
	
	@RequestMapping(value = "/exportCourseGrade" ,method = {RequestMethod.GET})
	public String exportCourseGrade(Integer courseid,HttpServletRequest request,HttpServletResponse response) throws Exception{
	        response.reset(); //清除buffer缓存  
	        //Map<String,Object> map=new HashMap<String,Object>();  
	        // 指定下载的文件名  
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
	        response.setHeader("Content-Disposition","attachment;filename="+new String("课程成绩单.xls".getBytes(),"iso-8859-1"));
	        //导出Excel对象  
	        XSSFWorkbook workbook = teacherExportGrade.exportExcelInfoWithId(courseid);
	        OutputStream output;  
	        try {  
	            output = response.getOutputStream(); 
	            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);  
	            bufferedOutput.flush();  
	            workbook.write(bufferedOutput);  
	            bufferedOutput.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }
	        return "redirect:/teacher/gradeCourse?id="+courseid;
	}
	
	@RequestMapping(value = "/exportCourseStatistics" ,method = {RequestMethod.GET})
	public String exportCourseStatistics(Integer teacherid,HttpServletRequest request,HttpServletResponse response) throws Exception{
	        response.reset(); //清除buffer缓存  
	        //Map<String,Object> map=new HashMap<String,Object>();  
	        // 指定下载的文件名  
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
	        response.setHeader("Content-Disposition","attachment;filename="+new String("课程成绩统计单.xls".getBytes(),"iso-8859-1"));
	        //导出Excel对象  
	        XSSFWorkbook workbook = teacherExportCourseStatistics.exportExcelInfoWithId(teacherid);
	        OutputStream output;  
	        try {  
	            output = response.getOutputStream(); 
	            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);  
	            bufferedOutput.flush();  
	            workbook.write(bufferedOutput);  
	            bufferedOutput.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }
	        return "redirect:/teacher/courseStatistics";
	}

}
