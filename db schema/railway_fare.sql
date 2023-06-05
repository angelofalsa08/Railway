-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: railway
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `fare`
--

DROP TABLE IF EXISTS `fare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fare` (
  `fare_id` int NOT NULL AUTO_INCREMENT,
  `train_id` int DEFAULT NULL,
  `class` varchar(20) DEFAULT NULL,
  `passenger_type` varchar(20) DEFAULT NULL,
  `fare_amount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`fare_id`),
  KEY `train_id` (`train_id`),
  CONSTRAINT `fare_ibfk_1` FOREIGN KEY (`train_id`) REFERENCES `train` (`train_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fare`
--

LOCK TABLES `fare` WRITE;
/*!40000 ALTER TABLE `fare` DISABLE KEYS */;
INSERT INTO `fare` VALUES (1,1,'Premium','Child',25.00),(2,1,'Premium','Adult',35.00),(3,1,'Premium','Senior',30.00),(4,1,'Premium','PWD',30.00),(5,1,'Regular','Child',15.00),(6,1,'Regular','Adult',25.00),(7,1,'Regular','Senior',20.00),(8,1,'Regular','PWD',20.00),(9,2,'Premium','Child',45.00),(10,2,'Premium','Adult',55.00),(11,2,'Premium','Senior',50.00),(12,2,'Premium','PWD',50.00),(13,2,'Regular','Child',35.00),(14,2,'Regular','Adult',45.00),(15,2,'Regular','Senior',40.00),(16,2,'Regular','PWD',40.00),(17,3,'Premium','Child',65.00),(18,3,'Premium','Adult',75.00),(19,3,'Premium','Senior',60.00),(20,3,'Premium','PWD',60.00),(21,3,'Regular','Child',55.00),(22,3,'Regular','Adult',65.00),(23,3,'Regular','Senior',50.00),(24,3,'Regular','PWD',50.00),(25,4,'Premium','Child',90.00),(26,4,'Premium','Adult',100.00),(27,4,'Premium','Senior',85.00),(28,4,'Premium','PWD',85.00),(29,4,'Regular','Child',80.00),(30,4,'Regular','Adult',90.00),(31,4,'Regular','Senior',75.00),(32,4,'Regular','PWD',75.00);
/*!40000 ALTER TABLE `fare` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-05 18:07:22
