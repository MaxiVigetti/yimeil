-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: yimeil
-- ------------------------------------------------------
-- Server version	9.1.0

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
-- Table structure for table `attachments`
--

DROP TABLE IF EXISTS `attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attachments` (
  `idattachments` int NOT NULL AUTO_INCREMENT,
  `filename` varchar(100) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `correo_emailId` int NOT NULL,
  PRIMARY KEY (`idattachments`),
  KEY `fk_attachments_correo_idx` (`correo_emailId`),
  CONSTRAINT `fk_attachments_correo` FOREIGN KEY (`correo_emailId`) REFERENCES `correo` (`emailId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachments`
--

LOCK TABLES `attachments` WRITE;
/*!40000 ALTER TABLE `attachments` DISABLE KEYS */;
INSERT INTO `attachments` VALUES (1,'archivo.txt','http://url.al.archivo',20),(2,'archivo.txt','http://url.al.archivo',21),(3,'archivo.txt','http://url.al.archivo',22),(4,'archivo.txt','http://url.al.archivo',23),(5,'archivo.txt','http://url.al.archivo',24),(6,'archivo.txt','http://url.al.archivo',25),(7,'archivo.txt','http://url.al.archivo',26),(8,'archivo.txt','http://url.al.archivo',27),(9,'archivo.txt','http://url.al.archivo',28),(10,'archivo.txt','http://url.al.archivo',29);
/*!40000 ALTER TABLE `attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `correo`
--

DROP TABLE IF EXISTS `correo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `correo` (
  `emailId` int NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) DEFAULT NULL,
  `body` longtext,
  `receivedAt` timestamp NULL DEFAULT NULL,
  `userId` varchar(45) NOT NULL,
  `from` varchar(255) NOT NULL,
  PRIMARY KEY (`emailId`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `correo`
--

LOCK TABLES `correo` WRITE;
/*!40000 ALTER TABLE `correo` DISABLE KEYS */;
INSERT INTO `correo` VALUES (1,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 00:51:34','',''),(2,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 00:53:00','',''),(3,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 00:53:01','',''),(4,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 00:53:02','',''),(5,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 00:53:03','',''),(6,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 00:53:03','',''),(7,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 00:57:45','',''),(8,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 00:57:46','',''),(9,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 00:57:47','',''),(10,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 00:57:48','',''),(11,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 00:57:49','',''),(12,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 00:57:50','',''),(13,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 01:02:56','',''),(14,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 01:08:52','',''),(15,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 01:11:39','',''),(16,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 01:15:25','',''),(17,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 01:15:26','',''),(18,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 02:03:13','',''),(19,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 02:03:18','',''),(20,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-26 03:14:18','yourSystemId',''),(21,'Probandoooo','FUNCIONAAA','2024-11-26 03:15:37','yourSystemId',''),(22,'Probandoooo','FUNCIONAAA','2024-11-26 03:33:17','yourSystemId',''),(23,'Probandoooo','FUNCIONAAA','2024-11-26 03:44:29','yourSystemId',''),(24,'Hola German','Hola German','2024-11-26 21:21:39','yourSystemId',''),(25,'Hola German','Hola German','2024-11-27 00:55:03','yourSystemId','Maxi@example.com'),(26,'Hola German','Hola German','2024-11-27 00:56:17','yourSystemId','Maxi@example.com'),(27,'Prueba','pruebas','2024-11-27 01:11:36','yourSystemId','Maxi@example.com'),(28,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-27 17:06:21','yourSystemId','remitente@example.com'),(29,'Asunto de prueba','Cuerpo del correo de prueba','2024-11-27 17:06:26','yourSystemId','remitente@example.com');
/*!40000 ALTER TABLE `correo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `from`
--

DROP TABLE IF EXISTS `from`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `from` (
  `idfrom` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `correo_emailId` int NOT NULL,
  PRIMARY KEY (`idfrom`),
  KEY `fk_from_correo1_idx` (`correo_emailId`),
  CONSTRAINT `fk_from_correo1` FOREIGN KEY (`correo_emailId`) REFERENCES `correo` (`emailId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `from`
--

LOCK TABLES `from` WRITE;
/*!40000 ALTER TABLE `from` DISABLE KEYS */;
INSERT INTO `from` VALUES (5,'dest1@example.com',20),(6,'dest2@example.com',20),(7,'dest1@example.com',21),(8,'dest2@example.com',21),(9,'dest1@example.com',22),(10,'dest2@example.com',22),(11,'dest1@example.com',23),(12,'dest2@example.com',23),(13,'German@example.com',24),(14,'dest2@example.com',24),(15,'German@example.com',25),(16,'dest2@example.com',25),(17,'German@example.com',26),(18,'dest2@example.com',26),(19,'German@example.com',27),(20,'dest1@example.com',28),(21,'dest2@example.com',28),(22,'dest1@example.com',29),(23,'dest2@example.com',29);
/*!40000 ALTER TABLE `from` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-27 14:22:52
