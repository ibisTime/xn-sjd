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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tcoin_arbitrate`;
CREATE TABLE `tcoin_arbitrate` (
  `code` varchar(32) NOT NULL COMMENT '工单编号',
  `trade_order_code` varchar(32) DEFAULT NULL COMMENT '交易订单编号',
  `yuangao` varchar(32) DEFAULT NULL COMMENT '原告',
  `beigao` varchar(32) DEFAULT NULL COMMENT '被告',
  `reason` text CHARACTER SET utf8mb4 COMMENT '仲裁理由',
  `attach` text CHARACTER SET utf8mb4 COMMENT '附件',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `result` text COMMENT '处理结果',
  `updater` varchar(45) DEFAULT NULL COMMENT '处理人',
  `update_datetime` datetime DEFAULT NULL COMMENT '处理时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tstd_user`;
CREATE TABLE `tstd_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登陆名',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `login_pwd` varchar(32) DEFAULT NULL COMMENT '登陆密码',
  `login_pwd_strength` char(1) DEFAULT NULL COMMENT '登陆密码强度',
  `level` varchar(4) DEFAULT NULL COMMENT '用户等级',
  `user_referee` varchar(32) DEFAULT NULL COMMENT '推荐人',
  `id_kind` char(1) DEFAULT NULL COMMENT '证件类型',
  `id_no` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `trade_pwd` varchar(32) DEFAULT NULL COMMENT '安全密码',
  `trade_pwd_strength` char(1) DEFAULT NULL COMMENT '安全密码强度',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `longitude` varchar(255) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(255) DEFAULT NULL COMMENT '维度',
  `trade_rate` decimal(8,4) DEFAULT NULL COMMENT '交易手续费',
  `create_datetime` datetime DEFAULT NULL COMMENT '注册时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tstd_user_ext`;
CREATE TABLE `tstd_user_ext` (
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `gender` char(1) DEFAULT NULL COMMENT '性别(1 男 0 女)',
  `introduce` varchar(255) DEFAULT NULL COMMENT '自我介绍',
  `birthday` varchar(16) DEFAULT NULL COMMENT '生日',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `diploma` varchar(4) DEFAULT NULL COMMENT '学位',
  `occupation` varchar(64) DEFAULT NULL COMMENT '职业',
  `grad_datetime` datetime DEFAULT NULL COMMENT '毕业时间',
  `work_time` varchar(4) DEFAULT NULL COMMENT '工作年限',
  `pdf` varchar(255) DEFAULT NULL COMMENT '用户资料',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tstd_sign_log`;
CREATE TABLE `tstd_sign_log` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `type` varchar(4) DEFAULT NULL COMMENT '分类（1-登录日志；2-签到日志）',
  `ip` varchar(255) DEFAULT NULL COMMENT 'ip',
  `client` varchar(4) DEFAULT NULL COMMENT '客户端',
  `location` varchar(255) DEFAULT NULL COMMENT '登录时定位',
  `create_datetime` datetime DEFAULT NULL COMMENT '签到时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tstd_auth_log`;
CREATE TABLE `tstd_auth_log` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `type` varchar(4) DEFAULT NULL COMMENT '认证类型',
  `auth_arg1` varchar(255) DEFAULT NULL COMMENT '入参1',
  `auth_arg2` varchar(255) DEFAULT NULL COMMENT '入参2',
  `auth_arg3` varchar(255) DEFAULT NULL COMMENT '入参3',
  `status` varchar(4) DEFAULT NULL COMMENT '状态 0=待处理 1=认证通过 2=认证不通过',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `approve_user` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `result` varchar(255) DEFAULT NULL COMMENT '出参',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tstd_blacklist`;
CREATE TABLE `tstd_blacklist` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `type` varchar(4) DEFAULT NULL COMMENT '拉黑粒度',
  `status` varchar(4) DEFAULT NULL COMMENT '状态 0-已失效 1-已生效',
  `create_datetime` datetime DEFAULT NULL COMMENT '拉黑时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` varchar(45) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tstd_read`;
CREATE TABLE `tstd_read` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `sms_code` varchar(32) DEFAULT NULL COMMENT '消息编号',
  `receive_way` varchar(4) DEFAULT NULL COMMENT '接受方式(站内消息，APP推送,短信)',
  `status` varchar(4) DEFAULT NULL COMMENT '状态 0-未阅读 1-已阅读 2-已删除',
  `create_datetime` datetime DEFAULT NULL COMMENT '推送时间',
  `read_datetime` datetime DEFAULT NULL COMMENT '阅读时间',
  `delete_datetime` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tstd_sms`;
CREATE TABLE `tstd_sms` (
  `code` varchar(32) NOT NULL COMMENT '消息编号',
  `type` varchar(4) DEFAULT NULL COMMENT '消息类型（系统公告，短信内容）',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `status` varchar(4) DEFAULT NULL COMMENT '状态 0-草稿 1-已发送 2-已回撤',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` varchar(45) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_dict`;
CREATE TABLE `tsys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `parent_key` varchar(32) DEFAULT NULL COMMENT '父亲key',
  `dkey` varchar(32) DEFAULT NULL COMMENT 'key',
  `dvalue` varchar(255) DEFAULT NULL COMMENT 'value',
  `updater` varchar(32) DEFAULT NULL COMMENT '最近修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最近修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_config`;
CREATE TABLE `tsys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `ckey` varchar(255) DEFAULT NULL COMMENT 'key',
  `cvalue` text COMMENT 'value',
  `updater` varchar(32) DEFAULT NULL COMMENT '最近修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最近修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_company`;
CREATE TABLE `tsys_company` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_department`;
CREATE TABLE `tsys_department` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `name` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `leader` varchar(255) DEFAULT NULL COMMENT '部门负责人',
  `leader_mobile` varchar(16) DEFAULT NULL COMMENT '负责人手机号',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '上级部门',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tsys_user`;
CREATE TABLE `tsys_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色编号',
  `department_code` varchar(32) DEFAULT NULL COMMENT '部门编号(当有部门表时才有此字段）',
  `real_name` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登录名',
  `login_pwd` varbinary(32) DEFAULT NULL COMMENT '登录密码',
  `login_pwd_strength` char(1) DEFAULT NULL COMMENT '登录密码强度',
  `create_datetime` datetime DEFAULT NULL COMMENT '注册时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_menu`;
CREATE TABLE `tsys_menu` (
  `code` varchar(32) NOT NULL DEFAULT '' COMMENT '编号',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '父亲key',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `type` varchar(2) DEFAULT NULL COMMENT '类型',
  `url` varchar(255) DEFAULT NULL COMMENT 'url',
  `order_no` int(11) DEFAULT NULL COMMENT '次序',
  `updater` varchar(32) DEFAULT NULL COMMENT '最近修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最近修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tsys_menu_role`;
CREATE TABLE `tsys_menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色编号',
  `menu_code` varchar(32) DEFAULT NULL COMMENT '菜单编号',
  `updater` varchar(32) DEFAULT NULL COMMENT '最近修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最近修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1144 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tsys_role`;
CREATE TABLE `tsys_role` (
  `code` varchar(32) DEFAULT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `updater`varchar(32) DEFAULT NULL COMMENT '最近修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最近修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_cnavigate`;
CREATE TABLE `tsys_cnavigate` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `url` varchar(255) DEFAULT NULL COMMENT '访问Url',
  `pic` varchar(255) DEFAULT NULL COMMENT '图片',
  `status` varchar(4) DEFAULT NULL COMMENT '状态(1 显示 0 不显示)',
  `location` varchar(32) DEFAULT NULL COMMENT '位置',
  `order_no` int(11) DEFAULT NULL COMMENT '相对位置编号',
  `belong` varchar(32) DEFAULT NULL COMMENT '属于(1 全局 2 地方默认 3 地方默认编号)',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '父编号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `content_type` varchar(32) DEFAULT NULL COMMENT '内容源类型',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_channel_bank`;
CREATE TABLE `tsys_channel_bank` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '编号（自增长）',
  `bank_code` varchar(32) DEFAULT NULL COMMENT '银行编号',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '银行名称',
  `channel_type` varchar(4) DEFAULT NULL COMMENT '渠道类型',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（启用/不启用）',
  `channel_bank` varchar(32) DEFAULT NULL COMMENT '渠道给银行的代号',
  `max_order` bigint(32) DEFAULT NULL COMMENT '笔数限制',
  `order_amount` bigint(32) DEFAULT NULL COMMENT '单笔限额',
  `day_amount` bigint(32) DEFAULT NULL COMMENT '每日限额',
  `month_amount` bigint(32) DEFAULT NULL COMMENT '每月限额',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

