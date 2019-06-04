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

import com.system.service.ExcelInfo;

@Controller
public class ExportExcelController {
	@Resource(name = "studentExportGrade")
	private ExcelInfo excelInfo;
	
	@RequestMapping("exportStudentGrade")
	public String export(HttpServletRequest request,HttpServletResponse response) throws Exception{
	        response.reset(); //清除buffer缓存  
	        //Map<String,Object> map=new HashMap<String,Object>();  
	        // 指定下载的文件名  
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
	        Subject subject = SecurityUtils.getSubject();
	        response.setHeader("Content-Disposition","attachment;filename="+new String("学生成绩表.xls".getBytes(),"iso-8859-1"));
	        //导出Excel对象  
	        XSSFWorkbook workbook = excelInfo.exportExcelInfo();
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

}
