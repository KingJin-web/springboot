
DROP TABLE IF EXISTS `user`;
/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : mybits

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 12/09/2021 16:06:08
*/

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          int(0) UNSIGNED                                 NOT NULL AUTO_INCREMENT,
    `name`        varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL     DEFAULT NULL,
    `sex`         char(6) CHARACTER SET utf8 COLLATE utf8_bin     NULL     DEFAULT NULL,
    `pwd`         varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL     DEFAULT NULL,
    `email`       varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL     DEFAULT NULL,
    `version`     bigint(0)                                       NOT NULL DEFAULT 1,
    `create_time` timestamp(0)                                    NULL     DEFAULT NULL,
    `update_time` timestamp(0)                                    NULL     DEFAULT NULL,
    `flag`        int(0) UNSIGNED                                 NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 66
  CHARACTER SET = utf8
  COLLATE = utf8_bin
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

