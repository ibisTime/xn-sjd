DROP TABLE IF EXISTS `tcoin_ads`;
CREATE TABLE `tcoin_ads` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `trade_type` varchar(32) NOT NULL COMMENT '广告类型（0购买 1出售）',
  `user_id` varchar(32) NOT NULL COMMENT '发布用户编号',
  `trade_currency` varchar(8) NOT NULL COMMENT '交易币种',
  `trade_coin` varchar(8) NOT NULL COMMENT '数字货币类型',
  `only_trust` varchar(2) NOT NULL COMMENT '是否只有信任的人可以交易',
  `premium_rate` decimal(6,4) NOT NULL COMMENT '溢价率',
  `total_count` decimal(64,0) NOT NULL COMMENT '广告的交易总额',
  `left_count` decimal(64,0) NOT NULL COMMENT '剩余可交易',
  `market_price` decimal(32,3) NOT NULL COMMENT '行情价格',
  `protect_price` decimal(32,3) NOT NULL COMMENT '保护单价',
  `true_price` decimal(32,3) NOT NULL COMMENT '溢价率换算后的真实价格',
  `min_trade` decimal(32,3) DEFAULT NULL COMMENT '单笔最小交易额',
  `max_trade` decimal(32,3) DEFAULT NULL COMMENT '单笔最大交易额',
  `pay_type` varchar(32) NOT NULL COMMENT '付款方式',
  `pay_limit` int(11) DEFAULT NULL COMMENT '付款超时时间（分钟）',
  `status` varchar(4) NOT NULL COMMENT '状态 0=草稿、1=上架中、2=下架',
  `leave_message` text CHARACTER SET utf8mb4 NOT NULL COMMENT '广告留言',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `update_datetime` datetime NOT NULL COMMENT '更新时间',
  `fee_rate` decimal(8,4) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tcoin_ads_display_time`;
CREATE TABLE `tcoin_ads_display_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ads_code` varchar(32) NOT NULL COMMENT '编号',
  `week` char(1) NOT NULL COMMENT '周几',
  `start_time` int(11) NOT NULL COMMENT '开始时间',
  `end_time` int(11) NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ads_week` (`ads_code`,`week`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tcoin_trade_order`;
CREATE TABLE `tcoin_trade_order` (
  `code` varchar(32) NOT NULL COMMENT '订单编号',
  `type` varchar(32) DEFAULT NULL COMMENT '订单类型',
  `ads_code` varchar(32) DEFAULT NULL COMMENT '广告编号',
  `buy_user` varchar(32) DEFAULT NULL COMMENT '买方',
  `sell_user` varchar(32) DEFAULT NULL COMMENT '卖方',
  `leave_message` text CHARACTER SET utf8mb4 COMMENT '广告留言',
  `trade_currency` varchar(32) DEFAULT NULL COMMENT '交易币种',
  `trade_coin` varchar(32) DEFAULT NULL COMMENT '交易数字货币类型',
  `trade_price` decimal(32,3) DEFAULT NULL COMMENT '交易单价',
  `trade_amount` decimal(32,3) DEFAULT NULL COMMENT '交易总额',
  `fee` decimal(64,0) DEFAULT NULL COMMENT '手续费',
  `count` decimal(64,0) DEFAULT NULL COMMENT '交易数字货币数量',
  `pay_type` varchar(32) DEFAULT NULL COMMENT '付款方式',
  `invalid_datetime` datetime DEFAULT NULL COMMENT '支付超时时间',
  `status` varchar(4) DEFAULT NULL COMMENT '订单状态',
  `sb_comment` varchar(32) DEFAULT NULL COMMENT '卖方对买方的评价',
  `bs_comment` varchar(32) DEFAULT NULL COMMENT '买方对卖方的评价',
  `mark_datetime` datetime DEFAULT NULL COMMENT '标记打款时间',
  `release_datetime` datetime DEFAULT NULL COMMENT '币释放时间',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '最后更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最后更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tcoin_arbitrate`;
CREATE TABLE `tcoin_arbitrate` (
  `code` varchar(32) NOT NULL COMMENT '工单编号',
  `trade_order_code` varchar(32) DEFAULT NULL COMMENT '交易订单编号',
  `yuangao` varchar(32) DEFAULT NULL COMMENT '原告',
  `beigao` varchar(32) DEFAULT NULL COMMENT '被告',
  `reason` text CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '仲裁理由',
  `attach` text CHARACTER SET utf8mb4 COMMENT '附件',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `result` text COMMENT '处理结果',
  `updater` varchar(45) DEFAULT NULL COMMENT '处理人',
  `update_datetime` datetime DEFAULT NULL COMMENT '处理时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tcoin_accept_order`;
CREATE TABLE `tcoin_accept_order`(
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型(0买单/1卖单)',  
  `buy_user` varchar(32) DEFAULT NULL COMMENT '买方',
  `sell_user` varchar(32) DEFAULT NULL COMMENT '卖方',
  `trade_currency` varchar(32) DEFAULT NULL COMMENT '交易币种',
  `trade_coin` varchar(32) DEFAULT NULL COMMENT '交易数字货币',
  `trade_price` decimal(32,3) DEFAULT NULL COMMENT '交易单价',
  `count` decimal(64,0) DEFAULT NULL COMMENT '交易数量',
  `trade_amount` decimal(32,3) DEFAULT NULL COMMENT '交易总额',
  `fee` decimal(64,0) DEFAULT NULL COMMENT '手续费',
  `invalid_datetime` datetime DEFAULT NULL COMMENT '支付超时时间',
  `pay_type` varchar(32) DEFAULT NULL COMMENT '付款方式',
  `pay_info` varchar(255) DEFAULT NULL COMMENT '付款信息',
  `pay_bank` varchar(255) DEFAULT NULL COMMENT '付款银行',
  `pay_card_no` varchar(32) DEFAULT NULL COMMENT '付款卡号',
  `receive_type` varchar(32) DEFAULT NULL COMMENT '收款方式',
  `receive_info` varchar(255) DEFAULT NULL COMMENT '收款信息',
  `receive_bank` varchar(255) DEFAULT NULL COMMENT '收款银行',
  `receive_card_no` varchar(32) DEFAULT NULL COMMENT '收款卡号',
  `status` varchar(4) DEFAULT NULL COMMENT '状态(0=待支付 1=已支付 2=已释放 3=已取消)',
  `mark_datetime` datetime DEFAULT NULL COMMENT '打款时间',
  `mark_pdf` varchar(2048) DEFAULT NULL COMMENT '打款截图',
  `mark_note` varchar(255) DEFAULT NULL COMMENT '打款说明',
  `release_datetime` datetime DEFAULT NULL COMMENT '币释放时间',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '最后更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最后更新时间',
  `remark` varchar(255) COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='承兑交易订单';


