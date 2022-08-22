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
  `data` datetime NOT NULL,
  `scadenza` datetime NOT NULL,
  `descrizione` varchar(255) NOT NULL,
  `stato_precedente_id` int(11) unsigned NOT NULL,
  `stato_attuale_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avanzamento`
--

LOCK TABLES `avanzamento` WRITE;
/*!40000 ALTER TABLE `avanzamento` DISABLE KEYS */;
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
INSERT INTO `pratica` VALUES (1,'2022-01','cartella per TARI',1,1,1),(2,'2022-02','cartella per IMU',2,2,1);
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
/*!40000 ALTER TABLE `pratica_professionista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pratica_utente`
--

DROP TABLE IF EXISTS `pratica_utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pratica_utente` (
  `pratica_id` int(11) unsigned NOT NULL,
  `utente_id` int(11) unsigned NOT NULL,
  UNIQUE KEY `pratica_id` (`pratica_id`,`utente_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pratica_utente`
--

LOCK TABLES `pratica_utente` WRITE;
/*!40000 ALTER TABLE `pratica_utente` DISABLE KEYS */;
/*!40000 ALTER TABLE `pratica_utente` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professionista`
--

LOCK TABLES `professionista` WRITE;
/*!40000 ALTER TABLE `professionista` DISABLE KEYS */;
INSERT INTO `professionista` VALUES (1,'Mario','Civile'),(2,'Sergio','Penale'),(3,'Marco','Tributario'),(4,'Antonio','Commerciale'),(5,'Mario','Civile'),(6,'Sergio','Penale'),(7,'Marco','Tributario'),(8,'Antonio','Commerciale');
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
-- Table structure for table `ruoli`
--

DROP TABLE IF EXISTS `ruoli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ruoli` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ruolo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruoli`
--

LOCK TABLES `ruoli` WRITE;
/*!40000 ALTER TABLE `ruoli` DISABLE KEYS */;
INSERT INTO `ruoli` VALUES (1,'ADMIN'),(2,'USER'),(3,'MANAGER'),(4,'PROFESSIONISTA');
/*!40000 ALTER TABLE `ruoli` ENABLE KEYS */;
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
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(64) NOT NULL,
  `role` varchar(45) NOT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES 
(1,'admin','$2a$10$XM50LLW.rmHL8qCbY00WYOjTZzTU8xL.TS08qY7UpaMzzi.vmMOZS','Corrado','Campisano','ROLE_ADMIN',1),
(2,'marisa','$2a$10$f3iFK1oIwXjQD6RKHB9Ur.2ZUt/jbo5v.6sYFU4BQQ/I4ud6Fo2.G','Marisa','Checchia','ROLE_MANAGER',1),
(3,'admin','$2a$10$XM50LLW.rmHL8qCbY00WYOjTZzTU8xL.TS08qY7UpaMzzi.vmMOZS','Corrado','Campisano','ROLE_ADMIN',1),
(4,'marisa','$2a$10$f3iFK1oIwXjQD6RKHB9Ur.2ZUt/jbo5v.6sYFU4BQQ/I4ud6Fo2.G','Marisa','Checchia','ROLE_MANAGER',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_specializzazione`
--

DROP TABLE IF EXISTS `users_specializzazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_specializzazione` (
  `user_id` int(11) unsigned NOT NULL,
  `specializzazione_id` int(11) unsigned NOT NULL,
  UNIQUE KEY `user_id` (`user_id`,`specializzazione_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_specializzazione`
--

LOCK TABLES `users_specializzazione` WRITE;
/*!40000 ALTER TABLE `users_specializzazione` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_specializzazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente_specializzazione`
--

DROP TABLE IF EXISTS `utente_specializzazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utente_specializzazione` (
  `utente_id` int(11) unsigned NOT NULL,
  `specializzazione_id` int(11) unsigned NOT NULL,
  UNIQUE KEY `utente_id` (`utente_id`,`specializzazione_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente_specializzazione`
--

LOCK TABLES `utente_specializzazione` WRITE;
/*!40000 ALTER TABLE `utente_specializzazione` DISABLE KEYS */;
INSERT INTO `utente_specializzazione` VALUES (4,1),(5,2),(6,3),(7,4);
/*!40000 ALTER TABLE `utente_specializzazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utenti`
--

DROP TABLE IF EXISTS `utenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utenti` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(64) NOT NULL,
  `role` varchar(45) NOT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utenti`
--

LOCK TABLES `utenti` WRITE;
/*!40000 ALTER TABLE `utenti` DISABLE KEYS */;
INSERT INTO `utenti` VALUES 
(1,'admin','$2a$10$XM50LLW.rmHL8qCbY00WYOjTZzTU8xL.TS08qY7UpaMzzi.vmMOZS','Corrado','Campisano','ADMIN',1),
(2,'marisa','$2a$10$f3iFK1oIwXjQD6RKHB9Ur.2ZUt/jbo5v.6sYFU4BQQ/I4ud6Fo2.G','Marisa','Checchia','MANAGER',1),
(3,'user','$2a$10$QPPbn6e6sNAsyi42hR2HieI78Hu2RjdK18XA/8snrKJOyh8Am/Y7a','sola','lettura','USER',1),
(4,'marioc','$2a$10$HDo6qWkTNGcTTkoDJUO4LO5BUgw9PviZTxbAJ2VcZmGIhsISPL5/m','Mario','Civile','PROFESSIONISTA',1),
(5,'sergiop','$2a$10$U/1RFnDXUK4fEWy0.HrlIuPqWbHs348367FRpNPv/32TM.pVyTM1i','Sergio','Penale','PROFESSIONISTA',1),
(6,'pierot','$2a$10$QFNZiAouu58gBb9dHvKY4OZU7i6EnWMwRa3qM7s/EkQC4LFTy04om','Piero','Tributario','PROFESSIONISTA',1),
(7,'luigic','$2a$10$0HUo3YzPyR6jx594FsggVONPPMKVcJSeZCOeMZ99j20v79UdzF42e','Luigi','Commerciale','PROFESSIONISTA',1);
/*!40000 ALTER TABLE `utenti` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-22 15:51:03
