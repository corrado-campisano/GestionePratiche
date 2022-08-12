-- MariaDB dump 10.19  Distrib 10.5.15-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: gestionePratiche
-- ------------------------------------------------------
-- Server version	10.5.15-MariaDB-0+deb11u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `avanzamento`
--

DROP TABLE IF EXISTS `avanzamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `avanzamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pratica_id` int(11) unsigned NOT NULL,
  `data` date NOT NULL,
  `descrizione` varchar(255) NOT NULL,
  `stato_precedente_id` int(11) unsigned NOT NULL,
  `stato_attuale_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avanzamento`
--

LOCK TABLES `avanzamento` WRITE;
/*!40000 ALTER TABLE `avanzamento` DISABLE KEYS */;
INSERT INTO `avanzamento` VALUES (1,1,'2022-09-08','presa in carico',1,3),(2,1,'2022-09-09','rigettata importo esiguo',3,2),(3,2,'2022-08-12','materiale archiviato su //server/share/folder ...',1,3),(4,2,'2022-08-12','da notificare entro 2022-08-23',3,4),(5,2,'2022-08-12','da depositare entro 2022-09-04',4,5),(6,2,'2022-08-12','sentenza prevista per il 2022-10-12',5,8),(7,2,'2022-08-12','fatturare entro il trimestre',8,9),(8,2,'2022-08-12','fattura emessa n. 34/2022',9,10);
/*!40000 ALTER TABLE `avanzamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ragione_sociale` varchar(255) NOT NULL,
  `codice_fiscale` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Mario Rossi','RSSMRO60P23T430D'),(2,'Sergio Bianchi','BNCSRG58D13H501F');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pratica`
--

DROP TABLE IF EXISTS `pratica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pratica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identificativo` varchar(255) NOT NULL,
  `descrizione` varchar(255) NOT NULL,
  `cliente_id` int(11) unsigned NOT NULL,
  `tipo_id` int(11) unsigned NOT NULL,
  `stato_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pratica`
--

LOCK TABLES `pratica` WRITE;
/*!40000 ALTER TABLE `pratica` DISABLE KEYS */;
INSERT INTO `pratica` VALUES (1,'2022-01','cartella per TARI',1,1,2),(2,'2022-02','cartella per IMU',2,2,10);
/*!40000 ALTER TABLE `pratica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pratica_professionista`
--

DROP TABLE IF EXISTS `pratica_professionista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pratica_professionista` (
  `pratica_id` int(11) unsigned NOT NULL,
  `professionista_id` int(11) unsigned NOT NULL,
  UNIQUE KEY `pratica_id` (`pratica_id`,`professionista_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pratica_professionista`
--

LOCK TABLES `pratica_professionista` WRITE;
/*!40000 ALTER TABLE `pratica_professionista` DISABLE KEYS */;
INSERT INTO `pratica_professionista` VALUES (1,1),(1,4),(2,3);
/*!40000 ALTER TABLE `pratica_professionista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professionista`
--

DROP TABLE IF EXISTS `professionista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professionista` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professionista`
--

LOCK TABLES `professionista` WRITE;
/*!40000 ALTER TABLE `professionista` DISABLE KEYS */;
INSERT INTO `professionista` VALUES (1,'Mario','Civile'),(2,'Sergio','Penale'),(3,'Marco','Tributario'),(4,'Antonio','Commerciale');
/*!40000 ALTER TABLE `professionista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professionista_specializzazione`
--

DROP TABLE IF EXISTS `professionista_specializzazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professionista_specializzazione` (
  `professionista_id` int(11) unsigned NOT NULL,
  `specializzazione_id` int(11) unsigned NOT NULL,
  UNIQUE KEY `professionista_id` (`professionista_id`,`specializzazione_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professionista_specializzazione`
--

LOCK TABLES `professionista_specializzazione` WRITE;
/*!40000 ALTER TABLE `professionista_specializzazione` DISABLE KEYS */;
INSERT INTO `professionista_specializzazione` VALUES (1,1),(2,2),(3,3),(4,4);
/*!40000 ALTER TABLE `professionista_specializzazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specializzazione`
--

DROP TABLE IF EXISTS `specializzazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specializzazione` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descrizione` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specializzazione`
--

LOCK TABLES `specializzazione` WRITE;
/*!40000 ALTER TABLE `specializzazione` DISABLE KEYS */;
INSERT INTO `specializzazione` VALUES (1,'avvocato civilista'),(2,'avvocato penalista'),(3,'avvocato tributarista'),(4,'commercialista');
/*!40000 ALTER TABLE `specializzazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stato_pratica`
--

DROP TABLE IF EXISTS `stato_pratica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stato_pratica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stato` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stato_pratica`
--

LOCK TABLES `stato_pratica` WRITE;
/*!40000 ALTER TABLE `stato_pratica` DISABLE KEYS */;
INSERT INTO `stato_pratica` VALUES (1,'nuova'),(2,'rigettata'),(3,'in lavorazione'),(4,'da notificare'),(5,'da depositare'),(6,'in scadenza'),(7,'scaduta'),(8,'in dibattimento'),(9,'da fatturare'),(10,'evasa');
/*!40000 ALTER TABLE `stato_pratica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_pratica`
--

DROP TABLE IF EXISTS `tipo_pratica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_pratica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_pratica`
--

LOCK TABLES `tipo_pratica` WRITE;
/*!40000 ALTER TABLE `tipo_pratica` DISABLE KEYS */;
INSERT INTO `tipo_pratica` VALUES (1,'tributario primo grado'),(2,'tributario appello'),(3,'tributario cassazione'),(4,'civile primo grado'),(5,'civile appello'),(6,'civile cassazione'),(7,'penale primo grado'),(8,'penale appello'),(9,'penale cassazione');
/*!40000 ALTER TABLE `tipo_pratica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `role` varchar(45) NOT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'marisa','$2a$10$f3iFK1oIwXjQD6RKHB9Ur.2ZUt/jbo5v.6sYFU4BQQ/I4ud6Fo2.G','ROLE_USER',1),(2,'admin','$2a$10$XM50LLW.rmHL8qCbY00WYOjTZzTU8xL.TS08qY7UpaMzzi.vmMOZS','ROLE_ADMIN',1);
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

-- Dump completed on 2022-08-12 10:50:00
