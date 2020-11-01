/*
Navicat MySQL Data Transfer

Source Server         : aa
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : web

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2020-11-01 12:14:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for right
-- ----------------------------
DROP TABLE IF EXISTS `right`;
CREATE TABLE `right` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `fatherId` int(11) DEFAULT NULL,
  `add` tinyint(4) DEFAULT NULL,
  `delete` tinyint(4) DEFAULT NULL,
  `read` tinyint(4) DEFAULT NULL,
  `update` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of right
-- ----------------------------
INSERT INTO `right` VALUES ('1', '用户管理', '\'el-icon-user-solid\'', null, null, null, null, null);
INSERT INTO `right` VALUES ('2', '教务管理', '\'el-icon-s-cooperation\'', null, null, null, null, null);
INSERT INTO `right` VALUES ('3', '权限管理', '\'el-icon-s-tools\'', null, null, null, null, null);
INSERT INTO `right` VALUES ('4', '用户列表', null, '1', '1', '1', '1', '1');
INSERT INTO `right` VALUES ('5', '用户编辑', null, '1', '1', '1', '1', '1');
INSERT INTO `right` VALUES ('6', '考试管理', null, '2', '1', '1', '1', '1');
INSERT INTO `right` VALUES ('7', '老师管理', null, '2', '1', '1', '1', '1');
INSERT INTO `right` VALUES ('8', '学生管理', null, '2', '1', '1', '1', '1');
INSERT INTO `right` VALUES ('9', '角色列表', null, '3', '1', '1', '1', '1');
INSERT INTO `right` VALUES ('10', '权限列表', null, '3', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `roleRight` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '\'[{\"id\":1,\"name\":\"用户管理\",\"icon\":\"el-icon-user-solid\",\"children\":[{\"id\":11,\"name\":\"用户列表\",\"path\":\"/user\",\"children\":[{\"id\":111,\"name\":\"增\",\"path\":\"add\"},{\"id\":112,\"name\":\"删\",\"path\":\"delete\"},{\"id\":113,\"name\":\"改\",\"path\":\"update\"},{\"id\":114,\"name\":\"查\",\"path\":\"read\"}]},{\"id\":12,\"name\":\"用户编辑\",\"path\":\"/userEdit\",\"children\":[{\"id\":121,\"name\":\"增\",\"path\":\"add\"},{\"id\":122,\"name\":\"删\",\"path\":\"delete\"},{\"id\":123,\"name\":\"改\",\"path\":\"update\"},{\"id\":124,\"name\":\"查\",\"path\":\"read\"}]}]},{\"id\":2,\"name\":\"教务管理\",\"icon\":\"el-icon-s-cooperation\",\"children\":[{\"id\":21,\"name\":\"学生管理\",\"children\":[{\"id\":211,\"name\":\"增\",\"path\":\"add\"},{\"id\":212,\"name\":\"删\",\"path\":\"delete\"},{\"id\":213,\"name\":\"改\",\"path\":\"update\"},{\"id\":214,\"name\":\"查\",\"path\":\"read\"}]},{\"id\":22,\"name\":\"教师管理\",\"children\":[{\"id\":221,\"name\":\"增\",\"path\":\"add\"},{\"id\":222,\"name\":\"删\",\"path\":\"delete\"},{\"id\":223,\"name\":\"改\",\"path\":\"update\"},{\"id\":224,\"name\":\"查\",\"path\":\"read\"}]},{\"id\":23,\"name\":\"考试管理\",\"children\":[{\"id\":231,\"name\":\"增\",\"path\":\"add\"},{\"id\":232,\"name\":\"删\",\"path\":\"delete\"},{\"id\":233,\"name\":\"改\",\"path\":\"update\"},{\"id\":234,\"name\":\"查\",\"path\":\"read\"}]}]},{\"id\":3,\"name\":\"权限管理\",\"icon\":\"el-icon-s-tools\",\"children\":[{\"id\":31,\"name\":\"角色列表\",\"path\":\"/role\",\"children\":[{\"id\":311,\"name\":\"增\",\"path\":\"add\"},{\"id\":312,\"name\":\"删\",\"path\":\"delete\"},{\"id\":313,\"name\":\"改\",\"path\":\"update\"},{\"id\":314,\"name\":\"查\",\"path\":\"read\"}]},{\"id\":32,\"name\":\"权限列表\",\"path\":\"/right\",\"children\":[{\"id\":321,\"name\":\"增\",\"path\":\"add\"},{\"id\":322,\"name\":\"删\",\"path\":\"delete\"},{\"id\":323,\"name\":\"改\",\"path\":\"update\"},{\"id\":324,\"name\":\"查\",\"path\":\"read\"}]}]}]\'');

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('1', '啊', '123', 'man');
INSERT INTO `students` VALUES ('2', '一正369aa', '123321kk', 'male');
INSERT INTO `students` VALUES ('4', '一正369aa12', '123321', 'male');
INSERT INTO `students` VALUES ('5', '阿达', '123321', 'male');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `myRoleId` int(11) NOT NULL,
  `userInfoId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', 'admin', '1', null);
