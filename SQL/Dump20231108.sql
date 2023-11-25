-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: carsonline_
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `booking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `Description` text,
  `FareAmount` bigint(20) DEFAULT NULL,
  `isProtect` bit(1) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  `car_id` varchar(255) DEFAULT NULL,
  `id_drivertrip` bigint(20) DEFAULT NULL,
  `id_dropoff` bigint(20) DEFAULT NULL,
  `id_pickup` bigint(20) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK128686spaxk6s1xffsqyb2fix` (`car_id`),
  KEY `FKbjr8bmbrgd65nxrekrtka0kfd` (`id_drivertrip`),
  KEY `FKtie0mooeplx1wu74mayeocogu` (`id_dropoff`),
  KEY `FK5kfyqs9rylc05mubq3isqrus7` (`id_pickup`),
  KEY `FKksyqug2s7al3nwt9laoclhp6f` (`id_user`),
  CONSTRAINT `FK128686spaxk6s1xffsqyb2fix` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`),
  CONSTRAINT `FK5kfyqs9rylc05mubq3isqrus7` FOREIGN KEY (`id_pickup`) REFERENCES `detailpickuplocation` (`id`),
  CONSTRAINT `FKbjr8bmbrgd65nxrekrtka0kfd` FOREIGN KEY (`id_drivertrip`) REFERENCES `drivertrip` (`id`),
  CONSTRAINT `FKksyqug2s7al3nwt9laoclhp6f` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FKtie0mooeplx1wu74mayeocogu` FOREIGN KEY (`id_dropoff`) REFERENCES `detaildropofflocation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_seat`
--

DROP TABLE IF EXISTS `booking_seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `booking_seat` (
  `booking_id` bigint(20) NOT NULL,
  `seat_id` bigint(20) NOT NULL,
  PRIMARY KEY (`booking_id`,`seat_id`),
  KEY `FK22okdpk3nw2twqxmps0lp0j0j` (`seat_id`),
  CONSTRAINT `FK22okdpk3nw2twqxmps0lp0j0j` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`id`),
  CONSTRAINT `FKqngtja5c01j14c58a4cix9tss` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_seat`
--

LOCK TABLES `booking_seat` WRITE;
/*!40000 ALTER TABLE `booking_seat` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking_seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `car` (
  `id` varchar(255) NOT NULL,
  `Availability` bit(1) DEFAULT NULL,
  `licenseplates` varchar(255) DEFAULT NULL,
  `phone_company` varchar(255) DEFAULT NULL,
  `idtypecar` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKlexv2k05j0ip9wpitsni3ssnj` (`licenseplates`),
  KEY `FKqr82uebs1lsiy79ypr01t2d5g` (`phone_company`),
  KEY `FKju5hovvshod2k3iq22ep3ssbj` (`idtypecar`),
  CONSTRAINT `FKju5hovvshod2k3iq22ep3ssbj` FOREIGN KEY (`idtypecar`) REFERENCES `typecar` (`id`),
  CONSTRAINT `FKqr82uebs1lsiy79ypr01t2d5g` FOREIGN KEY (`phone_company`) REFERENCES `company` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `company` (
  `phone` varchar(255) NOT NULL,
  `description` text,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `vnp_HashSecret` varchar(255) DEFAULT NULL,
  `vnp_TmnCode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company` varchar(255) DEFAULT NULL,
  `content` text,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detaildropofflocation`
--

DROP TABLE IF EXISTS `detaildropofflocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `detaildropofflocation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `detailLocation` varchar(255) DEFAULT NULL,
  `idtrip` bigint(20) DEFAULT NULL,
  `timedropoff` time(6) DEFAULT NULL,
  `id_trip` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKadqptevyhoy240ra9lq6a9440` (`id_trip`),
  CONSTRAINT `FKadqptevyhoy240ra9lq6a9440` FOREIGN KEY (`id_trip`) REFERENCES `trip` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detaildropofflocation`
--

LOCK TABLES `detaildropofflocation` WRITE;
/*!40000 ALTER TABLE `detaildropofflocation` DISABLE KEYS */;
/*!40000 ALTER TABLE `detaildropofflocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detailpickuplocation`
--

DROP TABLE IF EXISTS `detailpickuplocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `detailpickuplocation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `detailLocation` varchar(255) DEFAULT NULL,
  `idtrip` bigint(20) DEFAULT NULL,
  `timepickup` time(6) DEFAULT NULL,
  `id_trip` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh9vs6ffpp7i6ewfi19e9eumn9` (`id_trip`),
  CONSTRAINT `FKh9vs6ffpp7i6ewfi19e9eumn9` FOREIGN KEY (`id_trip`) REFERENCES `trip` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detailpickuplocation`
--

LOCK TABLES `detailpickuplocation` WRITE;
/*!40000 ALTER TABLE `detailpickuplocation` DISABLE KEYS */;
/*!40000 ALTER TABLE `detailpickuplocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver_trip_seat`
--

DROP TABLE IF EXISTS `driver_trip_seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `driver_trip_seat` (
  `driver_trip_id` bigint(20) NOT NULL,
  `seat_id` bigint(20) NOT NULL,
  PRIMARY KEY (`driver_trip_id`,`seat_id`),
  KEY `FKc4xuiuyljhu9wml1dgnp17o0k` (`seat_id`),
  CONSTRAINT `FK4mnsp1o1aia9kderuovk8c4k` FOREIGN KEY (`driver_trip_id`) REFERENCES `drivertrip` (`id`),
  CONSTRAINT `FKc4xuiuyljhu9wml1dgnp17o0k` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver_trip_seat`
--

LOCK TABLES `driver_trip_seat` WRITE;
/*!40000 ALTER TABLE `driver_trip_seat` DISABLE KEYS */;
/*!40000 ALTER TABLE `driver_trip_seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drivertrip`
--

DROP TABLE IF EXISTS `drivertrip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `drivertrip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `car_id` varchar(255) DEFAULT NULL,
  `id_trip` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKibg27p2xas3b7p8pxxcd7adba` (`car_id`),
  KEY `FKd63hvhp0qa15tcbs6r0v420qj` (`id_trip`),
  CONSTRAINT `FKd63hvhp0qa15tcbs6r0v420qj` FOREIGN KEY (`id_trip`) REFERENCES `trip` (`id`),
  CONSTRAINT `FKibg27p2xas3b7p8pxxcd7adba` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drivertrip`
--

LOCK TABLES `drivertrip` WRITE;
/*!40000 ALTER TABLE `drivertrip` DISABLE KEYS */;
/*!40000 ALTER TABLE `drivertrip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL,
  `amountt` varchar(255) DEFAULT NULL,
  `orderId` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `transDate` varchar(255) DEFAULT NULL,
  `trantype` varchar(255) DEFAULT NULL,
  `booking_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qq21idr3pxkcr2k3dewxakkjc` (`booking_id`),
  CONSTRAINT `FKt30qv3axmqwhk1wag867yxqum` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotions`
--

DROP TABLE IF EXISTS `promotions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `promotions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Code` varchar(20) DEFAULT NULL,
  `Description` text,
  `DiscountAmount` decimal(38,2) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `company_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7yi9862p4dvde0uh59bs79vdi` (`company_id`),
  CONSTRAINT `FK7yi9862p4dvde0uh59bs79vdi` FOREIGN KEY (`company_id`) REFERENCES `company` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotions`
--

LOCK TABLES `promotions` WRITE;
/*!40000 ALTER TABLE `promotions` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `seat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `idcar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsrvu18fkfs7ixs2n7wod9r32x` (`idcar`),
  CONSTRAINT `FKsrvu18fkfs7ixs2n7wod9r32x` FOREIGN KEY (`idcar`) REFERENCES `car` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `token` (
  `id` int(11) NOT NULL,
  `expired` bit(1) NOT NULL,
  `revoked` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `tokenType` enum('BEARER') DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3wi2t4g8oiplxjflw3o2lkv2y` (`token`),
  KEY `FKiiyr9nhulmfrvje08nvravy02` (`user_id`),
  CONSTRAINT `FKiiyr9nhulmfrvje08nvravy02` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token_seq`
--

DROP TABLE IF EXISTS `token_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `token_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_seq`
--

LOCK TABLES `token_seq` WRITE;
/*!40000 ALTER TABLE `token_seq` DISABLE KEYS */;
INSERT INTO `token_seq` VALUES (1);
/*!40000 ALTER TABLE `token_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip`
--

DROP TABLE IF EXISTS `trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `trip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `abouthours` int(11) DEFAULT NULL,
  `DropoffLocation` varchar(255) DEFAULT NULL,
  `DropoffTime` time(6) DEFAULT NULL,
  `PickupLocation` varchar(255) DEFAULT NULL,
  `PickupTime` time(6) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `urlimage` varchar(255) DEFAULT NULL,
  `phone_company` varchar(255) DEFAULT NULL,
  `PromotionsID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtrvomwrd100aj9y8cm00ilnh0` (`phone_company`),
  KEY `FKp5dp0rpwv55oiok14l40gfirq` (`PromotionsID`),
  CONSTRAINT `FKp5dp0rpwv55oiok14l40gfirq` FOREIGN KEY (`PromotionsID`) REFERENCES `promotions` (`id`),
  CONSTRAINT `FKtrvomwrd100aj9y8cm00ilnh0` FOREIGN KEY (`phone_company`) REFERENCES `company` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--

LOCK TABLES `trip` WRITE;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typecar`
--

DROP TABLE IF EXISTS `typecar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `typecar` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `numberofseats` int(11) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typecar`
--

LOCK TABLES `typecar` WRITE;
/*!40000 ALTER TABLE `typecar` DISABLE KEYS */;
/*!40000 ALTER TABLE `typecar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `LicenseNumber` varchar(255) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `points` int(11) DEFAULT NULL,
  `role` enum('ADMIN','DRIVER','OWNER','STAFF','USER') DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `phone_company` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKe6gkqunxajvyxl5uctpl2vl2p` (`email`),
  UNIQUE KEY `UK8f9egnwceetj2aq0hwqd8togf` (`phone`),
  KEY `FK4hxjoiymxx8bgkn1hlkjdcu72` (`phone_company`),
  CONSTRAINT `FK4hxjoiymxx8bgkn1hlkjdcu72` FOREIGN KEY (`phone_company`) REFERENCES `company` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_driver_trip`
--

DROP TABLE IF EXISTS `user_driver_trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_driver_trip` (
  `driver_trip_id` bigint(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`driver_trip_id`,`user_id`),
  KEY `FKeerub6vu23idg6j4qmckbjv0y` (`user_id`),
  CONSTRAINT `FK4bognvj83cb9sbkb2it4gn1l2` FOREIGN KEY (`driver_trip_id`) REFERENCES `drivertrip` (`id`),
  CONSTRAINT `FKeerub6vu23idg6j4qmckbjv0y` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_driver_trip`
--

LOCK TABLES `user_driver_trip` WRITE;
/*!40000 ALTER TABLE `user_driver_trip` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_driver_trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'carsonline_'
--

--
-- Dumping routines for database 'carsonline_'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-08 22:49:49
