/*
Navicat MySQL Data Transfer

Source Server         : fortiro
Source Server Version : 50533
Source Host           : localhost:3306
Source Database       : example

Target Server Type    : MYSQL
Target Server Version : 50533
File Encoding         : 65001

Date: 2016-05-13 18:23:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_company：ID、公司名称、状态、联系电话、地址、邮编、电子邮件、联系人、负责人、公司介绍、排序
-- ----------------------------
DROP TABLE IF EXISTS `sys_company`;
CREATE TABLE `sys_company` (
  `CO_ID` varchar(32) NOT NULL COMMENT '公司ID',
  `CO_NAME` varchar(100) NOT NULL COMMENT '公司名称',
  `CO_PHONE` varchar(20) DEFAULT NULL COMMENT '公司电话',
  `CO_ADR` varchar(500) DEFAULT NULL COMMENT '公司地址',
  `CO_ZIP` varchar(10) DEFAULT NULL COMMENT '邮政编码',
  `SORT` int(11) DEFAULT NULL COMMENT '排序列',
  `CO_EMAIL` varchar(100) DEFAULT NULL COMMENT '公司邮件地址',
  `CONTACT` varchar(100) DEFAULT NULL COMMENT '公司联络人',
  `STATUS` char(1) NOT NULL DEFAULT 'E' COMMENT '当前状态,E:有效的,I:无效的',
  `MANAGER` varchar(100) DEFAULT NULL COMMENT '公司负责人',
  `CO_DESC` varchar(500) DEFAULT NULL COMMENT '备注',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(32) DEFAULT NULL COMMENT '修改人',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`CO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司表';

-- ----------------------------
-- Table structure for sys_division：ID、部门名称、公司ID、负责人、电话、地址、状态、备注
-- ----------------------------
DROP TABLE IF EXISTS `sys_division`;
CREATE TABLE `sys_division` (
  `DIV_ID` varchar(32) NOT NULL COMMENT '部门ID',
  `DIV_NAME` varchar(100) NOT NULL COMMENT '部门名称',
  `MANAGER` varchar(32) DEFAULT NULL COMMENT '部门领导对应的USERID',
  `DIV_PHONE` varchar(50) DEFAULT NULL COMMENT '部门电话',
  `DIV_ADR` varchar(500) DEFAULT NULL COMMENT '部门地址',
  `CO_ID` varchar(32) NOT NULL COMMENT '所属公司ID',
  `DIV_DESC` varchar(500) DEFAULT NULL COMMENT '备注',
  `STATUS` char(1) NOT NULL DEFAULT 'E' COMMENT '当前状态,E:有效的,I:无效的',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`DIV_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织部门表';

-- ----------------------------
-- Table structure for sys_permission：ID、菜单名称、菜单代码、上级ID、上级名称、权限代码、权限名称、状态、URL、描述、是否必选
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `PMSN_ID` varchar(32) NOT NULL COMMENT '权限ID',
  `MENU_NAME` varchar(100) NOT NULL COMMENT '菜单名称',
  `MENU_CODE` varchar(100) NOT NULL COMMENT '菜单代码',
  `PRNT_ID` varchar(32) DEFAULT NULL COMMENT '父权限ID',
  `PRNT_NAME` varchar(100) DEFAULT NULL COMMENT '父权限名称',
  `PMSN_CODE` varchar(100) NOT NULL COMMENT '权限代码',
  `PMSN_NAME` varchar(100) NOT NULL COMMENT '权限名称',
  `STATUS` char(1) NOT NULL DEFAULT 'E' COMMENT '当前状态,E:有效的,I:无效的',
  `PMSN_URL` varchar(500) DEFAULT NULL COMMENT '权限对应URL',
  `PMSN_DESC` varchar(500) DEFAULT NULL COMMENT '权限描述',
  `REQUIRED` char(1) DEFAULT 'N' COMMENT '是否必选权限，Y是N否',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`PMSN_ID`),
  UNIQUE KEY `PMSN_CODE` (`PMSN_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';


-- ----------------------------
-- Table structure for sys_post:ID、岗位职称、部门ID、备注、状态
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `POST_ID` varchar(32) NOT NULL COMMENT '岗位ID',
  `POST_NAME` varchar(100) NOT NULL COMMENT '岗位名称',
  `DIV_ID` varchar(32) DEFAULT NULL COMMENT '部门ID',
  `POST_DESC` varchar(500) DEFAULT NULL COMMENT '备注',
  `STATUS` char(1) NOT NULL DEFAULT 'E' COMMENT '当前状态,E:有效的,I:无效的',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`POST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位表';


-- ----------------------------
-- Table structure for sys_post_role：ID、角色ID、职称ID、状态
-- ----------------------------
DROP TABLE IF EXISTS `sys_post_role`;
CREATE TABLE `sys_post_role` (
  `PR_ID` varchar(32) NOT NULL COMMENT '岗位角色配置ID',
  `ROLE_ID` varchar(32) NOT NULL COMMENT '角色ID',
  `POST_ID` varchar(32) NOT NULL COMMENT '岗位ID',
  `STATUS` char(1) NOT NULL DEFAULT 'E' COMMENT '当前状态,E:有效的,I:无效的',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`PR_ID`),
  KEY `FK_PR_ROLE` (`ROLE_ID`),
  KEY `FK_PR_POST` (`POST_ID`),
  CONSTRAINT `FK_PR_POST` FOREIGN KEY (`POST_ID`) REFERENCES `sys_post` (`POST_ID`),
  CONSTRAINT `FK_PR_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位角色表';

-- ----------------------------
-- Records of sys_post_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_prj_role：ID、项目组ID、角色ID、状态
-- ----------------------------
DROP TABLE IF EXISTS `sys_prj_role`;
CREATE TABLE `sys_prj_role` (
  `PR_ID` varchar(32) NOT NULL COMMENT '项目组角色配置ID',
  `PRJ_ID` varchar(32) NOT NULL COMMENT '项目组ID',
  `ROLE_ID` varchar(32) NOT NULL COMMENT '角色ID',
  `STATUS` char(1) NOT NULL DEFAULT 'E' COMMENT '当前状态,E:有效的,I:无效的',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`PR_ID`),
  KEY `FK_PRR_PRJ` (`PRJ_ID`),
  KEY `FK_PRR_ROLE` (`ROLE_ID`),
  CONSTRAINT `FK_PRR_PRJ` FOREIGN KEY (`PRJ_ID`) REFERENCES `sys_project` (`PRJ_ID`),
  CONSTRAINT `FK_PRR_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目组角色表';


-- ----------------------------
-- Table structure for sys_prj_user：ID、项目组ID、用户ID、状态
-- ----------------------------
DROP TABLE IF EXISTS `sys_prj_user`;
CREATE TABLE `sys_prj_user` (
  `PU_ID` varchar(32) NOT NULL COMMENT '项目成员配置ID',
  `PRJ_ID` varchar(32) NOT NULL COMMENT '项目组ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户ID',
  `STATUS` char(1) NOT NULL DEFAULT 'E' COMMENT '当前状态,E:有效的,I:无效的',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`PU_ID`),
  KEY `FK_PU_PRJ` (`PRJ_ID`),
  KEY `FK_PU_USER` (`USER_ID`),
  CONSTRAINT `FK_PU_PRJ` FOREIGN KEY (`PRJ_ID`) REFERENCES `sys_project` (`PRJ_ID`),
  CONSTRAINT `FK_PU_USER` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目组成员表';

-- ----------------------------
-- Table structure for sys_project：ID、项目组名称、介绍、负责人、状态、公司ID
-- ----------------------------
DROP TABLE IF EXISTS `sys_project`;
CREATE TABLE `sys_project` (
  `PRJ_ID` varchar(32) NOT NULL COMMENT '项目ID',
  `PRJ_NAME` varchar(100) NOT NULL COMMENT '项目组名称',
  `PRJ_DESC` varchar(500) DEFAULT NULL COMMENT '项目描述',
  `LEADER` varchar(32) NOT NULL COMMENT '项目领导',
  `STATUS` char(1) NOT NULL DEFAULT 'E' COMMENT '当前状态,E:有效的,I:无效的',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(32) DEFAULT NULL COMMENT '修改人',
  `CO_ID` varchar(32) DEFAULT NULL COMMENT '所属公司ID',
  PRIMARY KEY (`PRJ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目组表';

-- ----------------------------
-- Table structure for sys_role：ID、角色名称、介绍、状态、是否默认角色
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ROLE_ID` varchar(32) NOT NULL COMMENT '角色ID',
  `ROLE_NAME` varchar(100) NOT NULL COMMENT '角色名称',
  `ROLE_DESC` varchar(500) DEFAULT NULL COMMENT '角色描述',
  `STATUS` char(1) NOT NULL DEFAULT 'E' COMMENT '当前状态,E:有效的,I:无效的',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(32) DEFAULT NULL COMMENT '修改人',
  `IS_DEFAULT` char(1) DEFAULT 'N' COMMENT '是否默认角色',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_role_pmsn：ID、角色ID、权限ID、状态
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_pmsn`;
CREATE TABLE `sys_role_pmsn` (
  `RP_ID` varchar(32) NOT NULL COMMENT '权限角色配置ID',
  `ROLE_ID` varchar(32) NOT NULL COMMENT '角色ID',
  `PMSN_ID` varchar(32) NOT NULL COMMENT '权限ID',
  `STATUS` char(1) NOT NULL DEFAULT 'E' COMMENT '当前状态,E:有效的,I:无效的',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`RP_ID`),
  KEY `FK_RP_ROLE` (`ROLE_ID`),
  KEY `FK_RP_PMSN` (`PMSN_ID`),
  CONSTRAINT `FK_RP_PMSN` FOREIGN KEY (`PMSN_ID`) REFERENCES `sys_permission` (`PMSN_ID`),
  CONSTRAINT `FK_RP_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限角色表';

-- ----------------------------
-- Table structure for sys_user:ID、姓名、工号、入职日期、账号、密码、电子邮件、电话、地址、性别、状态、备注、
-- 第一次登陆时间、上一次登录时间、上一次登录IP、最近登录时间、登录次数、是否在线、排序
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` varchar(32) NOT NULL COMMENT '用户ID',
  `USER_NAME` varchar(20) NOT NULL COMMENT '用户名称',
  `USER_NO` varchar(20) NOT NULL COMMENT '员工工号',
  `JOIN_TIME` datetime DEFAULT NULL COMMENT '入职日期',
  `ACCOUNT` varchar(20) NOT NULL COMMENT '用户账号',
  `PASSWORD` varchar(100) DEFAULT NULL COMMENT '密码',
  `USER_EMAIL` varchar(200) DEFAULT NULL COMMENT '电子邮箱',
  `USER_PHONE` varchar(20) DEFAULT NULL COMMENT '用户电话',
  `USER_ADDR` varchar(200) DEFAULT NULL COMMENT '用户地址',
  `GENDER` char(1) DEFAULT 'M' NULL COMMENT '性别:M-男，F-女',
  `FIRST_LOGIN` datetime DEFAULT NULL COMMENT '第一次登录',
  `PREV_LOGIN` datetime DEFAULT NULL COMMENT '上一次登录',
  `PREV_IP` varchar(20) DEFAULT NULL COMMENT '上一次登录IP地址',
  `LAST_LOGIN` datetime DEFAULT NULL COMMENT '最后一次登录',
  `LOGIN_COUNT` varchar(32) DEFAULT NULL COMMENT '登录次数',
  `STATUS` char(1) NOT NULL DEFAULT 'E' COMMENT '当前状态,E:有效的,I:无效的',
  `USER_DESC` varchar(500) DEFAULT NULL COMMENT '备注',
  `IS_ONLINE` int(1) DEFAULT NULL COMMENT '是否在线，1在线0不在线',
  `SORT` int(11) DEFAULT NULL COMMENT '排序列',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for sys_user_pmsn：ID、用户ID、权限ID、状态
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_pmsn`;
CREATE TABLE `sys_user_pmsn` (
  `UPM_ID` varchar(32) NOT NULL COMMENT '用户权限配置ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户ID',
  `PMSN_ID` varchar(32) NOT NULL COMMENT '权限ID',
  `STATUS` char(1) NOT NULL DEFAULT 'E' COMMENT '当前状态,E:有效的,I:无效的',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`UPM_ID`),
  KEY `FK_UPM_USER` (`USER_ID`),
  KEY `FK_UPM_PMSN` (`PMSN_ID`),
  CONSTRAINT `FK_UPM_PMSN` FOREIGN KEY (`PMSN_ID`) REFERENCES `sys_permission` (`PMSN_ID`),
  CONSTRAINT `FK_UPM_USER` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限表';

-- ----------------------------
-- Table structure for sys_user_post：ID、用户ID、职称ID、状态
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `UP_ID` varchar(32) NOT NULL COMMENT '用户岗位配置ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '角色ID',
  `POST_ID` varchar(32) NOT NULL COMMENT '权限ID',
  `STATUS` char(1) NOT NULL DEFAULT 'E' COMMENT '当前状态,E:有效的,I:无效的',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`UP_ID`),
  KEY `FK_UP_USER` (`USER_ID`),
  KEY `FK_UP_POST` (`POST_ID`),
  CONSTRAINT `FK_UP_POST` FOREIGN KEY (`POST_ID`) REFERENCES `sys_post` (`POST_ID`),
  CONSTRAINT `FK_UP_USER` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户岗位表';

-- ----------------------------
-- Table structure for sys_user_role：ID、角色ID、用户ID、状态
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `UR_ID` varchar(32) NOT NULL COMMENT '用户角色配置ID',
  `ROLE_ID` varchar(32) NOT NULL COMMENT '角色ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户ID',
  `STATUS` char(1) NOT NULL DEFAULT 'E' COMMENT '当前状态,E:有效的,I:无效的',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`UR_ID`),
  KEY `FK_UR_ROLE` (`ROLE_ID`),
  KEY `FK_UR_USER` (`USER_ID`),
  CONSTRAINT `FK_UR_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ROLE_ID`),
  CONSTRAINT `FK_UR_USER` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Table structure for lib_book_category：ID、类型名
-- ----------------------------
DROP TABLE IF EXISTS `lib_book_category`;
CREATE TABLE `lib_book_category` (
  `CATEGORY_ID` varchar(32) NOT NULL COMMENT '图书类型ID',
  `CATEGORY_NAME` varchar(100) NOT NULL COMMENT '图书类型名称',
  PRIMARY KEY (`CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图书类型表';

-- ----------------------------
-- Table structure for lib_book_status：ID、状态名
-- ----------------------------
DROP TABLE IF EXISTS `lib_book_status`;
CREATE TABLE `lib_book_status` (
  `STATUS_ID` varchar(32) NOT NULL COMMENT '图书状态ID',
  `STATUS_NAME` varchar(100) NOT NULL COMMENT '图书状态名称',
  PRIMARY KEY (`STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图书借阅状态表';


-- ----------------------------
-- Table structure for lib_book：ID、书名、类型ID、作者、页数、定价、ISBN号、出版社、出版年份、图书封面、内容简介、状态
-- ----------------------------
DROP TABLE IF EXISTS `lib_book`;
CREATE TABLE `lib_book` (
  `BOOK_ID` varchar(32) NOT NULL COMMENT '图书ID',
  `BOOK_TITLE` varchar(100) NOT NULL COMMENT '图书名称',
  `CATEGORY_ID` varchar(32) NOT NULL COMMENT '类型ID',
  `AUTHOR` varchar(32) NOT NULL COMMENT '作者',
  `PAGES` int(11) NOT NULL COMMENT '页数',
  `PRICE` decimal(32,0) NOT NULL COMMENT '定价',
  `ISBN` varchar(20) NOT NULL COMMENT 'ISBN号码',
  `PUBLISHER` varchar(100) NOT NULL COMMENT '出版社' ,
  `EDITION_YEAR`  int(11) NOT NULL COMMENT '版权年份' ,
  `BOOK_IMG` varchar(500) NULL COMMENT '图书封面',
  `BOOK_DESC` varchar(500) DEFAULT NULL COMMENT '备注',
  `STATUS` char(1) NOT NULL DEFAULT 'E' COMMENT '当前状态,E:有效的,I:无效的',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`BOOK_ID`),
  KEY `FK_BOOK_TYPE` (`CATEGORY_ID`),
  CONSTRAINT `FK_BOOK_TYPE` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `lib_book_category` (`CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图书表';

-- ----------------------------
-- Table structure for lib_user_book：ID、成员ID、图书ID、状态
-- ----------------------------
DROP TABLE IF EXISTS `lib_user_book`;
CREATE TABLE `lib_user_book` (
  `UB_ID` varchar(32) NOT NULL COMMENT '用户图书配置ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户ID',
  `BOOK_ID` varchar(32) NOT NULL COMMENT '图书ID',
  `STATUS` char(1) NOT NULL DEFAULT 'E' COMMENT '当前状态,E:有效的,I:无效的',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`UB_ID`),
  KEY `FK_UB_USER` (`USER_ID`),
  KEY `FK_UB_BOOK` (`BOOK_ID`),
  CONSTRAINT `FK_UB_BOOK` FOREIGN KEY (`BOOK_ID`) REFERENCES `lib_book` (`BOOK_ID`),
  CONSTRAINT `FK_UB_USER` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户共享图书表';


-- ----------------------------
-- Table structure for lib_book_borrow：ID、图书ID、借阅人ID、借阅时间、归还时间、借阅状态
-- ----------------------------
DROP TABLE IF EXISTS `lib_book_borrow`;
CREATE TABLE `lib_book_borrow` (
  `BORROW_ID` varchar(32) NOT NULL COMMENT '借阅ID',
  `USER_ID` varchar(32) NOT NULL COMMENT '用户ID',
  `BOOK_ID` varchar(32) NOT NULL COMMENT '图书ID',
  `STATUS_ID` varchar(32) NOT NULL COMMENT '借阅状态ID',
  `BORROW_TIME` datetime DEFAULT NULL COMMENT '借阅时间',
  `RETURN_TIME` datetime DEFAULT NULL COMMENT '归还时间',
  `CREATED` datetime DEFAULT NULL COMMENT '创造日期',
  `LASTMOD` datetime DEFAULT NULL COMMENT '修改日期',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `MODIFYER` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`BORROW_ID`),
  KEY `FK_BB_USER` (`USER_ID`),
  KEY `FK_BB_BOOK` (`BOOK_ID`),
  KEY `FK_BB_STATUS` (`STATUS_ID`),
  CONSTRAINT `FK_BB_BOOK` FOREIGN KEY (`BOOK_ID`) REFERENCES `lib_book` (`BOOK_ID`),
  CONSTRAINT `FK_BB_USER` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`USER_ID`),
  CONSTRAINT `FK_BB_STATUS` FOREIGN KEY (`STATUS_ID`) REFERENCES `lib_book_status` (`STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='借阅记录表';


INSERT INTO `example`.`sys_user` (`USER_ID`, `USER_NAME`, `USER_NO`, `JOIN_TIME`, `ACCOUNT`, `PASSWORD`, `USER_EMAIL`, `USER_PHONE`, `USER_ADDR`, `GENDER`, `FIRST_LOGIN`, `PREV_LOGIN`, `PREV_IP`, `LAST_LOGIN`, `LOGIN_COUNT`, `STATUS`, `USER_DESC`, `IS_ONLINE`, `SORT`, `CREATED`, `LASTMOD`, `CREATER`, `MODIFYER`) VALUES ('9e6706baa946413b878d4fbaa6ec4322', '丁冬亮', '2018001', '2018-01-01 07:55:00', 'dyenigma', '05c6fff2f67c47abd6d555a2ff14aac6', NULL, NULL, NULL, 'M', NULL, NULL, NULL, NULL, NULL, 'E', NULL, NULL, NULL, '2018-04-10 07:55:30', '2018-04-10 07:55:42', NULL, NULL);