/*
 Navicat Premium Data Transfer

 Source Server         : DockerDB
 Source Server Type    : MySQL
 Source Server Version : 50744
 Source Host           : localhost:13306
 Source Schema         : stock

 Target Server Type    : MySQL
 Target Server Version : 50744
 File Encoding         : 65001

 Date: 13/02/2024 18:06:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dragon_activity_item_stock
-- ----------------------------
DROP TABLE IF EXISTS `dragon_activity_item_stock`;
CREATE TABLE `dragon_activity_item_stock`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pool_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '池子ID，attack_pool-攻击池、chest_pool-宝箱池',
  `item_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '奖励ID',
  `stock_type` tinyint(4) NOT NULL COMMENT '库存类型，1-每日、2-活动期间',
  `stock` int(11) NOT NULL COMMENT '库存，-1-代表无库存限制',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '审计字段-创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '审计字段-更新时间',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '审计字段-创建人',
  `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '审计字段-逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_pool_id_item_id`(`pool_id`, `item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '龙年活动奖品库存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dragon_activity_item_stock
-- ----------------------------
INSERT INTO `dragon_activity_item_stock` VALUES (1, '1', '1', 1, 1000, '2024-02-13 16:10:58', '2024-02-13 16:10:58', '', 0);
INSERT INTO `dragon_activity_item_stock` VALUES (2, '2', '2', 1, -1, '2024-02-13 18:03:35', '2024-02-13 18:03:35', '', 0);

SET FOREIGN_KEY_CHECKS = 1;
