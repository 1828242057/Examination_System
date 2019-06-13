package com.system.po;

public class Selectedcourse {
	private Integer id;
	
    private Integer courseid;

    private Integer studentid;

    private Integer mark;
    
    private Integer passed;
    
    private String examinationplan;
    
    private Integer session;
    
    public Integer getSession() {
        return session;
    }

    public void setSession(Integer session) {
        this.session = session;
    }
    
    public String getExaminationplan() {
        return examinationplan;
    }

    public void setExaminationplan(String examinationplan) {
        this.examinationplan = examinationplan;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
    
    public Integer getPassed() {
        return passed;
    }

    public void setPassed(Integer passed) {
        this.passed = passed;
    }
}