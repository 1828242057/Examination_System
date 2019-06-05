package com.system.controller;

import com.google.gson.Gson;
import com.system.exception.CustomException;
import com.system.po.*;
import com.system.service.CourseService;
import com.system.service.FeedbackService;
import com.system.service.SelectedCourseService;
import com.system.service.StudentService;
import com.system.service.TeacherService;
import com.system.service.ScoresService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Jacey on 2017/7/6.
 */

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
	
	@Resource(name = "feedbackServiceImpl")
	private FeedbackService feedbackService;

    @Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "selectedCourseServiceImpl")
    private SelectedCourseService selectedCourseService;
    
    @Resource(name = "scoresServiceImpl")
    private ScoresService scoresService;

    // 显示我的课程
    @RequestMapping(value = "/showCourse")
    public String stuCourseShow(Model model) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        List<CourseCustom> list = courseService.findByTeacherID(Integer.parseInt(username));
        model.addAttribute("courseList", list);

        return "teacher/showCourse";
    }

    // 显示成绩
    @RequestMapping(value = "/gradeCourse")
    public String gradeCourse(Integer id, Model model) throws Exception {
        if (id == null) {
            return "";
        }
        List<SelectedCourseCustom> list = selectedCourseService.findByCourseID(id);
        Scores scores;
        for(SelectedCourseCustom scc:list) {
        	scores=scoresService.findByID(scc.getId());
        	scc.setAttendancescores(scores.getAttendancescores());
        	scc.setBoardscores(scores.getBoardscores());
        	scc.setHomeworkscores(scores.getHomeworkscores());
        	scc.setExperimentalscores(scores.getExperimentalscores());
        }
        model.addAttribute("selectedCourseList", list);
        model.addAttribute("courseid", id);
        return "teacher/showGrade";
    }

    // 打分
    @RequestMapping(value = "/mark", method = {RequestMethod.GET})
    public String markUI(SelectedCourseCustom scc, Model model) throws Exception {

        SelectedCourseCustom selectedCourseCustom = selectedCourseService.findOne(scc);

        model.addAttribute("selectedCourse", selectedCourseCustom);

        return "teacher/mark";
    }

    // 打分
    @RequestMapping(value = "/mark", method = {RequestMethod.POST})
    public String mark(SelectedCourseCustom scc) throws Exception {
    	scc.setMark(scc.getAttendancescores()+scc.getBoardscores()+scc.getExperimentalscores()+scc.getHomeworkscores());
        selectedCourseService.updataOne(scc);
        Scores scores=new Scores();
        scores.setAttendancescores(scc.getAttendancescores());
        scores.setBoardscores(scc.getBoardscores());
        scores.setExperimentalscores(scc.getExperimentalscores());
        scores.setHomeworkscores(scc.getHomeworkscores());
        scores.setSelectedcourseid(scc.getId());
        scoresService.updateOne(scores);

        return "redirect:/teacher/gradeCourse?id="+scc.getCourseid();
    }

    //修改密码
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "teacher/passwordRest";
    }

    @RequestMapping(value = "selectCourse", method = {RequestMethod.POST})
    private String selectCourse(String findByName, Model model) throws Exception {

        List<CourseCustom> list = courseService.findByName(findByName);

        model.addAttribute("courseList", list);
        return "teacher/showCourse";
    }
	
	 //反馈信息  待修改  --廖永杰
    @RequestMapping(value = "/showResponsive")
    public String showResponsive(Model model) throws Exception {
		Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
		List<Feedback> list = feedbackService.findByTeacherID(Integer.parseInt(username));
		model.addAttribute("feedbackList", list);
		
        return "teacher/showResponsive";
    }
    
    //处理反馈信息  待修改  --廖永杰
    @RequestMapping(value = "/showFeedtext", method = {RequestMethod.GET})
    public String showFeedtext(Integer id, Model model) throws Exception {
		Feedback feedback =feedbackService.findByID(id);
		model.addAttribute("feedback", feedback);
        return "teacher/showFeedtext";
    }
	
	//处理反馈信息  待修改  --廖永杰
    @RequestMapping(value = "/showFeedtext", method = {RequestMethod.POST})
    public String feedbackProcessed(Feedback feedback) throws Exception {
		Feedback fb =feedbackService.findByID(feedback.getId());
		fb.setProcessed(true);
		fb.setProcesstext(feedback.getProcesstext());
		feedbackService.update(fb);
        return "redirect:showResponsive";
    }
    
    //统计分析（最低要求单科分值分段统计）  待修改  --廖永杰
    @RequestMapping(value = "/courseStatistics")
    public String courseStatistics(Model model) throws Exception {
		//显示已经打分完成的科目 然后点击分析按钮弹出统计图窗口
    	Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        //这里暂时先用所有的科目
        List<CourseCustom> list = courseService.findByTeacherID(Integer.parseInt(username));
        model.addAttribute("courseList", list);
        model.addAttribute("teacherId", Integer.parseInt(username));
        return "teacher/courseStatistics";
    }
    
    //产生单科分值分段统计图  待修改  --廖永杰
    @RequestMapping(value = "/doStatistics")
    public String doStatistics(Integer id,Model model) throws Exception {
		//统计图
    	if (id == null) {
            return "";
        }
        List<SelectedCourseCustom> list = selectedCourseService.findByCourseID(id);
        CourseCustom courseCustom=courseService.findById(id);
        model.addAttribute("selectedCourseList", new Gson().toJson(list));
        model.addAttribute("coursename", courseCustom.getCoursename());
        return "teacher/doStatistics";
    }
    
    @RequestMapping(value = "/scoresUpload", method = {RequestMethod.GET})
    private String scoresUpload(Integer courseid, String failMessage, String successMessage, String fileName, Model model) throws Exception {
    	model.addAttribute("courseid", courseid);
    	model.addAttribute("failMessage", failMessage);
		model.addAttribute("successMessage", successMessage);
		model.addAttribute("fileName",fileName);
        return "teacher/scoresUpload";
    }
}
