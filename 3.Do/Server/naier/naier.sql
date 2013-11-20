/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : naier

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2013-11-20 16:51:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `active`
-- ----------------------------
DROP TABLE IF EXISTS `active`;
CREATE TABLE `active` (
  `id` int(11) NOT NULL auto_increment,
  `active_title` varchar(50) NOT NULL,
  `active_poster` varchar(200) NOT NULL,
  `active_start` varchar(20) NOT NULL,
  `active_end` varchar(20) NOT NULL,
  `active_tel` varchar(20) NOT NULL,
  `active_description` text NOT NULL,
  `active_description_interface` text,
  `update_time` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of active
-- ----------------------------
INSERT INTO `active` VALUES ('2', '标题1', '/admin/upload/13847772851.jpg', '1月1', '2月1', '135555555551', '<p>阿斯蒂芬1</p>', '<p>阿斯蒂芬1</p>', '0000-00-00 00:00:00');
INSERT INTO `active` VALUES ('3', '覆盖', '', '', '', '', '', '', '0000-00-00 00:00:00');
INSERT INTO `active` VALUES ('4', '金利科技', '', '', '', '', '', '', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for `advise`
-- ----------------------------
DROP TABLE IF EXISTS `advise`;
CREATE TABLE `advise` (
  `id` int(11) NOT NULL auto_increment,
  `custom_id` int(11) default NULL,
  `custom_cellphone` varchar(20) NOT NULL,
  `advise_content` varchar(500) NOT NULL,
  `status` varchar(5) NOT NULL default '0',
  `update_time` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of advise
-- ----------------------------
INSERT INTO `advise` VALUES ('1', '17', '234234', 'asdfsadf', '0', '0000-00-00 00:00:00');
INSERT INTO `advise` VALUES ('2', '1', '234234', 'asdfsadf', '0', '0000-00-00 00:00:00');
INSERT INTO `advise` VALUES ('3', '1', '234234', 'asdfsadf', '0', '0000-00-00 00:00:00');
INSERT INTO `advise` VALUES ('4', '1', '234234', 'asdfsadf', '0', '0000-00-00 00:00:00');
INSERT INTO `advise` VALUES ('6', '18', '234234234', '234234', '1', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for `business`
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
  `id` int(11) NOT NULL auto_increment,
  `busi_title` varchar(50) NOT NULL,
  `busi_price` varchar(10) default NULL,
  `busi_introduce` text NOT NULL,
  `busi_introduce_interface` text,
  `update_time` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES ('2', '', '', '', '', '0000-00-00 00:00:00');
INSERT INTO `business` VALUES ('3', '业务1请问', '77请问', '<p>阿斯蒂芬请问请问</p>', '<p>阿斯蒂芬请问请问</p>', '0000-00-00 00:00:00');
INSERT INTO `business` VALUES ('4', '', '', '', '', '0000-00-00 00:00:00');
INSERT INTO `business` VALUES ('5', 'sdDF', '', '', '', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for `company`
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `ID` int(11) NOT NULL auto_increment,
  `company_pictures1` varchar(200) default NULL,
  `company_pictures2` varchar(200) default NULL,
  `company_pictures3` varchar(200) default NULL,
  `company_pictures4` varchar(200) default NULL,
  `company_about` text,
  `company_about_interface` text,
  `company_address` varchar(50) default NULL,
  `company_email` varchar(30) default NULL,
  `company_qq` varchar(30) default NULL,
  `company_advise_tel` varchar(20) default NULL,
  `company_complain_tel` varchar(20) default NULL,
  `company_secretary_tel` varchar(20) default NULL,
  `company_keeper_tel` varchar(20) default NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', '/admin/upload/13847504821.jpg', '/admin/upload/13847504822.jpg', '/admin/upload/13847504823.jpg', '/admin/upload/13847504824.jpg', '<p><span style=\"font-size: 20px;\"><em><strong>关于我们</strong></em></span><br/></p>', '<p><span style=\\\"font-size: 20px;\\\"><em><strong>关于我们</strong></em></span><br/></p>', '成华区东路128号', '234234@qq.com', '4124232323', '028-12345678', '028-12345678', '028-12345678', '028-12345678', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for `complain`
-- ----------------------------
DROP TABLE IF EXISTS `complain`;
CREATE TABLE `complain` (
  `id` int(11) NOT NULL auto_increment,
  `custom_id` int(10) NOT NULL,
  `keeper_id` int(11) NOT NULL,
  `complain_content` varchar(500) NOT NULL,
  `status` varchar(5) NOT NULL default '0',
  `update_time` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of complain
-- ----------------------------
INSERT INTO `complain` VALUES ('8', '17', '18', '做事神戳戳的', '0', '0000-00-00 00:00:00');
INSERT INTO `complain` VALUES ('9', '18', '19', '没吃干饭一样的', '0', '0000-00-00 00:00:00');
INSERT INTO `complain` VALUES ('10', '17', '18', '', '0', '0000-00-00 00:00:00');
INSERT INTO `complain` VALUES ('11', '17', '18', '', '0', '0000-00-00 00:00:00');
INSERT INTO `complain` VALUES ('12', '17', '18', '', '0', '0000-00-00 00:00:00');
INSERT INTO `complain` VALUES ('13', '17', '18', '', '0', '0000-00-00 00:00:00');
INSERT INTO `complain` VALUES ('14', '17', '18', '', '1', '0000-00-00 00:00:00');
INSERT INTO `complain` VALUES ('16', '17', '18', '', '1', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for `custom`
-- ----------------------------
DROP TABLE IF EXISTS `custom`;
CREATE TABLE `custom` (
  `id` int(11) NOT NULL auto_increment,
  `custom_username` varchar(20) NOT NULL,
  `custom_cellphone` varchar(20) NOT NULL,
  `custom_password` varchar(20) NOT NULL,
  `custom_name` varchar(10) NOT NULL,
  `custom_address` varchar(50) NOT NULL,
  `update_time` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of custom
-- ----------------------------
INSERT INTO `custom` VALUES ('17', 'xiaoming', '13568974453', '123456', '小明', '成都青羊区', '');
INSERT INTO `custom` VALUES ('18', 'xiaohong', '123213123', '', '小红', '成都青羊', '');

-- ----------------------------
-- Table structure for `keeper_info`
-- ----------------------------
DROP TABLE IF EXISTS `keeper_info`;
CREATE TABLE `keeper_info` (
  `id` int(11) NOT NULL auto_increment,
  `keeper_type_id` int(11) NOT NULL,
  `keeper_name` varchar(20) NOT NULL,
  `keeper_gender` varchar(10) NOT NULL,
  `keeper_age` varchar(10) NOT NULL,
  `keeper_photo` varchar(200) NOT NULL,
  `keeper_experience` varchar(20) NOT NULL,
  `keeper_level` varchar(10) NOT NULL,
  `keeper_professional` varchar(5) NOT NULL,
  `keeper_attitude` varchar(5) NOT NULL,
  `keeper_hardworking` varchar(5) NOT NULL,
  `keeper_attentive` varchar(5) NOT NULL,
  `keeper_special` varchar(50) NOT NULL,
  `keeper_introduce` varchar(500) NOT NULL,
  `keeper_ispush` varchar(5) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of keeper_info
-- ----------------------------
INSERT INTO `keeper_info` VALUES ('18', '2', '李嫂', '女', '35', '', '4', '4', '4', '4', '4', '4', '4', '从事该行业多年', '1', '0000-00-00 00:00:00');
INSERT INTO `keeper_info` VALUES ('19', '3', '张哥', '男', '40', '', '3', '3', '3', '3', '3', '3', '3', '从事该行业多年', '1', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for `keeper_order`
-- ----------------------------
DROP TABLE IF EXISTS `keeper_order`;
CREATE TABLE `keeper_order` (
  `id` int(11) NOT NULL auto_increment,
  `custom_id` int(11) NOT NULL,
  `keeper_id` int(11) NOT NULL,
  `start_time` varchar(10) NOT NULL,
  `end_time` varchar(10) NOT NULL,
  `order_description` varchar(200) default NULL,
  `order_status` varchar(5) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of keeper_order
-- ----------------------------
INSERT INTO `keeper_order` VALUES ('4', '18', '19', '2013年10月1', '2013年11月1', '煮饭1', '', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for `keeper_type`
-- ----------------------------
DROP TABLE IF EXISTS `keeper_type`;
CREATE TABLE `keeper_type` (
  `id` int(11) NOT NULL auto_increment,
  `type_name` varchar(20) NOT NULL,
  `type_code` varchar(20) NOT NULL,
  `type_description` varchar(100) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of keeper_type
-- ----------------------------
INSERT INTO `keeper_type` VALUES ('1', '生活咨询', 'type_code_1', '生活咨询的描述', '2013-11-12 11:11:11');
INSERT INTO `keeper_type` VALUES ('2', '形象设计', 'type_code_2', '形象设计的描述', '2013-11-12 11:11:11');
INSERT INTO `keeper_type` VALUES ('3', '居家环境', 'type_code_3', '居家环境的描述', '2013-11-12 11:11:11');
INSERT INTO `keeper_type` VALUES ('4', '营养健康', 'type_code_4', '营养健康的描述', '2013-11-12 11:11:11');
INSERT INTO `keeper_type` VALUES ('5', '住家明星', 'type_code_5', '住家明星的描述', '2013-11-12 11:11:11');
INSERT INTO `keeper_type` VALUES ('6', '装修咨询', 'type_code_6', '装修咨询的描述', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for `secretary_info`
-- ----------------------------
DROP TABLE IF EXISTS `secretary_info`;
CREATE TABLE `secretary_info` (
  `id` int(11) NOT NULL auto_increment,
  `type_id` int(11) NOT NULL,
  `region_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `address` varchar(100) default NULL,
  `tel` varchar(20) default NULL,
  `images` varchar(100) default NULL,
  `description` varchar(200) default NULL,
  `special` varchar(200) default NULL,
  `price` varchar(50) default NULL,
  `update_time` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of secretary_info
-- ----------------------------
INSERT INTO `secretary_info` VALUES ('6', '0', '0', '', '', '', '', '', '', '', '2013-11-17 21:03');
INSERT INTO `secretary_info` VALUES ('7', '8', '5', '阿斯蒂芬', '', '', '/admin/upload/13845890211.jpg', '', '', '', '2013-11-16 16:03');
INSERT INTO `secretary_info` VALUES ('8', '9', '5', 'asdf ', '', '', '', '', '', '', '2013-11-16 16:06');
INSERT INTO `secretary_info` VALUES ('11', '8', '6', '标题', '地址', '电话', '', '描述', '特色', '人均价格', '2013-11-17 14:28');
INSERT INTO `secretary_info` VALUES ('12', '6', '7', '标题1', '地址1', '电话1', '/admin/upload/13846720341.jpg', '描述1', '特色1', '人均价格1', '2013-11-17 15:07');
INSERT INTO `secretary_info` VALUES ('13', '5', '5', '标题1', '地址1', '电话1', '', '描述1', '特色1', '人均价格1', '2013-11-17 14:52');
INSERT INTO `secretary_info` VALUES ('14', '5', '5', '1323', '', '', '', '', '', '', '2013-11-17 15:24');
INSERT INTO `secretary_info` VALUES ('15', '4', '5', '123', '', '', '', '', '', '', '2013-11-17 15:24');
INSERT INTO `secretary_info` VALUES ('16', '5', '5', '123', '', '', '', '', '', '', '2013-11-17 15:24');
INSERT INTO `secretary_info` VALUES ('17', '4', '5', '123', '', '', '', '', '', '', '2013-11-17 15:24');
INSERT INTO `secretary_info` VALUES ('18', '4', '5', '23', '', '', '', '', '', '', '2013-11-17 15:24');
INSERT INTO `secretary_info` VALUES ('19', '5', '5', '234', '', '', '', '', '', '', '2013-11-17 15:33');
INSERT INTO `secretary_info` VALUES ('20', '0', '0', '', '', '', '', '', '', '', '2013-11-17 17:41');
INSERT INTO `secretary_info` VALUES ('21', '0', '0', '', '', '', '', '', '', '', '2013-11-17 17:42');

-- ----------------------------
-- Table structure for `secretary_region`
-- ----------------------------
DROP TABLE IF EXISTS `secretary_region`;
CREATE TABLE `secretary_region` (
  `id` int(11) NOT NULL auto_increment,
  `region_name` varchar(50) NOT NULL,
  `upate_time` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of secretary_region
-- ----------------------------
INSERT INTO `secretary_region` VALUES ('5', '青羊区', '0000-00-00 00:00:00');
INSERT INTO `secretary_region` VALUES ('6', '成华区', '0000-00-00 00:00:00');
INSERT INTO `secretary_region` VALUES ('7', '高新区', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for `secretary_type`
-- ----------------------------
DROP TABLE IF EXISTS `secretary_type`;
CREATE TABLE `secretary_type` (
  `cid` int(11) NOT NULL auto_increment,
  `cat` varchar(50) NOT NULL,
  `pid` int(11) default NULL,
  `upate_time` datetime default NULL,
  PRIMARY KEY  (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of secretary_type
-- ----------------------------
INSERT INTO `secretary_type` VALUES ('1', 'root', null, null);
INSERT INTO `secretary_type` VALUES ('4', '酒店', '1', null);
INSERT INTO `secretary_type` VALUES ('5', '美食', '1', null);
INSERT INTO `secretary_type` VALUES ('6', '豪华酒店', '4', null);
INSERT INTO `secretary_type` VALUES ('7', '经济酒店', '4', null);
INSERT INTO `secretary_type` VALUES ('8', '西餐', '5', null);
INSERT INTO `secretary_type` VALUES ('9', '面食', '5', null);

-- ----------------------------
-- Table structure for `service`
-- ----------------------------
DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `id` int(11) NOT NULL auto_increment,
  `serv_title` varchar(50) NOT NULL,
  `serv_introduce` text NOT NULL,
  `serv_introduce_interface` text,
  `update_time` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of service
-- ----------------------------
INSERT INTO `service` VALUES ('2', 'sadfsdaf234234', '<p><span style=\"font-size: 16px;\">2342342</span>34324</p>', '<p><span style=\\\"font-size: 16px;\\\">2342342</span>34324</p>', '0000-00-00 00:00:00');
INSERT INTO `service` VALUES ('3', '阿斯蒂芬', '<p>阿斯蒂芬</p>', '<p>阿斯蒂芬</p>', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for `shop`
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` int(11) NOT NULL auto_increment,
  `shop_name` varchar(20) NOT NULL,
  `shop_tel` varchar(20) NOT NULL,
  `shop_address` varchar(50) NOT NULL,
  `baidu_longitude` varchar(10) NOT NULL,
  `baidu_latitude` varchar(10) NOT NULL,
  `google_longitude` varchar(10) NOT NULL,
  `google_latitude` varchar(10) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('3', '门市a', '135234244a', '成都青羊a', '11a', '324234a', '234a', '234432a', '0000-00-00 00:00:00');
INSERT INTO `shop` VALUES ('4', '阿斯蒂芬', '', '', '', '', '', '', '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL auto_increment,
  `login` varchar(50) NOT NULL default '' COMMENT '登录帐号',
  `password` varchar(100) NOT NULL default '' COMMENT '密码',
  `name` varchar(50) default NULL,
  `phone` varchar(20) default NULL,
  `qq` varchar(20) default NULL,
  `note` varchar(100) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `login_u` USING BTREE (`login`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('22', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin', null, null, null);

-- ----------------------------
-- View structure for `complain_view`
-- ----------------------------
DROP VIEW IF EXISTS `complain_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `complain_view` AS select `complain`.`id` AS `complainid`,`complain`.`custom_id` AS `custom_id`,`complain`.`keeper_id` AS `keeper_id`,`complain`.`complain_content` AS `complain_content`,`complain`.`status` AS `status`,`keeper_info`.`keeper_type_id` AS `keeper_type_id`,`keeper_info`.`keeper_name` AS `keeper_name`,`keeper_info`.`id` AS `info.id`,`keeper_type`.`id` AS `type_id`,`keeper_type`.`type_name` AS `type_name`,`custom`.`id` AS `id`,`custom`.`custom_username` AS `custom_username`,`custom`.`custom_name` AS `custom_name` from ((((`keeper_info` join `complain`) join `keeper_type`) join `custom`) join `orderinfo_view`) where ((`complain`.`custom_id` = `custom`.`id`) and (`complain`.`keeper_id` = `keeper_info`.`id`) and (`keeper_info`.`keeper_type_id` = `keeper_type`.`id`)) ;

-- ----------------------------
-- View structure for `orderinfo_view`
-- ----------------------------
DROP VIEW IF EXISTS `orderinfo_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `orderinfo_view` AS select `custom`.`custom_username` AS `custom_username`,`custom`.`custom_name` AS `custom_name`,`keeper_info`.`keeper_name` AS `keeper_name`,`keeper_info`.`keeper_type_id` AS `keeper_type_id`,`keeper_type`.`id` AS `id`,`keeper_type`.`type_name` AS `type_name`,`keeper_order`.`start_time` AS `start_time`,`keeper_order`.`end_time` AS `end_time`,`keeper_order`.`custom_id` AS `custom_id`,`keeper_order`.`keeper_id` AS `keeper_id`,`keeper_order`.`order_description` AS `order_description`,`keeper_order`.`id` AS `orderid` from (((`keeper_order` join `keeper_info`) join `keeper_type`) join `custom`) where ((`keeper_order`.`custom_id` = `custom`.`id`) and (`keeper_order`.`keeper_id` = `keeper_info`.`id`) and (`keeper_info`.`keeper_type_id` = `keeper_type`.`id`)) ;
