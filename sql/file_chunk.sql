/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : uploadfile

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 30/04/2023 17:12:14
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_chunk
-- ----------------------------
DROP TABLE IF EXISTS `file_chunk`;
CREATE TABLE `file_chunk`
(
    `id`                 bigint unsigned NOT NULL AUTO_INCREMENT,
    `file_name`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '文件名',
    `chunk_number`       int                                                           DEFAULT NULL COMMENT '当前分片，从1开始',
    `chunk_size`         float                                                         DEFAULT NULL COMMENT '分片大小',
    `current_chunk_size` float                                                         DEFAULT NULL COMMENT '当前分片大小',
    `total_size`         double(20, 0
) DEFAULT NULL COMMENT '文件总大小',
  `total_chunk` int DEFAULT NULL COMMENT '总分片数',
  `identifier` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '文件标识',
  `relative_path` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'md5校验码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1533 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

SET
FOREIGN_KEY_CHECKS = 1;
