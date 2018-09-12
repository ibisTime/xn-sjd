CREATE TABLE `tcoin_btc_xaddress` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `address` varchar(255) DEFAULT NULL COMMENT '比特币地址',
  `privatekey` varchar(255) DEFAULT NULL COMMENT '私钥',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;