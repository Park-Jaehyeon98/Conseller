-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: j9b207.p.ssafy.io    Database: conseller
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auction`
--

DROP TABLE IF EXISTS `auction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auction` (
  `auction_idx` bigint NOT NULL AUTO_INCREMENT,
  `auction_completed_date` datetime(6) DEFAULT NULL,
  `auction_end_date` datetime(6) DEFAULT NULL,
  `auction_highest_bid` int NOT NULL,
  `auction_start_date` datetime(6) DEFAULT NULL,
  `auction_status` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `auction_text` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lower_price` int NOT NULL,
  `notification_created_date` datetime(6) DEFAULT NULL,
  `upper_price` int NOT NULL,
  `gifticon_idx` bigint DEFAULT NULL,
  `highest_bid_user_idx` bigint DEFAULT NULL,
  `user_idx` bigint DEFAULT NULL,
  PRIMARY KEY (`auction_idx`),
  KEY `FKia6ilqwlo1fgjats3rsj46n02` (`gifticon_idx`),
  KEY `FK3csrevtmbdyqiy0xrmyr6ym1a` (`highest_bid_user_idx`),
  KEY `FK1ygbobtxu45og08uwb0jh48sc` (`user_idx`),
  CONSTRAINT `FK1ygbobtxu45og08uwb0jh48sc` FOREIGN KEY (`user_idx`) REFERENCES `user` (`user_idx`),
  CONSTRAINT `FK3csrevtmbdyqiy0xrmyr6ym1a` FOREIGN KEY (`highest_bid_user_idx`) REFERENCES `user` (`user_idx`),
  CONSTRAINT `FKia6ilqwlo1fgjats3rsj46n02` FOREIGN KEY (`gifticon_idx`) REFERENCES `gifticon` (`gifticon_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction`
--

LOCK TABLES `auction` WRITE;
/*!40000 ALTER TABLE `auction` DISABLE KEYS */;
INSERT INTO `auction` VALUES (1,'2023-10-06 02:25:17.949669','2023-09-20 23:59:59.000000',2500,'2023-10-06 02:02:13.413129','낙찰','피자보다 치킨 먹고싶어요',1000,NULL,15000,2,3,2),(3,'2023-10-06 02:59:41.516526','2024-10-28 23:59:59.000000',10000,'2023-10-06 02:43:39.740114','낙찰','8500원까지 생각 중입니다. ',5000,NULL,10000,4,2,3),(4,'2023-10-06 03:13:10.220783','2024-08-31 23:59:59.000000',5000,'2023-10-06 03:02:57.837309','낙찰','상품권',2500,NULL,5000,7,3,2),(5,NULL,'2023-09-20 23:59:59.000000',3333,'2023-10-06 03:15:39.750046','거래 중','피자',2222,NULL,12300,2,3,2),(6,NULL,'2023-10-13 23:59:59.000000',2400,'2023-10-06 04:04:51.005114','진행 중','ssafy에서 받은 cu 5000원 상품권 팝니다!!',2000,NULL,4000,9,4,1),(7,NULL,'2024-07-15 23:59:59.000000',0,'2023-10-06 09:28:03.597894','진행 중','하한가 15000원에 올려요',15000,NULL,25000,22,NULL,2),(8,NULL,'2024-09-01 23:59:59.000000',0,'2023-10-06 09:29:19.584022','진행 중','맥도날드 먹고싶다',2000,NULL,4000,23,NULL,2),(9,NULL,'2024-09-09 23:59:59.000000',0,'2023-10-06 09:31:05.205345','진행 중','투썸 케이크는 맛있자나요',15000,NULL,29000,27,NULL,2),(10,NULL,'2023-12-13 23:59:59.000000',0,'2023-10-06 09:35:43.188009','진행 중','집 주변에 스타벅스가 없어요,,',9000,NULL,13000,26,NULL,2);
/*!40000 ALTER TABLE `auction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auction_bid`
--

DROP TABLE IF EXISTS `auction_bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auction_bid` (
  `auction_bid_idx` bigint NOT NULL AUTO_INCREMENT,
  `auction_bid_price` int NOT NULL,
  `auction_bid_status` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `auction_registed_date` datetime(6) DEFAULT NULL,
  `auction_idx` bigint DEFAULT NULL,
  `user_idx` bigint DEFAULT NULL,
  PRIMARY KEY (`auction_bid_idx`),
  KEY `FK9gljbu2imbac8hs5efavq56t0` (`auction_idx`),
  KEY `FKd5e6djxkvbgw5e6e5m50ogk1e` (`user_idx`),
  CONSTRAINT `FK9gljbu2imbac8hs5efavq56t0` FOREIGN KEY (`auction_idx`) REFERENCES `auction` (`auction_idx`),
  CONSTRAINT `FKd5e6djxkvbgw5e6e5m50ogk1e` FOREIGN KEY (`user_idx`) REFERENCES `user` (`user_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction_bid`
--

LOCK TABLES `auction_bid` WRITE;
/*!40000 ALTER TABLE `auction_bid` DISABLE KEYS */;
INSERT INTO `auction_bid` VALUES (3,2000,'실패','2023-10-06 02:18:01.958297',1,1),(4,2500,'낙찰','2023-10-06 02:19:42.228572',1,3),(5,10000,'낙찰','2023-10-06 02:44:19.730717',3,2),(9,5000,'낙찰','2023-10-06 03:11:07.954840',4,3),(12,3333,'낙찰 예정','2023-10-06 03:22:34.446533',5,3),(13,2400,'입찰','2023-10-06 08:43:11.415616',6,4);
/*!40000 ALTER TABLE `auction_bid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `barter`
--

DROP TABLE IF EXISTS `barter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `barter` (
  `barter_idx` bigint NOT NULL AUTO_INCREMENT,
  `barter_completed_date` datetime(6) DEFAULT NULL,
  `barter_created_date` datetime(6) DEFAULT NULL,
  `barter_end_date` datetime(6) NOT NULL,
  `barter_modified_date` datetime(6) DEFAULT NULL,
  `barter_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `barter_status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `barter_text` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `complete_guest_idx` bigint DEFAULT NULL,
  `host_idx` bigint DEFAULT NULL,
  `prefer_sub_catergory_idx` int DEFAULT NULL,
  `sub_category_idx` int DEFAULT NULL,
  PRIMARY KEY (`barter_idx`),
  KEY `FKookn6qtftkfldkwyq6si7fu25` (`complete_guest_idx`),
  KEY `FKi0ib51jvse0526ala54yadp12` (`host_idx`),
  KEY `FKkbhbm118wmnmg0oadpmm3ivs4` (`prefer_sub_catergory_idx`),
  KEY `FKr98livx1pp9lsvwmtmfde205l` (`sub_category_idx`),
  CONSTRAINT `FKi0ib51jvse0526ala54yadp12` FOREIGN KEY (`host_idx`) REFERENCES `user` (`user_idx`),
  CONSTRAINT `FKkbhbm118wmnmg0oadpmm3ivs4` FOREIGN KEY (`prefer_sub_catergory_idx`) REFERENCES `sub_category` (`sub_category_idx`),
  CONSTRAINT `FKookn6qtftkfldkwyq6si7fu25` FOREIGN KEY (`complete_guest_idx`) REFERENCES `user` (`user_idx`),
  CONSTRAINT `FKr98livx1pp9lsvwmtmfde205l` FOREIGN KEY (`sub_category_idx`) REFERENCES `sub_category` (`sub_category_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barter`
--

LOCK TABLES `barter` WRITE;
/*!40000 ALTER TABLE `barter` DISABLE KEYS */;
INSERT INTO `barter` VALUES (1,'2023-10-06 02:37:39.287964','2023-10-06 02:36:33.456277','2023-09-20 23:59:59.000000',NULL,'이거 바꾸고 싶어요','교환 완료','바꿔주세요',3,2,4,2),(2,NULL,'2023-10-06 04:18:13.750277','2023-10-09 23:59:59.000000',NULL,'치킨 선호해요','교환 가능','서브웨이랑 스타벅스 좋아하시는 분 교환해요~~',NULL,1,2,1),(3,NULL,'2023-10-06 09:32:19.665186','2024-04-30 23:59:59.000000',NULL,'햄버거 먹고싶어요','교환 가능','기왕이면 버거킹으로',NULL,2,1,8),(4,NULL,'2023-10-06 09:33:33.978625','2024-02-20 23:59:59.000000',NULL,'저 다이어트 합니다','교환 가능','커피만 받아요',NULL,2,8,2),(5,NULL,'2023-10-06 09:42:37.011604','2024-01-15 23:59:59.000000',NULL,'남은 거 다 털어요','교환 가능','금액만 맞출게요',NULL,2,2,4);
/*!40000 ALTER TABLE `barter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `barter_guest_item`
--

DROP TABLE IF EXISTS `barter_guest_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `barter_guest_item` (
  `barter_guest_item_idx` bigint NOT NULL AUTO_INCREMENT,
  `barter_request_idx` bigint NOT NULL,
  `gifticon_idx` bigint NOT NULL,
  PRIMARY KEY (`barter_guest_item_idx`),
  KEY `FK2fuixjlnos7m6i88q07nfydg5` (`barter_request_idx`),
  KEY `FK47p7cf6d9so8x76c1y48mudyu` (`gifticon_idx`),
  CONSTRAINT `FK2fuixjlnos7m6i88q07nfydg5` FOREIGN KEY (`barter_request_idx`) REFERENCES `barter_request` (`barter_request_idx`),
  CONSTRAINT `FK47p7cf6d9so8x76c1y48mudyu` FOREIGN KEY (`gifticon_idx`) REFERENCES `gifticon` (`gifticon_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barter_guest_item`
--

LOCK TABLES `barter_guest_item` WRITE;
/*!40000 ALTER TABLE `barter_guest_item` DISABLE KEYS */;
INSERT INTO `barter_guest_item` VALUES (1,1,7);
/*!40000 ALTER TABLE `barter_guest_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `barter_host_item`
--

DROP TABLE IF EXISTS `barter_host_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `barter_host_item` (
  `barter_host_item_idx` bigint NOT NULL AUTO_INCREMENT,
  `barter_idx` bigint NOT NULL,
  `gifticon_idx` bigint NOT NULL,
  PRIMARY KEY (`barter_host_item_idx`),
  KEY `FKhcmwfsqwmy4qfwj4o5bq1h38o` (`barter_idx`),
  KEY `FK9nwwtjfy318oakh0d9kf628h8` (`gifticon_idx`),
  CONSTRAINT `FK9nwwtjfy318oakh0d9kf628h8` FOREIGN KEY (`gifticon_idx`) REFERENCES `gifticon` (`gifticon_idx`),
  CONSTRAINT `FKhcmwfsqwmy4qfwj4o5bq1h38o` FOREIGN KEY (`barter_idx`) REFERENCES `barter` (`barter_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barter_host_item`
--

LOCK TABLES `barter_host_item` WRITE;
/*!40000 ALTER TABLE `barter_host_item` DISABLE KEYS */;
INSERT INTO `barter_host_item` VALUES (1,1,2),(2,1,4),(3,2,12),(4,2,5),(5,3,19),(6,3,20),(7,4,21),(8,5,25),(9,5,18);
/*!40000 ALTER TABLE `barter_host_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `barter_request`
--

DROP TABLE IF EXISTS `barter_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `barter_request` (
  `barter_request_idx` bigint NOT NULL AUTO_INCREMENT,
  `barter_request_status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `barter_idx` bigint NOT NULL,
  `user_idx` bigint NOT NULL,
  PRIMARY KEY (`barter_request_idx`),
  KEY `FKph4tqdomf1cpp4c3q0wwo2qnv` (`barter_idx`),
  KEY `FK33egi3cmcc5evrayoe3xa9uiy` (`user_idx`),
  CONSTRAINT `FK33egi3cmcc5evrayoe3xa9uiy` FOREIGN KEY (`user_idx`) REFERENCES `user` (`user_idx`),
  CONSTRAINT `FKph4tqdomf1cpp4c3q0wwo2qnv` FOREIGN KEY (`barter_idx`) REFERENCES `barter` (`barter_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barter_request`
--

LOCK TABLES `barter_request` WRITE;
/*!40000 ALTER TABLE `barter_request` DISABLE KEYS */;
INSERT INTO `barter_request` VALUES (1,'수락',1,3);
/*!40000 ALTER TABLE `barter_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `black_list`
--

DROP TABLE IF EXISTS `black_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `black_list` (
  `id` bigint NOT NULL,
  `access_token` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `refresh_token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6f7q8jqpi6gn04m10yhpv7lu0` (`refresh_token`),
  CONSTRAINT `FK6f7q8jqpi6gn04m10yhpv7lu0` FOREIGN KEY (`refresh_token`) REFERENCES `user` (`refresh_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `black_list`
--

LOCK TABLES `black_list` WRITE;
/*!40000 ALTER TABLE `black_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `black_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gifticon`
--

DROP TABLE IF EXISTS `gifticon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gifticon` (
  `gifticon_idx` bigint NOT NULL AUTO_INCREMENT,
  `gifticon_all_image_url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gifticon_barcode` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gifticon_data_image_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gifticon_end_date` datetime(6) NOT NULL,
  `gifticon_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gifticon_start_date` datetime(6) NOT NULL,
  `gifticon_status` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `main_category_idx` int DEFAULT NULL,
  `sub_category_idx` int DEFAULT NULL,
  `user_idx` bigint DEFAULT NULL,
  PRIMARY KEY (`gifticon_idx`),
  KEY `FKhi5r5391hyymcygux45f8k5pm` (`main_category_idx`),
  KEY `FKdi03ko4xnp9a698eytr7u1sid` (`sub_category_idx`),
  KEY `FKcvf077ccjfmtfcvofeya2s8wh` (`user_idx`),
  CONSTRAINT `FKcvf077ccjfmtfcvofeya2s8wh` FOREIGN KEY (`user_idx`) REFERENCES `user` (`user_idx`),
  CONSTRAINT `FKdi03ko4xnp9a698eytr7u1sid` FOREIGN KEY (`sub_category_idx`) REFERENCES `sub_category` (`sub_category_idx`),
  CONSTRAINT `FKhi5r5391hyymcygux45f8k5pm` FOREIGN KEY (`main_category_idx`) REFERENCES `main_category` (`main_category_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gifticon`
--

LOCK TABLES `gifticon` WRITE;
/*!40000 ALTER TABLE `gifticon` DISABLE KEYS */;
INSERT INTO `gifticon` VALUES (1,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/6f89ce29-499b-43e8-8863-634782503b32.jpg','921125315632','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/d5d9f370-8465-4e45-b7c6-80f75f9a2778.jpg','2023-09-20 23:59:59.000000','미스터피자 미스터트리오Ｌ','2023-10-06 01:48:25.050538','사용',1,3,2),(2,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/16c138e5-752e-4209-93a5-35511820e83d.jpg','921125315632','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/9117b685-4a86-430b-a012-beeb1f42edf7.jpg','2023-09-20 23:59:59.000000','미스터피자 미스터트리오Ｌ','2023-10-06 01:48:25.473641','경매',1,3,2),(3,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/844eda64-da55-4b09-86e9-fae64a0072e6.jpg','8310016554045408','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/9014b60f-8b67-4b6d-ab67-5022bbcc605b.jpg','2024-02-20 23:59:59.000000','롯데시네마 @또대 영화관람권 2매 스위트콤 녀판코 르보 Ｌ1개 보(오리지','2023-10-06 01:48:33.739438','사용',1,1,3),(4,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/fbf83f2a-f33d-426c-a5ff-d4387b3ae7aa.jpg','625257057050','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/559365b0-421d-4075-8e5a-c11c4ffc3a6f.jpg','2024-10-28 23:59:59.000000','처갓집양념치킨 슈프림 양념치킨 콜라 T','2023-10-06 01:56:17.016998','판매',1,2,2),(5,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/efb1c296-f603-4e1e-96ef-d211c4ccdbbd.jpg','762137989921','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/b51235a3-873f-47d1-9180-cf957a6cff8d.jpg','2023-12-23 23:59:59.000000','스타벅스 카페 아메리카노 T','2023-10-06 02:09:22.788334','물물교환',3,8,1),(6,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/b2025eca-a6d5-4e67-8db4-b17747ba5cac.jpg','924677381998',NULL,'2024-02-26 23:59:59.000000','해태)갈아만든배 캔 340ml','2023-10-06 02:11:14.086704','보관',2,6,4),(7,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/9d2d0932-1ec9-48f0-b05a-09b9ada26d7c.jpg','4523452865668878','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/83004724-9f77-47c3-b085-3e570417f4fe.jpg','2024-08-31 23:59:59.000000','CU 모바일상품권 5,000원','2023-10-06 02:34:17.745695','판매',2,4,3),(8,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/7240cbdb-d727-44ee-80f3-d5400223095e.jpg','551492651368','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/485d1507-5b75-4234-aab1-a4eb024cf0a3.jpg','2024-07-18 23:59:59.000000',' 보양엔 전복죽','2023-10-06 02:49:48.514969','보관',3,8,4),(9,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/51fa0edd-beda-43ff-8b8e-6b2a9d540b51.jpg','4523380935871373','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/82caa2c7-ddea-4a18-bb5b-1f5a1532922f.jpg','2023-10-13 23:59:59.000000','CU 모바일상품권 5,000원','2023-10-06 03:46:50.667589','경매',2,4,1),(10,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/3039f16c-5018-4346-ac48-86cada22f2d6.jpg','762765349684','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/cca7c5e9-3e5c-4d10-a3f9-f7963c22b6a1.jpg','2024-02-13 23:59:59.000000','스타벅스 카페 라떼 T','2023-10-06 03:52:39.193281','보관',3,8,1),(11,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/6d924b88-b8ba-4586-ac35-ba735b103671.jpg','762944236379','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/f4544286-3af0-4504-9794-33378f546c36.jpg','2024-04-30 23:59:59.000000','스타벅스 아이스 카페 아메리카노 T 2잔','2023-10-06 03:52:59.882528','보관',3,8,1),(12,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/4c5a9d2e-90ee-4140-a50e-970d10ea1b2d.jpg','946240132387','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/55950ce6-29f9-43b0-9056-66e1437792b9.jpg','2023-10-09 23:59:59.000000','써브웨이 에그마요(15cm) 웨지세트','2023-10-06 03:53:41.773431','물물교환',1,1,1),(13,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/a5559758-c730-4d51-aa94-0026e8d2c88d.jpg','762536993128','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/1368ee29-e250-4180-8834-eb1fc81e9d38.jpg','2023-10-07 23:59:59.000000','스타벅스 아이스 카페 아메리카노 T','2023-10-06 04:10:57.160205','보관',3,8,1),(14,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/19d87a1a-4637-4683-beac-d8c0256463c2.jpg','762765349682','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/1459c941-a334-4af9-beaf-e2ce2c59ce02.jpg','2023-10-07 23:59:59.000000','스타벅스 카페 라떼 T','2023-10-06 04:21:06.582137','보관',3,8,1),(15,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/b4a7cc96-7a71-4315-ab4e-e0e078c045e8.jpg','764694763326','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/8bc3d829-6484-409a-b1e8-e28f0f8cf4d5.jpg','2024-06-08 23:59:59.000000','스타벅스 아이스 카페 아메리카노 T','2023-10-06 08:42:03.456036','보관',3,8,4),(16,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/ebb9b301-b56a-4c7e-988a-5d7037d7788a.jpg','9355302819443195','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/5e990759-5113-4f55-9f53-d8e2ea875ab4.jpg','2024-06-01 23:59:59.000000','투썸플레이스 딸기 생크림 1호','2023-10-06 08:42:29.072236','판매',3,9,4),(17,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/de86ae6f-346e-4d5a-8528-17eae6e85f6f.jpg','763419754548','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/7dd7b465-dd91-421a-b705-53a1a6ac4185.jpg','2024-02-28 23:59:59.000000','스타벅스 초콜릿 크림 칩 프라푸치노','2023-10-06 08:45:29.813112','보관',3,8,4),(18,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/436a8c1f-d26f-45e0-80ed-90f010ece51a.jpg','9352432775928333','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/33eda38c-6e49-4905-bd59-74e66ab63f6b.jpg','2024-01-15 23:59:59.000000','투썸플레이스 떠먹는 아이스박스','2023-10-06 09:08:09.626614','물물교환',3,9,2),(19,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/f70be686-8dfc-454a-8d9a-63a436292e6b.jpg','762536993126','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/1154980e-684a-4ebf-9571-9824a7ab6a6f.jpg','2024-04-30 23:59:59.000000','스타벅스 아이스 카페 아메리카노 T','2023-10-06 09:08:49.654672','물물교환',3,8,2),(20,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/f7396446-7120-4766-8a5f-e21f4f995241.jpg','999548002507','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/7b6ed2ef-566d-4064-ab56-4089a2d9276f.jpg','2024-05-31 23:59:59.000000','스타벅스 아이스 카페 아메리카노 T','2023-10-06 09:09:48.016801','물물교환',3,8,2),(21,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/07454ba6-9f60-4dd6-be5f-42775f0dd085.jpg','941196365369','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/cc075433-9bce-43e6-a004-03b42ce361dc.jpg','2024-02-20 23:59:59.000000','골드킹(한마리)+뿌링치즈볼+콜 라1.25Ｌ T','2023-10-06 09:15:13.562710','물물교환',1,2,2),(22,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/98b32e34-02ba-4ec9-983a-71330a675877.jpg','92002758457962','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/2a8bae60-a94a-453a-b876-4f8bdc0fb624.jpg','2024-07-15 23:59:59.000000','배스킨라빈스 모바일 금액권 3만원권','2023-10-06 09:16:24.329603','경매',4,10,2),(23,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/a4284757-5226-409b-b516-ba788c70b3d7.jpg','946858764013','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/c28738fc-d134-4edc-ad1f-f260bafec0ee.jpg','2024-09-01 23:59:59.000000','맘스터치 통새우버거 세트','2023-10-06 09:17:01.413024','경매',1,1,2),(24,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/d8e6e3b3-581b-4fb2-9b26-3834ea9010fd.jpg','900711072370','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/d6422b41-561b-4c81-ae23-b798f62dad9d.jpg','2024-04-17 23:59:59.000000','해피머니온라인상품권[3,000원]','2023-10-06 09:17:33.347270','판매',2,4,2),(25,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/7c9a3bfe-d30d-4d68-b26e-4fb5dccb695b.jpg','900750336166','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/302925d3-f56d-483a-932b-d7cda5f9c444.jpg','2024-12-07 23:59:59.000000','이마트전용 3만원금액권','2023-10-06 09:18:25.225089','물물교환',2,4,2),(26,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/fd445e76-c258-4458-bbbf-a290dcb1a09f.jpg','768042267300','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/a7c163c4-2867-428e-82cd-0b41f8fa1932.jpg','2023-12-13 23:59:59.000000','스타벅스 카페 아메리카노 레이어 가 나슈 케이크','2023-10-06 09:18:55.804531','경매',3,8,2),(27,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/a132d928-247d-41af-b266-bb862d6a6a7d.jpg','9353892277524371','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/dcc33c12-2c85-442e-a89a-8b48b1d588e7.jpg','2024-09-09 23:59:59.000000','투썸플레이스 마이 투썸 하트','2023-10-06 09:19:36.231933','경매',3,8,2),(28,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/bdabf2c7-01db-4e3a-a380-6903b6370f04.jpg','769642710233','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/887e9e94-5e0e-4add-9b63-23fdee0d856f.jpg','2023-12-13 23:59:59.000000','스타벅스 토피 넛 라떼 T 산타 벨벳 치즈 케이크 T','2023-10-06 09:21:07.039133','판매',3,8,2),(29,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/41619391-c311-4d44-9464-2d93abbe487d.jpg','763650851133','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/85ce217d-a4f8-46c5-af22-2f0271f53704.jpg','2024-03-31 23:59:59.000000','스타벅스 아이스 슈크림 라떼 T','2023-10-06 09:21:46.361646','판매',3,8,2),(30,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/cd9827fa-54f8-42d6-a21e-28ff6a1acb11.jpg','8310016554045401','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/02332c09-ee18-4443-a958-5a994eb1d718.jpg','2024-02-20 23:59:59.000000','팝콘','2023-10-06 10:45:21.654051','보관',1,1,5),(31,'https://b207-conseller.s3.ap-northeast-2.amazonaws.com/5c431360-b747-43de-9786-1406a4465e39.jpg','4523452865668871','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/4e721545-8dc6-4359-923e-24be22c5fc4e.jpg','2024-08-31 23:59:59.000000','CU 모바일상품권 5,000원','2023-10-06 11:01:31.843890','보관',2,4,3);
/*!40000 ALTER TABLE `gifticon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inquiry`
--

DROP TABLE IF EXISTS `inquiry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquiry` (
  `inquiry_idx` bigint NOT NULL AUTO_INCREMENT,
  `inquiry_answer` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `inquiry_answer_date` datetime(6) DEFAULT NULL,
  `inquiry_content` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `inquiry_created_date` datetime(6) DEFAULT NULL,
  `inquiry_status` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `inquiry_title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `inquiry_type` int NOT NULL,
  `reported_user` bigint DEFAULT NULL,
  `user_idx` bigint DEFAULT NULL,
  PRIMARY KEY (`inquiry_idx`),
  KEY `FKt2a15dj5m6h6ag62k5kk0fpk2` (`reported_user`),
  KEY `FK2p6ysa53nqgh94hl2dva79ojj` (`user_idx`),
  CONSTRAINT `FK2p6ysa53nqgh94hl2dva79ojj` FOREIGN KEY (`user_idx`) REFERENCES `user` (`user_idx`),
  CONSTRAINT `FKt2a15dj5m6h6ag62k5kk0fpk2` FOREIGN KEY (`reported_user`) REFERENCES `user` (`user_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inquiry`
--

LOCK TABLES `inquiry` WRITE;
/*!40000 ALTER TABLE `inquiry` DISABLE KEYS */;
/*!40000 ALTER TABLE `inquiry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_category`
--

DROP TABLE IF EXISTS `main_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `main_category` (
  `main_category_idx` int NOT NULL AUTO_INCREMENT,
  `main_category_content` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`main_category_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_category`
--

LOCK TABLES `main_category` WRITE;
/*!40000 ALTER TABLE `main_category` DISABLE KEYS */;
INSERT INTO `main_category` VALUES (1,'버거/치킨/피자'),(2,'편의점'),(3,'카페/베이커리'),(4,'아이스크림'),(5,'기타');
/*!40000 ALTER TABLE `main_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification_entity`
--

DROP TABLE IF EXISTS `notification_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification_entity` (
  `notification_idx` bigint NOT NULL AUTO_INCREMENT,
  `notification_content` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `notification_created_date` datetime(6) DEFAULT NULL,
  `notification_title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `notification_type` int DEFAULT NULL,
  `seller` bit(1) NOT NULL,
  `user_idx` bigint DEFAULT NULL,
  PRIMARY KEY (`notification_idx`),
  KEY `FKrgv2doxv9e4lyanudnrnuqewg` (`user_idx`),
  CONSTRAINT `FKrgv2doxv9e4lyanudnrnuqewg` FOREIGN KEY (`user_idx`) REFERENCES `user` (`user_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification_entity`
--

LOCK TABLES `notification_entity` WRITE;
/*!40000 ALTER TABLE `notification_entity` DISABLE KEYS */;
INSERT INTO `notification_entity` VALUES (7,'대화 님과의 경매가 진행 중 입니다.','2023-10-06 02:18:49.924558','경매 거래 진행',1,_binary '\0',3),(10,'대화 님과의 경매가 취소되었습니다.','2023-10-06 02:19:31.491089','경매 거래 취소',1,_binary '\0',3),(13,'대화 님과의 경매가 진행 중 입니다.','2023-10-06 02:20:38.486740','경매 거래 진행',1,_binary '\0',3),(16,'대화 님과의 경매가 완료되었습니다.','2023-10-06 02:25:18.182417','경매 거래 완료',1,_binary '\0',3),(18,'대화 님이 스토어 거래를 취소하였습니다','2023-10-06 02:30:10.247348','스토어 거래 취소',2,_binary '\0',3),(22,'대화 님과의 스토어 거래가 취소되었습니다.','2023-10-06 02:30:57.469487','스토어 거래 취소',2,_binary '',3),(23,'대화 님이 스토어 입금을 완료하였습니다.','2023-10-06 02:31:57.282958','스토어 입금 완료',2,_binary '',3),(25,'대화 님과의 스토어 거래가 완료되었습니다.','2023-10-06 02:32:09.355551','스토어 거래 완료',2,_binary '',3),(26,'대화 님과의 스토어 거래가 취소되었습니다.','2023-10-06 02:35:10.250349','스토어 거래 취소',2,_binary '\0',3),(29,'B207노윤식 님의 요청이 수락되었습니다.','2023-10-06 02:37:39.507649','물물교환 알림',3,_binary '\0',3),(31,'대화 님과의 경매가 진행 중 입니다.','2023-10-06 02:44:20.028432','경매 거래 진행',1,_binary '',3),(32,'대화 님이 경매 입금을 완료하였습니다.','2023-10-06 02:46:13.746136','경매 입금 완료',1,_binary '',3),(34,'대화 님과의 경매가 완료되었습니다.','2023-10-06 02:59:42.414057','경매 거래 완료',1,_binary '',3),(36,'대화 님과의 스토어 거래가 완료되었습니다.','2023-10-06 02:59:50.129169','스토어 거래 완료',2,_binary '',3),(37,'대화 님이 스토어 거래를 취소하였습니다','2023-10-06 03:02:38.586312','스토어 거래 취소',2,_binary '\0',3),(39,'대화 님과의 경매가 진행 중 입니다.','2023-10-06 03:03:55.082328','경매 거래 진행',1,_binary '\0',3),(41,'대화 님이 경매를 취소하였습니다','2023-10-06 03:05:55.954493','경매 거래 취소',1,_binary '\0',3),(43,'대화 님과의 경매가 진행 중 입니다.','2023-10-06 03:06:06.693038','경매 거래 진행',1,_binary '\0',3),(45,'대화 님이 경매를 취소하였습니다','2023-10-06 03:06:57.333218','경매 거래 취소',1,_binary '\0',3),(48,'대화 님과의 경매가 진행 중 입니다.','2023-10-06 03:07:19.102741','경매 거래 진행',1,_binary '\0',3),(50,'대화 님과의 경매가 취소되었습니다.','2023-10-06 03:09:35.844107','경매 거래 취소',1,_binary '\0',3),(52,'대화 님과의 경매가 진행 중 입니다.','2023-10-06 03:11:09.088043','경매 거래 진행',1,_binary '\0',3),(54,'대화 님과의 경매가 완료되었습니다.','2023-10-06 03:13:11.006930','경매 거래 완료',1,_binary '\0',3),(56,'대화 님과의 경매가 진행 중 입니다.','2023-10-06 03:15:47.136700','경매 거래 진행',1,_binary '\0',3),(58,'대화 님과의 경매가 취소되었습니다.','2023-10-06 03:20:51.399814','경매 거래 취소',1,_binary '\0',3),(60,'대화 님이 스토어 거래를 취소하였습니다','2023-10-06 03:21:28.516078','스토어 거래 취소',2,_binary '\0',3),(62,'대화 님이 스토어 거래를 취소하였습니다','2023-10-06 03:22:08.286215','스토어 거래 취소',2,_binary '\0',3),(64,'대화 님과의 경매가 진행 중 입니다.','2023-10-06 03:22:20.716310','경매 거래 진행',1,_binary '\0',3),(66,'대화 님이 경매를 취소하였습니다','2023-10-06 03:22:23.478989','경매 거래 취소',1,_binary '\0',3),(69,'대화 님과의 경매가 진행 중 입니다.','2023-10-06 03:22:41.382297','경매 거래 진행',1,_binary '\0',3),(75,'스타벅스 카페 라떼 T 외 1개의 기프티콘 유효기간이 1일 남았습니다.','2023-10-06 04:30:00.224625','기프티콘 알림',1,_binary '\0',1),(76,'CU 모바일상품권 5,000원 기프티콘 유효기간이 7일 남았습니다.','2023-10-06 04:30:00.351934','기프티콘 알림',1,_binary '\0',1),(77,'스타벅스 카페 라떼 T 외 1개의 기프티콘 유효기간이 1일 남았습니다.','2023-10-06 04:35:00.207620','기프티콘 알림',1,_binary '\0',1),(78,'CU 모바일상품권 5,000원 기프티콘 유효기간이 7일 남았습니다.','2023-10-06 04:35:00.307126','기프티콘 알림',1,_binary '\0',1),(79,'스타벅스 카페 라떼 T 외 1개의 기프티콘 유효기간이 1일 남았습니다.','2023-10-06 04:40:00.212320','기프티콘 알림',1,_binary '\0',1),(80,'CU 모바일상품권 5,000원 기프티콘 유효기간이 7일 남았습니다.','2023-10-06 04:40:00.341669','기프티콘 알림',1,_binary '\0',1),(81,'스타벅스 카페 라떼 T 외 1개의 기프티콘 유효기간이 1일 남았습니다.','2023-10-06 04:45:00.228577','기프티콘 알림',1,_binary '\0',1),(82,'CU 모바일상품권 5,000원 기프티콘 유효기간이 7일 남았습니다.','2023-10-06 04:45:00.353595','기프티콘 알림',1,_binary '\0',1),(83,'홈런왕박주영 님이 입찰하셨습니다.','2023-10-06 08:43:12.030718','경매 입찰 알림',1,_binary '',1);
/*!40000 ALTER TABLE `notification_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `report_idx` bigint NOT NULL AUTO_INCREMENT,
  `report_completed_date` datetime(6) DEFAULT NULL,
  `report_created_date` datetime(6) DEFAULT NULL,
  `report_text` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `reported_idx` bigint DEFAULT NULL,
  `reporter_idx` bigint DEFAULT NULL,
  PRIMARY KEY (`report_idx`),
  KEY `FKqn9fnxdn3pehlyucqn7ielfqg` (`reported_idx`),
  KEY `FKs0asycbfskx7x2eq3d1qk0owc` (`reporter_idx`),
  CONSTRAINT `FKqn9fnxdn3pehlyucqn7ielfqg` FOREIGN KEY (`reported_idx`) REFERENCES `user` (`user_idx`),
  CONSTRAINT `FKs0asycbfskx7x2eq3d1qk0owc` FOREIGN KEY (`reporter_idx`) REFERENCES `user` (`user_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `store_idx` bigint NOT NULL AUTO_INCREMENT,
  `notification_created_date` datetime(6) DEFAULT NULL,
  `store_created_date` datetime(6) DEFAULT NULL,
  `store_end_date` datetime(6) DEFAULT NULL,
  `store_price` int NOT NULL,
  `store_status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `store_text` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `store_consumer_idx` bigint DEFAULT NULL,
  `gifticon_idx` bigint DEFAULT NULL,
  `store_user_idx` bigint DEFAULT NULL,
  PRIMARY KEY (`store_idx`),
  KEY `FKgjti38ga7w9t6x5wksftlgblo` (`store_consumer_idx`),
  KEY `FK5eymyaj8ks8aj8gnc8gckgw9a` (`gifticon_idx`),
  KEY `FKn9wgwyr3o4nubcsa5x7vnvq9j` (`store_user_idx`),
  CONSTRAINT `FK5eymyaj8ks8aj8gnc8gckgw9a` FOREIGN KEY (`gifticon_idx`) REFERENCES `gifticon` (`gifticon_idx`),
  CONSTRAINT `FKgjti38ga7w9t6x5wksftlgblo` FOREIGN KEY (`store_consumer_idx`) REFERENCES `user` (`user_idx`),
  CONSTRAINT `FKn9wgwyr3o4nubcsa5x7vnvq9j` FOREIGN KEY (`store_user_idx`) REFERENCES `user` (`user_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (1,NULL,'2023-10-06 02:26:24.574906','2023-09-20 23:59:59.000000',3000,'낙찰','미스터피자 미스터 트리오 입니다. ',2,2,3),(3,NULL,'2023-10-06 02:43:10.395714','2023-09-20 23:59:59.000000',5000,'낙찰','미스터피자 판매합니다.\n저렴하게 판매 중입니다..',2,2,3),(4,NULL,'2023-10-06 03:00:24.558601','2024-10-28 23:59:59.000000',15000,'진행 중','슈프림',NULL,4,2),(5,NULL,'2023-10-06 08:52:14.124433','2024-06-01 23:59:59.000000',6000,'진행 중','케이크를 안좋아해서 올려요. ! 드실분. 싸게  사가세요 ~',NULL,16,4),(6,NULL,'2023-10-06 09:23:20.942652','2023-12-13 23:59:59.000000',11000,'진행 중','크리스마스 한정 기간 음료입니다.',NULL,28,2),(7,NULL,'2023-10-06 09:24:44.002225','2024-04-17 23:59:59.000000',2500,'진행 중','빠르게 팔고 싶어서 싸게 올려요',NULL,24,2),(8,NULL,'2023-10-06 09:25:31.446797','2024-03-31 23:59:59.000000',4500,'진행 중','커피입니다',NULL,29,2),(9,NULL,'2023-10-06 09:25:59.030148','2024-08-31 23:59:59.000000',68830,'진행 중','팔아용',NULL,7,3);
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_category`
--

DROP TABLE IF EXISTS `sub_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sub_category` (
  `sub_category_idx` int NOT NULL AUTO_INCREMENT,
  `sub_category_content` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `main_category_idx` int DEFAULT NULL,
  PRIMARY KEY (`sub_category_idx`),
  KEY `FK3nj49xpr8q2in8uf7jmu5q865` (`main_category_idx`),
  CONSTRAINT `FK3nj49xpr8q2in8uf7jmu5q865` FOREIGN KEY (`main_category_idx`) REFERENCES `main_category` (`main_category_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_category`
--

LOCK TABLES `sub_category` WRITE;
/*!40000 ALTER TABLE `sub_category` DISABLE KEYS */;
INSERT INTO `sub_category` VALUES (1,'버거',1),(2,'치킨',1),(3,'피자',1),(4,'금액권',2),(5,'과자',2),(6,'음료',2),(7,'도시락/김밥류',2),(8,'커피',3),(9,'베이커리',3),(10,'베스킨라빈스',4),(11,'기타',2),(12,'기타',3),(13,'기타',4);
/*!40000 ALTER TABLE `sub_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `used_gifticon`
--

DROP TABLE IF EXISTS `used_gifticon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `used_gifticon` (
  `used_gifticon_idx` bigint NOT NULL AUTO_INCREMENT,
  `used_gifticon_barcode` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `used_gifticon_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`used_gifticon_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `used_gifticon`
--

LOCK TABLES `used_gifticon` WRITE;
/*!40000 ALTER TABLE `used_gifticon` DISABLE KEYS */;
INSERT INTO `used_gifticon` VALUES (1,'8310016554045408','2023-10-06 02:05:47.328108'),(2,'921125315632','2023-10-06 02:06:41.629563');
/*!40000 ALTER TABLE `used_gifticon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_idx` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `fcm_token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `refresh_token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_account` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_account_bank` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_age` int NOT NULL,
  `user_deleted_date` datetime(6) DEFAULT NULL,
  `user_deposit` bigint NOT NULL,
  `user_email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_gender` char(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_nickname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_pattern` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_phone_number` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_profile_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_restrict_count` int DEFAULT NULL,
  `user_restrict_end_date` datetime(6) DEFAULT NULL,
  `user_status` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`user_idx`),
  UNIQUE KEY `UK_b677sn8rlv4xp0xs28b2ypf2y` (`refresh_token`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2023-10-06 01:05:23.794426','2023-10-06 08:53:40.076458','cS0RngLHRoKT1SwoXvFjUO:APA91bGGI_RKgdnuwNkTBMLL3mW1ZAKpb5bZXj4bcJu6-p0vXpvA0gn0idUdK9TwpHBpbtr9sBOyf7x5_nnx2klQwRdutQA49dxawlI2cfcfvwEErRrAvtgmehueAjcC1MyHp3uGhIq7','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2xpem9uOSIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2OTc3NTk2MjB9.yGPWHyt3Sm5d17Iw_S-s3PS3RG1AOUub3_XM7ZFYGUc','국민은행','94580200202693',26,NULL,0,'holizon9@naver.com','M','holizon9','박재현','holizon9','$2a$10$uzUTe4CjrH0s9pEIIJ/5SuorMEDTnQEyFZWthTdEBahohAKV.KtWO','1-2-5-8','01068943121','https://b207-conseller.s3.ap-northeast-2.amazonaws.com/55ff01ea-deeb-43ab-855a-64bb1f548769.jpg',0,NULL,'정상'),(2,'2023-10-06 01:05:53.320641','2023-10-06 08:41:31.295763','cueoiViXRcyDqqxFR1U3Yk:APA91bEaUYvYbgVPDQwMIJv2GcQ-v_VvjPRQcGFC3KZCrKV9NuN9K-5x7N--UAae4BuCfOCjDcvLYumCZ4V23O6BWvvow3mfX952wo9YRn0bKvgbiLcQse1-OHyXmY_5PPvpgYLQt3pO','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlb2doazEyMyIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2OTc3NTg4OTF9.oVAsQ4VqmdSBLdOBbZ_HERUsrKzi0pfztyoN-EhAssc','64591042192907','하나은행',24,NULL,0,'daewha1021@gmail.com','M','eoghk123','박대화','대화','$2a$10$84iGcZUZzFbOYfDNEoPlquKKLk87ujOFCkcavQiNKVoCna8WS4aEW','4-5-8-7','01088298416',NULL,0,NULL,'정상'),(3,'2023-10-06 01:23:45.706533','2023-10-06 11:00:29.476174','fr5gJMCsSaKZ_-HODeltzx:APA91bHdXznDxKgNF9DoqSZRRF71iV6VwJZNzKYrn0LnD_DlDCZVbzs2DGuXdyi5onfISHdsqbLX7t7eoAxURch3ChbMkmOPv_KtTiLj_ObIP0mITso3U610Z7yKH6m8N_cq0S5fPOEW','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiMjA3IiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY5Nzc2NzIyOX0.sMZniXn_l_NRWDjIDIAhKvkQjxIZBGihO7GbnVYuHJQ','48460104124097','국민은행',25,NULL,0,'980430nys@gmail.com','M','b207','노윤식','B207노윤식','$2a$10$.wTb31KtC6U2cg3pLQCaU.up7XGdLTYx72AGxXjtZnDOMstAzis6m','1-2-5-8','01098790430',NULL,0,NULL,'정상'),(4,'2023-10-06 01:30:31.773331','2023-10-06 04:54:10.378765','cm-e1ORRSAqyRJC7iKNDOw:APA91bH89zXKbqcS-E5jDAMFyjM-3OuBAz9T9AB8SUl5wJ6O4b2HN7V7andwwrZ0igmgybC6QtvJo-8edae840nYDbAFHmfi3_xm440jh6VaARZcoApzZJKsy1klhgMBn9WkytjBiyHd','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraW1lbnNvbyIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2OTc3NDUyNTB9.gbppnBlDLsjJWLzzNdcBTKZ8Go7zpwdjOZoSm_x8-so','34691047826307','하나은행',27,NULL,0,'ekclstkfka5@gmail.com','M','kimensoo','김현수','홈런왕박주영','$2a$10$njp7Mg8H2vCOpHFiVlX..uFkXCv/aO82rKkEZI1KQmFOaqOr4Sa1K',NULL,'01050945330',NULL,0,NULL,'정상'),(5,'2023-10-06 10:17:12.598685','2023-10-06 10:46:48.383289','fr5gJMCsSaKZ_-HODeltzx:APA91bHdXznDxKgNF9DoqSZRRF71iV6VwJZNzKYrn0LnD_DlDCZVbzs2DGuXdyi5onfISHdsqbLX7t7eoAxURch3ChbMkmOPv_KtTiLj_ObIP0mITso3U610Z7yKH6m8N_cq0S5fPOEW','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraW15dW5hIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY5Nzc2NjQwOH0.9TySwSkc_vKpQF1WGCrS8Nd4Cd2f7ZPg480vhb5rAQ0','56344555245','우리은행',24,NULL,0,'kimyuna@gmail.com','F','kimyuna','김유나','yunakim','$2a$10$o.bOgkSBUT.WsUqrz2wME.Br6p74ubzJ2f6KGYK/kA5tmlwe8d9W2',NULL,'010955500150',NULL,0,NULL,'정상');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_user_idx` bigint NOT NULL,
  `roles` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  KEY `FK54ufifm0dplseidhwj4sar15n` (`user_user_idx`),
  CONSTRAINT `FK54ufifm0dplseidhwj4sar15n` FOREIGN KEY (`user_user_idx`) REFERENCES `user` (`user_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,'USER'),(2,'USER'),(3,'USER'),(4,'USER'),(5,'USER');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-06 11:24:15
