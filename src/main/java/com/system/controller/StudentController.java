package com.system.controller;

import com.system.exception.CustomException;
import com.system.po.*;
import com.system.service.CourseService;
import com.system.service.FeedbackService;
import com.system.service.ScoresService;
import com.system.service.SelectedCourseService;
import com.system.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.sql.Time;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Jacey on 2017/7/5.
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {
	
	@Resource(name = "feedbackServiceImpl")
    private FeedbackService feedbackService;

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
        List<CourseCustom> notList = new ArrayList<CourseCustom>();
        List<CourseCustom> beingList = new ArrayList<CourseCustom>();
        List<CourseCustom> alreadyList = new ArrayList<CourseCustom>();
        List<CourseCustom> passedList = new ArrayList<CourseCustom>();
        List<CourseCustom> list = null;
        Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());
        List<SelectedCourseCustom> selectedCourseList=studentCustom.getSelectedCourseList();
        int i;
        
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
        
        for(CourseCustom cc:list) {
        	for(i=0;i<selectedCourseList.size();i++) {
        		if(cc.getCourseid().equals(selectedCourseList.get(i).getCourseid())) {
        			if(selectedCourseList.get(i).getPassed()==0) {
        				passedList.add(cc);
        			}
        			else if(selectedCourseList.get(i).getOver()==true) alreadyList.add(cc);
        			else beingList.add(cc);
        			break;
        		}
        	}
        	if(i==selectedCourseList.size()) notList.add(cc);
        }

        model.addAttribute("notList", notList);
        model.addAttribute("beingList", beingList);
        model.addAttribute("alreadyList", alreadyList);
        model.addAttribute("passedList", passedList);
        model.addAttribute("pagingVO", pagingVO);

        return "student/showCourse";
       

    }

    // 选课操作
    @RequestMapping(value = "/stuSelectedCourse")
    public String stuSelectedCourse(int id,int page) throws Exception {
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(id);
        selectedCourseCustom.setStudentid(Integer.parseInt(username));
        
        SelectedCourseCustom s = selectedCourseService.findOne(selectedCourseCustom);

        if (s == null) {
        	selectedCourseCustom.setSession(1);
        	selectedCourseCustom.setPassed(0);
            selectedCourseService.save(selectedCourseCustom);
            s=selectedCourseService.findOne(selectedCourseCustom);
            Scores scores = new Scores();
            scores.setSelectedcourseid(s.getId());
            scoresService.save(scores);
        } else {
            throw new CustomException("该门课程你已经选了，不能再选");
        }
        return "redirect:/student/showCourse?page="+page;
    }

    // 退课操作
    @RequestMapping(value = "/outCourse")
    public String outCourse(int id) throws Exception {
    	SelectedCourseCustom sc=selectedCourseService.findById(id);
    	sc.setPassed(2);
    	selectedCourseService.updataOne(sc);

        return "redirect:/student/selectedCourse";
    }

    // 已选课程
    @RequestMapping(value = "/selectedCourse")
    public String selectedCourse(Model model) throws Exception {	
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());
        
        List<SelectedCourseCustom> beingList=new ArrayList<SelectedCourseCustom>();
        List<SelectedCourseCustom> passedList=new ArrayList<SelectedCourseCustom>();
       
        if(studentCustom!=null) {
        	List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();
        	for(SelectedCourseCustom scc:list) {
        		if(scc.getPassed()==1 && scc.getOver()==false) beingList.add(scc);
        		else if(scc.getPassed()==2) passedList.add(scc);
        	}
        }
        model.addAttribute("beingList", beingList);
        model.addAttribute("passedList", passedList);

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
			feedback.setTeachername(course.getTeachername());
		
			feedback.setProcessed(false);
		
			feedbackService.save(feedback);
		}
		
		return "redirect:showResponsive";
    }
    
    @RequestMapping(value = "/showFeedtext", method = {RequestMethod.GET})
    public String showFeedtext(Integer id, Model model) throws Exception {
		Feedback feedback =feedbackService.findByID(id);
		model.addAttribute("feedback", feedback);
        return "student/showFeedtext";
    }
    
    @RequestMapping(value = "/showResponsive")
    public String showResponsive(Model model) throws Exception {
		Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
		List<Feedback> list = feedbackService.findByStudentID(Integer.parseInt(username));
		model.addAttribute("feedbackList", list);
		
        return "student/showResponsive";
    }
}
