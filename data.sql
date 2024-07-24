-- MySQL dump 10.13  Distrib 8.0.37, for Win64 (x86_64)
--
-- Host: localhost    Database: group3
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_activity`
--

DROP TABLE IF EXISTS `t_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_activity` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '活动id 主键',
  `name` varchar(20) NOT NULL COMMENT '活动名称 非空',
  `club_id` bigint NOT NULL COMMENT '所属社团id 外键',
  `info` longtext NOT NULL COMMENT ' 活动内容 非空',
  `user_id` bigint NOT NULL COMMENT '负责人id 外键',
  `start_date` datetime NOT NULL COMMENT '开始时间 非空',
  `end_date` datetime NOT NULL COMMENT '结束时间 非空',
  `pos` varchar(20) NOT NULL COMMENT '活动地点',
  `cate` varchar(20) DEFAULT NULL COMMENT '活动类型',
  `limit_num` int DEFAULT NULL COMMENT '人数限制 ',
  `photo_path` varchar(255) DEFAULT NULL COMMENT '活动图片',
  PRIMARY KEY (`id`),
  KEY `t_act_t_club_id_fk` (`club_id`),
  KEY `t_act_t_user_id_fk` (`user_id`),
  CONSTRAINT `t_act_t_club_id_fk` FOREIGN KEY (`club_id`) REFERENCES `t_club` (`id`),
  CONSTRAINT `t_act_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_activity`
--

LOCK TABLES `t_activity` WRITE;
/*!40000 ALTER TABLE `t_activity` DISABLE KEYS */;
INSERT INTO `t_activity` VALUES (2,'睡懒觉活动',2,'睡懒觉',3,'2024-07-10 14:18:05','2024-08-10 14:18:07','M87星系中心黑洞','娱乐',114514,NULL),(3,'肘击篮球',2,'打篮球',6,'2008-08-24 08:00:00','2024-08-24 08:00:00','洛圣都','运动',242424,NULL),(4,'金铲铲之战',2,'玩原神',6,'2008-08-24 08:00:00','2024-08-24 08:00:00','荒漠迷城','王者荣耀！',242424,NULL);
/*!40000 ALTER TABLE `t_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_club`
--

DROP TABLE IF EXISTS `t_club`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_club` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '社团id 主键',
  `name` varchar(20) NOT NULL COMMENT '社团名称 非空 唯一',
  `cate` varchar(20) DEFAULT NULL COMMENT '社团类型',
  `date` datetime DEFAULT NULL COMMENT '社团成立日期 ',
  `info` longtext COMMENT '社团简介',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（假删除）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_club_unique_2` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='社团信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_club`
--

LOCK TABLES `t_club` WRITE;
/*!40000 ALTER TABLE `t_club` DISABLE KEYS */;
INSERT INTO `t_club` VALUES (2,'实习起床闹钟毁了我的赖床梦社','','2024-07-10 13:52:15','加入我们社团，一起赖床！',0),(3,'see you again 社','','2024-07-10 13:54:20','man！',0),(4,'河海可爱南梁社','逆天','2024-07-10 14:26:57','你敢来？',0),(5,'河海威猛南梁社','逆天','2024-07-10 14:27:24','你还敢来？',0);
/*!40000 ALTER TABLE `t_club` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_enroll`
--

DROP TABLE IF EXISTS `t_enroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_enroll` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '报名id 主键',
  `user_id` bigint NOT NULL COMMENT '用户id 外键',
  `activity_id` bigint NOT NULL COMMENT '活动id 外键',
  `date` datetime NOT NULL COMMENT '报名时间 非空',
  PRIMARY KEY (`id`),
  KEY `t_enroll_t_act_id_fk` (`activity_id`),
  KEY `t_enroll_t_user_id_fk` (`user_id`),
  CONSTRAINT `t_enroll_t_act_id_fk` FOREIGN KEY (`activity_id`) REFERENCES `t_activity` (`id`),
  CONSTRAINT `t_enroll_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动和用户关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_enroll`
--

LOCK TABLES `t_enroll` WRITE;
/*!40000 ALTER TABLE `t_enroll` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_enroll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_remark`
--

DROP TABLE IF EXISTS `t_remark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_remark` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评论id 主键',
  `activity_id` bigint NOT NULL COMMENT '活动id 外键',
  `user_id` bigint NOT NULL COMMENT '评价人id 外键',
  `info` longtext NOT NULL COMMENT '评价内容 非空',
  `date` datetime NOT NULL COMMENT '评价时间 非空',
  PRIMARY KEY (`id`),
  KEY `t_remark_t_act_id_fk` (`activity_id`),
  KEY `t_remark_t_user_id_fk` (`user_id`),
  CONSTRAINT `t_remark_t_act_id_fk` FOREIGN KEY (`activity_id`) REFERENCES `t_activity` (`id`),
  CONSTRAINT `t_remark_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评价信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_remark`
--

LOCK TABLES `t_remark` WRITE;
/*!40000 ALTER TABLE `t_remark` DISABLE KEYS */;
INSERT INTO `t_remark` VALUES (1,4,6,'hh\n','2024-07-25 02:12:31'),(2,4,6,'123123123123123123123\n','2024-07-25 02:12:38'),(3,4,7,'不如原神\n','2024-07-25 02:13:49');
/*!40000 ALTER TABLE `t_remark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id 主键 自增 ',
  `username` varchar(20) NOT NULL COMMENT '用户名 非空 唯一',
  `password` varchar(50) NOT NULL COMMENT '密码 非空',
  `sex` varchar(2) NOT NULL DEFAULT '男' COMMENT '性别 默认值男',
  `tel` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `per` tinyint NOT NULL DEFAULT '3' COMMENT '权限等级 1为超级管理员 2为管理员 3为普通用户',
  `motto` varchar(100) DEFAULT NULL COMMENT '个性签名 ',
  `grade` varchar(10) DEFAULT NULL COMMENT '年级',
  `club_id` bigint DEFAULT NULL COMMENT '所属俱乐部id',
  `photo_path` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_unique` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (2,'孙丽','f180623caa42379bc4518d06c9c9be05','男',NULL,2,NULL,NULL,3,'114514'),(3,'doog0','4297f44b13955235245b2497399d7a93','男',NULL,2,NULL,NULL,2,NULL),(4,'admin1','e00cf25ad42683b3df678c61f42c6bda','男',NULL,2,NULL,NULL,3,NULL),(5,'doog1','2c6316dd44cc959030190c3eda56537d','男',NULL,2,NULL,NULL,4,NULL),(6,'admin','21232f297a57a5a743894a0e4a801fc3','男',NULL,1,NULL,NULL,2,NULL),(7,'doog2','295eb3a0ec098744f5481847d04606bc','男',NULL,2,NULL,NULL,NULL,'https://hhu-group3.oss-cn-shanghai.aliyuncs.com/bb2f2c61-d79b-40eb-ae16-fc9a2dbd5909.jpg'),(8,'doog3','31bd7b97c0512eddaeea2ba5b0aea735','男',NULL,3,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-25  2:32:17
