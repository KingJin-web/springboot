/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 01/04/2022 16:51:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flag` int(0) NULL DEFAULT NULL,
  `head_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` bigint(0) NULL DEFAULT NULL,
  `role` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, NULL, NULL, NULL, NULL, 'admin', '$2a$10$GLlA4Odfs0uC5HOvoGvZF.oLap6J/Mgb7Slz9lVhskpOsmeAhWgKa', NULL, 0);
INSERT INTO `user` VALUES (2, NULL, NULL, NULL, NULL, 'user', '$2a$10$nz3uRJIqbhPdcbRx1wuWGuEwXxnRDC3VjWMcalbAmM19pCrmziZR2', NULL, 1);
INSERT INTO `user` VALUES (3, NULL, NULL, NULL, NULL, 'king', '$2a$10$qod5PpB0IiPMQVLV52Hl5eR79jIlmWkXu/515pULT/iBnfSl/VrN.', NULL, 2);
INSERT INTO `user` VALUES (4, NULL, NULL, NULL, NULL, 'Hello_World', '$2a$10$88ggzneKIWVz3TWhRQpy9.4MPjEdUhipc8/us9dB5PMXrJa/o8Hie', 18569139229, 1);
INSERT INTO `user` VALUES (5, NULL, NULL, NULL, NULL, 'Hello_World1aa', '$2a$10$yGQ33WBL8LMltibfLi1dZOPXJNaw4Hp23PVME0qfogeYP7xiNmK.y', 19973653797, 1);

SET FOREIGN_KEY_CHECKS = 1;
