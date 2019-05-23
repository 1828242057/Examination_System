package com.system.controller;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.system.po.StudentCustom;
import com.system.po.TeacherCustom;
import com.system.service.StudentService;
import com.system.service.TeacherService;
import com.system.service.impl.StudentServiceImpl;
import com.system.service.impl.TeacherServiceImpl;

@Controller
public class UploadController {
  
	@Resource(name = "studentServiceImpl")
    private StudentService studentService;
	
	@Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;
	
 @RequestMapping(value = "/stuUpload", produces = "text/html;charset=UTF-8")
	public String StuUpload(@RequestParam("file") MultipartFile file, HttpSession session) {
 
		// 错误信息  （可以定义一个结果类用于保存信息-错误个数，正确个数，错误信息等）
		ArrayList<String> errors = new ArrayList<String>();
        
 
		try {
			if (!file.isEmpty() && file.getOriginalFilename().endsWith("xls")) {
				// poi--exl解析
				InputStream is = file.getInputStream();
				HSSFWorkbook hssf = new HSSFWorkbook(is);
                //Sheet1-----是Excel表格左下方的名称
				HSSFSheet sheet = hssf.getSheet("Sheet1");
 
				DateFormat format1 = new SimpleDateFormat("dd/MM/yy");  
                //读取Excel表格从下标0开始（表头开始）
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    
					HSSFCell userid = sheet.getRow(i).getCell(0);
					HSSFCell username = sheet.getRow(i).getCell(1);
					HSSFCell sex = sheet.getRow(i).getCell(2);
					HSSFCell birthyear= sheet.getRow(i).getCell(3);
					HSSFCell grade = sheet.getRow(i).getCell(4);
					HSSFCell collegeid = sheet.getRow(i).getCell(5);
					
 
					if (userid == null) {
						errors.add("错误行："+(i+1)+",错误信息:学工号----不能为空！");
						continue;
					}
					if (username == null) {
						errors.add("错误行："+(i+1)+",错误信息:名字----不能为空");
						continue;
					}
					if (sex == null) {
						errors.add("错误行："+(i+1)+",错误信息:性别----不能为空");
						continue;
					}
					if (birthyear == null ) {
						errors.add("错误行："+(i+1)+",错误信息:出生日期----不能为空");
						continue;
					}
				
					if (grade == null ) {
						errors.add("错误行："+(i+1)+",错误信息:年级----不能为空");
						continue;
					}
					if (collegeid == null ) {
						errors.add("错误行："+(i+1)+",错误信息:专业id----不能为空");
						continue;
					}
					
					
					
					
					@SuppressWarnings("deprecation")
					StudentCustom t = new StudentCustom();
					t.setUserid(Integer.valueOf(userid.toString()));
					t.setUsername( username.toString());
					t.setSex(sex.toString());
					t.setBirthyear(format1.parse(birthyear.toString()));
					t.setGrade(format1.parse(grade.toString()));
					t.setCollegeid(Integer.valueOf(collegeid.toString()));
					
					if(!studentService.save(t))
						errors.add("插入失败");
						//获得插入时的路线id
					
				}
				is.close();
			}
          
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("errors", errors);
		return "/stuUpdate";
	}
	

 
 @RequestMapping(value = "/teaUpload", produces = "text/html;charset=UTF-8")
	public String TeaUpload(@RequestParam("file") MultipartFile file, HttpSession session) {
 
		// 错误信息  （可以定义一个结果类用于保存信息-错误个数，正确个数，错误信息等）
		ArrayList<String> errors = new ArrayList<String>();
        
 
		try {
			if (!file.isEmpty() && file.getOriginalFilename().endsWith("xls")) {
				// poi--exl解析
				InputStream is = file.getInputStream();
				HSSFWorkbook hssf = new HSSFWorkbook(is);
                //Sheet1-----是Excel表格左下方的名称
				HSSFSheet sheet = hssf.getSheet("Sheet1");
 
				DateFormat format1 = new SimpleDateFormat("dd/MM/yy");  
                //读取Excel表格从下标0开始（表头开始）
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    
					HSSFCell userid = sheet.getRow(i).getCell(0);
					HSSFCell username = sheet.getRow(i).getCell(1);
					HSSFCell sex = sheet.getRow(i).getCell(2);
					HSSFCell birthyear= sheet.getRow(i).getCell(3);
					HSSFCell degree = sheet.getRow(i).getCell(4);
					HSSFCell title = sheet.getRow(i).getCell(5);
					HSSFCell grade = sheet.getRow(i).getCell(6);
					HSSFCell collegeid= sheet.getRow(i).getCell(7);
					
 
					if (userid == null) {
						errors.add("错误行："+(i+1)+",错误信息:学工号----不能为空！");
						continue;
					}
					if (username == null) {
						errors.add("错误行："+(i+1)+",错误信息:名字----不能为空");
						continue;
					}
					if (sex == null) {
						errors.add("错误行："+(i+1)+",错误信息:性别----不能为空");
						continue;
					}
					if (birthyear == null ) {
						errors.add("错误行："+(i+1)+",错误信息:出生日期----不能为空");
						continue;
					}
				
					if (degree == null ) {
						errors.add("错误行："+(i+1)+",错误信息:学位----不能为空");
						continue;
					}
					if (grade == null ) {
						errors.add("错误行："+(i+1)+",错误信息:毕业日期----不能为空");
						continue;
					}
					if (title== null ) {
						errors.add("错误行："+(i+1)+",错误信息:职称----不能为空");
						continue;
					}
					if (collegeid == null ) {
						errors.add("错误行："+(i+1)+",错误信息:专业id----不能为空");
						continue;
					}
					
					
					
					
					@SuppressWarnings("deprecation")
					TeacherCustom t = new TeacherCustom();
					t.setUserid(Integer.valueOf(userid.toString()));
					t.setUsername( username.toString());
					t.setSex(sex.toString());
					t.setBirthyear(format1.parse(birthyear.toString()));
					t.setDegree(degree.toString());
					t.setTitle(title.toString());
					t.setGrade(format1.parse(grade.toString()));
					t.setCollegeid(Integer.valueOf(collegeid.toString()));
					
					if(!teacherService.save(t))
						errors.add("插入失败");
						//获得插入时的路线id
					
				}
				is.close();
			}
          
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("errors", errors);
		return "/teaUpdate";
	}
}
