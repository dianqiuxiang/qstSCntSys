CREATE DATABASE  IF NOT EXISTS `infomanagesys` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `infomanagesys`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: infomanagesys
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cost`
--

DROP TABLE IF EXISTS `cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cost` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）',
  `expenseItemID` int(11) DEFAULT NULL COMMENT '产品名称（外键）',
  `expenseAmount` decimal(10,0) DEFAULT NULL COMMENT '费用金额',
  `expenseDate` date DEFAULT NULL COMMENT '费用时间',
  `salesDepartmentID` int(11) DEFAULT NULL COMMENT '部门编号（外键）',
  `isDelete` int(11) DEFAULT NULL COMMENT '是否删除：0 没删除，1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='费用表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cost`
--

LOCK TABLES `cost` WRITE;
/*!40000 ALTER TABLE `cost` DISABLE KEYS */;
/*!40000 ALTER TABLE `cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customerinfo`
--

DROP TABLE IF EXISTS `customerinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customerinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）',
  `customerPhone` varchar(255) DEFAULT NULL COMMENT '顾客联系电话',
  `customerName` varchar(255) DEFAULT NULL COMMENT '顾客姓名',
  `customerAddress` varchar(255) DEFAULT NULL COMMENT '顾客住址',
  `salesDepartmentID` int(11) DEFAULT NULL COMMENT '部门编号（外键）',
  `isDelete` int(11) DEFAULT NULL COMMENT '是否删除：0 没删除，1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='顾客信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerinfo`
--

LOCK TABLES `customerinfo` WRITE;
/*!40000 ALTER TABLE `customerinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `customerinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeeinfo`
--

DROP TABLE IF EXISTS `employeeinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employeeinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employeeName` varchar(255) DEFAULT NULL COMMENT '员工姓名',
  `sex` char(4) CHARACTER SET utf8 DEFAULT NULL COMMENT '性别',
  `employeePhone` varchar(255) DEFAULT NULL COMMENT '员工联系电话',
  `employeeAddress` varchar(255) DEFAULT NULL COMMENT '员工住址',
  `employeeEmail` varchar(255) DEFAULT NULL COMMENT '员工邮箱',
  `salesDepartmentID` int(11) DEFAULT NULL COMMENT '部门编号（外键）',
  `isDelete` int(11) DEFAULT NULL COMMENT '是否删除：0 没删除，1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='员工信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employeeinfo`
--

LOCK TABLES `employeeinfo` WRITE;
/*!40000 ALTER TABLE `employeeinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `employeeinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `everymonthotherinfo`
--

DROP TABLE IF EXISTS `everymonthotherinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `everymonthotherinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `manageCost` decimal(10,2) DEFAULT NULL COMMENT '管理费用',
  `earlyNumber` int(11) DEFAULT NULL COMMENT '期初人数',
  `finalNumber` int(11) DEFAULT NULL COMMENT '期末人数',
  `singleExcessAmount` decimal(10,2) DEFAULT NULL COMMENT '单笔累计赠送超标金额',
  `overallExcessAmount` decimal(10,2) DEFAULT NULL COMMENT '整体考量赠送超标金额',
  `salesDepartmentID` int(11) DEFAULT NULL COMMENT '部门编号（外键）',
  `infoDate` date DEFAULT NULL COMMENT '月报其他信息时间',
  `isDelete` int(11) DEFAULT NULL COMMENT '是否删除：0 没删除，1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='月报其他信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `everymonthotherinfo`
--

LOCK TABLES `everymonthotherinfo` WRITE;
/*!40000 ALTER TABLE `everymonthotherinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `everymonthotherinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expenseitem`
--

DROP TABLE IF EXISTS `expenseitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expenseitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）',
  `expenseItem` varchar(255) DEFAULT NULL COMMENT '费用项目',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='费用项目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expenseitem`
--

LOCK TABLES `expenseitem` WRITE;
/*!40000 ALTER TABLE `expenseitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `expenseitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）',
  `menuName` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标路径',
  `menuUrl` varchar(255) DEFAULT NULL COMMENT '菜单URL',
  `parentID` int(11) DEFAULT NULL COMMENT '父亲节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderinfo`
--

DROP TABLE IF EXISTS `orderinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderinfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）',
  `orderCode` varchar(10) DEFAULT NULL COMMENT '订单编号',
  `customerType` varchar(255) DEFAULT NULL COMMENT '会员类型',
  `customerPhone` varchar(255) DEFAULT NULL COMMENT '顾客联系电话',
  `customerName` varchar(255) DEFAULT NULL COMMENT '顾客姓名',
  `customerAddress` varchar(255) DEFAULT NULL COMMENT '顾客住址',
  `orderDate` date DEFAULT NULL COMMENT '订单时间',
  `orderAmount` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `healthMember` varchar(255) DEFAULT NULL COMMENT '健康代表',
  `customerSign` varchar(255) DEFAULT NULL COMMENT '顾客签收',
  `salesDepartmentID` int(11) DEFAULT NULL COMMENT '部门编号（外键）',
  `isDelete` int(11) DEFAULT NULL COMMENT '是否删除：0 没删除，1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderinfo`
--

LOCK TABLES `orderinfo` WRITE;
/*!40000 ALTER TABLE `orderinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderproductinfo`
--

DROP TABLE IF EXISTS `orderproductinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderproductinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）',
  `orderID` int(11) DEFAULT NULL COMMENT '订单ID（外键）',
  `productID` int(11) DEFAULT NULL COMMENT '产品ID（外键）',
  `amount` decimal(10,0) DEFAULT NULL COMMENT '购买数量',
  `price` decimal(10,0) DEFAULT NULL COMMENT '购买单价',
  `totalMoney` decimal(10,0) DEFAULT NULL COMMENT '购买总价',
  `isDelete` int(11) DEFAULT NULL COMMENT '是否删除：0 没删除，1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='订单产品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderproductinfo`
--

LOCK TABLES `orderproductinfo` WRITE;
/*!40000 ALTER TABLE `orderproductinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderproductinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parameterinfo`
--

DROP TABLE IF EXISTS `parameterinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parameterinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ParameterName` varchar(255) DEFAULT NULL COMMENT '参数名称',
  `value` varchar(255) DEFAULT NULL COMMENT '参数值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='参数表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parameterinfo`
--

LOCK TABLES `parameterinfo` WRITE;
/*!40000 ALTER TABLE `parameterinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `parameterinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productinfo`
--

DROP TABLE IF EXISTS `productinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）',
  `productName` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `productUnit` varchar(255) DEFAULT NULL COMMENT '产品单位',
  `productPrice` decimal(10,2) DEFAULT NULL COMMENT '产品单价',
  `isDelete` int(11) DEFAULT NULL COMMENT '是否删除：0 没删除，1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='产品信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productinfo`
--

LOCK TABLES `productinfo` WRITE;
/*!40000 ALTER TABLE `productinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `productinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receiptinfo`
--

DROP TABLE IF EXISTS `receiptinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receiptinfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orderID` int(10) DEFAULT NULL COMMENT '订单ID',
  `receiptDate` date DEFAULT NULL COMMENT '收款日期',
  `receiptAmount` decimal(10,2) DEFAULT NULL COMMENT '收款金额',
  `receiptMenber` varchar(10) DEFAULT NULL COMMENT '收款人',
  `salesDepartmentID` int(10) DEFAULT NULL COMMENT '部门编号（外键）',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `isDelete` int(1) DEFAULT NULL COMMENT '是否删除：0 没删除，1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='收款信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receiptinfo`
--

LOCK TABLES `receiptinfo` WRITE;
/*!40000 ALTER TABLE `receiptinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `receiptinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesdepartmentinfo`
--

DROP TABLE IF EXISTS `salesdepartmentinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salesdepartmentinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `salesDepartmentName` varchar(255) DEFAULT NULL COMMENT '销售部名称',
  `salesDepartmentAddress` varchar(255) DEFAULT NULL COMMENT '销售部地址',
  `salesDepartmentPhone` varchar(255) DEFAULT NULL COMMENT '销售部联系电话',
  `parentID` int(11) DEFAULT NULL COMMENT '父亲节点',
  `isDelete` int(11) DEFAULT NULL COMMENT '是否删除：0 没删除，1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='销售部信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesdepartmentinfo`
--

LOCK TABLES `salesdepartmentinfo` WRITE;
/*!40000 ALTER TABLE `salesdepartmentinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `salesdepartmentinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）',
  `userName` varchar(255) DEFAULT NULL COMMENT '员工姓名',
  `pwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `userPhone` varchar(255) DEFAULT NULL COMMENT '用户联系电话',
  `userEmail` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `salesDepartmentID` int(11) DEFAULT NULL COMMENT '部门编号（外键）',
  `isDelete` int(11) DEFAULT NULL COMMENT '是否删除：0 没删除，1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (1,'123','123','123','123',123,0);
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-04 10:41:45
