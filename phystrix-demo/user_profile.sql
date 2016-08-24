/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : localtest

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2016-08-24 19:25:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user_profile`
-- ----------------------------
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `email` varchar(30) DEFAULT NULL COMMENT '邮件',
  `city` varchar(30) DEFAULT NULL COMMENT '城市',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `IDX_USER_ID` (`user_id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户信息表';

-- ----------------------------
-- Records of user_profile
-- ----------------------------
INSERT INTO `user_profile` VALUES ('1', '1', '12', 'jack@gmail.com', 'New York', '19056780123', '2016-08-10 17:12:25', null);
INSERT INTO `user_profile` VALUES ('2', '2', '27', 'alice@docker.com', 'london', '35678902', '2016-08-11 10:56:52', '2016-08-11 10:56:56');
INSERT INTO `user_profile` VALUES ('3', '3', '24', 'XXX@126.com', 'Beijing', '15909876789', '2016-08-11 10:57:43', '2016-08-11 10:57:46');
