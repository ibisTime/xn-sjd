
CREATE TABLE `tcoin_coin` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `symbol` varchar(64) NOT NULL DEFAULT '' COMMENT '英文简称',
  `ename` varchar(64) DEFAULT NULL COMMENT '英文名称',
  `cname` varchar(64) DEFAULT NULL COMMENT '中文名称',
  `type` varchar(32) NOT NULL COMMENT '分类 0-以太币 1-比特币 2—万维币 0T-以太token币 2T-万维token币',
  `unit` int(11) NOT NULL COMMENT '单位',
  `icon` varchar(255) NOT NULL COMMENT '币种图标',
  `pic1` varchar(255) DEFAULT NULL COMMENT '图标1',
  `pic2` varchar(255) DEFAULT NULL COMMENT '图标2',
  `pic3` varchar(255) DEFAULT NULL COMMENT '图标3',
  `order_no` int(11) NOT NULL COMMENT 'UI序号',
  `collect_start` decimal(64,0) NOT NULL COMMENT '归集阀值',
  `withdraw_fee` decimal(64,0) NOT NULL COMMENT '取现手续费',
  `contract_address` varchar(255) DEFAULT NULL COMMENT '合约地址',
  `contract_abi` text COMMENT '合约ABI',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `updater` varchar(32) NOT NULL COMMENT '最后操作人',
  `update_datetime` datetime NOT NULL COMMENT '最后操作时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='钱包支持币种';

CREATE TABLE `tcoin_eth_xaddress` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `address` varchar(255) NOT NULL COMMENT '以太坊地址',
  `keystore_pwd` varchar(255) NOT NULL COMMENT 'keystore密码',
  `keystore_name` text COMMENT 'keystore文件名',
  `keystore_content` text COMMENT 'keystore文件内容',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ETH用户分发地址';

CREATE TABLE `tcoin_eth_maddress` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `address` varchar(255) DEFAULT NULL COMMENT '以太坊地址',
  `keystore_pwd` varchar(255) NOT NULL COMMENT 'keystore密码',
  `keystore_name` text COMMENT 'keystore文件名',
  `keystore_content` text COMMENT 'keystore文件内容',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `abandon_datetime` datetime DEFAULT NULL COMMENT '弃用时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ETH平台散取地址';

CREATE TABLE `tcoin_eth_waddress` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `address` varchar(255) DEFAULT NULL COMMENT '以太坊地址',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `abandon_datetime` datetime DEFAULT NULL COMMENT '弃用时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ETH平台归集地址';

CREATE TABLE `tcoin_eth_transaction` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `hash` varchar(255) NOT NULL COMMENT '交易哈希',
  `nonce` bigint(32) NOT NULL COMMENT '交易次数',
  `block_hash` varchar(255) NOT NULL COMMENT '区块哈希',
  `block_number` bigint(32) NOT NULL COMMENT '区块号',
  `block_create_datetime` datetime NOT NULL COMMENT '区块形成时间',
  `transaction_index` bigint(32) NOT NULL COMMENT '交易index',
  `from` varchar(255) NOT NULL COMMENT '转出地址',
  `to` varchar(255) NOT NULL COMMENT '转入地址',
  `value` decimal(64,0) NOT NULL COMMENT '交易额',
  `sync_datetime` datetime NOT NULL COMMENT '同步时间',
  `gas_price` decimal(64,0) NOT NULL COMMENT 'gas价格',
  `gas_limit` bigint(20) NOT NULL COMMENT 'gas最大使用限制',
  `gas_used` bigint(20) NOT NULL COMMENT 'gas实际使用量',
  `gas_fee` decimal(64,0) NOT NULL COMMENT 'gas手续费',
  `input` text COMMENT 'input 输入',
  `public_key` text COMMENT 'publicKey',
  `raw` text COMMENT 'raw',
  `r` text COMMENT 'r',
  `s` text COMMENT 's',
  PRIMARY KEY (`id`),
  UNIQUE KEY `hash_UNIQUE` (`hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ETH交易记录';

CREATE TABLE `tcoin_token_event` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `hash` varchar(255) NOT NULL COMMENT '交易哈希',
  `token_from` varchar(255) DEFAULT NULL COMMENT 'token币发起地址',
  `token_to` varchar(255) DEFAULT NULL COMMENT 'token币接收地址',
  `token_value` decimal(64,0) DEFAULT NULL COMMENT 'token币数量',
  `token_log_index` bigint(32) DEFAULT NULL COMMENT 'event_log_index',
  `symbol` varchar(32) NOT NULL COMMENT '币种符号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ETHtoken交易event';

CREATE TABLE `tcoin_personal_address` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `symbol` varchar(32) DEFAULT NULL COMMENT '币种',
  `address` varchar(255) DEFAULT NULL COMMENT '提现地址',
  `label` varchar(64) DEFAULT NULL COMMENT '标签',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '最后操作人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最后一次更时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户取现地址';

CREATE TABLE `tstd_account` (
  `account_number` varchar(32) NOT NULL COMMENT '账户编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `address` varchar(255) DEFAULT NULL COMMENT '区块链地址',
  `type` varchar(4) DEFAULT NULL COMMENT '类别（B端账号，C端账号，平台账号）',
  `status` varchar(2) DEFAULT NULL COMMENT '状态（正常/程序冻结/人工冻结）',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '余额',
  `frozen_amount` decimal(64,0) DEFAULT NULL COMMENT '冻结金额',
  `md5` varchar(32) DEFAULT NULL COMMENT 'MD5',
  `in_amount` decimal(64,0) DEFAULT '0' COMMENT '总充值金额（入金）',
  `out_amount` decimal(64,0) DEFAULT '0' COMMENT '总取现金额（出金）',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `last_order` varchar(32) DEFAULT NULL COMMENT '最近一次变动对应的流水编号',
  PRIMARY KEY (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户';

CREATE TABLE `tstd_jour` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '流水类型（余额流水、冻结流水）',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '账户编号',
  `account_type` varchar(4) DEFAULT NULL COMMENT '账户类型',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `biz_type` varchar(32) DEFAULT NULL COMMENT '业务类型',
  `biz_note` varchar(255) DEFAULT NULL COMMENT '业务说明',
  `trans_amount` decimal(64,0) DEFAULT NULL COMMENT '变动金额',
  `pre_amount` decimal(64,0) DEFAULT NULL COMMENT '变动前金额',
  `post_amount` decimal(64,0) DEFAULT NULL COMMENT '变动后金额',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道类型',
  `channel_order` varchar(255) DEFAULT NULL COMMENT '支付渠道单号',
  `ref_no` varchar(255) DEFAULT NULL COMMENT '系统内部参考订单号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `work_date` varchar(8) DEFAULT NULL COMMENT '拟对账时间',
  `check_user` varchar(32) DEFAULT NULL COMMENT '对账人',
  `check_note` varchar(255) DEFAULT NULL COMMENT '对账说明',
  `check_datetime` datetime DEFAULT NULL COMMENT '对账时间',
  `adjust_user` varchar(32) DEFAULT NULL COMMENT '调账人',
  `adjust_note` varchar(255) DEFAULT NULL COMMENT '调账说明',
  `adjust_datetime` datetime DEFAULT NULL COMMENT '调账时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户流水';

CREATE TABLE `tstd_charge` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '账户编号',
  `account_type` varchar(4) DEFAULT NULL COMMENT '账户类型',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '充值金额',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `biz_type` varchar(32) DEFAULT NULL COMMENT '关联业务类型',
  `biz_note` varchar(255) DEFAULT NULL COMMENT '关联业务备注',
  `biz_no` varchar(255) DEFAULT NULL COMMENT '关联订单号',
  `pay_card_info` varchar(255) DEFAULT NULL COMMENT '支付渠道账号信息',
  `pay_card_no` varchar(255) DEFAULT NULL COMMENT '支付渠道账号',
  `status` varchar(4) NOT NULL COMMENT '状态 1待支付 2支付失败 3支付成功',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_note` varchar(255) DEFAULT NULL COMMENT '申请说明',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `pay_user` varchar(32) DEFAULT NULL COMMENT '支付回录人',
  `pay_note` varchar(255) DEFAULT NULL COMMENT '支付渠道说明',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `channel_order` varchar(64) DEFAULT NULL COMMENT '支付渠道单号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值订单';

CREATE TABLE `tstd_withdraw` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '账户编号',
  `account_type` varchar(4) DEFAULT NULL COMMENT '类别（B端账号，C端账号，平台账号）',
  `currency` varchar(32) DEFAULT NULL COMMENT '币种',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '取现金额',
  `fee` decimal(64,0) DEFAULT NULL COMMENT '手续费',
  `actual_amount` decimal(64,0) DEFAULT NULL COMMENT '实际到账金额',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `channel_bank` varchar(32) DEFAULT NULL COMMENT '渠道银行代号',
  `channel_order` varchar(255) DEFAULT NULL COMMENT '支付渠道编号',
  `pay_card_info` varchar(255) DEFAULT NULL COMMENT '支付渠道账号信息',
  `pay_card_no` varchar(255) DEFAULT NULL COMMENT '支付渠道账号',
  `status` varchar(4) NOT NULL COMMENT '状态',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_note` varchar(255) DEFAULT NULL COMMENT '申请说明',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `approve_user` varchar(32) DEFAULT NULL COMMENT '审批人',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审批说明',
  `approve_datetime` varchar(32) DEFAULT NULL COMMENT '审批时间',
  `pay_user` varchar(255) DEFAULT NULL COMMENT '支付回录人',
  `pay_note` varchar(255) DEFAULT NULL COMMENT '支付回录说明',
  `pay_fee` decimal(64,0) DEFAULT NULL COMMENT '支付渠道手续费（矿工费）',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付回录时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '取现订单';

CREATE TABLE `tstd_collect` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `currency` varchar(32) DEFAULT NULL COMMENT '币种',
  `from_address` text COMMENT '被归集地址',
  `to_address` text COMMENT '归集地址',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '归集数量',
  `tx_hash` varchar(255) DEFAULT NULL COMMENT '交易hash',
  `tx_fee` decimal(64,0) DEFAULT NULL COMMENT '矿工费',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '发起时间',
  `confirm_datetime` datetime DEFAULT NULL COMMENT '网络记账时间',
  `finish_datetime` datetime DEFAULT NULL COMMENT '完成时间',
  `ref_no` varchar(32) DEFAULT NULL COMMENT '关联订单号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '归集订单';

CREATE TABLE `tstd_deposit` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `currency` varchar(32) DEFAULT NULL COMMENT '币种',
  `from_address` text COMMENT '付款地址',
  `to_address` text COMMENT '收款地址',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '定存数量',
  `tx_hash` varchar(255) DEFAULT NULL COMMENT '交易hash',
  `tx_fee` decimal(64,0) DEFAULT NULL COMMENT '矿工费',
  `confirm_datetime` datetime DEFAULT NULL COMMENT '网络记账时间',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `ref_no` varchar(32) DEFAULT NULL COMMENT '关联订单号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '定存订单';

CREATE TABLE `tstd_hlorder` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `account_number` varchar(32) NOT NULL COMMENT '账户编号',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `jour_code` varchar(32) DEFAULT NULL COMMENT '流水号',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `direction` char(1) NOT NULL COMMENT '方向：1=蓝补；0=红冲',
  `amount` decimal(64,0) NOT NULL COMMENT '金额',
  `status` varchar(4) NOT NULL COMMENT '状态',
  `apply_user` varchar(32) NOT NULL COMMENT '申请人',
  `apply_note` varchar(255) NOT NULL COMMENT '申请说明',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `approve_user` varchar(32) DEFAULT NULL COMMENT '审批人',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审批说明',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审批时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '红蓝订单';

CREATE TABLE `tcoin_currency_rate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `currency` varchar(10) NOT NULL COMMENT '币种',
  `refer_currency` varchar(10) NOT NULL COMMENT '参照币种 为CNY',
  `origin` varchar(32) NOT NULL COMMENT '汇率来源',
  `rate` decimal(8,4) NOT NULL COMMENT '汇率',
  `update_datetime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '法币汇率';

CREATE TABLE `tcoin_market` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) NOT NULL COMMENT '数字货币',
  `refer_currency` varchar(10) NOT NULL COMMENT '参照法币币种',
  `origin` varchar(32) NOT NULL COMMENT '行情来源',
  `last_price` decimal(18,4) NOT NULL COMMENT '最新成交价格',
  `bid` decimal(18,4) DEFAULT NULL COMMENT '买家期望价格',
  `ask` decimal(18,4) DEFAULT NULL COMMENT '卖家期望价格',
  `mid` decimal(18,4) NOT NULL COMMENT '中间价格',
  `low` decimal(18,4) NOT NULL COMMENT '最低价格',
  `high` decimal(18,4) NOT NULL COMMENT '最高价格',
  `volume` varchar(32) DEFAULT NULL COMMENT '交易量',
  `update_datetime` datetime NOT NULL COMMENT '更新时间',
  `coinmarketcap_id` int(10) NOT NULL COMMENT 'coinmarketcap中的币种id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `origin` (`origin`,`symbol`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '币种行情';

/*
 * 1、法币币种用currency 数字货币币种用symbol  
 * 2、金额数据库字段都用decimal(64,0) domain用BigDecimal
 * */

