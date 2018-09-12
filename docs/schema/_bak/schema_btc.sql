DROP TABLE IF EXISTS `tcoin_btc_xaddress`;
CREATE TABLE `tcoin_btc_xaddress` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `address` varchar(255) DEFAULT NULL COMMENT '比特币地址',
  `privatekey` varchar(255) DEFAULT NULL COMMENT '私钥',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tcoin_btc_maddress`;
CREATE TABLE `tcoin_btc_maddress` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `address` varchar(255) DEFAULT NULL COMMENT '比特币地址',
  `privatekey` varchar(255) DEFAULT NULL COMMENT '私钥',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `status` varchar(4) DEFAULT NULL COMMENT '状态(0正常 1广播使用中 2=已弃用)',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='btc散取地址';

DROP TABLE IF EXISTS `tcoin_btc_waddress`;
CREATE TABLE `tcoin_btc_waddress` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `address` varchar(255) DEFAULT NULL COMMENT '比特币地址',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `status` varchar(4) DEFAULT NULL COMMENT '状态(0正常 1广播使用中 2=已弃用)',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='btc归集地址';

DROP TABLE IF EXISTS `tcoin_btc_transaction`;
CREATE TABLE `tcoin_btc_transaction` (
  `txid` varchar(255) NOT NULL COMMENT '交易ID',
  `version` varchar(32) DEFAULT NULL COMMENT '版本',
  `blockheight` bigint(32) DEFAULT NULL COMMENT '区块高度',
  `coinbase` varchar(32) DEFAULT NULL COMMENT '是否是挖矿奖励',
  `blockhash` varchar(255) DEFAULT NULL COMMENT '区块hash',
  `blocktime` datetime DEFAULT NULL COMMENT '区块生成时间',
  `valuein` decimal(64,0) DEFAULT NULL COMMENT '输入总额',
  `valueout` decimal(64,0) DEFAULT NULL COMMENT '输出总额',
  `fees` decimal(64,0) DEFAULT NULL COMMENT '矿工费',
  `vin` text COMMENT '输入列表',
  `vout` text COMMENT '输出列表',
  `ref_no` varchar(32) DEFAULT NULL COMMENT '关联订单号',
  PRIMARY KEY (`txid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tcoin_btc_utxo`;
CREATE TABLE `tcoin_btc_utxo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `txid` char(64) DEFAULT NULL,
  `vout` int(11) DEFAULT NULL,
  `count` decimal(64,0) DEFAULT NULL,
  `script_pub_key` text,
  `address` varchar(40) DEFAULT NULL,
  `sync_time` datetime DEFAULT NULL COMMENT '同步时间',
  `block_height` int(11) DEFAULT NULL,
  `status` varchar(4) DEFAULT NULL COMMENT '0-未使用，1-广播中，2-已使用',
  `address_type` varchar(4) DEFAULT NULL COMMENT '地址类型',
  `ref_type` varchar(4) DEFAULT NULL COMMENT '参考类型(1 取现 2 归集)',
  `ref_no` varchar(32) DEFAULT NULL COMMENT '参考编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `txid_vout_unique_key` (`txid`,`vout`),
  KEY `address` (`address`),
  KEY `status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
