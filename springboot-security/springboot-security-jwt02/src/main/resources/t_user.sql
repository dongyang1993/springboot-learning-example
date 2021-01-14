/*
 Navicat Premium Data Transfer

 Source Server         : boot2020
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : boot2020

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 14/01/2021 10:13:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `enabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '账号启用',
  `account_non_locked` tinyint(1) NOT NULL DEFAULT 1 COMMENT '账号锁定',
  `account_non_expired` tinyint(1) NOT NULL DEFAULT 0 COMMENT '账号过期',
  `credentials_non_expired` tinyint(1) NOT NULL DEFAULT 0 COMMENT '密码过期',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `t_users_name_uindex`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'root', '$2a$10$bcf77fTHvoMn80hytz3RaOkzODoIX3MRyZveXqILOrxDEvLlAR0aq', 1, 1, 1, 1, '2020-11-08 11:06:16', '2021-01-13 21:45:43');

SET FOREIGN_KEY_CHECKS = 1;
