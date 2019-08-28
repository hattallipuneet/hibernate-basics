CREATE DATABASE  IF NOT EXISTS `hb_stock_tracker`;
USE `hb_stock_tracker`;
--
-- Table structure for tables `stockerholder` and `stocks`
--

DROP TABLE IF EXISTS `stockholder`;

CREATE TABLE `stockholder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `stocks`;

CREATE TABLE `stocks` (
  `stockerholder_id` int(11) NOT NULL,
  `company_code` varchar(45) DEFAULT NULL,
  `company_name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;