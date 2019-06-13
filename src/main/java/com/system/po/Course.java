package com.system.po;

public class Course {
    private Integer courseid;

    private String coursename;

    private Integer teacherid;
    
    private String teachername;

    private String coursetime;

    private String classroom;

    private Integer courseweek;

    private String coursetype;

    private Integer collegeid;

    private Integer score;
    
    private Integer boardscores;
    
    private Integer homeworkscores;
    
    private Integer attendancescores;
    
    private Integer experimentalscores;
    
    private Integer session;
    
    private String examinationplan;
    
    public String getExaminationplan() {
        return examinationplan;
    }

    public void setExaminationplan(String examinationplan) {
        this.examinationplan = examinationplan;
    }
    
    public Integer getSession() {
        return session;
    }

    public void setSession(Integer session) {
        this.session = session;
    }
    
    public Integer getBoardscores() {
        return boardscores;
    }

    public void setBoardscores(Integer boardgcores) {
        this.boardscores = boardgcores;
    }
    
    public Integer getHomeworkscores() {
        return homeworkscores;
    }

    public void setHomeworkscores(Integer homeworkscores) {
        this.homeworkscores = homeworkscores;
    }
    
    public Integer getAttendancescores() {
        return attendancescores;
    }

    public void setAttendancescores(Integer attendancescores) {
        this.attendancescores = attendancescores;
    }
    
    public Integer getExperimentalscores() {
        return experimentalscores;
    }

    public void setExperimentalscores(Integer experimentalscores) {
        this.experimentalscores = experimentalscores;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }
    
    public String getTeachername() {
        return teachername;
    }
    
    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename == null ? null : coursename.trim();
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public String getCoursetime() {
        return coursetime;
    }

    public void setCoursetime(String coursetime) {
        this.coursetime = coursetime == null ? null : coursetime.trim();
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom == null ? null : classroom.trim();
    }

    public Integer getCourseweek() {
        return courseweek;
    }

    public void setCourseweek(Integer courseweek) {
        this.courseweek = courseweek;
    }

    public String getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(String coursetype) {
        this.coursetype = coursetype == null ? null : coursetype.trim();
    }

    public Integer getCollegeid() {
        return collegeid;
    }

    public void setCollegeid(Integer collegeid) {
        this.collegeid = collegeid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}