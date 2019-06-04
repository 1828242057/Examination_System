/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : examination_system

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-05-26 23:27:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `college`
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `collegeID` int(11) NOT NULL,
  `collegeName` varchar(200) NOT NULL COMMENT '课程名',
  PRIMARY KEY (`collegeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES ('1', '计算机系');
INSERT INTO `college` VALUES ('2', '设计系');
INSERT INTO `college` VALUES ('3', '财经系');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseID` int(11) NOT NULL AUTO_INCREMENT,
  `courseName` varchar(200) NOT NULL COMMENT '课程名称',
  `teacherID` int(11) NOT NULL,
  `courseTime` varchar(200) DEFAULT NULL COMMENT '开课时间',
  `classRoom` varchar(200) DEFAULT NULL COMMENT '开课地点',
  `courseWeek` int(200) DEFAULT NULL COMMENT '学时',
  `courseType` varchar(20) DEFAULT NULL COMMENT '课程类型',
  `collegeID` int(11) NOT NULL COMMENT '所属院系',
  `score` int(11) NOT NULL COMMENT '学分',
  `teacherName` varchar(20) NOT NULL,
  `boardScores` int(11) NOT NULL,
  `homeworkScores` int(11) NOT NULL,
  `attendanceScores` int(11) NOT NULL,
  `experimentalScores` int(11) NOT NULL,
  PRIMARY KEY (`courseID`),
  KEY `collegeID` (`collegeID`),
  KEY `teacherID` (`teacherID`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`),
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'C语言程序设计', '1000', '周一', '西一 201', '10', '必修课', '1', '3', '赵老', '70', '10', '10', '10');
INSERT INTO `course` VALUES ('2', 'Java程序设计', '1000', '周三', '西一 201', '10', '必修课', '1', '3', '赵老', '70', '10', '10', '10');
INSERT INTO `course` VALUES ('3', 'C++程序设计', '1001', '周四', '西一 202', '10', '必修课', '1', '3', '钱老', '70', '10', '10', '10');
INSERT INTO `course` VALUES ('4', 'C#程序设计', '1001', '周四', '西一 202', '10', '必修课', '1', '3', '钱老', '70', '10', '10', '10');
INSERT INTO `course` VALUES ('5', '哲学', '1002', '周一', '西一 203', '8', '必修课', '1', '2', '老孙', '60', '20', '10', '10');
INSERT INTO `course` VALUES ('6', '嵌入式系统', '1000', '周五', '西一204', '12', '必修课', '1', '4', '赵老', '70', '0', '10', '20');

-- ----------------------------
-- Table structure for `feedback`
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `studentID` int(11) NOT NULL,
  `courseID` int(11) NOT NULL,
  `teacherID` int(11) NOT NULL,
  `feedbackText` varchar(2000) NOT NULL,
  `processText` varchar(2000) DEFAULT NULL,
  `studentName` varchar(200) NOT NULL,
  `courseName` varchar(200) NOT NULL,
  `processed` bit(1) NOT NULL,
  `feedbackDate` date NOT NULL,
  `feedbackTime` time NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `feedback_fk_1` (`studentID`),
  KEY `feedback_fk_2` (`courseID`),
  KEY `feedback_fk_3` (`teacherID`),
  CONSTRAINT `feedback_fk_3` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`userID`),
  CONSTRAINT `feedback_fk_1` FOREIGN KEY (`studentID`) REFERENCES `student` (`userID`),
  CONSTRAINT `feedback_fk_2` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES ('1', '10000', '1', '1000', '老师您好！我认为我的成绩应该是101分，请您重新为我计分！', '你怕不是傻子？', '老一', 'C语言程序设计', '', '2019-05-23', '14:54:40');
INSERT INTO `feedback` VALUES ('2', '10000', '2', '1000', '老师您好！我的成绩为何还未录入？', '已补录！', '老一', 'Java程序设计', '', '2019-05-23', '18:36:57');
INSERT INTO `feedback` VALUES ('3', '10001', '1', '1000', '老师下周的C语言课我想请假！', '不行！', '老二', 'C语言程序设计', '', '2019-05-23', '18:53:03');
INSERT INTO `feedback` VALUES ('4', '10000', '3', '1001', '你好！老师！', null, '老一', 'C++程序设计', '', '2019-05-25', '17:23:16');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleID` int(11) NOT NULL,
  `roleName` varchar(20) NOT NULL,
  `permissions` varchar(255) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('0', 'admin', null);
INSERT INTO `role` VALUES ('1', 'teacher', null);
INSERT INTO `role` VALUES ('2', 'student', null);

-- ----------------------------
-- Table structure for `scores`
-- ----------------------------
DROP TABLE IF EXISTS `scores`;
CREATE TABLE `scores` (
  `selectedcourseID` int(11) NOT NULL,
  `boardScores` int(11) DEFAULT NULL,
  `homeworkScores` int(11) DEFAULT NULL,
  `attendanceScores` int(11) DEFAULT NULL,
  `experimentalScores` int(11) DEFAULT NULL,
  PRIMARY KEY (`selectedcourseID`),
  KEY `selectedcourseID` (`selectedcourseID`) USING BTREE,
  CONSTRAINT `scores_fk_1` FOREIGN KEY (`selectedcourseID`) REFERENCES `selectedcourse` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scores
-- ----------------------------
INSERT INTO `scores` VALUES ('52', '0', '0', '0', '0');
INSERT INTO `scores` VALUES ('56', '70', '10', '10', '10');
INSERT INTO `scores` VALUES ('57', '0', '0', '0', '0');
INSERT INTO `scores` VALUES ('58', null, null, null, null);
INSERT INTO `scores` VALUES ('60', '70', '10', '10', '10');
INSERT INTO `scores` VALUES ('61', null, null, null, null);
INSERT INTO `scores` VALUES ('62', null, null, null, null);

-- ----------------------------
-- Table structure for `selectedcourse`
-- ----------------------------
DROP TABLE IF EXISTS `selectedcourse`;
CREATE TABLE `selectedcourse` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `courseID` int(11) NOT NULL,
  `studentID` int(11) NOT NULL,
  `mark` int(11) DEFAULT NULL COMMENT '成绩',
  PRIMARY KEY (`ID`),
  KEY `courseID` (`courseID`),
  KEY `studentID` (`studentID`),
  CONSTRAINT `selectedcourse_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`),
  CONSTRAINT `selectedcourse_ibfk_2` FOREIGN KEY (`studentID`) REFERENCES `student` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of selectedcourse
-- ----------------------------
INSERT INTO `selectedcourse` VALUES ('52', '1', '10000', '0');
INSERT INTO `selectedcourse` VALUES ('56', '1', '10001', '100');
INSERT INTO `selectedcourse` VALUES ('57', '2', '10000', '0');
INSERT INTO `selectedcourse` VALUES ('58', '3', '10000', null);
INSERT INTO `selectedcourse` VALUES ('60', '2', '10001', '100');
INSERT INTO `selectedcourse` VALUES ('61', '3', '10001', null);
INSERT INTO `selectedcourse` VALUES ('62', '4', '10001', null);

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) NOT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `birthYear` date DEFAULT NULL COMMENT '出生日期',
  `grade` date DEFAULT NULL COMMENT '入学时间',
  `collegeID` int(11) NOT NULL COMMENT '院系id',
  PRIMARY KEY (`userID`),
  KEY `collegeID` (`collegeID`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`)
) ENGINE=InnoDB AUTO_INCREMENT=2216002 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('10000', '老一', '男', '1996-09-02', '2015-09-02', '1');
INSERT INTO `student` VALUES ('10001', '老二', '男', '1996-09-02', '2015-09-02', '1');
INSERT INTO `student` VALUES ('10002', '老三', '男', '1998-05-08', '2016-02-09', '1');
INSERT INTO `student` VALUES ('10003', '老四', '男', '1998-04-08', '2016-02-09', '1');
INSERT INTO `student` VALUES ('10004', '老五', '男', '1998-03-08', '2016-02-09', '1');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) NOT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `birthYear` date NOT NULL,
  `degree` varchar(20) DEFAULT NULL COMMENT '学历',
  `title` varchar(255) DEFAULT NULL COMMENT '职称',
  `grade` date DEFAULT NULL COMMENT '入职时间',
  `collegeID` int(11) NOT NULL COMMENT '院系',
  PRIMARY KEY (`userID`),
  KEY `collegeID` (`collegeID`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1000', '赵老', '男', '1980-09-02', '硕士', '普通教师', '2015-09-02', '1');
INSERT INTO `teacher` VALUES ('1001', '钱老', '男', '1980-01-01', '硕士', '普通教师', '2015-09-02', '1');
INSERT INTO `teacher` VALUES ('1002', '老孙', '男', '1970-05-08', '硕士', '普通教师', '2016-02-09', '1');

-- ----------------------------
-- Table structure for `userlogin`
-- ----------------------------
DROP TABLE IF EXISTS `userlogin`;
CREATE TABLE `userlogin` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `role` int(11) NOT NULL DEFAULT '2' COMMENT '角色权限',
  PRIMARY KEY (`userID`),
  KEY `role` (`role`),
  CONSTRAINT `userlogin_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`roleID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userlogin
-- ----------------------------
INSERT INTO `userlogin` VALUES ('1', 'admin', 'admin', '0');
INSERT INTO `userlogin` VALUES ('18', '1000', '123', '1');
INSERT INTO `userlogin` VALUES ('19', '1001', '123', '1');
INSERT INTO `userlogin` VALUES ('20', '10001', '123', '2');
INSERT INTO `userlogin` VALUES ('21', '10000', '123', '2');
INSERT INTO `userlogin` VALUES ('30', '10002', '123', '2');
INSERT INTO `userlogin` VALUES ('31', '10003', '123', '2');
INSERT INTO `userlogin` VALUES ('32', '10004', '123', '2');
INSERT INTO `userlogin` VALUES ('33', '1002', '123', '1');
