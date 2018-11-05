/*
 Navicat MySQL Data Transfer

 Source Server         : 47.96.161.183
 Source Server Version : 50633
 Source Host           : 47.96.161.183
 Source Database       : dev_xn_sjd

 Target Server Version : 50633
 File Encoding         : utf-8

 Date: 10/05/2018 00:38:32 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tb_tool`
-- ----------------------------
DROP TABLE IF EXISTS `tb_tool`;
CREATE TABLE `tb_tool` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `type` varchar(32) DEFAULT NULL COMMENT '分类',
  `pic` text COMMENT '图片',
  `price` decimal(64,0) DEFAULT NULL COMMENT '价格',
  `description` text COMMENT '描述',
  `validity_term` int(11) DEFAULT NULL COMMENT '有效时长(单位小时)',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（0上架/1下架）',
  `order_no` varchar(32) DEFAULT NULL COMMENT '序号',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_tool_order`
-- ----------------------------
DROP TABLE IF EXISTS `tb_tool_order`;
CREATE TABLE `tb_tool_order` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `tool_code` varchar(32) DEFAULT NULL COMMENT '道具编号',
  `tool_name` varchar(32) DEFAULT NULL COMMENT '道具名称',
  `tool_pic` varchar(255) DEFAULT NULL COMMENT '道具图片',
  `price` decimal(64,0) DEFAULT NULL COMMENT '购买价格',
  `validity_term` int(11) DEFAULT NULL COMMENT '购买时长(单位小时)',
  `user_id` varchar(32) DEFAULT NULL COMMENT '购买人',
  `create_datetime` datetime DEFAULT NULL COMMENT '购买时间',
  `status` varchar(32) DEFAULT NULL COMMENT '状态（0未使用/1已使用）',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_tool_use_record`
-- ----------------------------
DROP TABLE IF EXISTS `tb_tool_use_record`;
CREATE TABLE `tb_tool_use_record` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `tool_code` varchar(32) DEFAULT NULL COMMENT '道具编号',
  `tool_order_code` varchar(32) DEFAULT NULL COMMENT '道具订单编号',
  `adopt_tree_code` varchar(32) DEFAULT NULL COMMENT '认养权编号',
  `tool_type` varchar(4) DEFAULT NULL COMMENT '道具类型（0保护罩/1一件收取）',
  `status` varchar(32) DEFAULT NULL COMMENT '状态(1生效中 2已失效)',
  `user_id` varchar(32) DEFAULT NULL COMMENT '使用人',
  `create_datetime` datetime DEFAULT NULL COMMENT '使用时间',
  `invalid_datetime` datetime DEFAULT NULL COMMENT '失效时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `try_adopt_order`
-- ----------------------------
DROP TABLE IF EXISTS `try_adopt_order`;
CREATE TABLE `try_adopt_order` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '订单类型（1个人/2定向/3捐赠）',
  `product_code` varchar(32) DEFAULT NULL COMMENT '认养产品编号',
  `product_specs_name` varchar(255) DEFAULT NULL COMMENT '规格名称',
  `price` decimal(64,0) DEFAULT NULL COMMENT '认养价格',
  `start_datetime` datetime DEFAULT NULL COMMENT '认养开始时间',
  `end_datetime` datetime DEFAULT NULL COMMENT '认养结束时间',
  `quantity` int(11) DEFAULT '0' COMMENT '数量',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '金额',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '下单人编号',
  `apply_datetime` datetime DEFAULT NULL COMMENT '下单时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（0待支付1已取消2待认养3认养中4已到期）',
  `pay_type` varchar(4) DEFAULT NULL COMMENT '支付方式',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付渠道编号',
  `cny_deduct_amount` decimal(64,0) DEFAULT NULL COMMENT '人民币抵扣金额',
  `jf_deduct_amount` decimal(64,0) DEFAULT NULL COMMENT '积分抵扣金额',
  `pay_amount` decimal(64,0) DEFAULT NULL COMMENT '支付金额',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `back_jf_amount` decimal(64,0) DEFAULT NULL COMMENT '积分返点金额',
  `settle_status` varchar(32) DEFAULT NULL COMMENT '结算状态(0 不结算 1 待结算 2 已结算)',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人/定向/捐赠认养订单';

-- ----------------------------
--  Table structure for `try_adopt_order_tree`
-- ----------------------------
DROP TABLE IF EXISTS `try_adopt_order_tree`;
CREATE TABLE `try_adopt_order_tree` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `order_type` varchar(4) DEFAULT NULL COMMENT '订单类型',
  `order_code` varchar(32) DEFAULT NULL COMMENT '认养订单编号',
  `parent_category_code` varchar(32) DEFAULT NULL COMMENT '产品大类',
  `category_code` varchar(32) DEFAULT NULL COMMENT '分类编号',
  `owner_id` varchar(32) DEFAULT NULL COMMENT '产权方编号',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `tree_number` varchar(32) DEFAULT NULL COMMENT '古树编号',
  `start_datetime` datetime DEFAULT NULL COMMENT '认养开始时间',
  `end_datetime` datetime DEFAULT NULL COMMENT '认养结束时间',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '认养金额',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `current_holder` varchar(32) DEFAULT NULL COMMENT '当前持有人',
  `certificate_template` varchar(255) DEFAULT NULL COMMENT '证书模板',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='认养权';

-- ----------------------------
--  Table structure for `try_group_adopt_order`
-- ----------------------------
DROP TABLE IF EXISTS `try_group_adopt_order`;
CREATE TABLE `try_group_adopt_order` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `identify_code` varchar(32) DEFAULT NULL COMMENT '识别码',
  `product_code` varchar(32) DEFAULT NULL COMMENT '认养产品编号',
  `product_specs_code` varchar(32) DEFAULT NULL COMMENT '规格编号',
  `product_specs_name` varchar(255) DEFAULT NULL COMMENT '规格名称',
  `price` decimal(64,0) DEFAULT NULL COMMENT '认养价格',
  `year` float DEFAULT NULL COMMENT '认养年限',
  `start_datetime` datetime DEFAULT NULL COMMENT '认养开始时间',
  `end_datetime` datetime DEFAULT NULL COMMENT '认养结束时间',
  `quantity` int(11) DEFAULT '0' COMMENT '数量',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '金额',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '下单人编号',
  `apply_datetime` datetime DEFAULT NULL COMMENT '下单时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `pay_type` varchar(4) DEFAULT NULL COMMENT '支付方式',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付渠道编号',
  `pay_amount` decimal(64,0) DEFAULT NULL COMMENT '支付金额',
  `jf_deduct_amount` decimal(64,0) DEFAULT NULL COMMENT '积分抵扣金额',
  `cny_deduct_amount` decimal(64,0) DEFAULT NULL COMMENT '抵扣人民币金额',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `back_jf_amount` decimal(64,0) DEFAULT NULL COMMENT '积分返点金额',
  `settle_status` varchar(4) DEFAULT NULL COMMENT '结算状态(0 不结算 1 待结算 2 已结算)',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='集体认养订单';

-- ----------------------------
--  Table structure for `tsj_article`
-- ----------------------------
DROP TABLE IF EXISTS `tsj_article`;
CREATE TABLE `tsj_article` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `adopt_tree_code` varchar(32) DEFAULT NULL COMMENT '认养权编号',
  `tree_no` varchar(32) DEFAULT NULL COMMENT '树的编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型（1平台、2用户）',
  `open_level` varchar(4) DEFAULT NULL COMMENT '公开程度（1公开/2私密/3仅好友可见）',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `photo` text COMMENT '照片',
  `collect_count` int(11) DEFAULT NULL COMMENT '收藏总数',
  `point_count` int(11) DEFAULT NULL COMMENT '点赞总数',
  `read_count` int(11) DEFAULT NULL COMMENT '阅读总数',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（0保存/1发布/2下架/3审核）',
  `location` varchar(32) DEFAULT NULL COMMENT 'UI位置',
  `order_no` int(11) DEFAULT NULL COMMENT 'UI次序',
  `publish_user_id` varchar(32) DEFAULT NULL COMMENT '发布人',
  `publish_datetime` datetime DEFAULT NULL COMMENT '发布时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datatime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章';

-- ----------------------------
--  Table structure for `tsj_carbon_bubble_order`
-- ----------------------------
DROP TABLE IF EXISTS `tsj_carbon_bubble_order`;
CREATE TABLE `tsj_carbon_bubble_order` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `adopt_tree_code` varchar(32) DEFAULT NULL COMMENT '认养权编号',
  `adopt_user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `create_datetime` datetime DEFAULT NULL COMMENT '生成时间',
  `invalid_datetime` datetime DEFAULT NULL COMMENT '过期时间',
  `quantity` int(11) DEFAULT NULL COMMENT '碳泡泡数量',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（0待收取1已收取2已过期）',
  `taker` varchar(32) DEFAULT NULL COMMENT '收取人',
  `take_datetime` datetime DEFAULT NULL COMMENT '收取时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='碳泡泡产生订单';

-- ----------------------------
--  Table structure for `tsj_gift_order`
-- ----------------------------
DROP TABLE IF EXISTS `tsj_gift_order`;
CREATE TABLE `tsj_gift_order` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `to_user` varchar(32) DEFAULT NULL COMMENT '被赠送人' ,
  `adopt_tree_code` varchar(32) DEFAULT NULL COMMENT '认养权编号',
  `name` varchar(255) DEFAULT NULL COMMENT '礼物名称',
  `price` decimal(64,0) DEFAULT NULL COMMENT '礼物价格',
  `list_pic` varchar(255) DEFAULT NULL COMMENT '列表图片',
  `description` TEXT DEFAULT NULL COMMENT '礼物描述',
  `receiver` varchar(255) DEFAULT NULL COMMENT '收货人',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '区' ,
  `re_address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `re_mobile` varchar(32) DEFAULT NULL COMMENT '收货人手机号',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（0待认领/1已认领）',
  `create_datetime` datetime DEFAULT NULL COMMENT '产生时间',
  `claimer` varchar(255) DEFAULT NULL COMMENT '认领人',
  `claim_datetime` datetime DEFAULT NULL COMMENT '认领时间',
  `invalid_datetime` datetime DEFAULT NULL COMMENT '失效时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='礼物订单';

-- ----------------------------
--  Table structure for `tsj_give_carbon_bubble_record`
-- ----------------------------
DROP TABLE IF EXISTS `tsj_give_carbon_bubble_record`;
CREATE TABLE `tsj_give_carbon_bubble_record` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `quantity` int(11) DEFAULT NULL COMMENT '赠送数量',
  `user_id` varchar(32) DEFAULT NULL COMMENT '赠送人',
  `to_user_id` varchar(32) DEFAULT NULL COMMENT '被赠送人',
  `create_datetime` datetime DEFAULT NULL COMMENT '赠送时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsj_give_tree_record`
-- ----------------------------
DROP TABLE IF EXISTS `tsj_give_tree_record`;
CREATE TABLE `tsj_give_tree_record` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `adopt_tree_code` varchar(32) DEFAULT NULL COMMENT '认养权编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '赠送人',
  `to_user_id` varchar(32) DEFAULT NULL COMMENT '被赠送人',
  `create_datetime` datetime DEFAULT NULL COMMENT '赠送时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='赠送树记录';

-- ----------------------------
--  Table structure for `tsj_interact`
-- ----------------------------
DROP TABLE IF EXISTS `tsj_interact`;
CREATE TABLE `tsj_interact` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型（0点赞/1收藏）',
  `object_type` varchar(4) DEFAULT NULL COMMENT '对象类型（0文章/1树）',
  `object_code` varchar(32) DEFAULT NULL COMMENT '对象编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsj_share_record`
-- ----------------------------
DROP TABLE IF EXISTS `tsj_share_record`;
CREATE TABLE `tsj_share_record` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '分享人',
  `channel` varchar(4) DEFAULT NULL COMMENT '分享渠道(0微信/1朋友圈)',
  `create_datetime` datetime DEFAULT NULL COMMENT '分享时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsj_visitor`
-- ----------------------------
DROP TABLE IF EXISTS `tsj_visitor`;
CREATE TABLE `tsj_visitor` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `adopt_tree_code` varchar(32) DEFAULT NULL COMMENT '认养权编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '来访人id',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_datetime` datetime DEFAULT NULL COMMENT '来访时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='来访人';

-- ----------------------------
--  Table structure for `tstd_account`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_account`;
CREATE TABLE `tstd_account` (
  `account_number` varchar(32) NOT NULL COMMENT '账户编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `currency` varchar(32) DEFAULT NULL COMMENT '币种',
  `type` varchar(4) DEFAULT NULL COMMENT '类别（B端账号，C端账号，平台账号）',
  `status` varchar(2) DEFAULT NULL COMMENT '状态（正常/程序冻结/人工冻结）',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '余额',
  `total_amount` decimal(64,0) DEFAULT NULL COMMENT '累计加的金额',
  `frozen_amount` decimal(64,0) DEFAULT NULL COMMENT '冻结金额',
  `md5` varchar(32) DEFAULT NULL COMMENT 'MD5',
  `in_amount` decimal(64,0) DEFAULT '0' COMMENT '总充值金额（入金）',
  `out_amount` decimal(64,0) DEFAULT '0' COMMENT '总取现金额（出金）',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `last_order` varchar(32) DEFAULT NULL COMMENT '最近一次变动对应的流水编号',
  PRIMARY KEY (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户';

-- ----------------------------
--  Table structure for `tstd_bankcard`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_bankcard`;
CREATE TABLE `tstd_bankcard` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `bankcard_number` varchar(64) DEFAULT NULL COMMENT '银行卡编号',
  `bank_code` varchar(32) DEFAULT NULL COMMENT '银行行别',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '银行名称',
  `subbranch` varchar(255) DEFAULT NULL COMMENT '开户支行',
  `bind_mobile` varchar(32) DEFAULT NULL COMMENT '银行卡绑定手机号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `amount` bigint(32) DEFAULT NULL COMMENT '余额',
  `frozen_amount` bigint(32) DEFAULT NULL COMMENT '冻结金额',
  `md5` varchar(32) DEFAULT NULL COMMENT 'MD5',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `last_order` varchar(32) DEFAULT NULL COMMENT '最近一次变动对应的流水编号',
  `system_code` varchar(32) NOT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Table structure for `tstd_charge`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_charge`;
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
  `apply_user_type` varchar(32) DEFAULT NULL COMMENT '申请人类型',
  `apply_note` varchar(255) DEFAULT NULL COMMENT '申请说明',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_user` varchar(32) DEFAULT NULL COMMENT '支付回录人',
  `pay_note` varchar(255) DEFAULT NULL COMMENT '支付渠道说明',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `channel_order` varchar(255) DEFAULT NULL COMMENT '支付渠道单号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值订单';

-- ----------------------------
--  Table structure for `tstd_jour`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_jour`;
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

-- ----------------------------
--  Table structure for `tstd_read`
-- ----------------------------
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

-- ----------------------------
--  Table structure for `tstd_sign_log`
-- ----------------------------
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

-- ----------------------------
--  Table structure for `tstd_sms`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_sms`;
CREATE TABLE `tstd_sms` (
  `code` varchar(32) NOT NULL COMMENT '消息编号',
  `type` varchar(4) DEFAULT NULL COMMENT '消息类型（系统公告，短信内容）',
  `object` varchar(4) DEFAULT NULL COMMENT '对象类型(C:C端用户/O:产权方/M:养护方/A:代理商/P:平台方)',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `status` varchar(4) DEFAULT NULL COMMENT '状态 0-草稿 1-已发送 2-已回撤',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` varchar(45) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_user`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_user`;
CREATE TABLE `tstd_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登陆名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `kind` char(1) DEFAULT NULL COMMENT '用户类型（C 普通用户，D 渠道商）',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `login_pwd` varchar(32) DEFAULT NULL COMMENT '登陆密码',
  `login_pwd_strength` char(1) DEFAULT NULL COMMENT '登陆密码强度',
  `level` varchar(4) DEFAULT NULL COMMENT '用户等级',
  `user_referee_type` varchar(32) DEFAULT NULL COMMENT '推荐人类型',
  `user_referee` varchar(32) DEFAULT NULL COMMENT '推荐人',
  `agent_id` varchar(32) DEFAULT NULL COMMENT '所属代理商',
  `id_kind` char(1) DEFAULT NULL COMMENT '证件类型',
  `id_no` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `trade_pwd` varchar(32) DEFAULT NULL COMMENT '安全密码',
  `trade_pwd_strength` char(1) DEFAULT NULL COMMENT '安全密码强度',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `union_id` varchar(255) DEFAULT NULL COMMENT '联合编号',
  `h5_open_id` varchar(255) DEFAULT NULL COMMENT '公众号开放编号',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `longitude` varchar(255) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(255) DEFAULT NULL COMMENT '维度',
  `create_datetime` datetime DEFAULT NULL COMMENT '注册时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='C端用户';

-- ----------------------------
--  Table structure for `tstd_user_ext`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_user_ext`;
CREATE TABLE `tstd_user_ext` (
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `gender` char(1) DEFAULT NULL COMMENT '性别(1 男 0 女)',
  `introduce` varchar(255) DEFAULT NULL COMMENT '自我介绍',
  `birthday` varchar(16) DEFAULT NULL COMMENT '生日',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `driver_li` varchar(255) DEFAULT NULL COMMENT '驾驶照',
  `passport` varchar(255) DEFAULT NULL COMMENT '护照',
  `diploma` varchar(4) DEFAULT NULL COMMENT '学位',
  `occupation` varchar(64) DEFAULT NULL COMMENT '职业',
  `grad_datetime` datetime DEFAULT NULL COMMENT '毕业时间',
  `work_time` varchar(4) DEFAULT NULL COMMENT '工作年限',
  `pdf` varchar(255) DEFAULT NULL COMMENT '用户资料',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tstd_withdraw`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_withdraw`;
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
  `apply_user_type` varchar(32) DEFAULT NULL COMMENT '申请人类型',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='取现订单';

-- ----------------------------
--  Table structure for `tsys_agent_user`
-- ----------------------------
DROP TABLE IF EXISTS `tsys_agent_user`;
CREATE TABLE `tsys_agent_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型（0代理商/1业务员）',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登陆名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `login_pwd` varchar(32) DEFAULT NULL COMMENT '登陆密码',
  `login_pwd_strength` char(1) DEFAULT NULL COMMENT '登陆密码强度',
  `trade_pwd` varchar(32) DEFAULT NULL COMMENT '安全密码',
  `trade_pwd_strength` char(1) DEFAULT NULL COMMENT '安全密码强度',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_datetime` datetime DEFAULT NULL COMMENT '注册时间',
  `status` varchar(2) DEFAULT NULL COMMENT '状态(0待审核/1审核不通过/2正常/3已注销)',
  `user_referee` varchar(32) DEFAULT NULL COMMENT '推荐人',
  `level` varchar(32) DEFAULT NULL COMMENT '等级',
  `parent_user_id` varchar(32) DEFAULT NULL COMMENT '上级用户编号',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色编号',
  `updater` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理用户';

-- ----------------------------
--  Table structure for `tsys_apply_bind_maintain`
-- ----------------------------
DROP TABLE IF EXISTS `tsys_apply_bind_maintain`;
CREATE TABLE `tsys_apply_bind_maintain` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `owner_id` varchar(32) DEFAULT NULL COMMENT '产权方编号',
  `maintain_id` varchar(32) DEFAULT NULL COMMENT '养护方编号',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（1审核2审核不通过3已绑定4已解除）',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='申请绑定养护方';

-- ----------------------------
--  Table structure for `tsys_biz_log`
-- ----------------------------
DROP TABLE IF EXISTS `tsys_biz_log`;
CREATE TABLE `tsys_biz_log` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `adopt_tree_code` varchar(32) DEFAULT NULL COMMENT '认养权编号',
  `adopt_user_id` varchar(32) DEFAULT NULL COMMENT '认养用户编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型（1赠送/2留言/3收取碳泡泡）',
  `quantity` int(1) DEFAULT NULL COMMENT '数量',
  `note` varchar(255) DEFAULT NULL COMMENT '说明',
  `user_id` varchar(32) DEFAULT NULL COMMENT '操作人',
  `create_datetime` datetime DEFAULT NULL COMMENT '产生时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志';

-- ----------------------------
--  Table structure for `tsys_cnavigate`
-- ----------------------------
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
  `parent_code` varchar(32) DEFAULT NULL COMMENT '父编号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsys_company`
-- ----------------------------
DROP TABLE IF EXISTS `tsys_company`;
CREATE TABLE `tsys_company` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `charger` varchar(255) DEFAULT NULL COMMENT '负责人',
  `charge_mobile` varchar(32) DEFAULT NULL COMMENT '负责人联系方式',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `bussiness_license` text COMMENT '营业执照',
  `organization_code` varchar(255) DEFAULT NULL COMMENT '组织机构代码',
  `certificate_template` text COMMENT '证书模板',
  `contract_template` text COMMENT '合同模板',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsys_config`
-- ----------------------------
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

-- ----------------------------
--  Table structure for `tsys_dict`
-- ----------------------------
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

-- ----------------------------
--  Table structure for `tsys_maintain_project`
-- ----------------------------
DROP TABLE IF EXISTS `tsys_maintain_project`;
CREATE TABLE `tsys_maintain_project` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `maintain_id` varchar(32) DEFAULT NULL COMMENT '养护方编号',
  `project_name` varchar(32) DEFAULT NULL COMMENT '项目名称',
  `description` text COMMENT '描述',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsys_maintain_record`
-- ----------------------------
DROP TABLE IF EXISTS `tsys_maintain_record`;
CREATE TABLE `tsys_maintain_record` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `maintain` varchar(32) DEFAULT NULL COMMENT '养护方',
  `tree_number` varchar(32) DEFAULT NULL COMMENT '古树编号',
  `project_code` varchar(32) DEFAULT NULL COMMENT '养护项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '养护项目名称',
  `maintainer_code` varchar(32) DEFAULT NULL COMMENT '养护人编号',
  `maintainer_name` varchar(32) DEFAULT NULL COMMENT '养护人姓名',
  `pic` text COMMENT '照片',
  `description` text COMMENT '描述',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsys_maintainer`
-- ----------------------------
DROP TABLE IF EXISTS `tsys_maintainer`;
CREATE TABLE `tsys_maintainer` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `maintain_id` varchar(32) DEFAULT NULL COMMENT '养护方编号',
  `name` varchar(32) DEFAULT NULL COMMENT '养护人姓名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '养护人电话',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsys_menu`
-- ----------------------------
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

-- ----------------------------
--  Table structure for `tsys_menu_role`
-- ----------------------------
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsys_role`
-- ----------------------------
DROP TABLE IF EXISTS `tsys_role`;
CREATE TABLE `tsys_role` (
  `code` varchar(32) NOT NULL DEFAULT '' COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `type` varchar(4) DEFAULT NULL COMMENT '类型(C:C端用户/O:产权方/M:养护方/A:代理商/P:平台方)',
  `updater` varchar(32) DEFAULT NULL COMMENT '最近修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最近修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsys_settle`
-- ----------------------------
DROP TABLE IF EXISTS `tsys_settle`;
CREATE TABLE `tsys_settle` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `user_kind` varchar(4) DEFAULT NULL COMMENT '用户种类',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '结算金额',
  `rate` decimal(64,0) DEFAULT NULL COMMENT '结算比例',
  `ref_type` varchar(4) DEFAULT NULL COMMENT '参考类型',
  `ref_code` varchar(32) DEFAULT NULL COMMENT '参考订单编号',
  `ref_note` varchar(255) DEFAULT NULL COMMENT '参考说明',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `handler` varchar(32) DEFAULT NULL COMMENT '处理人',
  `handle_datetime` datetime DEFAULT NULL COMMENT '处理时间',
  `handle_note` varchar(255) DEFAULT NULL COMMENT '处理说明',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsys_user`
-- ----------------------------
DROP TABLE IF EXISTS `tsys_user`;
CREATE TABLE `tsys_user` (
  `user_id` varchar(32) NOT NULL,
  `kind` varchar(4) DEFAULT NULL COMMENT '类型',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登录名',
  `login_pwd` varchar(32) DEFAULT NULL COMMENT '登录密码',
  `login_pwd_strength` char(1) DEFAULT NULL COMMENT '登录密码强度',
  `trade_pwd` varchar(32) DEFAULT NULL COMMENT '安全密码',
  `trade_pwd_strength` char(1) DEFAULT NULL COMMENT '安全密码强度',
  `create_datetime` datetime DEFAULT NULL COMMENT '注册时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（1待审核2合伙中3已解除合伙4已注销）',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
--  Table structure for `tzb_category`
-- ----------------------------
DROP TABLE IF EXISTS `tzb_category`;
CREATE TABLE `tzb_category` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '上级编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `pic` text COMMENT '图片',
  `order_no` int(11) DEFAULT '0' COMMENT '顺序',
  `status` varchar(4) DEFAULT NULL COMMENT '状态(0待上架/1已上架)',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tzb_product`
-- ----------------------------
DROP TABLE IF EXISTS `tzb_product`;
CREATE TABLE `tzb_product` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `sell_type` varchar(4) DEFAULT NULL COMMENT '销售分类（0个人/1定向/2捐赠/3集体）',
  `direct_type` varchar(32) DEFAULT NULL COMMENT '定向类型(1=等级 2=个人)',
  `direct_object` varchar(255) DEFAULT NULL COMMENT '定向对象',
  `owner_id` varchar(32) DEFAULT NULL COMMENT '产权方编号',
  `parent_category_code` varchar(32) DEFAULT NULL COMMENT '产品大类',
  `category_code` varchar(32) DEFAULT NULL COMMENT '产品分类',
  `list_pic` text COMMENT '列表图片',
  `banner_pic` text COMMENT '详情banner图',
  `origin_place` varchar(255) DEFAULT NULL COMMENT '产地',
  `scientific_name` varchar(255) DEFAULT NULL COMMENT '学名',
  `variety` varchar(255) DEFAULT NULL COMMENT '品种',
  `rank` varchar(32) DEFAULT NULL COMMENT '级别',
  `description` text COMMENT '产品描述',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '区',
  `town` varchar(32) DEFAULT NULL COMMENT '镇',
  `raise_start_datetime` datetime DEFAULT NULL COMMENT '募集开始时间',
  `raise_end_datetime` datetime DEFAULT NULL COMMENT '募集结束时间',
  `raise_count` int(11) DEFAULT '0' COMMENT '募集总量',
  `now_count` int(11) DEFAULT '0' COMMENT '已募集数量',
  `identify_code` varchar(32) DEFAULT NULL COMMENT '当前识别码',
  `id_invalid_datetime` datetime DEFAULT NULL COMMENT '识别码失效时间',
  `specs_code` varchar(32) DEFAULT NULL COMMENT '当前集体订单规格',
  `location` varchar(32) DEFAULT NULL COMMENT 'UI位置',
  `order_no` int(11) DEFAULT '0' COMMENT 'UI次序',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（0草稿/1已提交待审核/2审核不通过/3审核通过待上架/4已上架待认养/5已锁定/7已认养可下架/8已下架）',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品';

-- ----------------------------
--  Table structure for `tzb_product_specs`
-- ----------------------------
DROP TABLE IF EXISTS `tzb_product_specs`;
CREATE TABLE `tzb_product_specs` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '规格名称',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `price` decimal(64,0) DEFAULT NULL COMMENT '认养价格',
  `start_datetime` datetime DEFAULT NULL COMMENT '认养开始时间',
  `end_datetime` datetime DEFAULT NULL COMMENT '认养结束时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品规格';

-- ----------------------------
--  Table structure for `tzb_tree`
-- ----------------------------
DROP TABLE IF EXISTS `tzb_tree`;
CREATE TABLE `tzb_tree` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `product_type` varchar(4) DEFAULT NULL COMMENT '产品类型',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `cur_order_code` varchar(32) DEFAULT NULL COMMENT '当前订单编号',
  `owner_id` varchar(32) DEFAULT NULL COMMENT '产权方编号',
  `tree_number` varchar(255) DEFAULT NULL COMMENT '树木编号',
  `age` int(11) DEFAULT '0' COMMENT '树龄',
  `origin_place` varchar(255) DEFAULT NULL COMMENT '产地',
  `scientific_name` varchar(255) DEFAULT NULL COMMENT '学名',
  `variety` varchar(255) DEFAULT NULL COMMENT '品种',
  `rank` varchar(32) DEFAULT NULL COMMENT '级别',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '区',
  `town` varchar(32) DEFAULT NULL COMMENT '镇',
  `longitude` varchar(32) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(32) DEFAULT NULL COMMENT '维度',
  `pic` text COMMENT '实景图',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（0待认养/1已认养）',
  `article_count` int(11) DEFAULT '0' COMMENT '文章数',
  `point_count` int(11) DEFAULT '0' COMMENT '点赞数',
  `collection_count` int(11) DEFAULT '0' COMMENT '收藏数',
  `adopt_count` int(11) DEFAULT '0' COMMENT '认养次数',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tsys_channel_bank`
-- ----------------------------
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

-- ----------------------------
--  Table structure for `tstd_user_relation`
-- ----------------------------
DROP TABLE IF EXISTS `tstd_user_relation`;
CREATE TABLE `tstd_user_relation` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `to_user` varchar(32) DEFAULT NULL COMMENT '关系人编号',
  `type` varchar(4) DEFAULT NULL COMMENT '关系类型',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tstd_address`;
CREATE TABLE `tstd_address` (
  `code` varchar(32) NOT NULL COMMENT '收件编号',
  `addressee` varchar(64) DEFAULT NULL COMMENT '收件人姓名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `province` varchar(64) DEFAULT NULL COMMENT '省份',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `district` varchar(64) DEFAULT NULL COMMENT '区',
  `detail_address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `is_default` char(1) DEFAULT NULL COMMENT '是否默认地址',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tys_presell_product`;
CREATE TABLE `tys_presell_product` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `owner_id` varchar(32) DEFAULT NULL COMMENT '产权方编号',
  `category_code` varchar(32) DEFAULT NULL COMMENT '产品分类',
  `list_pic` text DEFAULT NULL COMMENT '列表图片',

  `banner_pic` text DEFAULT NULL COMMENT '详情banner图',
  `description` text DEFAULT NULL COMMENT '图文详情',
  `origin_place` varchar(255) DEFAULT NULL COMMENT '产地',
  `scientific_name` varchar(255) DEFAULT NULL COMMENT '学名',
  `variety` varchar(255) DEFAULT NULL COMMENT '品种',

  `rank` varchar(32) DEFAULT NULL COMMENT '级别',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '区',
  `town` varchar(32) DEFAULT NULL COMMENT '镇',

  `longitude` varchar(32) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(32) DEFAULT NULL COMMENT '维度',
  `single_output` int DEFAULT NULL COMMENT '单颗树产量',
  `pack_unit` varchar(32) DEFAULT NULL COMMENT '包装单位',
  `pack_weight` FLOAT DEFAULT NULL COMMENT '包装重量',

  `tree_count` int DEFAULT NULL COMMENT '树木数量',
  `total_output` int DEFAULT NULL COMMENT '产出总量',
  `adopt_start_datetime` datetime DEFAULT NULL COMMENT '认养开始时间',
  `adopt_end_datetime` datetime DEFAULT NULL COMMENT '认养结束时间',
  `adopt_year` int DEFAULT 0 COMMENT '认养年限',

  `harvest_datetime` datetime DEFAULT NULL COMMENT '收货时间',
  `now_count` int DEFAULT 0 COMMENT '已预售数量',
  `location` varchar(32) DEFAULT NULL COMMENT 'UI位置',
  `order_no` int DEFAULT 0 COMMENT 'UI次序',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',

  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tys_presell_specs`;
CREATE TABLE `tys_presell_specs` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `name` varchar(255) DEFAULT NULL COMMENT '规格名称',
  `pack_count` int DEFAULT 0 COMMENT '包装数量',
  `price` decimal(18,8) DEFAULT NULL COMMENT '价格',
  `increase` FLOAT DEFAULT NULL COMMENT '每小时涨幅',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tys_presell_order`;
CREATE TABLE `tys_presell_order` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `specs_code` varchar(32) DEFAULT NULL COMMENT '规格编号',
  `specs_name` varchar(255) DEFAULT NULL COMMENT '规格名称',

  `price` decimal(18,8) DEFAULT NULL COMMENT '价格',
  `quantity` int DEFAULT 0 COMMENT '数量',
  `amount` decimal(18,8) DEFAULT NULL COMMENT '金额',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '下单人编号',
  `apply_datetime` datetime DEFAULT NULL COMMENT '下单时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',

  `pay_type` varchar(4) DEFAULT NULL COMMENT '支付方式',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付渠道编号',
  `cny_deduct_amount` decimal(18,8) DEFAULT NULL COMMENT '抵扣人民币',
  `jf_deduct_amount` decimal(18,8) DEFAULT NULL COMMENT '积分抵扣金额',
  `back_jf_amount` decimal(18,8) DEFAULT NULL COMMENT '积分返点金额',

  `pay_amount` decimal(18,8) DEFAULT NULL COMMENT '支付金额',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `settle_status` varchar(4) DEFAULT NULL COMMENT '结算状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tys_original_group`;
CREATE TABLE `tys_original_group` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `order_code` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `owner_id` varchar(32) DEFAULT NULL COMMENT '归属人',

  `price` decimal(18,8) DEFAULT NULL COMMENT '单价', 
  `quantity` int DEFAULT 0 COMMENT '数量',
  `presell_quantity` int DEFAULT 0 COMMENT '挂单数量',
  `unit` varchar(32) DEFAULT NULL COMMENT '单位',
  `adopt_start_datetime` datetime DEFAULT NULL COMMENT '认养开始时间',
  `adopt_end_datetime` datetime DEFAULT NULL COMMENT '认养结束时间',
  
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tys_presell_inventory`;
CREATE TABLE `tys_presell_inventory` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `group_type` varchar(4) DEFAULT NULL COMMENT '组类型',
  `group_code` varchar(32) DEFAULT NULL COMMENT '组编号',
  `tree_number` varchar(32) DEFAULT NULL COMMENT '树木编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tys_derive_group`;
CREATE TABLE `tys_derive_group` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `original_code` varchar(32) DEFAULT NULL COMMENT '原生组编号',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `type` varchar(4) DEFAULT NULL COMMENT '类型（定向/二维码/挂单）',

  `price` decimal(18,8) DEFAULT NULL COMMENT '单价', 
  `quantity` int DEFAULT 0 COMMENT '数量',
  `unit` varchar(32) DEFAULT NULL COMMENT '单位',
  `creater` varchar(32) DEFAULT NULL COMMENT '挂单人',
  `create_datetime` datetime DEFAULT NULL COMMENT '挂单时间',

  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `claimant` varchar(32) DEFAULT NULL COMMENT '成交人',
  `claim_datetime` datetime DEFAULT NULL COMMENT '成交时间',
  `wave` FLOAT DEFAULT NULL COMMENT '波动幅度',
  `url` varchar(255) DEFAULT NULL COMMENT '分享链接',

  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tys_presell_logistics`;
CREATE TABLE `tys_presell_logistics` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `original_group_code` varchar(32) DEFAULT NULL COMMENT '原生组编号',
  `logistics_company` varchar(4) DEFAULT NULL COMMENT '物流公司',
  `logistics_number` varchar(255) DEFAULT NULL COMMENT '物流单号',
  `deliver` varchar(32) DEFAULT null COMMENT '发货人',

  `deliver_count` int DEFAULT 0 COMMENT '发货数量',
  `deliver_datetime` datetime DEFAULT NULL COMMENT '发货时间',
  `receiver` varchar(32) DEFAULT NULL COMMENT '收货人',
  `receiver_mobile` varchar(32) DEFAULT NULL COMMENT '收货人手机号',
  `receiver_datetime` datetime DEFAULT NULL COMMENT '收货时间',

  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
