DROP TABLE IF EXISTS `tstd_divide`;
CREATE TABLE `tstd_divide` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种(X币)',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '当时余额', 
  `divi_profit` decimal(64,0) DEFAULT NULL COMMENT '分红利润',  
  `divi_amount` decimal(64,0) DEFAULT NULL COMMENT '分红余额',  
  `divi_datetime` datetime NOT NULL COMMENT '最后操作时间',
  `status` varchar(4) NOT NULL COMMENT '状态(0=待分红 1=已分红)',
  `updater` varchar(32) NOT NULL COMMENT '最后操作人',
  `update_datetime` datetime NOT NULL COMMENT '最后操作时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='币种分红快照';

DROP TABLE IF EXISTS `tstd_commission`;
CREATE TABLE `tstd_commission` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `user_kind` varchar(32) NOT NULL COMMENT '用户种类，1=普通用户 2=渠道商',
  `rel_user_id` varchar(32) NOT NULL COMMENT '关联用户编号',
  `currency` char(8) DEFAULT NULL COMMENT '币种',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '佣金',
  `order_amount` decimal(64,0) DEFAULT NULL COMMENT '订单金额',
  `rate` decimal(18,8) DEFAULT NULL COMMENT '佣金比例',
  `ref_type` char(1) DEFAULT NULL COMMENT '参考类型(1=注册分佣 2=交易分佣)',
  `ref_code` varchar(32) DEFAULT NULL COMMENT '参考订单编号',
  `ref_note` varchar(255) DEFAULT NULL COMMENT '参考说明',
  `status` char(1) NOT NULL COMMENT '状态(0=待结算 1=已结算 2=结算失败)',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `settle_datetime` datetime DEFAULT NULL COMMENT '结算时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分佣记录';

