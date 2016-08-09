-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:3307
-- Generation Time: 2016-04-21 15:10:23
-- 服务器版本： 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `atm`
--

-- --------------------------------------------------------
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admininfo
-- ----------------------------
DROP TABLE IF EXISTS `admininfo`;
CREATE TABLE `admininfo` (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `adminType` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admininfo
-- ----------------------------
INSERT INTO `admininfo` VALUES ('1', '1', '1', '0');
INSERT INTO `admininfo` VALUES ('2', '2', '2', '1');
INSERT INTO `admininfo` VALUES ('3', 'admin', 'admin', '1');

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `cardId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `password` varchar(6) NOT NULL,
  `frozen` int(1) NOT NULL DEFAULT '0',
  `loss` int(1) NOT NULL DEFAULT '0',
  `cash` decimal(30,3) NOT NULL DEFAULT '0.000',
  `closed` int(1) NOT NULL DEFAULT '0',
  `openDate` datetime NOT NULL,
  `cardNum` varchar(20) NOT NULL,
  PRIMARY KEY (`cardId`),
  KEY `FK_Reference_Card_UserInfo_CustomerID` (`userId`),
  CONSTRAINT `FK_Reference_Card_UserInfo_CustomerID` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES ('1', '1', '123456', '0', '0', '397.000', '0', '2015-10-02 07:04:03', '10000000001');
INSERT INTO `card` VALUES ('2', '2', '123456', '0', '0', '2351.700', '1', '2016-03-31 15:33:54', '10000000002');
INSERT INTO `card` VALUES ('9', '1', '123456', '0', '0', '900.000', '0', '2016-04-13 01:43:04', '10000000003');
INSERT INTO `card` VALUES ('10', '1', '123456', '0', '0', '5000.000', '0', '2016-04-13 01:43:04', '10000000004');
INSERT INTO `card` VALUES ('11', '1', '123456', '0', '0', '10000.000', '0', '2016-04-13 01:45:50', '10000000005');
INSERT INTO `card` VALUES ('12', '2', '123456', '0', '0', '1000.000', '0', '2016-04-13 01:45:50', '10000000006');
INSERT INTO `card` VALUES ('13', '18', '123456', '0', '0', '5000.000', '0', '2016-04-13 15:07:17', '10000000007');
INSERT INTO `card` VALUES ('14', '18', '123456', '0', '0', '1000.000', '0', '2016-04-13 15:11:21', '10000000008');
INSERT INTO `card` VALUES ('15', '19', '123456', '0', '0', '0.000', '0', '2016-04-15 20:40:58', '10000000009');
INSERT INTO `card` VALUES ('16', '24', '123456', '0', '0', '0.000', '0', '2016-04-19 21:05:31', '10000000010');
INSERT INTO `card` VALUES ('17', '24', '123456', '0', '0', '0.000', '0', '2016-04-19 21:05:31', '10000000011');
INSERT INTO `card` VALUES ('18', '24', '123456', '0', '1', '0.000', '1', '2016-04-19 21:05:31', '10000000012');
INSERT INTO `card` VALUES ('19', '25', '123456', '0', '0', '0.000', '0', '2016-04-20 23:39:06', '10000000014');

-- ----------------------------
-- Table structure for cardnum
-- ----------------------------
DROP TABLE IF EXISTS `cardnum`;
CREATE TABLE `cardnum` (
  `maxNum` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cardnum
-- ----------------------------
INSERT INTO `cardnum` VALUES ('10000000014');

-- ----------------------------
-- Table structure for tradeinfo
-- ----------------------------
DROP TABLE IF EXISTS `tradeinfo`;
CREATE TABLE `tradeinfo` (
  `tradeId` int(11) NOT NULL AUTO_INCREMENT,
  `cardId` int(11) NOT NULL,
  `tradeDate` datetime NOT NULL,
  `tradeType` int(11) NOT NULL,
  `tradeMoney` decimal(30,3) DEFAULT NULL,
  `target` int(11) DEFAULT NULL,
  PRIMARY KEY (`tradeId`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tradeinfo
-- ----------------------------
INSERT INTO `tradeinfo` VALUES ('2', '1', '2016-01-09 20:49:23', '2', '1.000', '2');
INSERT INTO `tradeinfo` VALUES ('3', '1', '2016-03-31 20:58:33', '2', '1.000', '2');
INSERT INTO `tradeinfo` VALUES ('4', '1', '2016-03-31 23:20:47', '1', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('5', '1', '2016-03-31 23:46:50', '0', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('6', '1', '2016-03-31 23:56:24', '1', '12.000', '-1');
INSERT INTO `tradeinfo` VALUES ('7', '2', '2016-04-01 00:13:59', '0', '123.000', '-1');
INSERT INTO `tradeinfo` VALUES ('8', '2', '2016-04-01 00:13:59', '2', '1.000', '1');
INSERT INTO `tradeinfo` VALUES ('9', '2', '2016-04-01 00:35:07', '1', '20.000', '-1');
INSERT INTO `tradeinfo` VALUES ('10', '2', '2016-04-01 00:35:07', '1', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('11', '1', '2016-04-01 00:44:59', '1', '1.000', '-1');
INSERT INTO `tradeinfo` VALUES ('12', '1', '2016-04-01 00:55:38', '0', '1.000', '-1');
INSERT INTO `tradeinfo` VALUES ('13', '1', '2016-04-01 00:57:00', '1', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('14', '1', '2016-04-01 19:07:24', '1', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('15', '1', '2016-04-01 19:16:13', '1', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('16', '1', '2016-04-01 19:16:13', '1', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('17', '1', '2016-04-01 19:41:23', '1', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('18', '1', '2016-04-01 19:41:23', '1', '500.000', '-1');
INSERT INTO `tradeinfo` VALUES ('19', '1', '2016-04-01 20:48:46', '1', '1000.000', '-1');
INSERT INTO `tradeinfo` VALUES ('20', '1', '2016-04-01 21:10:08', '1', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('21', '1', '2016-04-01 23:51:49', '0', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('22', '1', '2016-04-01 23:56:20', '2', '222.000', '2');
INSERT INTO `tradeinfo` VALUES ('23', '1', '2016-04-02 00:09:00', '2', '10.000', '2');
INSERT INTO `tradeinfo` VALUES ('24', '1', '2016-04-08 20:50:46', '1', '1000.000', '-1');
INSERT INTO `tradeinfo` VALUES ('25', '1', '2016-04-08 20:54:46', '1', '300.000', '-1');
INSERT INTO `tradeinfo` VALUES ('26', '1', '2016-04-08 20:58:35', '0', '1000.000', '-1');
INSERT INTO `tradeinfo` VALUES ('27', '1', '2016-04-09 00:08:20', '0', '500.000', '-1');
INSERT INTO `tradeinfo` VALUES ('28', '1', '2016-04-09 00:08:57', '0', '500.000', '-1');
INSERT INTO `tradeinfo` VALUES ('29', '1', '2016-04-09 12:21:43', '2', '1.200', '2');
INSERT INTO `tradeinfo` VALUES ('30', '1', '2016-04-09 12:33:04', '0', '1000.000', '-1');
INSERT INTO `tradeinfo` VALUES ('31', '1', '2016-04-13 01:49:25', '1', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('32', '1', '2016-04-13 01:49:25', '1', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('33', '9', '2016-04-13 01:52:12', '0', '1000.000', '-1');
INSERT INTO `tradeinfo` VALUES ('34', '9', '2016-04-13 01:52:12', '1', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('35', '1', '2016-04-13 20:54:00', '1', '300.000', '-1');
INSERT INTO `tradeinfo` VALUES ('36', '1', '2016-04-13 20:54:00', '2', '1.500', '2');
INSERT INTO `tradeinfo` VALUES ('37', '1', '2016-04-13 20:55:43', '2', '1.000', '2');
INSERT INTO `tradeinfo` VALUES ('38', '1', '2016-04-13 20:55:43', '1', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('39', '1', '2016-04-15 00:40:22', '2', '1000.000', '14');
INSERT INTO `tradeinfo` VALUES ('40', '1', '2016-04-15 17:15:26', '1', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('41', '1', '2016-04-15 17:16:17', '0', '500.000', '-1');
INSERT INTO `tradeinfo` VALUES ('42', '1', '2016-04-15 18:14:12', '0', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('43', '1', '2016-04-15 18:14:34', '0', '1000.000', '-1');
INSERT INTO `tradeinfo` VALUES ('44', '1', '2016-04-15 18:14:37', '0', '500.000', '-1');
INSERT INTO `tradeinfo` VALUES ('45', '1', '2016-04-15 18:14:40', '0', '5000.000', '-1');
INSERT INTO `tradeinfo` VALUES ('46', '1', '2016-04-15 18:14:43', '0', '3000.000', '-1');
INSERT INTO `tradeinfo` VALUES ('47', '1', '2016-04-15 18:14:46', '0', '10000.000', '-1');
INSERT INTO `tradeinfo` VALUES ('48', '1', '2016-04-15 18:15:39', '0', '300.000', '-1');
INSERT INTO `tradeinfo` VALUES ('49', '1', '2016-04-15 18:52:23', '2', '5050.000', '10');
INSERT INTO `tradeinfo` VALUES ('50', '1', '2016-04-15 20:00:18', '1', '1000.000', '-1');
INSERT INTO `tradeinfo` VALUES ('51', '1', '2016-04-15 20:05:51', '1', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('52', '1', '2016-04-15 20:37:21', '1', '600.000', '-1');
INSERT INTO `tradeinfo` VALUES ('53', '1', '2016-04-15 20:37:31', '1', '500.000', '-1');
INSERT INTO `tradeinfo` VALUES ('54', '1', '2016-04-19 00:10:09', '0', '100.000', '-1');
INSERT INTO `tradeinfo` VALUES ('55', '1', '2016-04-19 00:46:47', '0', '3200.000', '-1');
INSERT INTO `tradeinfo` VALUES ('56', '1', '2016-04-19 01:34:00', '2', '3.000', '2');
INSERT INTO `tradeinfo` VALUES ('57', '1', '2016-04-20 22:50:12', '1', '500.000', '-1');
INSERT INTO `tradeinfo` VALUES ('58', '1', '2016-04-20 22:51:19', '1', '100.000', '-1');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `personID` varchar(20) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `address` text,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', '诓兵', '1', '15699999999', '女', '广东财经小学');
INSERT INTO `userinfo` VALUES ('2', '小光', '444444444444444444', '18000000000', '男', '广东财经小学');
INSERT INTO `userinfo` VALUES ('15', 'hzy', '123123', null, '男', '123123');
INSERT INTO `userinfo` VALUES ('18', '小扬', '555555555555555555', '13333333333', '男', '123123');
INSERT INTO `userinfo` VALUES ('19', '小扬', '13245', '5453453', '男', '123123');
INSERT INTO `userinfo` VALUES ('20', '小扬', '472173912', '12222222222', '男', '123123');
INSERT INTO `userinfo` VALUES ('21', '你好', '123123', '213', '男', '广东财经小学');
INSERT INTO `userinfo` VALUES ('22', '小扬', '1002', '13333333332', '男', 'huiyadsad');
INSERT INTO `userinfo` VALUES ('23', '小扬', '44011222112', '13333333333', '女', '广东财经小学');
INSERT INTO `userinfo` VALUES ('24', '张三4', '333333333333333333', '18316000123', '男', '广东省');
INSERT INTO `userinfo` VALUES ('25', '23', '21321', '3213123', '男', '嘎嘎嘎嘎嘎');

