/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.53 : Database - owner-sso
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`owner-sso` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `owner-sso`;

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名字',
  `code` varchar(30) DEFAULT NULL COMMENT '菜单编码',
  `type` int(5) DEFAULT NULL COMMENT '菜单类型，0为模块菜单，1为业务系统',
  `desc` varchar(100) DEFAULT NULL COMMENT '菜单描述',
  `status` int(5) DEFAULT NULL COMMENT '状态，0为禁用1为启用',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单url',
  `icon_url` varchar(255) DEFAULT NULL COMMENT '系统iconUrl',
  `pid` bigint(20) DEFAULT NULL COMMENT '父级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

/*Data for the table `menu` */

insert  into `menu`(`id`,`name`,`code`,`type`,`desc`,`status`,`url`,`icon_url`,`pid`) values (1,'一体化监控','grafana',1,'平台运行状态的集中管理，CPU、内存、网络等计算资源监控',1,'http://10.10.9.11:4000/login/generic_oauth','http://127.0.0.1:9999/asset/grafana.png',NULL),(2,'系统管理','xtgl',0,'系统管理',1,'',NULL,NULL),(3,'机构管理','jggl',0,'机构管理',1,'',NULL,2),(4,'用户管理','yhgl',0,'用户管理',1,NULL,NULL,2),(5,'角色管理','jsgl',0,'角色管理',1,NULL,NULL,2),(6,'资源管理','zygl',0,'资源管理',1,NULL,NULL,2),(7,'系统日志','xtrz',0,'系统日志',1,NULL,NULL,2),(8,'个人中心','grzx',0,'个人中心',1,NULL,NULL,NULL),(9,'个人主页','grzy',0,'个人主页',1,NULL,NULL,8),(14,'数据治理','dgp',1,'\'数据采集过程跟踪，数据质量管理，智能纠错减少数据异常，让数据清澈如水',1,'http://127.0.0.1:8082/crDgp/login','http://127.0.0.1:9999/asset/dgp.png',NULL),(15,'数据仓库','kylin',1,'数据仓库管理，数据模型管理，数据多维分析',1,'http://192.168.1.14:7070/kylin/login','http://127.0.0.1:9999/asset/kylin.png',NULL),(16,'数据报表','superset',1,'大数据可视化分析，洞悉数据的蛛丝马迹，发现数据的潜在价值',1,'http://192.168.1.14:8089/login/','http://127.0.0.1:9999/asset/superset.png',NULL);

/*Table structure for table `oauth_access_token` */

DROP TABLE IF EXISTS `oauth_access_token`;

CREATE TABLE `oauth_access_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(255) DEFAULT NULL,
  KEY `token_id_index` (`token_id`),
  KEY `authentication_id_index` (`authentication_id`),
  KEY `user_name_index` (`user_name`),
  KEY `client_id_index` (`client_id`),
  KEY `refresh_token_index` (`refresh_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_access_token` */

/*Table structure for table `oauth_client_details` */

DROP TABLE IF EXISTS `oauth_client_details`;

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` text,
  `autoapprove` varchar(255) DEFAULT 'false',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_client_details` */

insert  into `oauth_client_details`(`client_id`,`resource_ids`,`client_secret`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`autoapprove`) values ('some_id','','some_secret','user','authorization_code','http://localhost:3000',NULL,3600,3600,NULL,'true');

/*Table structure for table `oauth_code` */

DROP TABLE IF EXISTS `oauth_code`;

CREATE TABLE `oauth_code` (
  `code` varchar(255) DEFAULT NULL,
  `authentication` blob,
  KEY `code_index` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_code` */

/*Table structure for table `oauth_refresh_token` */

DROP TABLE IF EXISTS `oauth_refresh_token`;

CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) DEFAULT NULL,
  `token` blob,
  `authentication` blob,
  KEY `token_id_index` (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `oauth_refresh_token` */

/*Table structure for table `organization` */

DROP TABLE IF EXISTS `organization`;

CREATE TABLE `organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `org_name` varchar(50) DEFAULT NULL COMMENT '机构名字',
  `concat_name` varchar(30) DEFAULT NULL COMMENT '联系人名字',
  `concat_phone` varchar(30) DEFAULT NULL COMMENT '联系人电话',
  `concat_address` varchar(100) DEFAULT NULL COMMENT '联系人地址',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int(5) DEFAULT '0' COMMENT '状态，0为禁用，1为启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `organization` */

insert  into `organization`(`id`,`org_name`,`concat_name`,`concat_phone`,`concat_address`,`create_by`,`create_time`,`update_by`,`update_time`,`status`) values (1,'admin','jzt','13650306666','广州市海珠区',NULL,NULL,NULL,NULL,1),(2,'test','test','13650306666','广州市海珠区',NULL,NULL,NULL,NULL,0);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL COMMENT '角色名字',
  `desc` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `org_id` bigint(20) DEFAULT NULL,
  `org_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`desc`,`create_by`,`create_time`,`update_by`,`update_time`,`org_id`,`org_name`) values (1,'admin','系统管理员',0,NULL,NULL,NULL,1,'广州宸瑞'),(2,'user','普通用户',NULL,NULL,NULL,NULL,1,'广州宸瑞'),(5,'test','test',NULL,NULL,NULL,NULL,1,'广州宸瑞');

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;

/*Data for the table `role_menu` */

insert  into `role_menu`(`id`,`role_id`,`menu_id`) values (2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,2,3),(11,2,2),(30,5,3),(31,5,5),(32,5,4),(33,5,6);

/*Table structure for table `system_log` */

DROP TABLE IF EXISTS `system_log`;

CREATE TABLE `system_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `op_phone` varchar(30) DEFAULT NULL COMMENT '手机号',
  `op_name` varchar(30) DEFAULT NULL COMMENT '操作人名字',
  `op_id` bigint(20) DEFAULT NULL COMMENT '操作人id',
  `record_date` datetime DEFAULT NULL COMMENT '记录时间',
  `op_org` varchar(50) DEFAULT NULL COMMENT '所属机构',
  `ip` varchar(255) DEFAULT NULL COMMENT '请求ip',
  `mac_ip` varchar(255) DEFAULT NULL COMMENT '请求macip',
  `op_time` varchar(30) DEFAULT NULL COMMENT '执行时间',
  `op_result` varchar(30) DEFAULT NULL COMMENT '执行结果',
  `op_entity` varchar(50) DEFAULT NULL COMMENT '操作模块',
  `op_action` varchar(50) DEFAULT NULL COMMENT '执行动作',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=449 DEFAULT CHARSET=utf8mb4;

/*Data for the table `system_log` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `nick_name` varchar(30) DEFAULT NULL COMMENT '昵称',
  `desc` varchar(100) DEFAULT NULL COMMENT '描述',
  `role_code` varchar(30) DEFAULT NULL COMMENT '角色代码',
  `role_id` bigint(20) DEFAULT NULL,
  `status` bigint(5) NOT NULL DEFAULT '0' COMMENT '状态（是否启用）',
  `org_code` bigint(20) DEFAULT NULL COMMENT '机构代码',
  `org_name` varchar(30) DEFAULT NULL COMMENT '机构名字',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_admin` int(5) DEFAULT '0' COMMENT '是否为管理员',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`id`,`user_name`,`password`,`email`,`phone`,`nick_name`,`desc`,`role_code`,`role_id`,`status`,`org_code`,`org_name`,`create_by`,`create_time`,`update_by`,`update_time`,`is_admin`) values (1,'admin','d033e22ae348aeb5660fc2140aec35850c4da997','12306@qq.com','13650304233','admin','系统管理员','admin',1,1,1,'admin',NULL,NULL,NULL,NULL,1),(2,'dev01','d033e22ae348aeb5660fc2140aec35850c4da997','666@qq.com','13650305153','dev01','测试人员','user',2,0,2,'test',NULL,NULL,NULL,NULL,0),(8,'dev02','d033e22ae348aeb5660fc2140aec35850c4da997','999@qq.com','13540305153','dev','系统开发人员','admin',2,1,2,'test',NULL,NULL,NULL,NULL,0);

/*Table structure for table `user_menu` */

DROP TABLE IF EXISTS `user_menu`;

CREATE TABLE `user_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单模块id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_menu` */

insert  into `user_menu`(`id`,`user_id`,`menu_id`) values (1,1,1),(20,1,10),(21,1,11),(23,1,13),(24,1,14),(25,1,15),(26,1,16);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`user_id`,`role_id`) values (1,1,1),(3,2,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
