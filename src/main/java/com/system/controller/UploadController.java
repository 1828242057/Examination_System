	package com.system.controller;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.system.exception.CustomException;
import com.system.po.CourseCustom;
import com.system.po.Scores;
import com.system.po.SelectedCourseCustom;
import com.system.po.StudentCustom;
import com.system.po.TeacherCustom;
import com.system.po.Userlogin;
import com.system.service.CollegeService;
import com.system.service.CourseService;
import com.system.service.ScoresService;
import com.system.service.SelectedCourseService;
import com.system.service.StudentService;
import com.system.service.TeacherService;
import com.system.service.UserloginService;
import com.system.service.impl.StudentServiceImpl;
import com.system.service.impl.TeacherServiceImpl;

@Controller
public class UploadController {
  
	@Resource(name = "studentServiceImpl")
    private StudentService studentService;
	
	@Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;
	
	@Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;
	
	@Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "collegeServiceImpl")
    private CollegeService collegeService;
    
    @Resource(name = "selectedCourseServiceImpl")
    private SelectedCourseService selectedCourseService;
    
    @Resource(name = "scoresServiceImpl")
    private ScoresService scoresService;
	
    
	@RequestMapping(value = "/stuUpload", method = {RequestMethod.POST},produces = "text/html;charset=UTF-8")
	public String StuUpload(@RequestParam("file") MultipartFile file,Model model) throws Exception {

		int i=0;
		String fileName=file.getOriginalFilename();
		String successMessage="",failMessage="";
		InputStream is=null;
		try {
			if(file.isEmpty()) {
			failMessage="传输终止！ 原因：文件为空！";
			}
			else if(!file.getOriginalFilename().endsWith("xls")) {
				failMessage="传输终止！ 原因：文件不是.xls类型！";
			}
			else {
				// poi--exl解析
				HSSFWorkbook hssf=null;
				HSSFSheet sheet=null;
				try{
					is = file.getInputStream();
					hssf = new HSSFWorkbook(is);
					//Sheet1-----是Excel表格左下方的名称
					sheet = hssf.getSheet("Sheet1");
				}
				catch(Exception e){
					failMessage="传输终止！ 原因：文件损坏！";
				}
		         
				if(failMessage.equals("")) {
					DateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");  
					NumberFormat nf = NumberFormat.getInstance();
					//读取Excel表格从下标0开始（表头开始）
					for (i=1; i <= sheet.getLastRowNum(); i++) {
		                    
					HSSFCell userid = sheet.getRow(i).getCell(0);
					HSSFCell username = sheet.getRow(i).getCell(1);
					HSSFCell sex = sheet.getRow(i).getCell(2);
					HSSFCell birthyear= sheet.getRow(i).getCell(3);
					HSSFCell grade = sheet.getRow(i).getCell(4);
					HSSFCell collegeid = sheet.getRow(i).getCell(5);
							
					//这里发现从Excel文件中读取的整数会带.0所以要去掉
					String s = nf.format(userid.getNumericCellValue());
					if (s.indexOf(",") >= 0) {
						s = s.replace(",", "");
					}
					StudentCustom t = new StudentCustom();
					t.setUserid(Integer.valueOf(s));
					t.setUsername( username.toString());
					t.setSex(sex.toString());
					t.setBirthyear(format1.parse(birthyear.toString()));
					t.setGrade(format1.parse(grade.toString()));
					String s1 = nf.format(collegeid.getNumericCellValue());
					if (s1.indexOf(",") >= 0) {
						s1 = s1.replace(",", "");
					}
					t.setCollegeid(Integer.valueOf(s1));
					
					if(!t.getSex().equals("男") && !t.getSex().equals("女")) throw new Exception();
							
					if(!studentService.save(t)) throw new Exception();
					
					//获得插入时的路线id
					//添加成功后，也添加到登录表
					Userlogin userlogin = new Userlogin();
					userlogin.setUsername(t.getUserid().toString());
					userlogin.setPassword("123");
					userlogin.setRole(2);
					userloginService.save(userlogin);
					}
					is.close();
				}
			}          
		} 
		catch (Exception e) {
			failMessage="传输终止！ 原因：第"+i+"行数据产生错误，可能是不规范数据，或者有重复学号！";
			is.close();
		}
				
		if(i!=0)
			successMessage="成功载入"+(i-1)+"条学生信息";

		model.addAttribute("failMessage", failMessage);
		model.addAttribute("successMessage", successMessage);
		model.addAttribute("fileName",fileName);
			
		return "/admin/stuUpload";
		
	}
	
	
	@RequestMapping(value = "/teaUpload",method = {RequestMethod.POST}, produces = "text/html;charset=UTF-8")
	public String TeaUpload(@RequestParam("file") MultipartFile file, Model model) throws Exception {
 
		int i=0;
		String fileName=file.getOriginalFilename();
		String successMessage="",failMessage="";
        
 
		if(file.isEmpty()) {
			failMessage="传输终止！ 原因：文件为空！";
		}
		else if(!file.getOriginalFilename().endsWith("xls")) {
			failMessage="传输终止！ 原因：文件不是.xls类型！";
		}
		else {
			// poi--exl解析
			InputStream is=null;
			HSSFWorkbook hssf=null;
			HSSFSheet sheet=null;
			try{
				is = file.getInputStream();
				hssf = new HSSFWorkbook(is);
				//Sheet1-----是Excel表格左下方的名称
				sheet = hssf.getSheet("Sheet1");
			}
			catch(Exception e){
				failMessage="传输终止！ 原因：文件损坏！";
			}

               //读取Excel表格从下标0开始（表头开始）
			if(failMessage.equals("")) {
				DateFormat format1 = new SimpleDateFormat("dd/MM/yyyy"); 
				NumberFormat nf = NumberFormat.getInstance();
				try {
					for (i = 1; i <= sheet.getLastRowNum(); i++) {
                    
						HSSFCell userid = sheet.getRow(i).getCell(0);
						HSSFCell username = sheet.getRow(i).getCell(1);
						HSSFCell sex = sheet.getRow(i).getCell(2);
						HSSFCell birthyear= sheet.getRow(i).getCell(3);
						HSSFCell degree = sheet.getRow(i).getCell(4);
						HSSFCell title = sheet.getRow(i).getCell(5);
						HSSFCell grade = sheet.getRow(i).getCell(6);
						HSSFCell collegeid= sheet.getRow(i).getCell(7);			
					
						String s = nf.format(userid.getNumericCellValue());
						if (s.indexOf(",") >= 0) {
							s = s.replace(",", "");
						}
						TeacherCustom t = new TeacherCustom();
						t.setUserid(Integer.valueOf(s));
						t.setUsername( username.toString());
						t.setSex(sex.toString());
						t.setBirthyear(format1.parse(birthyear.toString()));
						t.setDegree(degree.toString());
						t.setTitle(title.toString());
						t.setGrade(format1.parse(grade.toString()));
						String s1 = nf.format(collegeid.getNumericCellValue());
						if (s1.indexOf(",") >= 0) {
							s1 = s1.replace(",", "");
						}
						t.setCollegeid(Integer.valueOf(s1));
						
						if(!t.getSex().equals("男") && !t.getSex().equals("女")) throw new Exception();
						
						if(!t.getDegree().equals("本科") && !t.getDegree().equals("硕士") && !t.getDegree().equals("博士")) throw new Exception();
					
						if(!t.getTitle().equals("普通教师") && !t.getTitle().equals("助教") && !t.getTitle().equals("讲师") && !t.getTitle().equals("副教授") && !t.getTitle().equals("教授")) throw new Exception();
					
						if(!teacherService.save(t))
							throw new Exception();
						
						//获得插入时的路线id
						//添加成功后，也添加到登录表
						Userlogin userlogin = new Userlogin();
						userlogin.setUsername(t.getUserid().toString());
						userlogin.setPassword("123");
						userlogin.setRole(1);
						userloginService.save(userlogin);
					}
				}
				catch (Exception e) {
					failMessage="传输终止！ 原因：第"+i+"行数据产生错误，可能是不规范数据，或者教工号重复。</p><div class=\"col-sm-1\"></div><p style=\"color:red;\">注意：学历只能是本科、硕士或者博士！职称只能是普通教师、助教、讲师、副教授、教授。";
				}
				is.close();
			} 
		}
			
		if(i!=0)
			successMessage="成功载入"+(i-1)+"条教师信息";
		
		model.addAttribute("failMessage", failMessage);
		model.addAttribute("successMessage", successMessage);
		model.addAttribute("fileName",fileName);
		
		return "/admin/teaUpload";
	}
	
	
	@RequestMapping(value = "/courseUpload",method = {RequestMethod.POST}, produces = "text/html;charset=UTF-8")
	public String CourseUpload(@RequestParam("file") MultipartFile file, Model model) throws Exception {

		int i=0;
		String fileName=file.getOriginalFilename();
		String successMessage="",failMessage="";
        
 
		if(file.isEmpty()) {
			failMessage="传输终止！ 原因：文件为空！";
		}
		else if(!file.getOriginalFilename().endsWith("xls")) {
			failMessage="传输终止！ 原因：文件不是.xls类型！";
		}
		else {
			// poi--exl解析
			InputStream is=null;
			HSSFWorkbook hssf=null;
			HSSFSheet sheet=null;
			try{
				is = file.getInputStream();
				hssf = new HSSFWorkbook(is);
				//Sheet1-----是Excel表格左下方的名称
				sheet = hssf.getSheet("Sheet1");
			}
			catch(Exception e){
				failMessage="传输终止！ 原因：文件损坏！";
			}
			
               //读取Excel表格从下标0开始（表头开始）
			if(failMessage.equals("")) {
				NumberFormat nf = NumberFormat.getInstance();
				try {
					for (i = 1; i <= sheet.getLastRowNum(); i++) {
                    
						HSSFCell courseName = sheet.getRow(i).getCell(0);
						HSSFCell teacherID = sheet.getRow(i).getCell(1);
						HSSFCell courseTime= sheet.getRow(i).getCell(2);
						HSSFCell classRoom = sheet.getRow(i).getCell(3);
						HSSFCell courseWeek = sheet.getRow(i).getCell(4);
						HSSFCell courseType = sheet.getRow(i).getCell(5);
						HSSFCell collegeID= sheet.getRow(i).getCell(6);
						HSSFCell score = sheet.getRow(i).getCell(7);
						HSSFCell boardScores = sheet.getRow(i).getCell(8);
						HSSFCell homeworkScores = sheet.getRow(i).getCell(9);
						HSSFCell attendanceScores = sheet.getRow(i).getCell(10);
						HSSFCell experimentalScores= sheet.getRow(i).getCell(11);	
					
						CourseCustom t = new CourseCustom();
						
						String s;
						
						t.setCoursename(courseName.toString());
						
						s = nf.format(teacherID.getNumericCellValue());
						if (s.indexOf(",") >= 0) {
							s = s.replace(",", "");
						}
						t.setTeacherid(Integer.valueOf(s));
						
						t.setCoursetime(courseTime.toString());
						
						t.setClassroom(classRoom.toString());
						
						s = nf.format(courseWeek.getNumericCellValue());
						if (s.indexOf(",") >= 0) {
							s = s.replace(",", "");
						}
						t.setCourseweek(Integer.valueOf(s));
						
						t.setCoursetype(courseType.toString());
						
						s = nf.format(collegeID.getNumericCellValue());
						if (s.indexOf(",") >= 0) {
							s = s.replace(",", "");
						}
						t.setCollegeid(Integer.valueOf(s));
						
						s = nf.format(score.getNumericCellValue());
						if (s.indexOf(",") >= 0) {
							s = s.replace(",", "");
						}
						t.setScore(Integer.valueOf(s));
						
						s = nf.format(boardScores.getNumericCellValue());
						if (s.indexOf(",") >= 0) {
							s = s.replace(",", "");
						}
						t.setBoardscores(Integer.valueOf(s));
						
						s = nf.format(homeworkScores.getNumericCellValue());
						if (s.indexOf(",") >= 0) {
							s = s.replace(",", "");
						}
						t.setHomeworkscores(Integer.valueOf(s));
						
						s = nf.format(attendanceScores.getNumericCellValue());
						if (s.indexOf(",") >= 0) {
							s = s.replace(",", "");
						}
						t.setAttendancescores(Integer.valueOf(s));
						
						s = nf.format(experimentalScores.getNumericCellValue());
						if (s.indexOf(",") >= 0) {
							s = s.replace(",", "");
						}
						t.setExperimentalscores(Integer.valueOf(s));	
						
						if(!t.getCoursetype().equals("公共课") && !t.getCoursetype().equals("必修课") && !t.getCoursetype().equals("选修课")) throw new Exception();
						
						if(t.getCourseweek()<1 || t.getScore()<0 || t.getExperimentalscores()<0 || t.getBoardscores()<0 ||t.getAttendancescores()<0 || t.getHomeworkscores()<0) throw new Exception();
						
						List<CourseCustom> courseList = courseService.findByName(t.getCoursename());
						for(CourseCustom c:courseList) {
							if(c.getTeacherid().equals(t.getTeacherid())) {
								throw new Exception();
							}
						}
						
						TeacherCustom teacherCustom = teacherService.findById(t.getTeacherid());
						t.setTeachername(teacherCustom.getUsername());
						
						t.setSession(1);
					
						if(!courseService.save(t))
							throw new Exception();
						//获得插入时的路线id
					
					}
				}
				catch (Exception e) {
					failMessage="传输终止！ 原因：第"+i+"行数据产生错误，可能存在不规范数据，教师不存在，或者课程重复！</p><div class=\"col-sm-1\"></div><p style=\"color:red;\">注意：课程类型只能是必修课、选修课或者公共课！";
				}
				is.close();
			} 
		}
			
		if(i!=0)
			successMessage="成功载入"+(i-1)+"条课程信息";
		
		model.addAttribute("failMessage", failMessage);
		model.addAttribute("successMessage", successMessage);
		model.addAttribute("fileName",fileName);
		
		return "/admin/courseUpload";
	}
	
	@RequestMapping(value = "/scoresUpload",method = {RequestMethod.POST}, produces = "text/html;charset=UTF-8")
	public String ScoresUpload(@RequestParam("file") MultipartFile file, @RequestParam("courseid") Integer courseid, @RequestParam("session") Integer session, Model model) throws Exception {

		int i=0;
		String fileName=file.getOriginalFilename();
		String successMessage="",failMessage="";
        
 
		if(file.isEmpty()) {
			failMessage="传输终止！ 原因：文件为空！";
		}
		else if(!file.getOriginalFilename().endsWith("xls")) {
			failMessage="传输终止！ 原因：文件不是.xls类型！";
		}
		else {
			// poi--exl解析
			InputStream is=null;
			HSSFWorkbook hssf=null;
			HSSFSheet sheet=null;
			try{
				is = file.getInputStream();
				hssf = new HSSFWorkbook(is);
				//Sheet1-----是Excel表格左下方的名称
				sheet = hssf.getSheet("Sheet1");
			}
			catch(Exception e){
				failMessage="传输终止！ 原因：文件损坏！";
			}
			
               //读取Excel表格从下标0开始（表头开始）
			if(failMessage.equals("")) {
				NumberFormat nf = NumberFormat.getInstance();
				try {
					for (i = 1; i <= sheet.getLastRowNum(); i++) {
                    
						HSSFCell studentID = sheet.getRow(i).getCell(0);
						HSSFCell boardScores = sheet.getRow(i).getCell(1);
						HSSFCell homeworkScores= sheet.getRow(i).getCell(2);
						HSSFCell attendanceScores = sheet.getRow(i).getCell(3);
						HSSFCell experimentalScores = sheet.getRow(i).getCell(4);
						
						String s = nf.format(studentID.getNumericCellValue());
						if (s.indexOf(",") >= 0) {
							s = s.replace(",", "");
						}
						int studentID_int=Integer.valueOf(s);
						
						CourseCustom courseCustom = new CourseCustom();
						courseCustom.setCourseid(courseid);
						courseCustom.setSession(session);
						List<SelectedCourseCustom> sccList=selectedCourseService.findPresentSessionCourse(courseCustom);
						
						int loopSccList;
						for(loopSccList=0;loopSccList<sccList.size();loopSccList++) {
							if(sccList.get(loopSccList).getStudentid()==studentID_int) break;
						}
						if(loopSccList==sccList.size()) throw new Exception();
						
						s = nf.format(boardScores.getNumericCellValue());
						if (s.indexOf(",") >= 0) {
							s = s.replace(",", "");
						}
						int boardScores_int=Integer.valueOf(s);
						
						s = nf.format(homeworkScores.getNumericCellValue());
						if (s.indexOf(",") >= 0) {
							s = s.replace(",", "");
						}
						int homeworkScores_int=Integer.valueOf(s);
						
						s = nf.format(attendanceScores.getNumericCellValue());
						if (s.indexOf(",") >= 0) {
							s = s.replace(",", "");
						}
						int attendanceScores_int=Integer.valueOf(s);
						
						s = nf.format(experimentalScores.getNumericCellValue());
						if (s.indexOf(",") >= 0) {
							s = s.replace(",", "");
						}
						int experimentalScores_int=Integer.valueOf(s);
						
						if(boardScores_int<0 || homeworkScores_int<0 || attendanceScores_int<0 || experimentalScores_int<0) throw new Exception();
						
						sccList.get(loopSccList).setMark(boardScores_int+homeworkScores_int+attendanceScores_int+experimentalScores_int);
						selectedCourseService.updataOne(sccList.get(loopSccList));
	
						Scores scores= new Scores();
						scores.setSelectedcourseid(sccList.get(loopSccList).getId());
						scores.setAttendancescores(attendanceScores_int);
						scores.setBoardscores(boardScores_int);
						scores.setExperimentalscores(experimentalScores_int);
						scores.setHomeworkscores(homeworkScores_int);
						scoresService.updateOne(scores);
						//获得插入时的路线id
					
					}
				}
				catch (Exception e) {
					failMessage="传输终止！ 原因：第"+i+"行数据产生错误，可能存在不规范数据，学生不存在，或者该学生没有选此课程！";
				}
				is.close();
			} 
		}
			
		if(i!=0)
			successMessage="成功载入"+(i-1)+"条成绩信息";
		
		model.addAttribute("failMessage", failMessage);
		model.addAttribute("successMessage", successMessage);
		model.addAttribute("fileName",fileName);
		
		return "redirect:/teacher/scoresUpload?courseid="+courseid;
	}
}