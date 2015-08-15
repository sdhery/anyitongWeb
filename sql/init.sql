/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.5.23 : Database - framework
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`framework` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `framework`;

/*Table structure for table `prd_contacts` */

DROP TABLE IF EXISTS `prd_contacts`;

CREATE TABLE `prd_contacts` (
  `contactsId` int(22) NOT NULL AUTO_INCREMENT,
  `customerId` int(22) NOT NULL,
  `realName` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `sex` int(2) DEFAULT NULL COMMENT '性别',
  `bind` int(2) DEFAULT NULL COMMENT '关系',
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '固定电话',
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `mobilePhone` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '移动电话',
  `createTime` datetime NOT NULL,
  `lastModifiedTime` datetime NOT NULL,
  PRIMARY KEY (`contactsId`)
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `prd_contacts` */

insert  into `prd_contacts`(`contactsId`,`customerId`,`realName`,`sex`,`bind`,`phone`,`address`,`mobilePhone`,`createTime`,`lastModifiedTime`) values (12,10,'2',1,2,'2','2','2','2015-07-16 20:37:11','2015-07-16 20:37:11'),(11,10,'2',1,2,'2','2','2','2015-07-16 20:37:11','2015-07-16 20:37:11'),(10,9,'2',2,2,'2','2','2','2015-07-14 22:39:08','2015-07-14 22:39:08'),(9,9,'1',1,1,'1','1','1','2015-07-14 22:39:08','2015-07-14 22:39:08'),(13,11,'R',1,NULL,'R','I','U','2015-07-16 20:44:50','2015-07-16 20:44:50'),(14,11,'',1,NULL,'','','','2015-07-16 20:44:50','2015-07-16 20:44:50'),(15,12,'R',1,NULL,'R','I','U','2015-07-16 20:45:44','2015-07-16 20:45:44'),(16,12,'',1,NULL,'','','','2015-07-16 20:45:44','2015-07-16 20:45:44'),(17,13,'R',1,NULL,'R','I','U','2015-07-16 20:45:53','2015-07-16 20:45:53'),(18,13,'',1,NULL,'','','','2015-07-16 20:45:53','2015-07-16 20:45:53'),(19,14,'R',1,NULL,'R','I','U','2015-07-16 20:46:12','2015-07-16 20:46:12'),(20,14,'',1,NULL,'','','','2015-07-16 20:46:12','2015-07-16 20:46:12'),(21,15,'R',1,NULL,'R','I','U','2015-07-16 20:46:51','2015-07-16 20:46:51'),(22,15,'',1,NULL,'','','','2015-07-16 20:46:51','2015-07-16 20:46:51'),(23,16,'R',1,NULL,'R','I','U','2015-07-16 20:52:33','2015-07-16 20:52:33'),(24,16,'',1,NULL,'','','','2015-07-16 20:52:33','2015-07-16 20:52:33'),(25,17,'Y',1,NULL,'4','4','I','2015-07-16 20:54:05','2015-07-16 20:54:05'),(26,17,'',1,NULL,'','','','2015-07-16 20:54:05','2015-07-16 20:54:05'),(27,18,'Y',1,NULL,'U','O','U','2015-07-16 20:55:39','2015-07-16 20:55:39'),(28,18,'',1,NULL,'','','','2015-07-16 20:55:39','2015-07-16 20:55:39'),(29,19,'3',1,NULL,'R','','U','2015-07-16 20:57:30','2015-07-16 20:57:30'),(30,19,'',1,NULL,'','','','2015-07-16 20:57:30','2015-07-16 20:57:30'),(31,20,'U',1,NULL,'I','U','T','2015-07-16 20:59:58','2015-07-16 20:59:58'),(32,20,'',1,NULL,'','','','2015-07-16 20:59:58','2015-07-16 20:59:58'),(33,21,'5塘',1,NULL,'4','W','Q','2015-07-20 21:45:26','2015-07-20 21:45:26'),(34,21,'T',1,NULL,'6','Y','O','2015-07-20 21:45:26','2015-07-20 21:45:26');

/*Table structure for table `prd_customer` */

DROP TABLE IF EXISTS `prd_customer`;

CREATE TABLE `prd_customer` (
  `customerId` int(22) NOT NULL AUTO_INCREMENT,
  `sysUserId` int(22) NOT NULL COMMENT '录入者用户ID',
  `pagerId` varchar(256) COLLATE utf8_bin NOT NULL COMMENT '呼叫器ID',
  `realName` varchar(256) COLLATE utf8_bin NOT NULL,
  `sex` int(2) DEFAULT NULL,
  `purchaseType` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '购买类型',
  `homeSituation` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '居家状况',
  `idCard` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `mobilePhone` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `roadTraffic` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '交通道路',
  `address` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `lastModifiedTime` datetime NOT NULL,
  `mapLongitude` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '地图经度',
  `mapLatitude` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '地图纬度',
  `mapPicPath` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '录入者时地图图片',
  `recordPath` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '被录入者录音',
  `approvalStatus` int(2) NOT NULL DEFAULT '1' COMMENT '编辑状态:默认1.未编辑,2.正在编辑,3.编辑完成,4.审批不通过',
  `approvalDes` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '审批原因',
  `approvalSysUserId` int(22) DEFAULT NULL COMMENT '审批者',
  PRIMARY KEY (`customerId`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `prd_customer` */

insert  into `prd_customer`(`customerId`,`sysUserId`,`pagerId`,`realName`,`sex`,`purchaseType`,`homeSituation`,`idCard`,`mobilePhone`,`phone`,`roadTraffic`,`address`,`createTime`,`lastModifiedTime`,`mapLongitude`,`mapLatitude`,`mapPicPath`,`recordPath`,`approvalStatus`,`approvalDes`,`approvalSysUserId`) values (1,0,'','1111',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-07-02 15:18:34','2015-07-02 15:18:34',NULL,NULL,NULL,NULL,3,NULL,NULL),(2,0,'','root',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2015-07-03 10:30:14','2015-07-03 10:30:14',NULL,NULL,NULL,NULL,3,NULL,NULL),(9,0,'dd','1',2,'0','0',NULL,'1','1','1','1','2015-07-14 22:39:08','2015-07-14 22:39:08',NULL,NULL,NULL,NULL,2,NULL,NULL),(10,6,'888','2',1,'0','0',NULL,'2','2','2','2','2015-07-16 20:37:11','2015-07-16 20:37:11',NULL,NULL,NULL,NULL,1,NULL,NULL),(11,6,'U','1',1,'0','0',NULL,'Q','1','Q','1','2015-07-16 20:44:50','2015-07-16 20:44:50',NULL,NULL,NULL,NULL,1,NULL,NULL),(12,6,'U','1',1,'0','0',NULL,'Q','1','Q','1','2015-07-16 20:45:44','2015-07-16 20:45:44',NULL,NULL,NULL,NULL,1,NULL,NULL),(13,6,'O','1',1,'0','0',NULL,'Q','1','Q','1','2015-07-16 20:45:53','2015-07-16 20:45:53',NULL,NULL,NULL,NULL,1,NULL,NULL),(14,6,'O','1',1,'0','0',NULL,'Q','1','Q','1','2015-07-16 20:46:12','2015-08-02 18:43:36',NULL,NULL,NULL,NULL,2,NULL,12),(15,6,'O','1',1,'0','0',NULL,'Q','1','Q','1','2015-07-16 20:46:51','2015-08-03 11:40:34',NULL,NULL,NULL,NULL,3,NULL,12),(16,6,'O','1',1,'0','0',NULL,'Q','1','Q','1','2015-07-16 20:52:33','2015-08-03 11:40:26',NULL,NULL,NULL,NULL,3,NULL,12),(17,6,'','T',1,'0','0',NULL,'','O','','U','2015-07-16 20:54:05','2015-08-01 22:09:20',NULL,NULL,NULL,NULL,4,'è¾å¥èµææè¯¯',12),(18,6,'','U',1,'0','0',NULL,'','O','I','I','2015-07-16 20:55:39','2015-07-16 20:55:39',NULL,NULL,NULL,NULL,2,NULL,1),(19,6,'','Yy',1,'0','0',NULL,'','','U','I','2015-07-16 20:57:30','2015-07-16 20:57:30',NULL,NULL,NULL,NULL,3,'444',1),(20,6,'O','U',1,'0','0',NULL,'U','P','O','I','2015-07-16 20:59:58','2015-07-21 21:46:05',NULL,NULL,NULL,NULL,3,'3333',1),(21,6,'uu','塘我想',1,'0','0',NULL,'天堂','33','有','天涯','2015-07-20 21:45:26','2015-08-01 22:05:36','113.102187','22.971638',NULL,NULL,3,NULL,12);

/*Table structure for table `prd_health_status` */

DROP TABLE IF EXISTS `prd_health_status`;

CREATE TABLE `prd_health_status` (
  `health_status_id` int(22) NOT NULL AUTO_INCREMENT,
  `customer_id` int(22) NOT NULL,
  `status_type` int(2) NOT NULL COMMENT '健忘状况类型',
  `status_des` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `createTime` datetime NOT NULL,
  `lastModifiedTime` datetime NOT NULL,
  PRIMARY KEY (`health_status_id`),
  KEY `health_status_id` (`health_status_id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `prd_health_status` */

insert  into `prd_health_status`(`health_status_id`,`customer_id`,`status_type`,`status_des`,`createTime`,`lastModifiedTime`) values (2,9,0,'行动良好','2015-07-14 22:39:10','2015-07-14 22:39:10'),(3,10,0,'行动良好','2015-07-16 20:37:11','2015-07-16 20:37:11'),(4,11,0,'行动良好','2015-07-16 20:44:50','2015-07-16 20:44:50'),(5,12,0,'行动良好','2015-07-16 20:45:44','2015-07-16 20:45:44'),(6,13,0,'行动良好','2015-07-16 20:45:53','2015-07-16 20:45:53'),(7,14,0,'行动良好','2015-07-16 20:46:12','2015-07-16 20:46:12'),(8,15,0,'行动良好','2015-07-16 20:46:51','2015-07-16 20:46:51'),(9,16,0,'行动良好','2015-07-16 20:52:33','2015-07-16 20:52:33'),(10,17,0,'行动良好','2015-07-16 20:54:05','2015-07-16 20:54:05'),(11,18,0,'行动良好','2015-07-16 20:55:39','2015-07-16 20:55:39'),(12,19,0,'行动良好','2015-07-16 20:57:30','2015-07-16 20:57:30'),(13,20,0,'行动良好','2015-07-16 20:59:58','2015-07-16 20:59:58'),(14,20,1,'行动良好','2015-07-16 20:59:58','2015-07-16 20:59:58'),(15,21,0,'行动良好','2015-07-20 21:45:26','2015-07-20 21:45:26'),(16,21,1,'行动良好','2015-07-20 21:45:26','2015-07-20 21:45:26');

/*Table structure for table `prd_inf_article` */

DROP TABLE IF EXISTS `prd_inf_article`;

CREATE TABLE `prd_inf_article` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_bin NOT NULL,
  `content` longtext COLLATE utf8_bin,
  `createTime` datetime NOT NULL,
  `lastModifiedTime` datetime NOT NULL,
  KEY `article_id` (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `prd_inf_article` */

insert  into `prd_inf_article`(`article_id`,`title`,`content`,`createTime`,`lastModifiedTime`) values (1,'test234','<p>22</p>\r\n','2015-06-24 15:21:40','2015-06-24 17:17:17'),(2,'2215','<p>22</p>\r\n','2015-06-24 15:23:51','2015-06-24 15:24:44'),(3,'33',NULL,'2015-06-24 15:04:23','2015-06-24 15:12:31'),(4,'44',NULL,'2015-06-24 15:04:23','2015-06-24 15:12:31'),(5,'55',NULL,'2015-06-24 15:04:23','2015-06-24 15:12:31'),(6,'66',NULL,'2015-06-24 15:04:23','2015-06-24 15:12:31'),(7,'77',NULL,'2015-06-24 15:04:23','2015-06-24 15:12:31'),(8,'8',NULL,'2015-06-24 15:04:23','2015-06-24 15:12:31'),(9,'9',NULL,'2015-06-24 15:04:23','2015-06-24 15:12:31'),(10,'10',NULL,'2015-06-24 15:04:23','2015-06-24 15:12:31'),(11,'11',NULL,'2015-06-24 15:04:23','2015-06-24 15:12:31'),(12,'12',NULL,'2015-06-24 15:04:23','2015-06-24 15:12:31'),(13,'13',NULL,'2015-06-24 15:04:23','2015-06-24 15:12:31'),(14,'14',NULL,'2015-06-24 15:04:23','2015-06-24 15:12:31'),(15,'15',NULL,'2015-06-24 15:04:23','2015-06-24 15:12:31'),(16,'17',NULL,'2015-06-24 15:04:23','2015-06-24 15:12:31'),(17,'18',NULL,'2015-06-24 15:04:23','2015-06-24 15:12:31'),(18,'19',NULL,'2015-06-24 15:04:23','2015-06-24 15:12:31'),(19,'20',NULL,'2015-06-24 15:04:23','2015-06-24 15:12:31'),(20,'22222222222222','<p>2222222222</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(21,'2222222222222','<p>2222222222</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(22,'222','<p>2222</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(23,'8888','<p>8888</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(24,'666666666','<p>666666666666</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(25,'tttttttttttttt','<p>yyyyyyyyyyyyyy</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(26,'yyyyyyyyyyyyyyy','<p>yyyyyyyyyyyyyyyyy</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(27,'11','<p>111</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(28,'222','<p>222</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(29,'222','<p>222</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(30,'222','<p>222</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(31,'222','<p>222</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(32,'111','<p>111</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(33,'222','<p>2222</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(34,'222','<p>2222</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(35,'222','<p>2222</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(36,'222','<p>222</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(37,'222','<p>222</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(38,'222','<p>222</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(39,'111','<p>111</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(40,'111','<p>111</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(41,'777','<p>7777</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(42,'888888888888','<p>8888888888888</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(43,'222','<p>2222</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(44,'222','<p>222</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(45,'222','<p>2222</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(46,'333','<p>3333</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(47,'222','<p>222</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(48,'9999999999','<p>9999999999999999</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(49,'777','<p>7777</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(50,'888','<p>8888</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(51,'8888','<p>88888</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(52,'test44','<p>8888</p>\r\n','2015-06-24 15:04:23','2015-06-24 15:12:31'),(53,'今天','<p>今天</p>\r\n','2015-07-01 15:35:48','2015-07-01 15:35:48'),(54,'今天','<p>今天</p>\r\n','2015-07-01 15:38:02','2015-07-01 15:38:02');

/*Table structure for table `prd_relief_agencies` */

DROP TABLE IF EXISTS `prd_relief_agencies`;

CREATE TABLE `prd_relief_agencies` (
  `reliefAgenciesId` int(22) NOT NULL AUTO_INCREMENT,
  `customerId` int(22) NOT NULL,
  `name` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `contact` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '联系人/部门',
  `phone` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
  `address` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `type` int(2) NOT NULL COMMENT '机构类型',
  `createTime` datetime NOT NULL,
  `lastModifiedTime` datetime NOT NULL,
  PRIMARY KEY (`reliefAgenciesId`)
) ENGINE=MyISAM AUTO_INCREMENT=85 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `prd_relief_agencies` */

insert  into `prd_relief_agencies`(`reliefAgenciesId`,`customerId`,`name`,`contact`,`phone`,`address`,`type`,`createTime`,`lastModifiedTime`) values (11,9,'5','5','5','5',3,'2015-07-14 22:39:08','2015-07-14 22:39:08'),(10,9,'4','4','4','4',2,'2015-07-14 22:39:08','2015-07-14 22:39:08'),(9,9,'3','3','3','3',1,'2015-07-14 22:39:08','2015-07-14 22:39:08'),(8,9,'2','2','2','2',0,'2015-07-14 22:39:08','2015-07-14 22:39:08'),(7,9,'1','1','1','1',5,'2015-07-14 22:39:08','2015-07-14 22:39:08'),(12,9,'6','6','6','6',4,'2015-07-14 22:39:08','2015-07-14 22:39:08'),(13,10,'2','2','2','3',5,'2015-07-16 20:37:11','2015-07-16 20:37:11'),(14,10,'4','4','4','4',0,'2015-07-16 20:37:11','2015-07-16 20:37:11'),(15,10,'5','5','5','5',1,'2015-07-16 20:37:11','2015-07-16 20:37:11'),(16,10,'6','6','6','6',2,'2015-07-16 20:37:11','2015-07-16 20:37:11'),(17,10,'7','7','7','7',3,'2015-07-16 20:37:11','2015-07-16 20:37:11'),(18,10,'8','8','8','8',4,'2015-07-16 20:37:11','2015-07-16 20:37:11'),(19,11,'R','U','I','O',5,'2015-07-16 20:44:50','2015-07-16 20:44:50'),(20,11,'','','','',0,'2015-07-16 20:44:50','2015-07-16 20:44:50'),(21,11,'','','','',1,'2015-07-16 20:44:50','2015-07-16 20:44:50'),(22,11,'','','','',2,'2015-07-16 20:44:50','2015-07-16 20:44:50'),(23,11,'','','','',3,'2015-07-16 20:44:50','2015-07-16 20:44:50'),(24,11,'','','','',4,'2015-07-16 20:44:50','2015-07-16 20:44:50'),(25,12,'R','U','I','O',5,'2015-07-16 20:45:44','2015-07-16 20:45:44'),(26,12,'','','','',0,'2015-07-16 20:45:44','2015-07-16 20:45:44'),(27,12,'','','','',1,'2015-07-16 20:45:44','2015-07-16 20:45:44'),(28,12,'','','','',2,'2015-07-16 20:45:44','2015-07-16 20:45:44'),(29,12,'','','','',3,'2015-07-16 20:45:44','2015-07-16 20:45:44'),(30,12,'','','','',4,'2015-07-16 20:45:44','2015-07-16 20:45:44'),(31,13,'R','U','I','O',5,'2015-07-16 20:45:53','2015-07-16 20:45:53'),(32,13,'','','','',0,'2015-07-16 20:45:53','2015-07-16 20:45:53'),(33,13,'','','','',1,'2015-07-16 20:45:53','2015-07-16 20:45:53'),(34,13,'','','','',2,'2015-07-16 20:45:53','2015-07-16 20:45:53'),(35,13,'','','','',3,'2015-07-16 20:45:53','2015-07-16 20:45:53'),(36,13,'','','','',4,'2015-07-16 20:45:53','2015-07-16 20:45:53'),(37,14,'R','U','I','O',5,'2015-07-16 20:46:12','2015-07-16 20:46:12'),(38,14,'','','','',0,'2015-07-16 20:46:12','2015-07-16 20:46:12'),(39,14,'','','','',1,'2015-07-16 20:46:12','2015-07-16 20:46:12'),(40,14,'','','','',2,'2015-07-16 20:46:12','2015-07-16 20:46:12'),(41,14,'','','','',3,'2015-07-16 20:46:12','2015-07-16 20:46:12'),(42,14,'','','','',4,'2015-07-16 20:46:12','2015-07-16 20:46:12'),(43,15,'R','U','I','O',5,'2015-07-16 20:46:51','2015-07-16 20:46:51'),(44,15,'','','','',0,'2015-07-16 20:46:51','2015-07-16 20:46:51'),(45,15,'','','','',1,'2015-07-16 20:46:51','2015-07-16 20:46:51'),(46,15,'','','','',2,'2015-07-16 20:46:51','2015-07-16 20:46:51'),(47,15,'','','','',3,'2015-07-16 20:46:51','2015-07-16 20:46:51'),(48,15,'','','','',4,'2015-07-16 20:46:51','2015-07-16 20:46:51'),(49,16,'R','U','I','O',5,'2015-07-16 20:52:33','2015-07-16 20:52:33'),(50,16,'','','','',0,'2015-07-16 20:52:33','2015-07-16 20:52:33'),(51,16,'','','','',1,'2015-07-16 20:52:33','2015-07-16 20:52:33'),(52,16,'','','','',2,'2015-07-16 20:52:33','2015-07-16 20:52:33'),(53,16,'','','','',3,'2015-07-16 20:52:33','2015-07-16 20:52:33'),(54,16,'','','','',4,'2015-07-16 20:52:33','2015-07-16 20:52:33'),(55,17,'1','3','2','4',5,'2015-07-16 20:54:05','2015-07-16 20:54:05'),(56,17,'','','','',0,'2015-07-16 20:54:05','2015-07-16 20:54:05'),(57,17,'','','','',1,'2015-07-16 20:54:05','2015-07-16 20:54:05'),(58,17,'','','','',2,'2015-07-16 20:54:05','2015-07-16 20:54:05'),(59,17,'','','','',3,'2015-07-16 20:54:05','2015-07-16 20:54:05'),(60,17,'','','','',4,'2015-07-16 20:54:05','2015-07-16 20:54:05'),(61,18,'U','I','U','I',5,'2015-07-16 20:55:39','2015-07-16 20:55:39'),(62,18,'','','','',0,'2015-07-16 20:55:39','2015-07-16 20:55:39'),(63,18,'','','','',1,'2015-07-16 20:55:39','2015-07-16 20:55:39'),(64,18,'','','','',2,'2015-07-16 20:55:39','2015-07-16 20:55:39'),(65,18,'','','','',3,'2015-07-16 20:55:39','2015-07-16 20:55:39'),(66,18,'','','','',4,'2015-07-16 20:55:39','2015-07-16 20:55:39'),(67,19,'3','4','R','4',5,'2015-07-16 20:57:30','2015-07-16 20:57:30'),(68,19,'','','','',0,'2015-07-16 20:57:30','2015-07-16 20:57:30'),(69,19,'','','','',1,'2015-07-16 20:57:30','2015-07-16 20:57:30'),(70,19,'','','','',2,'2015-07-16 20:57:30','2015-07-16 20:57:30'),(71,19,'','','','',3,'2015-07-16 20:57:30','2015-07-16 20:57:30'),(72,19,'','','','',4,'2015-07-16 20:57:30','2015-07-16 20:57:30'),(73,20,'3','U','R','I',5,'2015-07-16 20:59:58','2015-07-16 20:59:58'),(74,20,'','','','',0,'2015-07-16 20:59:58','2015-07-16 20:59:58'),(75,20,'','','','',1,'2015-07-16 20:59:58','2015-07-16 20:59:58'),(76,20,'','','','',2,'2015-07-16 20:59:58','2015-07-16 20:59:58'),(77,20,'','','','',3,'2015-07-16 20:59:58','2015-07-16 20:59:58'),(78,20,'','','','',4,'2015-07-16 20:59:58','2015-07-16 20:59:58'),(79,21,'1','2','3','r',5,'2015-07-20 21:45:26','2015-07-20 21:45:26'),(80,21,'','','','',0,'2015-07-20 21:45:26','2015-07-20 21:45:26'),(81,21,'','','','',1,'2015-07-20 21:45:26','2015-07-20 21:45:26'),(82,21,'','','','',2,'2015-07-20 21:45:26','2015-07-20 21:45:26'),(83,21,'','','','',3,'2015-07-20 21:45:26','2015-07-20 21:45:26'),(84,21,'','','','',4,'2015-07-20 21:45:26','2015-07-20 21:45:26');

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `resource_url` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`resource_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`resource_id`,`resource_name`,`resource_url`) values (1,'所有资源','/admin/**'),(4,'管理员列表','/admin/admin/**'),(2,'登记列表','/admin/customer/**'),(3,'业务员列表','/admin/user/**'),(6,'查看树','/admin/loadTree'),(5,'登录','/admin/main');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `role_value` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`role_value`) values (1,'超级管理员','ROLE_root'),(2,'座席角色','ROLE_seat');

/*Table structure for table `sys_role_dispatcher` */

DROP TABLE IF EXISTS `sys_role_dispatcher`;

CREATE TABLE `sys_role_dispatcher` (
  `role_id` int(11) NOT NULL,
  `obj_id` int(11) NOT NULL,
  `obj_type` int(1) NOT NULL,
  KEY `role_id` (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_role_dispatcher` */

insert  into `sys_role_dispatcher`(`role_id`,`obj_id`,`obj_type`) values (1,1,1),(2,12,1);

/*Table structure for table `sys_role_resource` */

DROP TABLE IF EXISTS `sys_role_resource`;

CREATE TABLE `sys_role_resource` (
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_role_resource` */

insert  into `sys_role_resource`(`role_id`,`resource_id`) values (1,1),(2,2),(2,5),(2,6);

/*Table structure for table `sys_tree` */

DROP TABLE IF EXISTS `sys_tree`;

CREATE TABLE `sys_tree` (
  `tree_id` int(11) NOT NULL AUTO_INCREMENT,
  `tree_name` varchar(256) COLLATE utf8_bin NOT NULL,
  `tree_parent_id` int(11) NOT NULL,
  `url` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`tree_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_tree` */

insert  into `sys_tree`(`tree_id`,`tree_name`,`tree_parent_id`,`url`) values (1,'后台管理',0,NULL),(2,'登记管理',1,NULL),(5,'登记列表',2,'/admin/customer/findCustomerVoList'),(4,'业务员列表',3,'/admin/user/list'),(3,'业务员管理',1,NULL),(6,'权限管理',1,NULL),(7,'管理员管理',6,'/admin/admin/list');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `SYS_USER_ID` int(22) NOT NULL AUTO_INCREMENT,
  `LOGIN_ID` varchar(256) COLLATE utf8_bin NOT NULL,
  `realName` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(256) COLLATE utf8_bin NOT NULL,
  `IS_ADMIN` int(2) NOT NULL DEFAULT '0' COMMENT '是否管理员,0是会员,1是管理员',
  `createTime` datetime NOT NULL,
  `lastModifiedTime` datetime NOT NULL,
  `LAST_LOGIN_TIME` datetime DEFAULT NULL,
  `jobNumber` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '工号',
  PRIMARY KEY (`SYS_USER_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_user` */

insert  into `sys_user`(`SYS_USER_ID`,`LOGIN_ID`,`realName`,`password`,`IS_ADMIN`,`createTime`,`lastModifiedTime`,`LAST_LOGIN_TIME`,`jobNumber`) values (1,'root','超级管理员','e10adc3949ba59abbe56e057f20f883e',1,'2015-07-11 11:46:40','2015-07-11 11:46:45','2015-08-01 17:17:18',NULL),(12,'test1','test1','e10adc3949ba59abbe56e057f20f883e',1,'2015-07-26 18:53:06','2015-07-26 18:53:46','2015-08-07 15:21:01',NULL),(6,'test','何庆权','e10adc3949ba59abbe56e057f20f883e',0,'2015-07-11 15:56:49','2015-07-11 16:40:11','2015-07-23 11:04:54',NULL),(9,'何庆权','何先生','e10adc3949ba59abbe56e057f20f883e',0,'2015-07-22 21:58:38','2015-07-23 14:06:26','2015-07-22 21:59:12',NULL),(11,'test','你好1','e10adc3949ba59abbe56e057f20f883e',1,'2015-07-26 18:07:38','2015-07-26 18:13:28',NULL,NULL);

/*Table structure for table `t1` */

DROP TABLE IF EXISTS `t1`;

CREATE TABLE `t1` (
  `t1_id` int(22) NOT NULL AUTO_INCREMENT,
  `t1_status` int(2) DEFAULT NULL,
  `t_u_id` int(22) DEFAULT NULL,
  `t_a_id` int(22) DEFAULT NULL,
  KEY `t1_id` (`t1_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t1` */

insert  into `t1`(`t1_id`,`t1_status`,`t_u_id`,`t_a_id`) values (1,0,1,NULL),(2,0,1,1),(3,0,1,NULL);

/*Table structure for table `t2` */

DROP TABLE IF EXISTS `t2`;

CREATE TABLE `t2` (
  `t2_id` int(22) NOT NULL AUTO_INCREMENT,
  `t2_name` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `t1_id` int(22) DEFAULT NULL,
  `t2_group_id` int(22) DEFAULT NULL,
  KEY `t2_id` (`t2_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t2` */

insert  into `t2`(`t2_id`,`t2_name`,`t1_id`,`t2_group_id`) values (1,'t2-1-name',1,1),(2,'t2-2-name',3,1);

/*Table structure for table `t_a` */

DROP TABLE IF EXISTS `t_a`;

CREATE TABLE `t_a` (
  `t_a_id` int(22) NOT NULL AUTO_INCREMENT,
  `t_a_name` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `t_a_code` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `t_a_group_id` int(22) DEFAULT NULL,
  KEY `t_a_id` (`t_a_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_a` */

insert  into `t_a`(`t_a_id`,`t_a_name`,`t_a_code`,`t_a_group_id`) values (1,'t_a_name_1','t_a_code_1',1);

/*Table structure for table `t_u` */

DROP TABLE IF EXISTS `t_u`;

CREATE TABLE `t_u` (
  `t_u_id` int(22) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `t_u_group_id` int(22) DEFAULT NULL,
  KEY `t_u_id` (`t_u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_u` */

insert  into `t_u`(`t_u_id`,`u_name`,`t_u_group_id`) values (1,'u-name-1',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
