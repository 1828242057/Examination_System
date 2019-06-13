/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : examination_system

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-06-13 16:40:01
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
  `session` int(11) NOT NULL,
  `examinationPlan` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`courseID`),
  KEY `collegeID` (`collegeID`),
  KEY `teacherID` (`teacherID`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`),
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'C语言程序设计', '1000', '周一', '西一 201', '10', '必修课', '1', '3', '赵老', '70', '10', '10', '10', '2', null);
INSERT INTO `course` VALUES ('2', 'Java程序设计', '1000', '周三', '西一 201', '10', '必修课', '1', '3', '赵老', '70', '10', '10', '10', '1', '2019.06.18 8:20-10:20 东三101');
INSERT INTO `course` VALUES ('3', 'C++程序设计', '1001', '周四', '西一 202', '10', '必修课', '1', '3', '钱老', '70', '10', '10', '10', '1', null);
INSERT INTO `course` VALUES ('4', 'C#程序设计', '1001', '周四', '西一 202', '10', '必修课', '1', '3', '钱老', '70', '10', '10', '10', '1', null);
INSERT INTO `course` VALUES ('5', '哲学', '1002', '周一', '西一 203', '8', '必修课', '1', '2', '老孙', '60', '20', '10', '10', '1', null);
INSERT INTO `course` VALUES ('6', '嵌入式', '1000', '周五', '西一204', '12', '必修课', '1', '4', '赵老', '70', '0', '10', '20', '1', '2019.06.19 8:20-10:20 东三101');
INSERT INTO `course` VALUES ('8', '计算机组成原理', '1000', '周五', '西一204', '12', '必修课', '1', '4', '赵老', '70', '0', '10', '20', '1', null);
INSERT INTO `course` VALUES ('9', '编译原理', '1000', '周三', '西一 201', '10', '必修课', '1', '3', '赵老', '70', '10', '10', '10', '1', null);

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
  `teacherName` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `feedback_fk_1` (`studentID`),
  KEY `feedback_fk_2` (`courseID`),
  KEY `feedback_fk_3` (`teacherID`),
  CONSTRAINT `feedback_fk_1` FOREIGN KEY (`studentID`) REFERENCES `student` (`userID`),
  CONSTRAINT `feedback_fk_2` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`),
  CONSTRAINT `feedback_fk_3` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES ('12', '10001', '1', '1000', '你好', '你好', '老二', 'C语言程序设计', '', '2019-06-07', '18:11:24', '赵老');

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
INSERT INTO `scores` VALUES ('52', '60', '0', '0', '0');
INSERT INTO `scores` VALUES ('56', '70', '10', '10', '10');
INSERT INTO `scores` VALUES ('57', '59', '0', '0', '0');
INSERT INTO `scores` VALUES ('60', '70', '10', '10', '10');
INSERT INTO `scores` VALUES ('61', null, null, null, null);
INSERT INTO `scores` VALUES ('62', null, null, null, null);
INSERT INTO `scores` VALUES ('64', '80', '0', '0', '0');
INSERT INTO `scores` VALUES ('65', '70', '0', '0', '0');
INSERT INTO `scores` VALUES ('66', '60', '0', '0', '0');
INSERT INTO `scores` VALUES ('67', '40', '0', '0', '0');
INSERT INTO `scores` VALUES ('68', '30', '0', '0', '0');
INSERT INTO `scores` VALUES ('69', '20', '0', '0', '0');
INSERT INTO `scores` VALUES ('70', '10', '0', '0', '0');
INSERT INTO `scores` VALUES ('71', '10', '0', '0', '0');
INSERT INTO `scores` VALUES ('72', null, null, null, null);
INSERT INTO `scores` VALUES ('75', null, null, null, null);
INSERT INTO `scores` VALUES ('77', null, null, null, null);
INSERT INTO `scores` VALUES ('78', null, null, null, null);
INSERT INTO `scores` VALUES ('79', null, null, null, null);

-- ----------------------------
-- Table structure for `selectedcourse`
-- ----------------------------
DROP TABLE IF EXISTS `selectedcourse`;
CREATE TABLE `selectedcourse` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `courseID` int(11) NOT NULL,
  `studentID` int(11) NOT NULL,
  `mark` int(11) DEFAULT NULL COMMENT '成绩',
  `passed` int(1) NOT NULL,
  `examinationPlan` varchar(200) DEFAULT NULL,
  `session` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `courseID` (`courseID`),
  KEY `studentID` (`studentID`),
  CONSTRAINT `selectedcourse_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`),
  CONSTRAINT `selectedcourse_ibfk_2` FOREIGN KEY (`studentID`) REFERENCES `student` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of selectedcourse
-- ----------------------------
INSERT INTO `selectedcourse` VALUES ('52', '1', '10000', '60', '1', '2019.06.17 8:20-10:20 东三101', '1');
INSERT INTO `selectedcourse` VALUES ('56', '1', '10001', '100', '1', '2019.06.17 8:20-10:20 东三101', '1');
INSERT INTO `selectedcourse` VALUES ('57', '2', '10000', '59', '1', '2019.06.18 8:20-10:20 东三101', '1');
INSERT INTO `selectedcourse` VALUES ('60', '2', '10001', '100', '1', '2019.06.18 8:20-10:20 东三101', '1');
INSERT INTO `selectedcourse` VALUES ('61', '3', '10001', null, '2', null, '1');
INSERT INTO `selectedcourse` VALUES ('62', '4', '10001', null, '1', null, '1');
INSERT INTO `selectedcourse` VALUES ('64', '1', '10002', '80', '1', '2019.06.17 8:20-10:20 东三101', '1');
INSERT INTO `selectedcourse` VALUES ('65', '1', '10003', '70', '1', '2019.06.17 8:20-10:20 东三101', '1');
INSERT INTO `selectedcourse` VALUES ('66', '1', '10004', '60', '1', '2019.06.17 8:20-10:20 东三101', '1');
INSERT INTO `selectedcourse` VALUES ('67', '1', '10005', '40', '1', '2019.06.17 8:20-10:20 东三101', '1');
INSERT INTO `selectedcourse` VALUES ('68', '1', '10006', '30', '1', '2019.06.17 8:20-10:20 东三101', '1');
INSERT INTO `selectedcourse` VALUES ('69', '1', '10007', '20', '1', '2019.06.17 8:20-10:20 东三101', '1');
INSERT INTO `selectedcourse` VALUES ('70', '1', '10008', '10', '1', '2019.06.17 8:20-10:20 东三101', '1');
INSERT INTO `selectedcourse` VALUES ('71', '1', '10009', '10', '1', '2019.06.17 8:20-10:20 东三101', '1');
INSERT INTO `selectedcourse` VALUES ('72', '4', '10000', null, '1', null, '1');
INSERT INTO `selectedcourse` VALUES ('75', '5', '10000', null, '1', null, '1');
INSERT INTO `selectedcourse` VALUES ('77', '3', '10000', null, '0', null, '1');
INSERT INTO `selectedcourse` VALUES ('78', '6', '10000', null, '1', '2019.06.19 8:20-10:20 东三101', '1');
INSERT INTO `selectedcourse` VALUES ('79', '5', '10001', null, '1', null, '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=10010 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('10000', '老一', '男', '1996-09-02', '2015-09-02', '1');
INSERT INTO `student` VALUES ('10001', '老二', '男', '1996-09-02', '2015-09-02', '1');
INSERT INTO `student` VALUES ('10002', '老三', '男', '1998-05-08', '2016-02-09', '1');
INSERT INTO `student` VALUES ('10003', '老四', '男', '1998-04-08', '2016-02-09', '1');
INSERT INTO `student` VALUES ('10004', '老五', '男', '1998-03-08', '2016-02-09', '1');
INSERT INTO `student` VALUES ('10005', '老六', '男', '1996-09-02', '2015-09-02', '1');
INSERT INTO `student` VALUES ('10006', '老七', '男', '1996-09-02', '2015-09-02', '1');
INSERT INTO `student` VALUES ('10007', '老八', '男', '1996-09-02', '2015-09-02', '1');
INSERT INTO `student` VALUES ('10008', '老九', '男', '1996-09-02', '2015-09-02', '1');
INSERT INTO `student` VALUES ('10009', '老十', '男', '1996-09-02', '2015-09-02', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

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
INSERT INTO `userlogin` VALUES ('34', '10005', '123', '2');
INSERT INTO `userlogin` VALUES ('35', '10006', '123', '2');
INSERT INTO `userlogin` VALUES ('36', '10007', '123', '2');
INSERT INTO `userlogin` VALUES ('37', '10008', '123', '2');
INSERT INTO `userlogin` VALUES ('38', '10009', '123', '2');
