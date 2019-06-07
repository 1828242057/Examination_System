package com.system.po;

import java.sql.*;

public class Feedback {
	private Integer id;
	private Integer studentid;
	private Integer courseid;
	private Integer teacherid;
	private String feedbacktext;
	private String processtext;
	private String studentname;
	private String coursename;
	private Boolean processed;
	private Date feedbackdate;
	private Time feedbacktime;
	private String teachername;
	
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
	public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }
    
    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }
	
	public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }
    
    public String getFeedbacktext() {
        return feedbacktext;
    }

    public void setFeedbacktext(String feedbacktext) {
        this.feedbacktext = feedbacktext;
    }
	
	public String getProcesstext() {
        return processtext;
    }

    public void setProcesstext(String processtext) {
        this.processtext = processtext;
    }
    
    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }
    
    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }
    
    public Date getFeedbackdate() {
        return feedbackdate;
    }

    public void setFeedbackdate(Date feedbackdate) {
        this.feedbackdate = feedbackdate;
    }
    
    public Time getFeedbacktime() {
        return feedbacktime;
    }

    public void setFeedbacktime(Time feedbacktime) {
        this.feedbacktime = feedbacktime;
    }
    
    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }
    
    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }
    
    public String getTeachername() {
        return teachername;
    }
}
