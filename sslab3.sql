/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : sslab3

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2016-06-20 21:25:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) DEFAULT NULL,
  wechat varchar(45) DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for meeting_employee
-- ----------------------------
DROP TABLE IF EXISTS meeting_employee;
CREATE TABLE meeting_employee (
  id int(11) NOT NULL AUTO_INCREMENT,
  employee varchar(45) DEFAULT NULL,
  start int(11) DEFAULT NULL,
  end int(11) DEFAULT NULL,
  meetingId int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for meeting_room
-- ----------------------------
DROP TABLE IF EXISTS meeting_room;
CREATE TABLE meeting_room (
  id int(11) NOT NULL AUTO_INCREMENT,
  roomId int(11) DEFAULT NULL,
  end int(11) DEFAULT NULL,
  start int(11) DEFAULT NULL,
  meetingId int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for meeting
-- ----------------------------
DROP TABLE IF EXISTS meeting;
CREATE TABLE meeting (
  id int(11) NOT NULL AUTO_INCREMENT,
  title varchar(45) DEFAULT NULL,
  roomId int(11) DEFAULT NULL,
  sponsor varchar(45) DEFAULT NULL,
  start datetime DEFAULT NULL,
  duration int(11) DEFAULT NULL,
  content varchar(135) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
