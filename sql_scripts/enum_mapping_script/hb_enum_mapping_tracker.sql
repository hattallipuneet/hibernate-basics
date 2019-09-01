CREATE DATABASE  IF NOT EXISTS `hb_enum_mapping_tracker`;
USE `hb_enum_mapping_tracker`;

DROP TABLE IF EXISTS `process`;

CREATE TABLE `process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;