/*
SQLyog Ultimate v8.32
MySQL - 5.6.50 : Database - sdlcksups
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sdlcksups` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sdlcksups`;

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` int(11) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求地址',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2006 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限）',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `salt` varchar(20) DEFAULT '' COMMENT '盐加密',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Table structure for table `sys_user_online` */

DROP TABLE IF EXISTS `sys_user_online`;

CREATE TABLE `sys_user_online` (
  `sessionId` varchar(50) NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) DEFAULT '0' COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在线用户记录';

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';



-- ----------------------------
-- Table structure for api_log
-- ----------------------------
DROP TABLE IF EXISTS `api_log`;
CREATE TABLE `api_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `api_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口地址',
  `request_params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `response_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '响应状态码',
  `response_body` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '响应信息',
  `request_header` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求头部信息',
  `create_time` datetime NULL DEFAULT NULL COMMENT '调用时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for create_order
-- ----------------------------
DROP TABLE IF EXISTS `create_order`;
CREATE TABLE `create_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `response_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '响应状态码',
  `tracking_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方转运单号',
  `label_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '面单地址',
  `format` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '面单类型  PDF/PNG',
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口返回订单号，接口返回值',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格，接口返回值',
  `price_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格明细，接口返回值',
  `package_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '基础运费，接口返回值',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结果',
  `pdf_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '本地保存的面单地址 pdf',
  `png_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '本地保存的面单地址 png',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of create_order
-- ----------------------------


-- ----------------------------
-- Table structure for provice
-- ----------------------------
DROP TABLE IF EXISTS `provice`;
CREATE TABLE `provice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'US' COMMENT '国家编号',
  `state_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '州/省二字简码',
  `state_name` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '州/省名字',
  `state_name_cn` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_flag` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '美国州信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of provice
-- ----------------------------
INSERT INTO `provice` VALUES (8, 'US', 'AL', 'Alabama', '亚拉巴马', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (9, 'US', 'AK', 'Alaska', '阿拉斯加', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (10, 'US', 'AZ', 'Arizona', '亚利桑那', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (11, 'US', 'AR', 'Arkansas', '阿肯色', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (12, 'US', 'CA', 'California', '加利福尼亚', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (13, 'US', 'CO', 'Colorado', '科罗拉多', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (14, 'US', 'CT', 'Connecticut', '康涅狄格', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (15, 'US', 'DE', 'Delaware', '特拉华', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (16, 'US', 'DC', 'District of Columbia', '哥伦比亚特区', '2019-02-20 17:48:32', '2019-02-20 17:47:57', 0);
INSERT INTO `provice` VALUES (17, 'US', 'FL', 'Florida', '佛罗里达', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (18, 'US', 'GA', 'Georgia', '佐治亚', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (19, 'US', 'HI', 'Hawaii', '夏威夷', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (20, 'US', 'ID', 'Idaho', '爱达荷', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (21, 'US', 'IL', 'Illinois', '伊利诺斯', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (22, 'US', 'IN', 'Indiana', '印第安纳', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (23, 'US', 'IA', 'Iowa', '爱荷华', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (24, 'US', 'KS', 'Kansas', '堪萨斯', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (25, 'US', 'KY', 'Kentucky', '肯塔基', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (26, 'US', 'LA', 'Louisiana', '路易斯安那', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (27, 'US', 'ME', 'Maine', '缅因', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (28, 'US', 'MD', 'Maryland', '马里兰', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (29, 'US', 'MA', 'Massachusetts', '马萨诸塞', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (30, 'US', 'MI', 'Michigan', '密歇根', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (31, 'US', 'MN', 'Minnesota', '明尼苏达', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (32, 'US', 'MS', 'Mississippi', '密西西比', '2019-02-20 17:44:57', '2019-02-20 17:44:57', 0);
INSERT INTO `provice` VALUES (33, 'US', 'MO', 'Missouri', '密苏里', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (34, 'US', 'MT', 'Montana', '蒙大拿', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (35, 'US', 'NE', 'Nebraska', '内布拉斯加', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (36, 'US', 'NV', 'Nevada', '内华达', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (37, 'US', 'NH', 'New Hampshire', '新罕布什尔', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (38, 'US', 'NJ', 'New Jersey', '新泽西', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (39, 'US', 'NM', 'New Mexico', '新墨西哥', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (40, 'US', 'NY', 'New York', '纽约', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (41, 'US', 'NC', 'North', '北卡罗来纳', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (42, 'US', 'ND', 'North', '北达科他', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (43, 'US', 'OH', 'Ohio', '俄亥俄', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (44, 'US', 'OK', 'Oklahoma', '俄克拉何马', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (45, 'US', 'OR', 'Oregon', '俄勒冈', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (46, 'US', 'PA', 'Pennsylvania', '宾夕法尼亚', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (47, 'US', 'RI', 'Rhode Island', '罗得岛', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (48, 'US', 'SC', 'South Carolina', '南卡罗来纳', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (49, 'US', 'SD', 'South Dakota', '南达科他', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (50, 'US', 'TN', 'Tennessee', '田纳西', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (51, 'US', 'TX', 'Texas', '得克萨斯', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (52, 'US', 'UT', 'Utah', '犹他', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (53, 'US', 'VT', 'Vermont', '佛蒙特', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (54, 'US', 'VA', 'Virginia', '弗吉尼亚', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (55, 'US', 'WA', 'Washington', '华盛顿', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (56, 'US', 'WV', 'West Virginia', '西弗吉尼亚', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (57, 'US', 'WI', 'Wisconsin', '威斯康星', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);
INSERT INTO `provice` VALUES (58, 'US', 'WY', 'Wyoming', '怀俄明', '2019-02-20 17:44:58', '2019-02-20 17:44:58', 0);

-- ----------------------------
-- Table structure for sys_param
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `app_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商家授权码',
  `secret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'sign规则必须字段',
  `interface_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口地址',
  `service_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '渠道代码 销售确认',
  `label_size` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '面单大小 4*6/A4',
  `signature_service` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名服务，不传或传0: 无签名 、1：Adult Signature 2: Direct Signature',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL,
  `param_note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数说明',
  `url_type` int(2) NOT NULL DEFAULT 0 COMMENT '逻辑删除标记 默认0 删除1',
  `deleted_flag` int(2) NOT NULL DEFAULT 0 COMMENT '逻辑删除标记 默认0 删除1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 98 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统配置表' ROW_FORMAT = Dynamic;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`menu_name`,`parent_id`,`order_num`,`url`,`menu_type`,`visible`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (2000,'订单管理',0,1,'#','C','0','order:manager:view','fa fa-bars','',now(),'','',''),(2001,'订单列表',2000,1,'/order/view','C','0','order:manager:list','#','',now(),'',NULL,'');
insert  into `sys_menu`(`menu_id`,`menu_name`,`parent_id`,`order_num`,`url`,`menu_type`,`visible`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (3000,'日志管理',0,2,'#','C','0','apilog:manager:view','fa fa-bars','',now(),'','',''),(3001,'日志列表',3000,1,'/apilog/view','C','0','apilog:manager:list','#','',now(),'',NULL,'');
insert  into `sys_menu`(`menu_id`,`menu_name`,`parent_id`,`order_num`,`url`,`menu_type`,`visible`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (4000,'参数管理',0,3,'#','C','0','sysparam:manager:view','fa fa-bars','',now(),'','',''),(4001,'参数设置',4000,1,'/sysparam/view','C','0','sysparam:manager:list','#','',now(),'',NULL,'');

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`role_key`,`role_sort`,`data_scope`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,'管理员','admin',1,'1','0','0','admin','2018-03-16 11:33:00','admin','2021-05-19 04:01:20','管理员'),(100,'普通角色','common',2,'1','0','0','admin','2021-05-19 04:01:15','',NULL,NULL);

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values (100,2000),(100,2001),(100,3000),(100,3001),(100,4000),(100,4001);

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`dept_id`,`login_name`,`user_name`,`user_type`,`email`,`phonenumber`,`sex`,`avatar`,`password`,`salt`,`status`,`del_flag`,`login_ip`,`login_date`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values (1,103,'admin','','00','admin@163.com','15888888888','1','','29c67a30398638269fe600f73a054934','111111','0','0','127.0.0.1','2021-05-19 15:26:36','admin','2018-03-16 11:33:00','','2021-05-19 07:26:39','管理员');

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values (1,1);

INSERT INTO `sys_param` (`app_key`, `secret`, `interface_address`, `service_code`, `label_size`, `signature_service`, `create_time`, `update_time`, `param_note`, `url_type`, `deleted_flag` )
VALUES
	('30669cfc157ba29a', 'a8bde428746b540bd95291c417b2d260', 'http://www.furthertry.com/ApiOrder.create.api', NULL, '4*6', NULL, '2019-06-21 09:17:20', NULL, '下单接口', 1, 0 );
INSERT INTO `sys_param` (`app_key`, `secret`, `interface_address`, `service_code`, `label_size`, `signature_service`, `create_time`, `update_time`, `param_note`, `url_type`, `deleted_flag` )
VALUES
	('30669cfc157ba29a', 'a8bde428746b540bd95291c417b2d260', 'http://www.furthertry.com/ApiOrder.calPrice.api', NULL, NULL, NULL, '2019-06-21 09:18:35', NULL, '获取价格订单接口', 2, 0 );
INSERT INTO `sys_param` ( `app_key`, `secret`, `interface_address`, `service_code`, `label_size`, `signature_service`, `create_time`, `update_time`, `param_note`, `url_type`, `deleted_flag` )
VALUES
	('30669cfc157ba29a', 'a8bde428746b540bd95291c417b2d260', 'http://www.furthertry.com/ApiOrder.label.api', NULL, NULL, NULL, '2019-06-21 09:19:00', NULL, '获取面单接口', 3, 0 );
INSERT INTO `sys_param` (`app_key`, `secret`, `interface_address`, `service_code`, `label_size`, `signature_service`, `create_time`, `update_time`, `param_note`, `url_type`, `deleted_flag` )
VALUES
	('30669cfc157ba29a', 'a8bde428746b540bd95291c417b2d260', 'http://www.furthertry.com/ApiOrder.checkAddress.api', NULL, NULL, NULL, '2019-06-21 09:51:13', NULL, '检测地址', 4, 0 );





/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
