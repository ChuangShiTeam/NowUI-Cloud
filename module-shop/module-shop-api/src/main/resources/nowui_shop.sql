/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost
 Source Database       : nowui_shop

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : utf-8

 Date: 12/24/2017 12:00:41 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `product_info`
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `productId` varchar(32) NOT NULL,
  `appId` varchar(32) NOT NULL,
  `productName` varchar(100) NOT NULL,
  `systemCreateUserId` varchar(32) NOT NULL,
  `systemCreateTime` datetime NOT NULL,
  `systemUpdateUserId` varchar(32) NOT NULL,
  `systemUpdateTime` datetime NOT NULL,
  `systemVersion` int(11) NOT NULL,
  `systemStatus` tinyint(1) NOT NULL,
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `product_info`
-- ----------------------------
BEGIN;
INSERT INTO `product_info` VALUES ('0c38779111d64ee4a12150f23e4fde47', 'df2078d6c9eb46babb0df957127273ab', '000', '123456789', '2017-12-19 18:27:01', '123456789', '2017-12-19 18:27:09', '1', '0'), ('147c3320bc174ecd8e97c22e4aa9c3c2', 'df2078d6c9eb46babb0df957127273ab', '2', '123456789', '2017-12-19 20:45:36', '123456789', '2017-12-19 20:47:25', '1', '0'), ('1b8b6273ef3c4c5d9e05dd8679362a8c', 'df2078d6c9eb46babb0df957127273ab', '2', '123456789', '2017-12-19 20:45:39', '123456789', '2017-12-19 20:47:36', '1', '0'), ('1eae776ffb10479e82f7d2c17ab6bd95', 'df2078d6c9eb46babb0df957127273ab', '000', '123456789', '2017-12-19 10:48:45', '123456789', '2017-12-19 18:25:33', '2', '0'), ('1eb668dd17d74524930fe600a5ae1544', 'df2078d6c9eb46babb0df957127273ab', '11', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-20 00:54:17', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-20 00:55:36', '1', '0'), ('27eac0913b1449848578d2abf585a871', 'df2078d6c9eb46babb0df957127273ab', '22', '123456789', '2017-12-19 20:45:49', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-20 00:49:29', '2', '0'), ('2dd277342d534dd0879af388fea59769', 'df2078d6c9eb46babb0df957127273ab', 'xxx', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-23 23:21:43', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-23 23:21:43', '0', '1'), ('393c2b3e6431462384b1de59cb21221b', 'df2078d6c9eb46babb0df957127273ab', '33', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-24 01:02:05', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-24 01:02:05', '0', '1'), ('3dc6ef31de3a4813b4d380a2d2897d8e', 'df2078d6c9eb46babb0df957127273ab', '2', '123456789', '2017-12-19 20:45:37', '123456789', '2017-12-19 20:47:33', '1', '0'), ('3fe751fad316426ea496ae11b15aa460', 'df2078d6c9eb46babb0df957127273ab', 'bbb', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-24 00:48:12', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-24 00:48:12', '0', '1'), ('4398eb2840c74a20ad0739c05faf1adc', 'df2078d6c9eb46babb0df957127273ab', '1112', '123456789', '2017-12-19 18:26:58', '123456789', '2017-12-19 20:44:17', '4', '0'), ('4e656326023941789954d4b950ca563a', 'df2078d6c9eb46babb0df957127273ab', '00', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-22 11:51:08', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-22 11:51:32', '1', '0'), ('4e8a1b65b6b943cfaedf0afe1022c327', 'df2078d6c9eb46babb0df957127273ab', 'vvv', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-23 23:47:52', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-23 23:47:52', '0', '1'), ('51011bc0a29449a8bbe7b3946508baa3', 'df2078d6c9eb46babb0df957127273ab', '000', '123456789', '2017-12-19 18:26:50', '123456789', '2017-12-19 18:27:15', '1', '0'), ('57d5137d2fd84bc58f3a47070b5693bc', 'df2078d6c9eb46babb0df957127273ab', 'nnn', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-24 00:50:53', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-24 00:50:53', '0', '1'), ('64430fb98a8f48a48bcc29284879c1f3', 'df2078d6c9eb46babb0df957127273ab', '111', '123456789', '2017-12-19 10:43:21', '123456789', '2017-12-19 10:50:08', '3', '0'), ('6f028dfe323346978e01b979be70bfa1', 'df2078d6c9eb46babb0df957127273ab', 'asd123', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-22 13:40:24', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-23 02:26:55', '2', '1'), ('79e2896fb8104e2c8ad834a5d4b7f466', 'df2078d6c9eb46babb0df957127273ab', '11', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-24 00:59:19', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-24 00:59:19', '0', '1'), ('9fac4747b7b947e8b7178c0c43c77a6c', 'df2078d6c9eb46babb0df957127273ab', 'mmm', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-24 00:53:41', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-24 00:53:41', '0', '1'), ('9fbd4922d16f4663a622a8f5f30ed1dd', 'df2078d6c9eb46babb0df957127273ab', '1', '123456789', '2017-12-19 10:59:02', '123456789', '2017-12-19 18:25:30', '5', '0'), ('b906ac55d2be44faa639e391f61176c1', 'df2078d6c9eb46babb0df957127273ab', 'ccc', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-23 23:23:46', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-23 23:23:46', '0', '1'), ('c01e2a21271e433dac70c561d06cfe9c', 'df2078d6c9eb46babb0df957127273ab', '112233', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-20 00:55:30', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-22 11:49:38', '11', '0'), ('c1d75b1527c64b22a831839055f3062f', 'df2078d6c9eb46babb0df957127273ab', 'ddd', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-23 23:42:35', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-23 23:42:35', '0', '1'), ('cf6bf6e5866f4064bff0dde0d8ba454f', 'df2078d6c9eb46babb0df957127273ab', '001122333', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-22 11:51:23', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-22 13:32:51', '3', '1'), ('f25167e9ef704ece877a290a466dff9d', 'df2078d6c9eb46babb0df957127273ab', 'zzz', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-23 11:38:00', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-23 11:38:00', '0', '1'), ('f40fa552f35b42e98db2ef65df735bc4', 'df2078d6c9eb46babb0df957127273ab', '2', '123456789', '2017-12-19 20:45:42', '123456789', '2017-12-19 20:47:39', '1', '0'), ('f52aa02fb6d64c04b1fff15cbaa130b1', 'df2078d6c9eb46babb0df957127273ab', '2', '123456789', '2017-12-19 20:45:37', '123456789', '2017-12-19 20:47:30', '1', '0'), ('f64b122e65f7429998382891f892bdb4', 'df2078d6c9eb46babb0df957127273ab', '22', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-24 01:00:02', 'ffb11c2d4a3043ec8eb28c8cca9d1fc8', '2017-12-24 01:00:02', '0', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
