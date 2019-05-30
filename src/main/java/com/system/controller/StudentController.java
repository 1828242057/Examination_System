package com.system.controller;

import com.system.exception.CustomException;
import com.system.po.*;
import com.system.service.CourseService;
import com.system.service.FeedbackService;
import com.system.service.ScoresService;
import com.system.service.SelectedCourseService;
import com.system.service.StudentService;
import com.system.service.impl.stuExportCourseInfo;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.Time;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
/**
 * Created by Jacey on 2017/7/5.
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {
	
	@Resource(name = "feedbackServiceImpl")
    private FeedbackService feedbackService;
	
	@Resource(name = "stuExportCourseInfo")
    private stuExportCourseInfo stuExportCourse;


	@Resource(name = "scoresServiceImpl")
    private ScoresService scoresService;
	
    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "studentServiceImpl")
    private StudentService studentService;

    @Resource(name = "selectedCourseServiceImpl")
    private SelectedCourseService selectedCourseService;

    @RequestMapping(value = "/showCourse")
    public String stuCourseShow(Model model, Integer page) throws Exception {
        List<CourseCustom> list = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(courseService.getCountCouse());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            list = courseService.findByPaging(1);
        } else {
            pagingVO.setToPageNo(page);
            list = courseService.findByPaging(page);
        }

        model.addAttribute("courseList", list);
        model.addAttribute("pagingVO", pagingVO);

        return "student/showCourse";
       

    }

    // 选课操作
    @RequestMapping(value = "/stuSelectedCourse")
    public String stuSelectedCourse(int id) throws Exception {
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(id);
        selectedCourseCustom.setStudentid(Integer.parseInt(username));

        SelectedCourseCustom s = selectedCourseService.findOne(selectedCourseCustom);

        if (s == null) {
            selectedCourseService.save(selectedCourseCustom);
            s=selectedCourseService.findOne(selectedCourseCustom);
            Scores scores = new Scores();
            scores.setSelectedcourseid(s.getId());
            scoresService.save(scores);
        } else {
            throw new CustomException("该门课程你已经选了，不能再选");
        }
        return "redirect:/student/selectedCourse";
    }

    // 退课操作
    @RequestMapping(value = "/outCourse")
    public String outCourse(int id) throws Exception {
        scoresService.remove(id);
        selectedCourseService.remove(id);

        return "redirect:/student/selectedCourse";
    }

    // 已选课程
    @RequestMapping(value = "/selectedCourse")
    public String selectedCourse(Model model) throws Exception {
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());
        
        List<SelectedCourseCustom> list;
        if(studentCustom==null) list=null;
        else list = studentCustom.getSelectedCourseList();
        model.addAttribute("selectedCourseList", list);

        return "student/selectCourse";
    }

    // 已修课程
    @RequestMapping(value = "/overCourse")
    public String overCourse(Model model) throws Exception {

        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());

        List<SelectedCourseCustom> list;
        if(studentCustom==null) list=null;
        else {
        	list = studentCustom.getSelectedCourseList();
        	Scores scores;
        	for(SelectedCourseCustom sc:list) {
        		scores=scoresService.findByID(sc.getId());
        		sc.setBoardscores(scores.getBoardscores());
        		sc.setHomeworkscores(scores.getHomeworkscores());
        		sc.setAttendancescores(scores.getAttendancescores());
        		sc.setExperimentalscores(scores.getExperimentalscores());
        	}
        }
        model.addAttribute("selectedCourseList", list);

        return "student/overCourse";
    }

    //修改密码
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "student/passwordRest";
    }
	
	//反馈加载控制器
    @RequestMapping(value = "/Responsive", method = {RequestMethod.GET})
    public String Responsive(Model model) throws Exception {
		Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());
        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();
        model.addAttribute("selectedCourseCustomList", list);
       return "student/Responsive";
    }
    
    
    //反馈提交控制器
    @RequestMapping(value = "/Responsive" ,method = {RequestMethod.POST})
    public String Responsive(Feedback feedback) throws Exception {
		if(feedback.getFeedbacktext() == null || feedback.getFeedbacktext().equals("")){
			throw new CustomException("反馈信息不能为空");
		}
		else{
			Subject subject = SecurityUtils.getSubject();
			String username = (String) subject.getPrincipal();
			feedback.setStudentid(Integer.parseInt(username));
		
			Date currentDate = new Date(System.currentTimeMillis());
			feedback.setFeedbackdate(currentDate);
			Time currentTime = new Time(System.currentTimeMillis());
			feedback.setFeedbacktime(currentTime);
		
			Student student = studentService.findById(feedback.getStudentid());
			feedback.setStudentname(student.getUsername());
		
			Course course = courseService.findById(feedback.getCourseid());
			feedback.setCoursename(course.getCoursename());
			feedback.setTeacherid(course.getTeacherid());
		
			feedback.setProcessed(false);
		
			feedbackService.save(feedback);
		}
		
		return "student/Responsive";
    }
    
  //课程导出控制器  未实现
    @RequestMapping(value = "/exportCourse", method = {RequestMethod.GET})
    public void export(HttpServletRequest request,HttpServletResponse response) throws Exception{
        response.reset(); //清除buffer缓存  
        //Map<String,Object> map=new HashMap<String,Object>();  
        // 指定下载的文件名  
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
        response.setHeader("Content-Disposition","attachment;filename="+new String("用户表.xlsx".getBytes(),"iso-8859-1"));
        //导出Excel对象  
        XSSFWorkbook workbook = stuExportCourse.exportExcelInfo();
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


    
    }
}
