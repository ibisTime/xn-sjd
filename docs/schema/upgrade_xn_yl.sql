##0.0.1 1/24发包
ALTER TABLE `tsj_article` 
ADD COLUMN `is_top` VARCHAR(4) NULL COMMENT '是否置顶' AFTER `order_no`;

ALTER TABLE `tstd_user` 
ADD COLUMN `friend_count` INT DEFAULT 0 COMMENT '好友数量' AFTER `agent_id`;

ALTER TABLE `tstd_sms` 
ADD COLUMN `user_id` VARCHAR(32) NULL COMMENT '消息用户' AFTER `object`;

ALTER TABLE `tsc_commodity` 
ADD COLUMN `original_place` VARCHAR(255) NULL COMMENT '原产地' AFTER `original_price`;

ALTER TABLE `tsc_cart` 
ADD COLUMN `status` VARCHAR(4) NULL COMMENT '状态' AFTER `amount`;

ALTER TABLE `tsys_company` 
ADD COLUMN `common_seal` TEXT NULL COMMENT '公章' AFTER `contract_template`;

##0.0.2
update tstd_user_relation set status = 1 where user_id = to_user;

ALTER TABLE `tzb_product` 
ADD COLUMN `age` INT NULL COMMENT '树龄' AFTER `now_count`;

ALTER TABLE `tzb_product` 
CHANGE COLUMN `description` `description` LONGTEXT NULL DEFAULT NULL COMMENT '产品描述' ;

##0.0.3
ALTER TABLE `try_adopt_order_tree` 
ADD COLUMN `contract` LONGTEXT NULL COMMENT '协议' AFTER `certificate_template`;

##0.0.4
ALTER TABLE `tsc_commodity_order_detail` 
CHANGE COLUMN `cny_deduct_amount` `cny_deduct_amount` DECIMAL(64,0) NULL DEFAULT 0 COMMENT '抵扣人民币金额' ,
CHANGE COLUMN `jf_deduct_amount` `jf_deduct_amount` DECIMAL(64,0) NULL DEFAULT 0 COMMENT '抵扣积分金额' ,
CHANGE COLUMN `back_jf_amount` `back_jf_amount` DECIMAL(64,0) NULL DEFAULT 0 COMMENT '返积分金额' ;

##0.0.5
ALTER TABLE `tsc_commodity` 
ADD COLUMN `approve_note` VARCHAR(255) NULL COMMENT '审核备注' AFTER `remark`,
ADD COLUMN `single_postage_count` INT DEFAULT NULL COMMENT '单邮费数量';

ALTER TABLE `tsc_after_sale` 
ADD COLUMN `refund_reason` varchar(32) DEFAULT NULL COMMENT '退款原因',
ADD COLUMN `message` TEXT DEFAULT NULL COMMENT '留言';

ALTER TABLE `tsc_commodity_order_detail` 
ADD COLUMN `pay_type` varchar(4) DEFAULT NULL COMMENT '支付方式';

DROP TABLE IF EXISTS `tstd_search_history`;
CREATE TABLE `tstd_search_history` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `type` varchar(4) DEFAULT NULL COMMENT '分类',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `search_datetime` datetime DEFAULT NULL COMMENT '搜索时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsj_steal_carbon_bubble_record`;
CREATE TABLE `tsj_steal_carbon_bubble_record` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `steal_user_id` varchar(32) DEFAULT NULL COMMENT '偷取人编号',
  `stealed_user_id` varchar(32) DEFAULT NULL COMMENT '被偷取人编号',
  `adopt_tree_code` varchar(32) DEFAULT NULL COMMENT '认养权编号',
  `carbon_bubble_order_code` varchar(32) DEFAULT NULL COMMENT '碳泡泡订单编号',
  `quantity` varchar(255) DEFAULT NULL COMMENT '数量',
  `steal_datetime` datetime DEFAULT NULL COMMENT '偷取时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_notify_user`;
CREATE TABLE `tsys_notify_user` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `mobile` varchar(32) DEFAULT NULL COMMENT '电话',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_official_seal`;
CREATE TABLE `tsys_official_seal` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `department` varchar(255) DEFAULT NULL COMMENT '部门',
  `pic` varchar(255) DEFAULT NULL COMMENT '公章图片',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
