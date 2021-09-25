-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: exhibition
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `exhibitions`
--

DROP TABLE IF EXISTS `exhibitions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exhibitions` (
  `exhibition_id` int NOT NULL AUTO_INCREMENT,
  `exhibition_name` varchar(255) NOT NULL,
  `description` varchar(1023) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `date_start` date NOT NULL,
  `date_end` date NOT NULL,
  `theme` varchar(255) NOT NULL,
  `exhibition_status` enum('ACTIVE','SUSPENDED') NOT NULL,
  `hall` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`exhibition_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exhibitions`
--

LOCK TABLES `exhibitions` WRITE;
/*!40000 ALTER TABLE `exhibitions` DISABLE KEYS */;
INSERT INTO `exhibitions` VALUES (1,'POWER ENGINEERING FOR INDUSTRY \'2021','(power, electrical equipment and technologies for nuclear, thermal, hydro power plants, high-voltage power grids, substations, power distribution grids, power supply systems of industrial enterprises)',500.00,'2021-09-26','2021-10-09','power','ACTIVE',1),(2,'ECOENERGY EXPO \'2021','(solar & wind power, domestic and industrial energy storage systems, distributed energy, bioenergy, eco-transport, LED lighting, industrial ecology)',200.00,'2021-08-01','2021-09-19','power','ACTIVE',1),(3,'ELECTRO INSTALL \'2021','(low‑voltage protective, switching, control, voltage and current regulation devices, control and metering devices, electrical wiring and installation products, lightning protection and earthing systems, lighting equipment, automation components and systems, driving equipment)',300.00,'2021-08-28','2021-09-12','power','ACTIVE',1),(4,'KYIV FASHION 2021','Організатор: ТОВ \"Київський міжнародний контрактовий ярмарок\" ',200.00,'2021-09-28','2021-10-01','fashion','ACTIVE',2),(5,'PRO BEAUTY EXPO','Congress of Beauty Industry',100.00,'2021-11-11','2021-11-25','fashion','ACTIVE',1),(6,'PUBLIC HEALTH','30th International Medical Exhibition PUBLIC HEALTH',400.00,'2021-09-30','2021-10-07','health','SUSPENDED',1),(7,'INTERNATIONAL DENTAL FORUM','International exhibition of dental equipment and materials',320.00,'2021-09-27','2021-10-01','health','SUSPENDED',2),(8,'PHARMTECH & INGREDIENTS UKRAINE','International exhibition of equipment, raw materials and technologies for pharmaceutical production',140.00,'2021-10-11','2021-10-28','health','ACTIVE',1),(9,'MTEC. KYIV','Medical Travel Exhibition & Conference',0.00,'2021-11-14','2021-11-23','health','ACTIVE',1),(10,'HANDMADE-Expo Exhibition of needlework and creativity №1 in Ukraine','34th International wholesale Exhibition. Business and trends of Art & Hobby. Products for needlework and handmade creativity. Designer products. The exposition of the masters’ handmade artworks. Market of ideas. Decor. The world of dolls. Vishivanki. Seminars and master classes',100.00,'2021-09-15','2021-10-20','handmade','ACTIVE',3),(11,'XIX INTERNATIONAL ENERGY BUSINESS FORUM \"5E\"','Energy. Electrical Engineering. Energy Efficiency. Ecology. Energy Resources.',210.00,'2021-09-22','2021-09-30','power','ACTIVE',1),(12,'INPRODMASH 2021','International Trade Fair of Equipment and Technologies for Food Processing Industry',230.00,'2021-09-11','2021-09-14','prom','ACTIVE',1),(13,'UPAKOVKA 2021','Міжнародна виставка обладнання і технологій для упаковки',230.00,'2021-09-14','2021-09-23','prom','ACTIVE',3),(14,'PRO BEAUTY EXPO','Конгрес індустрії краси',239.00,'2021-09-21','2021-09-23','fashion','ACTIVE',2);
/*!40000 ALTER TABLE `exhibitions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `exhibition_id` int NOT NULL,
  `user_id` int NOT NULL,
  `order_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`order_id`),
  KEY `exhibition_id` (`exhibition_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `exhibition_id` FOREIGN KEY (`exhibition_id`) REFERENCES `exhibitions` (`exhibition_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,1,1),(12,1,21),(11,1,22),(10,1,23),(8,1,24),(12,1,25),(10,1,26),(9,1,27),(12,1,28),(10,3,29),(9,3,30),(13,3,31),(12,3,32),(13,4,33),(12,4,34);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `login` varchar(127) NOT NULL,
  `user_id` int NOT NULL AUTO_INCREMENT,
  `password` char(128) NOT NULL,
  `first_name` varchar(127) NOT NULL,
  `last_name` varchar(127) NOT NULL,
  `date_of_birth` date NOT NULL,
  `gender` enum('MALE','FEMALE') NOT NULL,
  `role` enum('USER','ADMIN') NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('user',1,'febad17a20a92804e3132fc8a89b99d21c32a6ea58b8faa36b3186d8b60dfb97','Vadim','Radchenko','1991-09-28','MALE','USER'),('admin',2,'febad17a20a92804e3132fc8a89b99d21c32a6ea58b8faa36b3186d8b60dfb97','Test','Deeerr','2021-09-01','MALE','ADMIN'),('warwar',3,'febad17a20a92804e3132fc8a89b99d21c32a6ea58b8faa36b3186d8b60dfb97','Rootigggg','Rooting','2021-05-03','MALE','USER'),('test2',4,'febad17a20a92804e3132fc8a89b99d21c32a6ea58b8faa36b3186d8b60dfb97','Asa','Ders','1998-09-07','FEMALE','USER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-25  6:21:49
