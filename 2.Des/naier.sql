/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.25a : Database - naier
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`naier` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `naier`;

/*Table structure for table `active` */

DROP TABLE IF EXISTS `active`;

CREATE TABLE `active` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `active_title` varchar(50) NOT NULL,
  `active_poster` varchar(200) NOT NULL,
  `active_start` varchar(20) NOT NULL,
  `active_end` varchar(20) NOT NULL,
  `active_tel` varchar(20) NOT NULL,
  `active_description` text NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `active` */

/*Table structure for table `advise` */

DROP TABLE IF EXISTS `advise`;

CREATE TABLE `advise` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `custom_cellphone` varchar(20) NOT NULL,
  `advise_content` varchar(500) NOT NULL,
  `status` varchar(5) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `advise` */

/*Table structure for table `business` */

DROP TABLE IF EXISTS `business`;

CREATE TABLE `business` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `busi_title` varchar(50) NOT NULL,
  `busi_price` varchar(10) DEFAULT NULL,
  `busi_introduce` text NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `business` */

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `company_pictures` varchar(50) NOT NULL,
  `company_about` text NOT NULL,
  `company_address` varchar(50) NOT NULL,
  `company_email` varchar(30) NOT NULL,
  `company_qq` varchar(30) NOT NULL,
  `company_advise_tel` varchar(20) NOT NULL,
  `company_complain_tel` varchar(20) NOT NULL,
  `company_secretary_tel` varchar(20) DEFAULT NULL,
  `company_keeper_tel` varchar(20) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `company` */

/*Table structure for table `complain` */

DROP TABLE IF EXISTS `complain`;

CREATE TABLE `complain` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `custom_id` int(10) NOT NULL,
  `keeper_id` int(11) NOT NULL,
  `complain_content` varchar(500) NOT NULL,
  `status` varchar(5) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `complain` */

/*Table structure for table `custom` */

DROP TABLE IF EXISTS `custom`;

CREATE TABLE `custom` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `custom_username` varchar(20) NOT NULL,
  `custom_cellphone` varchar(20) NOT NULL,
  `custom_password` varchar(20) NOT NULL,
  `custom_name` varchar(10) NOT NULL,
  `custom_address` varchar(50) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `custom` */

/*Table structure for table `keeper_info` */

DROP TABLE IF EXISTS `keeper_info`;

CREATE TABLE `keeper_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `keeper_type_id` int(11) NOT NULL,
  `keeper_name` varchar(20) NOT NULL,
  `keeper_gender` varchar(10) NOT NULL,
  `keeper_age` varchar(10) NOT NULL,
  `keeper_photo` varchar(20) NOT NULL,
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
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `keeper_info` */

/*Table structure for table `keeper_order` */

DROP TABLE IF EXISTS `keeper_order`;

CREATE TABLE `keeper_order` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `custom_id` int(11) NOT NULL,
  `keeper_id` int(11) NOT NULL,
  `keeper_type_id` int(11) NOT NULL,
  `start_time` varchar(10) NOT NULL,
  `endtime` varchar(10) NOT NULL,
  `order_description` varchar(200) DEFAULT NULL,
  `order_status` varchar(5) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `keeper_order` */

/*Table structure for table `keeper_type` */

DROP TABLE IF EXISTS `keeper_type`;

CREATE TABLE `keeper_type` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) NOT NULL,
  `type_code` varchar(20) NOT NULL,
  `type_description` varchar(100) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `keeper_type` */

insert  into `keeper_type`(`ID`,`type_name`,`type_code`,`type_description`,`update_time`) values (1,'生活咨询','type_code_1','生活咨询的描述','2013-11-12 11:11:11'),(2,'形象设计','type_code_2','形象设计的描述','2013-11-12 11:11:11'),(3,'居家环境','type_code_3','居家环境的描述','2013-11-12 11:11:11'),(4,'营养健康','type_code_4','营养健康的描述','2013-11-12 11:11:11'),(5,'住家明星','type_code_5','住家明星的描述','2013-11-12 11:11:11');

/*Table structure for table `secretary_info` */

DROP TABLE IF EXISTS `secretary_info`;

CREATE TABLE `secretary_info` (
  `ID` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `region_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `images` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `special` varchar(200) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `secretary_info` */

/*Table structure for table `secretary_region` */

DROP TABLE IF EXISTS `secretary_region`;

CREATE TABLE `secretary_region` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `region_name` varchar(50) NOT NULL,
  `upate_time` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `secretary_region` */

/*Table structure for table `secretary_type` */

DROP TABLE IF EXISTS `secretary_type`;

CREATE TABLE `secretary_type` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) NOT NULL,
  `parent_type_id` int(11) NOT NULL,
  `upate_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `secretary_type` */

/*Table structure for table `service` */

DROP TABLE IF EXISTS `service`;

CREATE TABLE `service` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `serv_title` varchar(50) NOT NULL,
  `serv_introduce` text NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `service` */

/*Table structure for table `shop` */

DROP TABLE IF EXISTS `shop`;

CREATE TABLE `shop` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `shop_name` varchar(20) NOT NULL,
  `shop_tel` varchar(20) NOT NULL,
  `shop_address` varchar(50) NOT NULL,
  `baidu_longitude ` varchar(10) NOT NULL,
  `baidu_latitude` varchar(10) NOT NULL,
  `google_longitude ` varchar(10) NOT NULL,
  `google_latitude` varchar(10) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `shop` */

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`ID`,`login`,`password`,`update_time`) values (1,'admin','123456','2013-11-12 11:43:51');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
