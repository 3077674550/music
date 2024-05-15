/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80300
 Source Host           : localhost:3306
 Source Schema         : music

 Target Server Type    : MySQL
 Target Server Version : 80300
 File Encoding         : 65001

 Date: 15/05/2024 08:42:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '收藏类型，0歌曲，1歌单',
  `song_id` int NULL DEFAULT NULL COMMENT '歌曲id',
  `song_list_id` int NULL DEFAULT NULL COMMENT '歌单id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collect
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '评论类型，0歌曲，1歌单',
  `song_id` int NULL DEFAULT NULL COMMENT '歌曲id',
  `song_list_id` int NULL DEFAULT NULL COMMENT '歌单id',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '评论时间',
  `up` int NULL DEFAULT 0 COMMENT '评论点赞数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for consumer
-- ----------------------------
DROP TABLE IF EXISTS `consumer`;
CREATE TABLE `consumer`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别，1男，0女',
  `phone_num` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电邮',
  `birthday` datetime NULL DEFAULT NULL COMMENT '生日',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '签名',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `avator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '前端用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of consumer
-- ----------------------------
INSERT INTO `consumer` VALUES (1, 'mike', '12345', 1, '12345', '12345', '2004-01-01 00:00:00', 'hello', 'china', 'pic', '2024-05-12 15:18:55', '2024-05-13 00:25:29');

-- ----------------------------
-- Table structure for list_song
-- ----------------------------
DROP TABLE IF EXISTS `list_song`;
CREATE TABLE `list_song`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `song_id` int NULL DEFAULT NULL COMMENT '歌曲id',
  `song_list_id` int NULL DEFAULT NULL COMMENT '歌单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '歌单包含歌曲列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of list_song
-- ----------------------------
INSERT INTO `list_song` VALUES (1, 2, 1);

-- ----------------------------
-- Table structure for lyric
-- ----------------------------
DROP TABLE IF EXISTS `lyric`;
CREATE TABLE `lyric`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `song_id` int NULL DEFAULT NULL COMMENT '歌曲id',
  `timestamp` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '歌词时间戳',
  `lyric_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '歌词',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lyric
-- ----------------------------
INSERT INTO `lyric` VALUES (1, 3, '00:00.000_00:01.000_00:07.328_00:12.328_00:21.578_00:25.828_00:30.328_00:34.578_00:39.078_00:43.577_00:48.078_00:50.577_00:56.828_01:01.328_01:05.827_01:10.077_01:14.827_01:19.328_01:23.328_01:27.578_01:50.578_01:54.829_01:59.079_02:03.578_02:08.079_02:12.579_02:16.828_02:19.578_02:25.828_02:30.328_02:34.828_02:39.078_02:43.829_02:47.827_02:52.328_02:56.577_03:01.329_03:05.828_03:10.079_03:14.078_03:19.328_03:23.578_03:28.077_03:31.829_03:36.577', '作词 : 无_作曲 : 无_作词：方文山_谱曲：周杰伦_cover:源砸砸砸__素胚勾勒出青花笔锋浓转淡_瓶身描绘的牡丹一如你初妆_冉冉檀香透过窗心事我了然_宣纸上走笔至此搁一半_釉色渲染仕女图韵味被私藏_而你嫣然的一笑如含苞待放_你的美一缕飘散_去到我去不了的地方_天青色等烟雨 而我在等你_炊烟袅袅升起 隔江千万里_在瓶底书汉隶仿前朝的飘逸_就当我为遇见你伏笔_天青色等烟雨 而我在等你_月色被打捞起 晕开了结局_如传世的青花瓷自顾自美丽_你眼带笑意_色白花青的锦鲤跃然于碗底_临摹宋体落款时却惦记着你_你隐藏在窑烧里千年的秘密_极细腻犹如绣花针落地_帘外芭蕉惹骤雨门环惹铜绿_而我路过那江南小镇惹了你_在泼墨山水画里_你从墨色深处被隐去_天青色等烟雨 而我在等你_炊烟袅袅升起 隔江千万里_在瓶底书汉隶仿前朝的飘逸_就当我为遇见你伏笔_天青色等烟雨 而我在等你_月色被打捞起 晕开了结局_如传世的青花瓷自顾自美丽_你眼带笑意_天青色等烟雨 而我在等你_炊烟袅袅升起 隔江千万里_在瓶底书汉隶仿前朝的飘逸_就当我为遇见你伏笔_天青色等烟雨 而我在等你_月色被打捞起 晕开了结局_如传世的青花瓷自顾自美丽_你眼带笑意');
INSERT INTO `lyric` VALUES (2, 3, '00:00.000_00:00.917_00:04.922_00:09.170_00:13.421_00:17.415_00:21.420_00:24.670_00:28.674_00:33.424_00:39.415_00:42.165_00:44.673_00:47.415_00:55.415_00:59.419_01:03.666_01:07.918_01:11.666_01:15.418_01:19.423_01:23.917_01:27.666', '作词 : 李瑞洵/陈少琪_吉他改编：超超_风到这里就是黏_黏住过客的思念_雨到了这里缠成线_缠着我们流连人世间_你在身边就是缘_缘分写在三生石上面_爱有万分之一甜_宁愿我就葬在这一点_圈圈圆圆圈圈_天天年年天天 的我_深深看你的脸_生气的温柔 埋怨的温柔 的脸_不懂爱恨情愁煎熬的我们_都以为相爱就像风云的善变_相信爱一天 抵过永远_在这一刹那冻结了时间_不懂怎么表现温柔的我们_还以为殉情只是古老的传言_离愁能有多痛 痛有多浓_当梦被埋在江南烟雨中_心碎了才懂');

-- ----------------------------
-- Table structure for rank
-- ----------------------------
DROP TABLE IF EXISTS `rank`;
CREATE TABLE `rank`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `song_list_id` int NULL DEFAULT NULL COMMENT '歌单id',
  `consumer_id` int NULL DEFAULT NULL COMMENT '用户id',
  `score` int NULL DEFAULT NULL COMMENT '评分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评价' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rank
-- ----------------------------
INSERT INTO `rank` VALUES (4, 3, 4, 5);
INSERT INTO `rank` VALUES (5, 3, 4, 5);

-- ----------------------------
-- Table structure for singer
-- ----------------------------
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌手名',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别，1男，0女',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `birthday` datetime NULL DEFAULT NULL COMMENT '生日',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属地区',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '歌手' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of singer
-- ----------------------------

-- ----------------------------
-- Table structure for song
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `singer_id` int NULL DEFAULT NULL COMMENT '歌手id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌名',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌曲简介',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌曲图片',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌曲地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '歌曲' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of song
-- ----------------------------
INSERT INTO `song` VALUES (1, 3, 'like_magic', 'jyp family song', '2024-05-12 15:18:55', '2024-05-13 00:25:29', '/img/songPic/1715531128988example.jpg', '/song/J_Y_ Park _ Stray Kids _ ITZY _ NMIXX - Like Magic.ogg');
INSERT INTO `song` VALUES (2, 2, '江南', '经典歌曲', '2024-05-13 23:19:28', '2024-05-13 23:20:48', '/img/songPic/1715613648376example.jpg', '/song/林俊杰 - 江南.ogg');

-- ----------------------------
-- Table structure for song_list
-- ----------------------------
DROP TABLE IF EXISTS `song_list`;
CREATE TABLE `song_list`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `style` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '风格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '歌单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of song_list
-- ----------------------------
INSERT INTO `song_list` VALUES (2, 'KPOP', '/img/songPic/example.jpg', '韩流音乐', 'pop');
INSERT INTO `song_list` VALUES (3, 'pop', '/img/songPic/example.jpg', '流行音乐', 'pop music');

SET FOREIGN_KEY_CHECKS = 1;
