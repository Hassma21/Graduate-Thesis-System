-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: gts
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `thesistb`
--

DROP TABLE IF EXISTS `thesistb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thesistb` (
  `THESISNO` int NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(200) NOT NULL,
  `ABSTRACT` text NOT NULL,
  `AUTHORID` int NOT NULL,
  `YEAROFTHESIS` date NOT NULL,
  `TYPEID` int NOT NULL,
  `UNIVERSITYID` int NOT NULL,
  `INSTITUTEID` int NOT NULL,
  `NUMBEROFPAGE` smallint DEFAULT NULL,
  `LANGUAGEID` tinyint NOT NULL,
  `SUBMISSIONDATE` datetime NOT NULL,
  `SUPERVISORID` int NOT NULL,
  `CO_SUPERVISORID` int DEFAULT NULL,
  PRIMARY KEY (`THESISNO`),
  KEY `AUTHORID` (`AUTHORID`),
  KEY `TYPEID` (`TYPEID`),
  KEY `UNIVERSITYID` (`UNIVERSITYID`),
  KEY `INSTITUTEID` (`INSTITUTEID`),
  KEY `LANGUAGEID` (`LANGUAGEID`),
  KEY `SUPERVISORID` (`SUPERVISORID`),
  KEY `CO_SUPERVISORID` (`CO_SUPERVISORID`),
  CONSTRAINT `thesistb_ibfk_1` FOREIGN KEY (`AUTHORID`) REFERENCES `authortb` (`AUTHORID`),
  CONSTRAINT `thesistb_ibfk_2` FOREIGN KEY (`TYPEID`) REFERENCES `typetb` (`TYPEID`),
  CONSTRAINT `thesistb_ibfk_3` FOREIGN KEY (`UNIVERSITYID`) REFERENCES `universitytb` (`UNIVERSITYID`),
  CONSTRAINT `thesistb_ibfk_4` FOREIGN KEY (`INSTITUTEID`) REFERENCES `institutetb` (`INSTITUTEID`),
  CONSTRAINT `thesistb_ibfk_5` FOREIGN KEY (`LANGUAGEID`) REFERENCES `languagetb` (`LANGUAGEID`),
  CONSTRAINT `thesistb_ibfk_6` FOREIGN KEY (`SUPERVISORID`) REFERENCES `visortb` (`VISORID`),
  CONSTRAINT `thesistb_ibfk_7` FOREIGN KEY (`CO_SUPERVISORID`) REFERENCES `visortb` (`VISORID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-10 15:18:03
