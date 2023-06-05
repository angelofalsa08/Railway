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
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `seat_id` int NOT NULL AUTO_INCREMENT,
  `train_id` int DEFAULT NULL,
  `seat_number` varchar(10) DEFAULT NULL,
  `class` varchar(20) DEFAULT NULL,
  `availability_status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`seat_id`),
  KEY `train_id` (`train_id`),
  CONSTRAINT `seat_ibfk_1` FOREIGN KEY (`train_id`) REFERENCES `train` (`train_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,1,'S1P1','Premium','Occupied'),(2,1,'S1P2','Premium','Occupied'),(3,1,'S1P3','Premium','Available'),(4,1,'S1P4','Premium','Available'),(5,1,'S1P5','Premium','Available'),(6,1,'S1R1','Regular','Available'),(7,1,'S1R2','Regular','Available'),(8,1,'S1R3','Regular','Available'),(9,1,'S1R4','Regular','Available'),(10,1,'S1R5','Regular','Occupied'),(11,2,'S2P1','Premium','Occupied'),(12,2,'S2P2','Premium','Occupied'),(13,2,'S2P3','Premium','Available'),(14,2,'S2P4','Premium','Available'),(15,2,'S2P5','Premium','Available'),(16,2,'S2R1','Regular','Available'),(17,2,'S2R2','Regular','Available'),(18,2,'S2R3','Regular','Available'),(19,2,'S2R4','Regular','Occupied'),(20,2,'S2R5','Regular','Occupied'),(21,3,'S3P1','Premium','Available'),(22,3,'S3P2','Premium','Available'),(23,3,'S3P3','Premium','Available'),(24,3,'S3P4','Premium','Available'),(25,3,'S3P5','Premium','Available'),(26,3,'S3R1','Regular','Available'),(27,3,'S3R2','Regular','Available'),(28,3,'S3R3','Regular','Available'),(29,3,'S3R4','Regular','Available'),(30,3,'S3R5','Regular','Available'),(31,4,'S4P1','Premium','Available'),(32,4,'S4P2','Premium','Available'),(33,4,'S4P3','Premium','Available'),(34,4,'S4P4','Premium','Available'),(35,4,'S4P5','Premium','Available'),(36,4,'S4R1','Regular','Available'),(37,4,'S4R2','Regular','Available'),(38,4,'S4R3','Regular','Available'),(39,4,'S4R4','Regular','Available'),(40,4,'S4R5','Regular','Available');
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
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
