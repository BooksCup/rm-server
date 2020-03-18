/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.27-log : Database - rm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rm` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `rm`;

/*Table structure for table `t_backlog` */

DROP TABLE IF EXISTS `t_backlog`;

CREATE TABLE `t_backlog` (
  `backlog_id` varchar(32) NOT NULL COMMENT '待办事项表主键',
  `backlog_module_id` varchar(32) DEFAULT NULL COMMENT '模块表主键',
  `backlog_sprint_id` varchar(32) DEFAULT NULL COMMENT '迭代表主键',
  `backlog_type` varchar(1) DEFAULT NULL COMMENT '类型("0":story "1":bug)',
  `backlog_status_id` varchar(32) DEFAULT NULL COMMENT '状态表主键',
  `backlog_current_user_id` varchar(32) DEFAULT NULL COMMENT '当前处理人ID(对应用户表主键)',
  `backlog_current_user_name` varchar(200) DEFAULT NULL COMMENT '当前处理人名字(对应用户表user_name字段),此处为快照',
  `backlog_title` varchar(200) DEFAULT NULL COMMENT '标题',
  `backlog_priority_order` int(5) DEFAULT NULL COMMENT '优先级顺序(目前存在1-10种优先级)',
  `backlog_priority` varchar(2) DEFAULT NULL COMMENT '优先级 "0":"低" "1":"中" "2":"高"',
  `backlog_importance` varchar(2) DEFAULT NULL COMMENT '重要程度 "0":"提示" "1":"一般" "2":"重要" "3":"关键"',
  `backlog_link_sprint` varchar(1) DEFAULT NULL COMMENT '开始结束日期是否联动迭代 "0":"否" "1":"是"',
  `backlog_begin_date` varchar(20) DEFAULT NULL COMMENT '预计开始日期',
  `backlog_end_date` varchar(20) DEFAULT NULL COMMENT '预计结束日期',
  `backlog_create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`backlog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_e_contract_account` */

DROP TABLE IF EXISTS `t_e_contract_account`;

CREATE TABLE `t_e_contract_account` (
  `account_id` varchar(32) NOT NULL COMMENT '云签章个人账号表主键',
  `account_third_party_user_id` varchar(32) DEFAULT NULL COMMENT '用户唯一标识，可传入第三方平台的个人用户id、证件号、手机号、邮箱等，如果设置则作为账号唯一性字段，相同信息不可重复创建。（个人用户与机构的唯一标识不可重复）',
  `account_name` varchar(200) DEFAULT NULL COMMENT '姓名（非实名签署时必填）',
  `account_sign_pwd` varchar(100) DEFAULT NULL COMMENT '签署密码',
  `account_id_type` varchar(50) DEFAULT NULL COMMENT '证件类型,默认CRED_PSN_CH_IDCARD',
  `account_id_number` varchar(100) DEFAULT NULL COMMENT '证件号（非实名签署时必填）',
  `account_mobile` varchar(50) DEFAULT NULL COMMENT '手机号码，默认空，手机号为空时无法使用短信意愿认证',
  `account_mail` varchar(50) DEFAULT NULL COMMENT '邮箱地址，默认空',
  `account_create_time` varchar(20) DEFAULT NULL COMMENT '账号创建时间',
  `account_modify_time` varchar(20) DEFAULT NULL COMMENT '账号修改时间',
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_e_contract_org` */

DROP TABLE IF EXISTS `t_e_contract_org`;

CREATE TABLE `t_e_contract_org` (
  `org_id` varchar(32) NOT NULL COMMENT '电子合同机构表主键',
  `org_third_party_user_id` varchar(32) DEFAULT NULL COMMENT '机构唯一标识，可传入第三方平台机构id、企业证件号、企业邮箱等如果设置则作为账号唯一性字段，相同id不可重复创建。（个人用户与机构的唯一标识不可重复）',
  `org_creator` varchar(32) DEFAULT NULL COMMENT '创建人个人账号id（调用个人账号创建接口返回的accountId）',
  `org_name` varchar(200) DEFAULT NULL COMMENT '机构名称',
  `org_id_type` varchar(50) DEFAULT NULL COMMENT '证件类型，默认CRED_ORG_USCC',
  `org_id_number` varchar(100) DEFAULT NULL COMMENT '证件号',
  `org_legal_id_number` varchar(200) DEFAULT NULL COMMENT '企业法人证件号',
  `org_legal_name` varchar(100) DEFAULT NULL COMMENT '企业法人名称',
  `org_create_time` varchar(20) DEFAULT NULL COMMENT '机构创建时间',
  `org_modify_time` varchar(20) DEFAULT NULL COMMENT '机构修改时间',
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_e_contract_seal` */

DROP TABLE IF EXISTS `t_e_contract_seal`;

CREATE TABLE `t_e_contract_seal` (
  `seal_id` varchar(50) NOT NULL COMMENT '印章表主键',
  `seal_account_type` varchar(1) DEFAULT NULL COMMENT '"0":"个人印章"  "1":"机构印章"',
  `seal_create_type` varchar(1) DEFAULT NULL COMMENT '创建方式 "0":"通过模板创建" "1":"通过图片创建"',
  `seal_account_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `seal_org_id` varchar(32) DEFAULT NULL COMMENT '机构ID',
  `seal_alias` varchar(200) DEFAULT NULL COMMENT '印章别名',
  `seal_color` varchar(10) DEFAULT NULL COMMENT '印章颜色，RED-红色， BLUE-蓝色，BLACK-黑色',
  `seal_height` int(5) DEFAULT NULL COMMENT '印章高度, 默认95px',
  `seal_width` int(5) DEFAULT NULL COMMENT '印章宽度, 默认95px',
  `seal_type` varchar(20) DEFAULT NULL COMMENT '模板类型',
  `seal_htext` varchar(20) DEFAULT NULL COMMENT '横向文，可设置0-8个字，企业名称超出25个字后，不支持设置横向文',
  `seal_qtext` varchar(20) DEFAULT NULL COMMENT '下弦文，可设置0-20个字，企业企业名称超出25个字后，不支持设置下弦文',
  `seal_central` varchar(20) DEFAULT NULL COMMENT '中心图案类型，STAR-圆形有五角星，NONE-圆形无五角星',
  `seal_data` longtext COMMENT '印章数据',
  `seal_transparent_flag` varchar(1) DEFAULT '0' COMMENT '是否对图片进行透明化处理，"0":"否" "1":"是" 默认false',
  `seal_file_key` varchar(200) DEFAULT NULL COMMENT '印章fileKey',
  `seal_url` varchar(500) DEFAULT NULL COMMENT '印章下载地址, 有效时间1小时',
  `seal_create_time` varchar(20) DEFAULT NULL COMMENT '印章创建时间',
  PRIMARY KEY (`seal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_e_contract_token` */

DROP TABLE IF EXISTS `t_e_contract_token`;

CREATE TABLE `t_e_contract_token` (
  `token_id` varchar(32) NOT NULL COMMENT 'token表主键',
  `token_app_id` varchar(100) DEFAULT NULL COMMENT 'appId',
  `token_secret` varchar(100) DEFAULT NULL COMMENT 'secret',
  `token_grantType` varchar(100) DEFAULT NULL COMMENT 'grantType',
  `token_content` varchar(500) DEFAULT NULL COMMENT 'token内容',
  `token_expires_in` bigint(20) DEFAULT NULL COMMENT 'token到期时间戳',
  `token_expiry_time` varchar(20) DEFAULT NULL COMMENT 'token到期时间',
  `token_create_time` varchar(20) DEFAULT NULL COMMENT 'token创建时间',
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_epic` */

DROP TABLE IF EXISTS `t_epic`;

CREATE TABLE `t_epic` (
  `epic_id` varchar(32) NOT NULL COMMENT '公司战略表主键',
  `epic_title` varchar(100) DEFAULT NULL COMMENT '标题',
  `epic_create_user` varchar(32) DEFAULT NULL COMMENT '创建用户',
  `epic_create_time` varchar(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`epic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_module` */

DROP TABLE IF EXISTS `t_module`;

CREATE TABLE `t_module` (
  `module_id` varchar(32) NOT NULL COMMENT '模块表主键',
  `module_name` varchar(100) DEFAULT NULL COMMENT '模块名',
  `module_desc` varchar(200) DEFAULT NULL COMMENT '模块描述',
  `module_create_time` varchar(20) DEFAULT NULL COMMENT '模块创建时间',
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_printer` */

DROP TABLE IF EXISTS `t_printer`;

CREATE TABLE `t_printer` (
  `printer_id` varchar(32) NOT NULL COMMENT '打印机表主键',
  `printer_sn` varchar(100) DEFAULT NULL COMMENT '打印机编号SN',
  `printer_key` varchar(100) DEFAULT NULL COMMENT '打印机识别码KEY(存于底部标签)',
  `printer_name` varchar(200) DEFAULT NULL COMMENT '打印机名称，如地址、店铺名等，便于管理',
  `printer_data_card_no` varchar(200) DEFAULT NULL COMMENT '流量卡号码',
  `printer_create_time` varchar(20) DEFAULT NULL COMMENT '打印机添加时间',
  PRIMARY KEY (`printer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_printer_config` */

DROP TABLE IF EXISTS `t_printer_config`;

CREATE TABLE `t_printer_config` (
  `config_id` varchar(32) NOT NULL COMMENT '打印机配置表主键',
  `config_user` varchar(100) DEFAULT NULL COMMENT 'USER',
  `config_ukey` varchar(100) DEFAULT NULL COMMENT 'UKEY',
  `config_create_time` varchar(20) DEFAULT NULL COMMENT '配置创建时间',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_printer_order` */

DROP TABLE IF EXISTS `t_printer_order`;

CREATE TABLE `t_printer_order` (
  `order_id` varchar(32) NOT NULL COMMENT '打印订单表',
  `order_no` varchar(100) DEFAULT NULL COMMENT '服务器返回订单ID',
  `order_printer_sn` varchar(100) DEFAULT NULL COMMENT '打印机编号',
  `order_content` varchar(5000) DEFAULT NULL COMMENT '打印内容',
  `order_ret_code` varchar(10) DEFAULT NULL COMMENT '订单返回码',
  `order_ret_message` varchar(200) DEFAULT NULL COMMENT '订单返回消息',
  `order_times` varchar(5) DEFAULT NULL COMMENT '打印联数/次数',
  `order_create_time` varchar(20) DEFAULT NULL COMMENT '服务器接收订单时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_sprint` */

DROP TABLE IF EXISTS `t_sprint`;

CREATE TABLE `t_sprint` (
  `sprint_id` varchar(32) NOT NULL COMMENT '迭代表主键',
  `sprint_name` varchar(100) DEFAULT NULL COMMENT '迭代名称',
  `sprint_desc` varchar(200) DEFAULT NULL COMMENT '迭代描述',
  `sprint_begin_date` varchar(20) DEFAULT NULL COMMENT '开始日期',
  `sprint_end_date` varchar(20) DEFAULT NULL COMMENT '结束日期',
  `sprint_create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `sprint_update_time` varchar(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`sprint_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_status` */

DROP TABLE IF EXISTS `t_status`;

CREATE TABLE `t_status` (
  `status_id` varchar(32) NOT NULL COMMENT '状态表主键',
  `status_name` varchar(100) DEFAULT NULL COMMENT '状态名',
  `status_desc` varchar(200) DEFAULT NULL COMMENT '状态描述',
  `status_type` varchar(1) DEFAULT NULL COMMENT '"0":开始态 "1":进行态 "2":结束态',
  `status_can_delete` varchar(1) DEFAULT '0' COMMENT '"0":可删除 "1":不可删除',
  `status_order` int(5) DEFAULT NULL COMMENT '状态排序',
  `status_create_time` varchar(20) DEFAULT NULL COMMENT '状态创建时间',
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户表主键',
  `user_name` varchar(200) DEFAULT NULL COMMENT '用户名',
  `user_phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `user_mail` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `user_desc` varchar(200) DEFAULT NULL COMMENT '描述',
  `user_status` varchar(1) DEFAULT NULL COMMENT '用户状态 "0":启用 "1":停用',
  `user_create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `user_last_login_time` varchar(20) DEFAULT NULL COMMENT '用户最后一次登录时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
