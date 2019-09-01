CREATE DATABASE  IF NOT EXISTS `hb_single_table_mapping_tracker`;
USE `hb_single_table_mapping_tracker`;

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `acc_type` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner` varchar(45) DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  `interest_rate` decimal(3,2) DEFAULT NULL,
  `credit_limit` decimal(19,2) DEFAULT NULL,
  `over_draft_fee` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;