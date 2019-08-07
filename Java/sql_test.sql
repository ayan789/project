/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : sql_test

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 07/08/2019 18:15:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_group1
-- ----------------------------
DROP TABLE IF EXISTS `t_group1`;
CREATE TABLE `t_group1` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_group1
-- ----------------------------
BEGIN;
INSERT INTO `t_group1` VALUES ('1', 'g1_1');
INSERT INTO `t_group1` VALUES ('2', 'g1_2');
COMMIT;

-- ----------------------------
-- Table structure for t_group2
-- ----------------------------
DROP TABLE IF EXISTS `t_group2`;
CREATE TABLE `t_group2` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_group2
-- ----------------------------
BEGIN;
INSERT INTO `t_group2` VALUES ('1', 'g2_1');
INSERT INTO `t_group2` VALUES ('2', 'g2_2');
COMMIT;

-- ----------------------------
-- Table structure for t_group_test
-- ----------------------------
DROP TABLE IF EXISTS `t_group_test`;
CREATE TABLE `t_group_test` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `group1_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `group2_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_group_test
-- ----------------------------
BEGIN;
INSERT INTO `t_group_test` VALUES ('1', 'aaa', 'g1_1', 'g2_1');
INSERT INTO `t_group_test` VALUES ('2', 'bbb', 'g1_2', 'g2_2');
INSERT INTO `t_group_test` VALUES ('3', 'ccc', 'g1_1', 'g2_2');
INSERT INTO `t_group_test` VALUES ('4', 'ddd', 'g1_1', 'g2_1');
COMMIT;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `menu_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `menu_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
BEGIN;
INSERT INTO `t_menu` VALUES ('1', '增加');
INSERT INTO `t_menu` VALUES ('2', '删除');
INSERT INTO `t_menu` VALUES ('3', '查看');
INSERT INTO `t_menu` VALUES ('4', '修改');
COMMIT;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `role_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES ('1', 'admin1');
INSERT INTO `t_role` VALUES ('2', 'admin2');
COMMIT;

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `role_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `t_role_menu` VALUES ('1', '1', '1');
INSERT INTO `t_role_menu` VALUES ('2', '1', '2');
INSERT INTO `t_role_menu` VALUES ('3', '2', '3');
INSERT INTO `t_role_menu` VALUES ('4', '2', '4');
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `effective_time` timestamp NULL DEFAULT NULL,
  `invalid_time` timestamp NULL DEFAULT NULL,
  `sts` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('9001', '张三', '2019-08-09 16:20:27', '2019-08-07 16:19:37', '2019-08-07 17:38:04', '2019-08-07 17:38:09', '1');
INSERT INTO `t_user` VALUES ('9002', 'lisi', NULL, '2019-08-08 16:19:41', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
BEGIN;
INSERT INTO `t_user_role` VALUES ('1', '9001', '1');
INSERT INTO `t_user_role` VALUES ('2', '9002', '2');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
