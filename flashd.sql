/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : flashd

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 02/05/2023 17:51:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT 'UpperDepID1，FirstDep0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Department',
  `order_num` int(11) NULL DEFAULT NULL COMMENT 'sort',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT 'DeleteOrNot  -1：Delete  0：Normal',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'DepManage' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', 'UK', '0', '0');
INSERT INTO `sys_dept` VALUES ('14', '1', 'Leeds', '0', '0');
INSERT INTO `sys_dept` VALUES ('15', '14', 'LS1-LS5', '0', '0');
INSERT INTO `sys_dept` VALUES ('16', '14', 'LS6-LS10', '0', '0');
INSERT INTO `sys_dept` VALUES ('17', '14', 'LS11-LS15', '0', '0');
INSERT INTO `sys_dept` VALUES ('18', '14', 'LS16-LS20', '0', '0');
INSERT INTO `sys_dept` VALUES ('19', '14', 'LS20-LS27', '0', '0');
INSERT INTO `sys_dept` VALUES ('20', '15', 'Trinity', '0', '0');
INSERT INTO `sys_dept` VALUES ('21', '15', 'Morrisons', '0', '0');
INSERT INTO `sys_dept` VALUES ('22', '15', 'Railway Station', '0', '0');
INSERT INTO `sys_dept` VALUES ('23', '15', 'The Grand Theatre & Opera House Leeds', '0', '0');
INSERT INTO `sys_dept` VALUES ('24', '15', 'John Lewis & Partners', '0', '0');
INSERT INTO `sys_dept` VALUES ('25', '15', 'Victoria Leeds', '0', '0');
INSERT INTO `sys_dept` VALUES ('26', '15', 'Leeds City Museum', '0', '0');
INSERT INTO `sys_dept` VALUES ('27', '15', 'Park Square', '0', '0');
INSERT INTO `sys_dept` VALUES ('28', '15', 'PMT Leeds', '0', '0');
INSERT INTO `sys_dept` VALUES ('29', '15', 'Leeds General Infirmary', '0', '0');
INSERT INTO `sys_dept` VALUES ('30', '15', 'The Edge', '0', '0');
INSERT INTO `sys_dept` VALUES ('31', '15', 'ALDI', '0', '0');
INSERT INTO `sys_dept` VALUES ('32', '15', 'Burley Green', '0', '0');
INSERT INTO `sys_dept` VALUES ('33', '16', 'Extentia Stand', '0', '0');
INSERT INTO `sys_dept` VALUES ('34', '16', 'Phantom The Lounge', '0', '0');
INSERT INTO `sys_dept` VALUES ('35', '16', 'Cross Stamford St', '0', '0');
INSERT INTO `sys_dept` VALUES ('36', '16', 'Ramada Leeds East M1', '0', '0');
INSERT INTO `sys_dept` VALUES ('37', '16', 'LEEDS AIRSOFT LIMITED', '0', '0');
INSERT INTO `sys_dept` VALUES ('38', '17', 'Leeds City Office Park', '0', '0');
INSERT INTO `sys_dept` VALUES ('39', '17', 'Lawns Park Primary School', '0', '0');
INSERT INTO `sys_dept` VALUES ('40', '17', 'Henconner Pharmacy', '0', '0');
INSERT INTO `sys_dept` VALUES ('41', '17', 'Leeds Gymnastics Club', '0', '0');
INSERT INTO `sys_dept` VALUES ('42', '17', 'The Springs', '0', '0');
INSERT INTO `sys_dept` VALUES ('43', '18', 'Golden Acre Park', '0', '0');
INSERT INTO `sys_dept` VALUES ('44', '18', 'Allerton High School', '0', '0');
INSERT INTO `sys_dept` VALUES ('45', '18', 'Leeds Bradford Airport', '0', '0');
INSERT INTO `sys_dept` VALUES ('46', '18', 'Westside Retail Park', '0', '0');
INSERT INTO `sys_dept` VALUES ('47', '19', 'Chevin Country Park', '0', '0');
INSERT INTO `sys_dept` VALUES ('48', '19', 'Ingmanthorpe Park', '0', '0');
INSERT INTO `sys_dept` VALUES ('49', '19', 'Leeds United Training Ground', '0', '0');
INSERT INTO `sys_dept` VALUES ('50', '19', 'Sapphire Vehicle Services (VMU)', '0', '0');
INSERT INTO `sys_dept` VALUES ('51', '19', 'Available Car Leeds', '0', '0');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT 'username',
  `operation` varchar(50) DEFAULT NULL COMMENT 'Useroperation',
  `method` varchar(200) DEFAULT NULL COMMENT 'RequestMethod',
  `params` varchar(5000) DEFAULT NULL COMMENT 'RequestPara',
  `time` bigint(20) NOT NULL COMMENT 'ExcutionTime(ms)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IPaddress',
  `create_date` datetime DEFAULT NULL COMMENT 'CreateTime',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Syslog';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT 'Parent menu ID, level 1 menu is 0',
  `name` varchar(50) DEFAULT NULL COMMENT 'Menu name',
  `url` varchar(200) DEFAULT NULL COMMENT 'MenuURL',
  `perms` varchar(500) DEFAULT NULL COMMENT 'Authorization(e.g. user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT 'Type   0：Catalogue   1：Menu   2：Button',
  `icon` varchar(50) DEFAULT NULL COMMENT 'Menu symbol',
  `order_num` int(11) DEFAULT NULL COMMENT 'Sort',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Menu management';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, 'System management', NULL, NULL, 0, 'fa fa-cog', 100);
INSERT INTO `sys_menu` VALUES (2, 101, 'user information', 'modules/sys/user.html', NULL, 1, 'fa fa-user', 1);
INSERT INTO `sys_menu` VALUES (3, 1, 'Roles', 'modules/sys/role.html', NULL, 1, 'fa fa-user-secret', 2);
INSERT INTO `sys_menu` VALUES (4, 1, 'Menu management', 'modules/sys/menu.html', NULL, 1, 'fa fa-th-list', 3);
INSERT INTO `sys_menu` VALUES (14, 6, 'log list', NULL, 'sys:schedule:log', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (15, 2, 'view', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (16, 2, 'add', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (17, 2, 'update', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (18, 2, 'delete', NULL, 'sys:user:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (19, 3, 'view', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (20, 3, 'add', NULL, 'sys:role:save,sys:menu:perms', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (21, 3, 'update', NULL, 'sys:role:update,sys:menu:perms', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (22, 3, 'delete', NULL, 'sys:role:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (23, 4, 'view', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (24, 4, 'add', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (25, 4, 'update', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (26, 4, 'delete', NULL, 'sys:menu:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (31, 1, 'Transit Location', 'modules/sys/dept.html', NULL, 1, 'fa fa-file-code-o', 1);
INSERT INTO `sys_menu` VALUES (32, 31, 'view', NULL, 'sys:dept:list,sys:dept:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (33, 31, 'add', NULL, 'sys:dept:save,sys:dept:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (34, 31, 'update', NULL, 'sys:dept:update,sys:dept:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (35, 31, 'delete', NULL, 'sys:dept:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (43, 78, 'uploade', NULL, 'sys:tMaterialFile:importPsot', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (89, 0, 'Order', NULL, NULL, 0, NULL, 0);
INSERT INTO `sys_menu` VALUES (90, 89, 'Order management', 'modules/sys/dingdan.html', NULL, 1, NULL, 0);
INSERT INTO `sys_menu` VALUES (91, 90, 'add', NULL, 'sys:dingdan:save', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (92, 90, 'delete', NULL, 'sys:dingdan:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (93, 90, 'update', NULL, 'sys:dingdan:update', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (94, 90, 'view', NULL, 'sys:dingdan:info,sys:dingdan:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (101, 0, 'USER', NULL, NULL, 0, NULL, 4);
INSERT INTO `sys_menu` VALUES (108, 0, 'Transfer Point', NULL, NULL, 0, NULL, 1);
INSERT INTO `sys_menu` VALUES (109, 108, 'Point management', 'modules/sys/yszyd.html', NULL, 1, NULL, 0);
INSERT INTO `sys_menu` VALUES (110, 109, 'add', NULL, 'sys:yszyd:save', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (111, 109, 'delete', NULL, 'sys:yszyd:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (112, 109, 'update', NULL, 'sys:yszyd:update', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (113, 109, 'view', NULL, 'sys:yszyd:info,sys:yszyd:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (114, 0, 'Item type', NULL, NULL, 0, NULL, 1);
INSERT INTO `sys_menu` VALUES (115, 114, 'Type management', 'modules/sys/type.html', NULL, 1, NULL, 0);
INSERT INTO `sys_menu` VALUES (116, 115, 'add', NULL, 'sys:type:save', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (117, 115, 'delete', NULL, 'sys:type:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (118, 115, 'update', NULL, 'sys:type:update', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (119, 115, 'view', NULL, 'sys:type:info,sys:type:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (120, 90, 'Change logistics status', NULL, 'sys:dingdan:info,sys:dingdan:list,sys:dingdan:bgwlzt', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (198, 0, 'Billing', NULL, NULL, 0, NULL, 1);
INSERT INTO `sys_menu` VALUES (199, 198, 'Billing management', 'modules/sys/zdcx.html', NULL, 1, NULL, 0);
INSERT INTO `sys_menu` VALUES (200, 199, 'view', NULL, 'sys:dingdan:zdcxlist', 2, NULL, 0);

-- ----------------------------
-- Table structure for sys_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order`  (
  `id` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Primary key id',
  `goodstype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'goodstype1：Clothes2：FragileGoods3：Liquid',
  `orderamount` double(20, 2) NULL DEFAULT NULL COMMENT 'OrderAmount',
  `ordertime` datetime(0) NULL DEFAULT NULL COMMENT 'Order time',
  `parintid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `customeraccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'CustomerAccount',
  `endtime` datetime(0) NULL DEFAULT NULL,
  `receivingaddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Receiving Address',
  `totalordernumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Total order number',
  `currentlytransportedtothetransferpoint` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Currently transported to the transfer point',
  `whethertoconfirmreceiptofgoods` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Whether to confirm receipt of goods 1: to be picked up 2: picked up on behalf of the shipment 3: logistics in transit 4: have received items 5: items in transit 6: items returned in 7 items have been returned to the shipper',
  `standby1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `standby2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `totalpaymentamount` double(20, 2) NULL DEFAULT NULL COMMENT 'Total payment amount',
  `numberofitems` int(20) NULL DEFAULT NULL COMMENT 'Number of items',
  `Iteminformation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Item Information',
  `customershippingaddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Customer shipping address',
  `coordinatex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Coordinate X',
  `coordinatey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Coordinate Y',
  `weight` double(20, 2) NULL DEFAULT NULL COMMENT 'Weight',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT 'createtime',
  `createuser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'createuser',
  `updatetime` datetime(0) NULL DEFAULT NULL COMMENT 'updatetime',
  `updateuser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'updateuser',
  `shippersname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Shipper\'s name',
  `senderscontactinformation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Sender\'s contact information',
  `consigneesname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Consignee\'s name',
  `consigneescontactinformation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Consignee\'s contact information',
  `remarks` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Remarks',
  `personinchargeoflogistics` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Person in charge of logistics',
  `sendingcity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Sending city',
  `destinationcity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Destination city',
  `startpointcoordinatesx` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Start point coordinates X',
  `startpointcoordinatesy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Start point coordinates Y',
  `whethertheuserpaid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Whether the user paid 1: Paid',
  `postcode1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Postcode1',
  `postcode2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Postcode2',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order
-- ----------------------------
INSERT INTO `sys_order` VALUES ('35b70c6264804e078cdea4322a28d900', '6c78cfa0f5fa4ce39d13780fb9adeb1f', 3.22, '2023-04-22 10:43:18', NULL, 'admin', NULL, 'Leeds', NULL, NULL, '2', NULL, NULL, NULL, 2, '1111', 'Trinity', NULL, '2.0', NULL, NULL, NULL, '2023-04-22 17:44:32', 'admin', 'cc', '100000009879', 'Test', '10000000087', 'ddddd', 'admin', NULL, NULL, NULL, NULL, NULL, 'LS01', 'Ls03');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'RoleName',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Reference',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT 'DepID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'CreateTime',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Role' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'administrators', 'administrators', 1, '2023-04-20 22:06:24');
INSERT INTO `sys_role` VALUES (2, 'user', 'user', 1, '2023-04-19 22:05:40');
INSERT INTO `sys_role` VALUES (3, 'courier', 'courier', NULL, '2023-04-17 10:13:25');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT 'RoleID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT 'DepID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'RoleAndDepRelation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT 'RoleID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT 'MenuID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 991 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'RoleAndMenuRelation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (928, 2, 1);
INSERT INTO `sys_role_menu` VALUES (929, 2, 2);
INSERT INTO `sys_role_menu` VALUES (930, 2, 15);
INSERT INTO `sys_role_menu` VALUES (931, 2, 77);
INSERT INTO `sys_role_menu` VALUES (932, 2, 78);
INSERT INTO `sys_role_menu` VALUES (933, 2, 82);
INSERT INTO `sys_role_menu` VALUES (934, 2, 89);
INSERT INTO `sys_role_menu` VALUES (935, 2, 90);
INSERT INTO `sys_role_menu` VALUES (936, 2, 94);
INSERT INTO `sys_role_menu` VALUES (937, 2, 95);
INSERT INTO `sys_role_menu` VALUES (938, 2, 96);
INSERT INTO `sys_role_menu` VALUES (939, 2, 100);
INSERT INTO `sys_role_menu` VALUES (940, 1, 1);
INSERT INTO `sys_role_menu` VALUES (941, 1, 2);
INSERT INTO `sys_role_menu` VALUES (942, 1, 15);
INSERT INTO `sys_role_menu` VALUES (943, 1, 16);
INSERT INTO `sys_role_menu` VALUES (944, 1, 17);
INSERT INTO `sys_role_menu` VALUES (945, 1, 18);
INSERT INTO `sys_role_menu` VALUES (946, 1, 77);
INSERT INTO `sys_role_menu` VALUES (947, 1, 78);
INSERT INTO `sys_role_menu` VALUES (948, 1, 43);
INSERT INTO `sys_role_menu` VALUES (949, 1, 79);
INSERT INTO `sys_role_menu` VALUES (950, 1, 80);
INSERT INTO `sys_role_menu` VALUES (951, 1, 81);
INSERT INTO `sys_role_menu` VALUES (952, 1, 82);
INSERT INTO `sys_role_menu` VALUES (953, 1, 89);
INSERT INTO `sys_role_menu` VALUES (954, 1, 90);
INSERT INTO `sys_role_menu` VALUES (955, 1, 91);
INSERT INTO `sys_role_menu` VALUES (956, 1, 92);
INSERT INTO `sys_role_menu` VALUES (957, 1, 93);
INSERT INTO `sys_role_menu` VALUES (958, 1, 94);
INSERT INTO `sys_role_menu` VALUES (959, 1, 95);
INSERT INTO `sys_role_menu` VALUES (960, 1, 96);
INSERT INTO `sys_role_menu` VALUES (961, 1, 97);
INSERT INTO `sys_role_menu` VALUES (962, 1, 98);
INSERT INTO `sys_role_menu` VALUES (963, 1, 99);
INSERT INTO `sys_role_menu` VALUES (964, 1, 100);
INSERT INTO `sys_role_menu` VALUES (978, 3, 89);
INSERT INTO `sys_role_menu` VALUES (979, 3, 90);
INSERT INTO `sys_role_menu` VALUES (980, 3, 91);
INSERT INTO `sys_role_menu` VALUES (981, 3, 92);
INSERT INTO `sys_role_menu` VALUES (982, 3, 93);
INSERT INTO `sys_role_menu` VALUES (983, 3, 94);
INSERT INTO `sys_role_menu` VALUES (984, 3, 120);
INSERT INTO `sys_role_menu` VALUES (985, 3, 108);
INSERT INTO `sys_role_menu` VALUES (986, 3, 109);
INSERT INTO `sys_role_menu` VALUES (987, 3, 110);
INSERT INTO `sys_role_menu` VALUES (988, 3, 111);
INSERT INTO `sys_role_menu` VALUES (989, 3, 112);
INSERT INTO `sys_role_menu` VALUES (990, 3, 113);

-- ----------------------------
-- Table structure for sys_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_type`;
CREATE TABLE `sys_type`  (
  `id` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Primaryid',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Classification',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT 'CreateTime',
  `createuser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'CreateMem',
  `updateuser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UpdateUser',
  `updatetime` datetime(0) NULL DEFAULT NULL COMMENT 'UpdateTime',
  `xh` int(20) NULL DEFAULT NULL COMMENT 'Sort',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'classificaiton' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Records of sys_type
-- ----------------------------
INSERT INTO `sys_type` VALUES ('22bc6839dd9741d7869a6840f1bb0bdd', 'other', '2023-04-16 20:44:41', 'admin', 'admin', '2023-04-17 20:19:30', 5);
INSERT INTO `sys_type` VALUES ('6c78cfa0f5fa4ce39d13780fb9adeb1f', 'Liquid items', '2023-04-16 15:32:19', 'admin', 'admin', '2023-04-17 20:19:08', 2);
INSERT INTO `sys_type` VALUES ('bd6baed92abc4bd2a3a11095c304be33', 'fragile products', '2023-04-16 15:32:26', 'admin', 'admin', '2023-04-17 20:19:21', 3);
INSERT INTO `sys_type` VALUES ('c4a0a8a7daae422a959fd75c743f5a1b', 'Clothing supplies', '2023-04-16 15:32:05', 'admin', 'admin', '2023-04-17 20:18:47', 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'username',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'password',
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'salt',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'email',
  `mobile` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'mobile',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT 'Status  0：Reject   1：Normal',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT 'DepID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'CreateTime',
  `qq` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `idnumb` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ID',
  `wxnub` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'WhatsApp',
  `birthday` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Birthday',
  `political` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Religion',
  `ramark` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Profile',
  `membership` decimal(10, 2) NULL DEFAULT NULL COMMENT 'member',
  `stadynum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '',
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '',
  `college` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '',
  `classinfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '',
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '',
  `xb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '',
  `zzmm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '',
  `jtzz` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '',
  `jtysgj` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '',
  `khh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '',
  `yhkh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Sysuser' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'cdac762d0ba79875489f6a8b430fa8b5dfe0cdd81da38b80f02f33328af7fd4a', 'YzcmCZNvbXocrsz9dm8e', 'yizhentianfei@163.com', '07410009878', 1, 2, '2023-04-21 11:11:11', '123456789', '3699999999999998', '2564655', '2020-06-20 ', 'No', 'Good', 99.50, 'admin', 'admin', 'bing', NULL, NULL, NULL, NULL, '2', 'Sky', 'male', 'No', 'Leeds University', '1', '0926', '288');
INSERT INTO `sys_user` VALUES (32, 'jjrzcxx', 'f97cda858cc8a61435682e1d7909a0b9332e2680440f4316a8cc91193157c219', 'ozJHQKFcobFAmhfl997u', 'yizhentianfei@163.com', '15215868595', 1, 1, '2021-04-21 13:57:11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'buttefuLi', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (33, 'wltest@163.com', 'f3b1259ca77644df0aab6f910dc75daea849e09a4d2307683d984edb15999cbd', 'fERBT96TW4QUFaaLIMx6', 'wltest@163.com', NULL, 1, NULL, '2023-04-21 10:14:52', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'testone', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (34, 'wltest2@163.com', '029031714b1c58c8a411463700acefd611a87130944de0e3645727b26cbaea5d', 'ch2TGwyp8UnS78o7odW8', 'wltest2@163.com', '1998888888', 1, NULL, '2023-04-21 10:15:18', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'testtwo', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT 'UserID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT 'RoleID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'UserAndRoleRelation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (26, 7, 1);
INSERT INTO `sys_user_role` VALUES (27, 7, 2);
INSERT INTO `sys_user_role` VALUES (32, 3, 1);
INSERT INTO `sys_user_role` VALUES (34, 2, 2);
INSERT INTO `sys_user_role` VALUES (37, 11, 1);
INSERT INTO `sys_user_role` VALUES (38, 15, 1);
INSERT INTO `sys_user_role` VALUES (39, 16, NULL);
INSERT INTO `sys_user_role` VALUES (40, 18, NULL);
INSERT INTO `sys_user_role` VALUES (45, 22, 2);
INSERT INTO `sys_user_role` VALUES (52, 1, 1);
INSERT INTO `sys_user_role` VALUES (61, 32, 2);
INSERT INTO `sys_user_role` VALUES (62, 33, 3);
INSERT INTO `sys_user_role` VALUES (63, 34, 3);

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'token',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT 'ExpiryDate',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT 'UpdateTime',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `token`(`token`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'SysUserToken' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES (1, 1, '5dc4c8cfcd21de443c3c190e9d139df4', '2023-04-26 07:20:07', '2023-04-25 19:20:07');
INSERT INTO `sys_user_token` VALUES (2, 13, '919d1af9b7526b4d3033d44e9f9c5a63', '2023-04-22 07:51:11', '2023-04-21 19:51:11');
INSERT INTO `sys_user_token` VALUES (3, 14, 'e2585e92e88dbe6e95d3456a82b46910', '2023-04-22 07:59:57', '2023-04-21 19:59:57');
INSERT INTO `sys_user_token` VALUES (4, 15, 'ae98f1cca3693118e9871144fa518176', '2023-04-22 10:10:31', '2023-04-21 22:10:31');
INSERT INTO `sys_user_token` VALUES (5, 20, '2389d8d70af940fdee4b8c4dc259803b', '2023-04-22 10:18:59', '2023-04-21 22:18:59');
INSERT INTO `sys_user_token` VALUES (6, 21, '1cda7044bb4f17740b79edb13be2f4de', '2023-04-22 10:25:08', '2023-04-21 22:25:08');
INSERT INTO `sys_user_token` VALUES (7, 22, 'c75613c98415a96c7d35f74ee1edf0fe', '2023-04-22 10:26:27', '2023-04-21 22:26:27');
INSERT INTO `sys_user_token` VALUES (8, 23, 'eda990b8276d7ce5dc899a22db464f78', '2023-04-22 10:27:39', '2023-04-21 22:27:39');
INSERT INTO `sys_user_token` VALUES (9, 24, '94c05bbdca5a80b6467729f97f39fb07', '2023-04-23 04:10:36', '2023-04-22 16:10:36');
INSERT INTO `sys_user_token` VALUES (10, 25, 'f5bd0f5285c78be838e952768936de89', '2023-04-23 04:05:28', '2023-04-22 16:05:28');
INSERT INTO `sys_user_token` VALUES (11, 26, 'f0d13718a6ab9df3747a86b40955a594', '2023-04-23 04:06:04', '2023-04-22 16:06:04');
INSERT INTO `sys_user_token` VALUES (12, 27, '5d6e7ff8baca1775dd37d8bad19af31c', '2023-04-23 04:06:39', '2023-04-22 16:06:39');
INSERT INTO `sys_user_token` VALUES (13, 28, '7ccdc30f0766485a10a75e61bda11a77', '2023-04-02 05:50:35', '2023-04-02 17:50:35');
INSERT INTO `sys_user_token` VALUES (14, 32, 'b2b643364f706a0bafdb04ff032b9a6e', '2023-04-03 02:01:43', '2023-04-02 14:01:43');
INSERT INTO `sys_user_token` VALUES (15, 29, '9267ebbce8f42c2d0a4619387f3e89db', '2023-04-03 09:55:22', '2023-04-02 21:55:22');
INSERT INTO `sys_user_token` VALUES (16, 30, '39f2fc2efb30d4ac12e7dececf37b97c', '2023-04-03 09:56:00', '2023-04-02 21:56:00');
INSERT INTO `sys_user_token` VALUES (17, 31, '7a755c3e9c0b5ca95d13273a036d16a1', '2023-04-03 10:12:48', '2023-04-02 22:12:48');

-- ----------------------------
-- Table structure for sys_yszyd
-- ----------------------------
DROP TABLE IF EXISTS `sys_yszyd`;
CREATE TABLE `sys_yszyd`  (
  `id` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'PrimaryId',
  `dingdanid` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Order',
  `wldd` datetime(0) NULL DEFAULT NULL COMMENT 'TransferArrvingTime',
  `updatetime` datetime(0) NULL DEFAULT NULL COMMENT 'UpdateTime',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT 'CreateTime',
  `updateuser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UpdateUser',
  `createuser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'CreateUser',
  `deptid` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'TransferPoint',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Info',
  `zydx` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'TPX',
  `zydy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'tpy',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'TPManagement' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Records of sys_yszyd
-- ----------------------------
INSERT INTO `sys_yszyd` VALUES ('4a60fbf3c0104de79a912b1730f0185a', 'cec43bfa53dd4703968005bbd0f8230b', '2023-04-06 10:08:35', '2023-04-07 20:39:28', '2023-04-07 10:08:37', 'admin', 'admin', '16', 'issue', '120.384859', '36.084945');
INSERT INTO `sys_yszyd` VALUES ('55def8d5f7e244afbce4f5fc1cfd1bc9', 'ef001c8b95d549b3bdbd1c63fca37589', '2023-04-08 15:15:50', '2023-04-08 15:16:07', '2023-04-08 15:16:07', 'admin', 'admin', '1', 'issue', '109.624919', '18.307894');
INSERT INTO `sys_yszyd` VALUES ('86acc13785804c6781e1734732e68ac2', 'cec43bfa53dd4703968005bbd0f8230b', '2023-04-07 20:39:48', '2023-04-07 20:40:35', '2023-04-07 20:40:35', 'admin', 'admin', '19', 'issue', '117.011439', '36.653418');
INSERT INTO `sys_yszyd` VALUES ('9240c16aa8d84ca2960074318a8f2553', '0cd46a47951c4c93b3cf0ed87a5a2c52', '2023-04-09 14:30:49', '2023-04-09 14:32:33', '2023-04-09 14:32:33', 'admin', 'admin', '22', 'issue', '113.265527', '23.15576');
INSERT INTO `sys_yszyd` VALUES ('a1677f947708451b9f30803c9b18f9c1', 'ef001c8b95d549b3bdbd1c63fca37589', '2023-04-08 00:00:00', '2023-04-08 15:14:56', '2023-04-08 15:14:56', 'admin', 'admin', '22', 'issue', '101.877322', '26.582089');
INSERT INTO `sys_yszyd` VALUES ('af93c300f9f14588b66034e3180a9223', 'ef001c8b95d549b3bdbd1c63fca37589', '2023-04-07 15:13:37', '2023-04-08 15:14:07', '2023-04-08 15:14:07', 'admin', 'admin', '19', 'issue', '113.784344', '34.76554');
INSERT INTO `sys_yszyd` VALUES ('e486d3fe272e41f1841d5290c87de785', 'cec43bfa53dd4703968005bbd0f8230b', '2023-04-07 10:18:21', '2023-04-07 20:38:28', '2023-04-07 10:18:23', 'admin', 'admin', '18', 'issue', '121.077425', '37.503897');

-- ----------------------------
-- Table structure for tb_scms_file
-- ----------------------------
DROP TABLE IF EXISTS `tb_scms_file`;
CREATE TABLE `tb_scms_file`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `smodelidstate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'SaveStatus',
  `sfilename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'FileName',
  `sfilelength` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'FilePath',
  `saccessoryname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'SourceFileName',
  `parentid` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Parentid',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
