/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : lovol_jp

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 28/02/2023 13:47:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `realname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `sex` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `company` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属公司',
  `department` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '部门',
  `phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` bit(1) NULL DEFAULT NULL COMMENT '管理员状态：0正常；1禁止',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除：0未删除；1删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (1, 'wyx_lms', 'lmslmslms', 'lms', 'nan', '雷沃', '信息', '198', '178', b'0', b'0', 'admin', NULL, NULL, '2023-02-10 18:28:19');
INSERT INTO `admin_user` VALUES (2, 'wyx', 'lms', 'lms', 'nv', '网', NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for case
-- ----------------------------
DROP TABLE IF EXISTS `case`;
CREATE TABLE `case`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pro_id` int NULL DEFAULT NULL COMMENT '产品id',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `summary_picture` blob NULL COMMENT '摘要图片',
  `picture` longblob NULL COMMENT '图片',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  `date` datetime NULL DEFAULT NULL COMMENT '日期',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '案例删除：0未删除；1删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `udpate_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '案例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of case
-- ----------------------------
INSERT INTO `case` VALUES (1, NULL, '草泥馬', '私はあなたのお父さんです', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for data
-- ----------------------------
DROP TABLE IF EXISTS `data`;
CREATE TABLE `data`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `pro_category_id` int NULL DEFAULT NULL COMMENT '产品分类id',
  `status` bit(1) NULL DEFAULT NULL COMMENT '是否显示：0不显示；1显示',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '数据状态：0未删除；1删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '数据字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of data
-- ----------------------------
INSERT INTO `data` VALUES (1, 'くっさくき-ビジネスユニット', '', 1, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data` VALUES (2, 'くっさくき-プラットフォーム', '', 1, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data` VALUES (3, 'くっさくき-マシン全体の重量', '', 1, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data` VALUES (4, 'くっさくき-ひょうじゅんバヶットようりょう', '', 1, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data` VALUES (5, 'くっさくき-ていかくでんりょく', NULL, 1, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data` VALUES (6, 'ローダ-定格積載量', NULL, 2, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data` VALUES (7, 'ローダ-整備品質', NULL, 2, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data` VALUES (8, 'ローダ-バケット容量', NULL, 2, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data` VALUES (9, 'こうようこうじうんぱんそうち', NULL, 3, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data` VALUES (10, '純電気工学輸送設備', NULL, 3, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data` VALUES (11, 'ハイブリッドエンジニアリング輸送設備', NULL, 3, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data` VALUES (12, 'インテリジェント運転工学輸送設備', NULL, 3, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data` VALUES (13, '鉱山用スプリンクラー', NULL, 3, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data` VALUES (14, '鉱山用多機能防塵車', NULL, 3, NULL, b'0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for data_item
-- ----------------------------
DROP TABLE IF EXISTS `data_item`;
CREATE TABLE `data_item`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `item` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '数据项值',
  `data_id` int NULL DEFAULT NULL COMMENT '数据id',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '|',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 196 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '数据项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of data_item
-- ----------------------------
INSERT INTO `data_item` VALUES (11, '0-0.05m³', 4, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (12, '0.23-0.6m³', 4, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (13, '0.96-1.5m³', 4, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (14, '1.7-2.0m³', 4, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (15, '2.8-3.6m³', 4, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (16, '4.4-5.0m³', 4, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (17, '0-11.8kW', 5, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (18, '36.8-92kW', 5, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (19, '129-190kW', 5, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (20, '260-566kW', 5, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (21, '3-4T', 6, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (22, '5-6T', 6, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (23, '6-7T', 6, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (24, '8-10T', 6, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (25, '10-16t', 7, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (26, '16.5-18t', 7, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (27, '18.5-23t', 7, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (28, '23.5-24.5t', 7, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (29, '25-30t', 7, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (30, '1.5-3.6m³', 8, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (31, '4.0-5.5m³', 8, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (32, '5.5-8.0m³', 8, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (169, '0-6t', 3, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (170, '6-20t', 3, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (171, '20-30t', 3, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (172, '30-60t', 3, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (173, '60-100t', 3, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (180, 'R1.5', 2, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (181, 'R6', 2, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (182, 'R8', 2, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (183, 'R13', 2, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (184, 'R20', 2, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (185, 'R25', 2, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (186, 'R30', 2, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (187, 'R45', 2, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (188, 'R65', 2, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (189, '専用機', 2, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (190, 'マイクロショベル', 1, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (191, 'ショベル', 1, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (192, 'ちゅうかんくっさくき', 1, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (193, 'だいくっさくき', 1, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (194, 'こうさくくっさくき', 1, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `data_item` VALUES (195, '専用機', 1, b'0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件类型',
  `size` bigint NULL DEFAULT NULL COMMENT '文件大小',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '下载链接',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '是否禁用链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `news_category_id` int NULL DEFAULT NULL COMMENT '所属类别id',
  `pro_category_id` int NULL DEFAULT NULL COMMENT '产品分类id',
  `pro_id` int NULL DEFAULT NULL COMMENT '产品id',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `outer_chain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '外链',
  `summary_picture` longblob NULL COMMENT '摘要图片',
  `news_abstract` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '新闻摘要',
  `picture` blob NULL COMMENT '图片',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '新闻内容',
  `project_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '项目编号',
  `scene` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '使用场景',
  `key_word` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关键词(以逗号分隔)',
  `date` datetime NULL DEFAULT NULL COMMENT '发布日期',
  `is_recommend` int NULL DEFAULT NULL COMMENT '是否推荐：0推荐；1不推荐',
  `status` bit(1) NULL DEFAULT NULL COMMENT '是否显示：0显示；1不显示',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除：0不删除；1删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '新闻表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, 1, 1, NULL, '新闻', '萨达', NULL, '1', NULL, '1', '', NULL, '1', '2023-02-20 17:50:37', NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `news` VALUES (2, 2, 2, NULL, 'a撒大大', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `news` VALUES (3, 3, 3, NULL, '2', NULL, NULL, 'sad12313', NULL, NULL, 'da', NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `news` VALUES (4, 1, 1, NULL, '撒打啊打', NULL, NULL, 'das', NULL, NULL, 'sadad', NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `news` VALUES (5, 2, 2, NULL, 'asdasda', NULL, NULL, 'adadadda', NULL, NULL, 'sadadadasa', NULL, NULL, NULL, 1, b'0', b'0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for news_category
-- ----------------------------
DROP TABLE IF EXISTS `news_category`;
CREATE TABLE `news_category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '新闻名称',
  `parent_id` int NULL DEFAULT NULL COMMENT '父级id',
  `picture` longblob NULL COMMENT '图片',
  `status` bit(1) NULL DEFAULT NULL COMMENT '是否显示：0显示；1不显示',
  `is_delete` bit(1) NOT NULL COMMENT '是否删除：0不删除；1删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '新闻类别' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of news_category
-- ----------------------------
INSERT INTO `news_category` VALUES (1, '产品新闻·', NULL, NULL, b'0', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `news_category` VALUES (2, '企业新闻', NULL, NULL, b'0', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `news_category` VALUES (3, '案例分析', NULL, NULL, b'0', b'0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for online_delivery
-- ----------------------------
DROP TABLE IF EXISTS `online_delivery`;
CREATE TABLE `online_delivery`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属类别',
  `position` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '岗位',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `information` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '留言信息',
  `date` datetime NULL DEFAULT NULL COMMENT '提交时间',
  `is_dispose` bit(1) NULL DEFAULT NULL COMMENT '是否处理：0处理；1未处理',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除：0不删除；1删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '在线投递' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of online_delivery
-- ----------------------------
INSERT INTO `online_delivery` VALUES (1, 'xixillllll', NULL, 'xiguan', 'dasasa', NULL, NULL, '2023-10-16 13:53:26', NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `online_delivery` VALUES (2, 'dsad', NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `online_delivery` VALUES (3, 'asd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `online_delivery` VALUES (4, 'dassa', NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `online_delivery` VALUES (5, '撒大大撒大大撒撒大大', NULL, NULL, NULL, NULL, NULL, NULL, b'1', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `online_delivery` VALUES (6, 'sdad', NULL, NULL, NULL, 'dsada', NULL, NULL, b'1', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `online_delivery` VALUES (7, '撒大大', NULL, NULL, NULL, NULL, NULL, NULL, b'1', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `online_delivery` VALUES (8, '1', '', '', '', '', '', NULL, b'1', b'0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for online_orders
-- ----------------------------
DROP TABLE IF EXISTS `online_orders`;
CREATE TABLE `online_orders`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `business` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属业务',
  `pro_category_id` int NULL DEFAULT NULL COMMENT '所属类别id',
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '型号',
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '留言信息',
  `date` datetime(6) NULL DEFAULT NULL COMMENT '提交日期',
  `is_forward` bit(1) NULL DEFAULT NULL COMMENT '是否转发：0转发；1未转发',
  `is_dispose` bit(1) NULL DEFAULT NULL COMMENT '是否处理：0处理；1未处理',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除：0不删除；1删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '在线留言' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of online_orders
-- ----------------------------
INSERT INTO `online_orders` VALUES (1, '你', '打', '打', '打', 1, 'mi', '的', '2023-02-16 11:10:58.000000', NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `online_orders` VALUES (2, '2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `online_orders` VALUES (3, NULL, '2', 'email', NULL, 1, 'pmode', 'zaixian', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `online_orders` VALUES (4, NULL, NULL, '3', NULL, 2, 'aaaaaaa', 'sda', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `online_orders` VALUES (5, '孙甲豪', '赵庆', '袁航', '王鸿禹', NULL, '徐英豪', NULL, NULL, NULL, b'1', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `online_orders` VALUES (6, '孙甲豪大傻逼', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `online_orders` VALUES (7, '袁航', '袁航', '袁航', '袁航', NULL, '袁航', NULL, NULL, NULL, b'1', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `online_orders` VALUES (8, '', '', '', '', NULL, '', NULL, NULL, NULL, b'1', b'0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限名称',
  `parent_id` int NULL DEFAULT NULL COMMENT '父级id',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `is_permit` bit(1) NULL DEFAULT NULL COMMENT '是否允许：0允许；1不允许',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除：0不删除；1删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '权限1', NULL, NULL, b'0', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (2, '权限2', NULL, NULL, b'0', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (3, '权限3', NULL, NULL, b'0', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (4, '权限4', NULL, NULL, b'0', b'1', NULL, NULL, NULL, NULL);
INSERT INTO `permission` VALUES (5, '权限5', NULL, NULL, b'1', b'0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for pic
-- ----------------------------
DROP TABLE IF EXISTS `pic`;
CREATE TABLE `pic`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pic_category_id` int NULL DEFAULT NULL COMMENT '图片类别ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `pic` longblob NULL COMMENT '图片',
  `status` int NULL DEFAULT NULL COMMENT '是否显示：0显示；1不显示',
  `is_recommend` int NULL DEFAULT NULL COMMENT '是否推荐：0推荐；1不推荐',
  `is_delete` int NULL DEFAULT NULL COMMENT '是否删除：0不删除；1删除',
  `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片链接',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '图片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pic
-- ----------------------------

-- ----------------------------
-- Table structure for pic_category
-- ----------------------------
DROP TABLE IF EXISTS `pic_category`;
CREATE TABLE `pic_category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类别名称',
  `parent_id` int NULL DEFAULT NULL COMMENT '父级ID',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `picture` longblob NULL COMMENT '图片',
  `status` bit(1) NULL DEFAULT NULL COMMENT '是否显示：0显示；1不显示',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除：0不删除；1删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '图片类别' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pic_category
-- ----------------------------

-- ----------------------------
-- Table structure for pro
-- ----------------------------
DROP TABLE IF EXISTS `pro`;
CREATE TABLE `pro`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pro_category_id` int NULL DEFAULT NULL COMMENT '产品分类id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '设备名称',
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品型号',
  `picture` longblob NULL COMMENT '产品图片',
  `information` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品资料',
  `status` bit(1) NULL DEFAULT NULL COMMENT '是否显示：0显示；1不显示',
  `is_recommend` bit(1) NULL DEFAULT NULL COMMENT '是否推荐：0推荐；1不推荐',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除：0不删除；1删除',
  `vr_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'VR链接',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '产品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pro
-- ----------------------------
INSERT INTO `pro` VALUES (1, 1, 'lm', '77', NULL, 'da1', b'0', b'0', b'0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pro` VALUES (9, 4, 'lms', '567789', NULL, '1', NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pro` VALUES (10, 1, '亲亲', '1', NULL, '信息', b'0', b'0', b'0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pro` VALUES (11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pro` VALUES (12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pro` VALUES (13, NULL, '的撒大', NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pro` VALUES (14, NULL, NULL, '1', NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pro` VALUES (15, NULL, NULL, '2', NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pro` VALUES (16, 2, NULL, '1', NULL, NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pro` VALUES (17, 2, NULL, 'mol', NULL, '1', NULL, NULL, b'0', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for pro_case
-- ----------------------------
DROP TABLE IF EXISTS `pro_case`;
CREATE TABLE `pro_case`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `product_id` int NULL DEFAULT NULL COMMENT '产品id',
  `case_id` int NULL DEFAULT NULL COMMENT '案例id',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除：0不删除；1删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '产品案例关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pro_case
-- ----------------------------

-- ----------------------------
-- Table structure for pro_category
-- ----------------------------
DROP TABLE IF EXISTS `pro_category`;
CREATE TABLE `pro_category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类别名称',
  `parent_id` int NULL DEFAULT NULL COMMENT '父级id',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `picture` longblob NULL COMMENT '图片',
  `service_hotline` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '服务热线',
  `status` bit(1) NULL DEFAULT NULL COMMENT '是否显示：0显示；1不显示',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除：0不删除；1删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(6) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 913 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '产品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pro_category
-- ----------------------------
INSERT INTO `pro_category` VALUES (1, '挖掘机', NULL, NULL, NULL, '400-8291-888', b'0', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `pro_category` VALUES (2, '装载机', NULL, NULL, NULL, '400-8291-888', b'0', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `pro_category` VALUES (3, '特车', NULL, NULL, NULL, '400-8287-586', b'0', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `pro_category` VALUES (4, '矿用工程运输装备', 3, NULL, NULL, NULL, b'0', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `pro_category` VALUES (5, '纯电动工程运输装备', 3, NULL, NULL, NULL, b'0', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `pro_category` VALUES (6, '混合动力工程运输装备', 3, NULL, NULL, NULL, b'0', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `pro_category` VALUES (7, '智能驾驶工程运输设备', 3, NULL, NULL, NULL, b'0', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `pro_category` VALUES (8, '矿用洒水车', 3, NULL, NULL, NULL, b'0', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `pro_category` VALUES (9, '矿用多功能抑尘车', 3, NULL, NULL, NULL, b'0', b'0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for pro_data_item
-- ----------------------------
DROP TABLE IF EXISTS `pro_data_item`;
CREATE TABLE `pro_data_item`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_item_id` int NULL DEFAULT NULL COMMENT '数据项id',
  `pro_id` int NULL DEFAULT NULL COMMENT '产品id',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除：0不删除；1删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '产品分类数据筛选表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pro_data_item
-- ----------------------------
INSERT INTO `pro_data_item` VALUES (1, 26, 17, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `pro_data_item` VALUES (2, 27, 17, b'0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for property
-- ----------------------------
DROP TABLE IF EXISTS `property`;
CREATE TABLE `property`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pro_category_id` int NULL DEFAULT NULL COMMENT '产品分类id',
  `property_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '属性名',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除：0未删除；1删除',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '属性表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of property
-- ----------------------------
INSERT INTO `property` VALUES (5, 2, 'xixi', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `property` VALUES (6, 2, '我才', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `property` VALUES (7, 2, '打', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `property` VALUES (8, 2, '1', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `property` VALUES (9, 2, '232131', b'0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for property_value
-- ----------------------------
DROP TABLE IF EXISTS `property_value`;
CREATE TABLE `property_value`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pro_id` int NULL DEFAULT NULL COMMENT '产品id',
  `property_id` int NULL DEFAULT NULL COMMENT '属性项id',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '属性值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '属性值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of property_value
-- ----------------------------
INSERT INTO `property_value` VALUES (1, 10, 2, '11');
INSERT INTO `property_value` VALUES (2, 10, 3, '12');
INSERT INTO `property_value` VALUES (3, 17, 5, '梁非凡');

-- ----------------------------
-- Table structure for recruitment
-- ----------------------------
DROP TABLE IF EXISTS `recruitment`;
CREATE TABLE `recruitment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pos_category_id` int NULL DEFAULT NULL COMMENT '招聘类别',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '招聘名称',
  `education` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '学历',
  `recruiters_num` int NULL DEFAULT NULL COMMENT '招聘人数',
  `department` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属部门',
  `detailed_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '详细地址',
  `job_requirements` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '任职要求',
  `post_responsibilities` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '岗位职责',
  `date` datetime NULL DEFAULT NULL COMMENT '发布日期',
  `status` bit(1) NULL DEFAULT NULL COMMENT '是否显示：0显示；1不显示',
  `is_urgent` bit(1) NULL DEFAULT NULL COMMENT '是否紧急：0紧急；1不紧急',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除：0不删除；1删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '招聘表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recruitment
-- ----------------------------
INSERT INTO `recruitment` VALUES (1, NULL, '营销经理', '本科及以上学历', 18, '海外营销公司', '', '1.国际经济与贸易相关专业，机械专业，本科及以上学历。 2.英语四级及以上，能熟练运用英语，与外商有效沟通。 3.能独立开发客户，积极、熟练地运用各种方法和工具联系国外客户。 4.具备良好的商业谈判技巧和沟通能力。 5.熟悉进出口流程和规则，熟悉国际支付及其交易风险。 6.具有良好的协调能力、抗压能力和团队合作精神，能接受长期驻外出差。', '1.开发客户，开拓市场，维护客户关系。 2.负责销售，处理客户询单，促成交易，解决客户需求。 3.执行客户所订购产品的采购作业，并进行品质和交易控制。 4.负责交易谈判，制订销售合同，并进行有效管理。 5.跟踪订单的执行，协调并解决交易过程产生的问题。 6.催收货款或信用证，保障收款安全。 7.执行其他跟进出口业务相关的工作。', NULL, b'0', NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `recruitment` VALUES (2, NULL, '服务经理', '本科及以上学历', 7, '海外营销公司', '', '1.熟悉服务配件相关知识和技能，工程机械行业经验的优先考虑； 2.熟悉经销商管理经验和服务作业经验； 3.善于沟通，有较好的组织协调能力； 4.熟练使用办公软件； 5.英语口语能正常交流； 6.积极主动、责任感、高度执行力，对公司追求极致产品和服务有高度认同感，能接受长期驻外出差。', '1.负责本区域市场质量问题的处理； 2.负责配件订单的获取、下发、发运； 3.完成区域配件年度销量及年度利润指标； 4.协助业务人员完成市场整机销售指标； 5.负责海外经销商质量信息管理； 6.区域外派服务执行、经销商培训管理等。', NULL, b'0', NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `recruitment` VALUES (3, NULL, '商品开发与管理岗', '本科及以上学历', 1, '海外营销公司', NULL, '1.学历及专业：本科及以上学历，机械、自动化及相关专业优先考虑； 2.工作经验：具备2年及以上同行业工作经验； 3.知识技能：熟知工程机械产品知识，并熟悉装配工艺、故障处理；能熟练操作办公软件； 4.综合素质：有较好的沟通能力及团队协作能力，执行力强，抗压能力强。', '1.负责与技术研究院做资源拓展、技术参数和新产品开发建议的技术沟通； 2.负责制定新产品推广方案（产品技术性能方面）； 3.负责根据客户的反馈，做挖掘机方面的商品改进； 4.负责维护新开发产品的资源信息； 5.负责联系工艺维护和制定装箱方案，并对装箱方案中存在的问题，进行研究，并提出改进。 6.负责ODM供应商的考察开发，ODM产品的技术沟通、价格谈判及审批，ODM商品资源管理。', NULL, b'0', NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `recruitment` VALUES (4, NULL, '风险控制岗', '本科及以上学历', 1, '海外营销公司', NULL, '1.学历及专业：本科及以上学历，国际贸易等相关专业优先考虑； 2.工作经验：具备1年及以上外贸相关工作经验； 3.知识技能：能熟练应用Office办公软件； 4.综合素质：有较好的沟通能力及团队协作能力，组织协调能力及学习能力强。', '1、中信保业务管理； 2、海外业务回款管理，跟踪及调度； 3、海外业务风险管理； 4、海外销售合同管理； 5、海外退税单据管理； 6、海外物流费用报销与管理', NULL, b'0', NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `recruitment` VALUES (5, NULL, '市场管理岗', '本科及以上学历', 1, '海外营销公司', NULL, '1.学历及专业：本科及以上学历，国际贸易等相关管理专业优先考虑； 2.工作经验：具备1年及以上外贸相关工作经验； 3.知识技能：能熟练应用Office办公软件； 4.综合素质：有较好的沟通能力及团队协作能力，组织协调能力及学习能力强。', '1.负责海外人员出访管理，制定年度出访计划，管控出访过程（日报、周报、定位、问题闭环处理、出访费用、签证等）； 2.负责海外商务政策管理，制定报批商务政策并执行监督；管控佣金、返利、展会营销活动支持等销售费用； 3.负责海外翻译管理，翻译公司招标组织，执行文件报价、报批、翻译、沟通。 4.领导交付的其他临时性工作。', NULL, b'0', NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `recruitment` VALUES (6, NULL, '营销管理岗', '本科及以上学历', 1, '海外营销公司', NULL, '1.学历及专业：本科及以上学历，国际贸易等相关管理专业优先考虑； 2.工作经验：具备1年及以上外贸相关工作经验； 3.知识技能：能熟练应用Office办公软件； 4.综合素质：有较好的沟通能力及团队协作能力，组织协调能力及学习能力强。', '1.负责海外人员出访管理，制定年度出访计划，管控出访过程（日报、周报、定位、问题闭环处理、出访费用、签证等）； 2.负责海外商务政策管理，制定报批商务政策并执行监督；管控佣金、返利、展会营销活动支持等销售费用； 3.负责海外翻译管理，翻译公司招标组织，执行文件报价、报批、翻译、沟通。 4.领导交付的其他临时性工作。', NULL, b'0', NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `recruitment` VALUES (7, NULL, '零部件图册开发岗', '大专及以上学历', 1, '海外营销公司', NULL, '1.学历及专业：大专及以上学历，电气、机械类相关专业；  2.工作经验：一年以上工作经验，制造、技术岗位工作经验者优先；  3.知识技能：能看懂机械、电气、液压、动力图纸及编制图纸，熟知机械知识和制造技术，熟练使用AutoCAD、Proe、UG等设计软件和办公软件；                        4.综合素质：具有良好的沟通、学习及团队合作能力，理解能力强、抗压能力强，认同公司文化，作风严谨。', '1.爆炸图制作及台账管理； 2.电子图册开发及台账管理； 3.技术变更维护及台账管理； 4.拆散件图册开发及台账管理； 5.SES系统实物照片维护； 6.SES系统图册审批及发布； 7.新项目开发进度推动及管理； 8.新项目相关内容培训（对后台人员及各服务经理）； 9.领导交付的其他临时性工作。', NULL, b'0', NULL, b'0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for recruitment_category
-- ----------------------------
DROP TABLE IF EXISTS `recruitment_category`;
CREATE TABLE `recruitment_category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `status` bit(1) NULL DEFAULT NULL COMMENT '是否显示：0显示；1不显示',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除：0不删除；1删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '招聘类别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recruitment_category
-- ----------------------------
INSERT INTO `recruitment_category` VALUES (1, '1', b'0', b'1', NULL, NULL, NULL, NULL);
INSERT INTO `recruitment_category` VALUES (2, '开始', b'0', b'1', NULL, NULL, NULL, NULL);
INSERT INTO `recruitment_category` VALUES (3, '再见', b'0', b'1', NULL, NULL, NULL, NULL);
INSERT INTO `recruitment_category` VALUES (4, '李明声', b'0', b'1', NULL, NULL, NULL, NULL);
INSERT INTO `recruitment_category` VALUES (5, '王艺璇', b'0', b'1', NULL, NULL, NULL, NULL);
INSERT INTO `recruitment_category` VALUES (6, '啊', NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `recruitment_category` VALUES (7, '啊', b'0', b'1', NULL, NULL, NULL, NULL);
INSERT INTO `recruitment_category` VALUES (8, '社会招聘', b'0', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `recruitment_category` VALUES (9, '校园招聘', b'1', b'0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色名称',
  `status` bit(1) NULL DEFAULT NULL COMMENT '状态：0正常；1禁用',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除：0不删除；1删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '信息工程部', b'0', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `role` VALUES (2, '品牌管理部', b'0', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `role` VALUES (3, '人力资源室', b'0', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `role` VALUES (4, '客服交互部', b'0', b'0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` int NULL DEFAULT NULL COMMENT '权限id',
  `is_delete` bit(1) NULL DEFAULT NULL COMMENT '是否删除：0不删除；1删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int NULL DEFAULT NULL COMMENT '管理员id',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (68, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `user_role` VALUES (69, 1, 4, NULL, NULL, NULL, NULL);
INSERT INTO `user_role` VALUES (76, 2, 1, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
