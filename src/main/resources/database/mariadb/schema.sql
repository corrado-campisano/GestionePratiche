
DROP TABLE IF EXISTS `utenti`;
CREATE TABLE `utenti` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(64) NOT NULL,
  `role` varchar(45) NOT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ruoli`;
CREATE TABLE `ruoli` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ruolo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `specializzazione`;
CREATE TABLE `specializzazione` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descrizione` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `utente_specializzazione`;
CREATE TABLE `utente_specializzazione` (
  utente_id INT(11) UNSIGNED NOT NULL,
  specializzazione_id INT(11) UNSIGNED NOT NULL,
  UNIQUE (utente_id,specializzazione_id)
) engine=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ragione_sociale` varchar(255) NOT NULL,
  `codice_fiscale` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `stato_pratica`;
CREATE TABLE `stato_pratica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stato` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tipo_pratica`;
CREATE TABLE `tipo_pratica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `professionista`;
CREATE TABLE `professionista` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `professionista_specializzazione`;
CREATE TABLE `professionista_specializzazione` (
  professionista_id INT(11) UNSIGNED NOT NULL,
  specializzazione_id INT(11) UNSIGNED NOT NULL,
  UNIQUE (professionista_id,specializzazione_id)
) engine=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `pratica`;
CREATE TABLE `pratica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identificativo` varchar(255) NOT NULL,
  `descrizione` varchar(255) NOT NULL,
  `cliente_id` INT(11) UNSIGNED NOT NULL,
  `tipo_id` INT(11) UNSIGNED NOT NULL,
  `stato_id` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `pratica_utente`;
CREATE TABLE `pratica_utente` (
  pratica_id INT(11) UNSIGNED NOT NULL,
  utente_id INT(11) UNSIGNED NOT NULL,
  UNIQUE (pratica_id,utente_id)
) engine=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `pratica_professionista`;
CREATE TABLE `pratica_professionista` (
  pratica_id INT(11) UNSIGNED NOT NULL,
  professionista_id INT(11) UNSIGNED NOT NULL,
  UNIQUE (pratica_id,professionista_id)
) engine=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `avanzamento`;
CREATE TABLE `avanzamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pratica_id`  INT(11) UNSIGNED NOT NULL,
  `data` DATETIME NOT NULL,
  `scadenza` DATETIME NOT NULL,
  `descrizione` varchar(255) NOT NULL,
  `stato_precedente_id` INT(11) UNSIGNED NOT NULL,
  `stato_attuale_id` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
) engine=InnoDB DEFAULT CHARSET=utf8;
