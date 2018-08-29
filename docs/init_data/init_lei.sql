DROP TABLE IF EXISTS `tsimu_group`;
CREATE TABLE IF NOT EXISTS `tsimu_group` (
  `code` VARCHAR(32) NOT NULL COMMENT '编号',
  `match_code` VARCHAR(32) NOT NULL COMMENT '赛事编号',
  `team_code` VARCHAR(32) NOT NULL COMMENT '战队编号',
  `user_id` VARCHAR(32) NOT NULL COMMENT '用户编号',
  `symbol` VARCHAR(32) NOT NULL COMMENT '代币符号',
  `init_amount` DECIMAL(64,0) NOT NULL COMMENT '初始金额',
  `balance` DECIMAL(64,0) NOT NULL COMMENT ' 余额',
  `total_assets` DECIMAL(64,0) NOT NULL COMMENT '总资产',
  `total_benefit` DECIMAL(64,0) NULL COMMENT '总收益',
  `day_benefit` DECIMAL(64,0) NULL COMMENT '今日收益',
  `week_benefit` DECIMAL(64,0) NULL COMMENT '周收益',
  `month_benefit` DECIMAL(64,0) NULL COMMENT '月收益',
  `order_no` BIGINT(32) NOT NULL COMMENT '排行',
  `follow_number` BIGINT(32) NULL COMMENT '关注人数',
  `status` VARCHAR(32) NOT NULL COMMENT '状态（1-进行中，0-已结束）',
  `create_datetime` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`code`))
ENGINE = InnoDB DEFAULT CHARSET=utf8
COMMENT = '组合表';

DROP TABLE IF EXISTS `tsimu_group_coin`;
CREATE TABLE IF NOT EXISTS `tsimu_group_coin` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `group_code` VARCHAR(32) NOT NULL COMMENT '组合编号',
  `symbol` VARCHAR(32) NULL COMMENT '代币符号',
  `count` DECIMAL(64,18) NULL COMMENT '数量',
  `frozen_count` DECIMAL(64,18) NULL COMMENT '冻结数量',
  `assets` DECIMAL(64,18) NULL COMMENT '币种资产',
  `rate` DECIMAL(18,4) NULL COMMENT '占比',
  PRIMARY KEY (`id`))
ENGINE = InnoDB DEFAULT CHARSET=utf8
COMMENT = '币种配置表';

DROP TABLE IF EXISTS `tsimu_group_coin`;
CREATE TABLE IF NOT EXISTS `tsimu_group_coin` (
  `account_number` varchar(32) NOT NULL COMMENT '账户编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `group_code` VARCHAR(32) NOT NULL COMMENT '组合编号',
  `symbol` VARCHAR(32) NULL COMMENT '代币符号',
  `count` DECIMAL(64,18) NULL COMMENT '数量',
  `frozen_count` DECIMAL(64,18) NULL COMMENT '冻结数量',
  `assets` DECIMAL(64,18) NULL COMMENT '币种资产',
  `rate` DECIMAL(18,4) NULL COMMENT '占比',
  `last_order` varchar(32) DEFAULT NULL COMMENT '最近一次变动对应的流水编号',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`account_number`))
ENGINE = InnoDB DEFAULT CHARSET=utf8mb4
COMMENT = '币种配置账户表';

DROP TABLE IF EXISTS `tsimu_group_coin_jour`;
CREATE TABLE `tsimu_group_coin_jour` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `ref_no` varchar(32) DEFAULT NULL COMMENT '参考订单号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '账户编号',
  `trans_amount` bigint(32) DEFAULT NULL COMMENT '变动金额',
  `group_code` VARCHAR(32) NOT NULL COMMENT '组合编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `symbol` varchar(8) DEFAULT NULL COMMENT '币种',
  `biz_type` varchar(32) DEFAULT NULL COMMENT '业务类型',
  `biz_note` varchar(255) DEFAULT NULL COMMENT '业务类型',
  `pre_amount` bigint(32) DEFAULT NULL COMMENT '变动前金额',
  `post_amount` bigint(32) DEFAULT NULL COMMENT '变动后金额',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`code`)) 
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
COMMENT = '币种配置账户流水表';


DROP TABLE IF EXISTS `tsimu_attention`;
CREATE TABLE IF NOT EXISTS `tsimu_attention` (
  `code` VARCHAR(32) NOT NULL COMMENT '编号',
  `type` VARCHAR(32) NOT NULL COMMENT '类型(1-关注/0-提醒)',
  `user_id` VARCHAR(32) NOT NULL COMMENT '用户编号',
  `group_code` VARCHAR(32) NOT NULL COMMENT '组合编号',
  `group_user_id` VARCHAR(32) NOT NULL COMMENT '组合用户编号',
  `create_datetime` DATETIME NOT NULL COMMENT '关注时间',
  PRIMARY KEY (`code`))
ENGINE = InnoDB DEFAULT CHARSET=utf8
COMMENT = '关注表';

DROP TABLE IF EXISTS `tsimu_day_benefit`;
CREATE TABLE IF NOT EXISTS `tsimu_day_benefit` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `group_code` VARCHAR(32) NOT NULL COMMENT '组合编号',
  `yesterday_assets` DECIMAL(64,18) NULL COMMENT '昨日总资产',
  `today_assets` DECIMAL(64,18) NULL COMMENT '今日总资产',
  `benefit` DECIMAL(64,18) NULL COMMENT '收益',
  `benefit_rate` DECIMAL(18,4) NULL COMMENT '收益率',
  `create_datetime` DATETIME NOT NULL COMMENT '产生日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB DEFAULT CHARSET=utf8
COMMENT = '每日收益统计表';

DROP TABLE IF EXISTS `tsimu_order`;
CREATE TABLE IF NOT EXISTS `tsimu_order` (
  `code` VARCHAR(32) NOT NULL COMMENT '编号',
  `group_code` VARCHAR(32) NOT NULL COMMENT '组合编号',
  `user_id` VARCHAR(32) NOT NULL COMMENT '用户编号',
  `exchange` VARCHAR(255) NOT NULL COMMENT '交易所',
  `symbol` VARCHAR(32) NOT NULL COMMENT '交易币种',
  `to_symbol` VARCHAR(32) NOT NULL COMMENT '计价币种',
  `type` VARCHAR(32) NOT NULL COMMENT '类型(1-市价，0-限价)',
  `direction` VARCHAR(32) NOT NULL COMMENT '买卖方向(0-买入，1-卖出)',
  `price` DECIMAL(64,18) NOT NULL COMMENT '委托价格',
  `total_count` DECIMAL(64,18) NOT NULL COMMENT '委托总数量',
  `total_amount` DECIMAL(64,18) NOT NULL COMMENT '委托总金额',
  `traded_count` DECIMAL(64,18) NULL COMMENT '已成交数量',
  `traded_amount` DECIMAL(64,18) NULL COMMENT '已成交总金额',
  `traded_fee` DECIMAL(64,18) NULL COMMENT '已成交总手续费',
  `last_traded_datetime` DATETIME NULL COMMENT '最后成交时间',
  `create_datetime` DATETIME NOT NULL COMMENT '创建时间',
  `cancel_datetime` DATETIME NULL COMMENT '撤销时间',
  `status` VARCHAR(32) NULL COMMENT '状态(0-待成交, 1-部分成交, 2-部分成交撤销, 3-完全成交, 4-已撤销)',
  PRIMARY KEY (`code`))
ENGINE = InnoDB DEFAULT CHARSET=utf8
COMMENT = '委托单';

DROP TABLE IF EXISTS `tsimu_order_detail`;
CREATE TABLE IF NOT EXISTS `tsimu_order_detail` (
  `code` VARCHAR(32) NOT NULL COMMENT '编号',
  `order_code` VARCHAR(32) NULL COMMENT '委托单编号',
  `traded_price` DECIMAL(64,18) NULL COMMENT '成交价格',
  `traded_count` DECIMAL(64,18) NULL COMMENT '成交数量',
  `traded_amount` DECIMAL(64,18) NULL COMMENT '成交总金额',
  `traded_fee` DECIMAL(64,18) NULL COMMENT '成交手续费',
  `create_datetime` DATETIME NULL COMMENT '成交时间',
  PRIMARY KEY (`code`))
ENGINE = InnoDB DEFAULT CHARSET=utf8mb4
COMMENT = '成交明细单';

DROP TABLE IF EXISTS `tmk_coin`;
CREATE TABLE IF NOT EXISTS `tmk_coin` (
  `id` BIGINT(11) NOT NULL COMMENT '序号',
  `symbol` VARCHAR(32) NOT NULL COMMENT '币种符号',
  `cname` VARCHAR(255) NOT NULL COMMENT '中文简称',
  `ename` VARCHAR(255) NOT NULL COMMENT '英文简称',
  `type` VARCHAR(32) NULL COMMENT '类型',
  `unit` INT(11) NOT NULL COMMENT '单位',
  `logo` VARCHAR(255) NOT NULL COMMENT '图标',
  `order_no` BIGINT(11) NULL COMMENT 'UI序号',
  `status` VARCHAR(32) NULL COMMENT '状态',
  `updater` VARCHAR(32) NULL COMMENT '更新人',
  `update_datetime` DATETIME NULL COMMENT '更新时间',
  `remark` TEXT NULL COMMENT '备注',
  PRIMARY KEY (`id`))
ENGINE = InnoDB DEFAULT CHARSET=utf8
COMMENT = '币种';

DROP TABLE IF EXISTS `tmk_exchange`;
CREATE TABLE IF NOT EXISTS `tmk_exchange` (
  `id` BIGINT(11) NOT NULL COMMENT '序号',
  `ename` VARCHAR(255) NOT NULL COMMENT '交易所英文名',
  `cname` VARCHAR(255) NOT NULL COMMENT '交易所名称',
  `introduce` TEXT NULL COMMENT '简介',
  `status` VARCHAR(32) NULL COMMENT '状态',
  `order_no` BIGINT(11) NULL COMMENT 'UI序号',
  `updater` VARCHAR(32) NULL COMMENT '更新人',
  `update_datetime` DATETIME NULL COMMENT '更新时间',
  `remark` TEXT NULL COMMENT '备注',
  PRIMARY KEY (`id`))
ENGINE = InnoDB DEFAULT CHARSET=utf8
COMMENT = '交易所';

DROP TABLE IF EXISTS `tmk_exchange_pair`;
CREATE TABLE IF NOT EXISTS `tmk_exchange_pair` (
  `id` BIGINT(11) NOT NULL COMMENT '序号',
  `exchange_ename` VARCHAR(255) NULL COMMENT '交易所英文名',
  `symbol` VARCHAR(32) NULL COMMENT '基础币种',
  `to_symbol` VARCHAR(32) NULL COMMENT '计价币种',
  `price` DECIMAL(64,18) NULL COMMENT '最新价格',
  PRIMARY KEY (`id`))
ENGINE = InnoDB DEFAULT CHARSET=utf8
COMMENT = '交易对';


