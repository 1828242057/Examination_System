package com.system.po;

/**
 * Created by Jacey on 2017/6/29.
 */
public class SelectedCourseCustom extends Selectedcourse {
    //新增Student 对象字段
    private StudentCustom studentCustom;

    //扩展课程信息对象
    private CourseCustom courseCustom;
    
    //判断该学生是否已经完成该课程
    private Boolean over = false;
    
    private Integer boardscores;
	private Integer homeworkscores;
	private Integer attendancescores;
	private Integer experimentalscores;
	
	public Integer getBoardscores() {
        return boardscores;
    }

    public void setBoardscores(Integer boardscores) {
        this.boardscores = boardscores;
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

    public Boolean getOver() {
        return over;
    }

    public void setOver(Boolean over) {
        this.over = over;
    }

    public StudentCustom getStudentCustom() {
        return studentCustom;
    }

    public void setStudentCustom(StudentCustom studentCustom) {
        this.studentCustom = studentCustom;
    }

    public CourseCustom getCouseCustom() {
        return courseCustom;
    }

    public void setCouseCustom(CourseCustom couseCustom) {
        this.courseCustom = couseCustom;
    }
}
