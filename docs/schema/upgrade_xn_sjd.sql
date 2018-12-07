ALTER TABLE `tzb_product` 
ADD COLUMN `parent_category_code` VARCHAR(32) NULL COMMENT '产品大类' AFTER `owner_id`;

ALTER TABLE `try_adopt_order_tree` 
ADD COLUMN `parent_category_code` VARCHAR(32) NULL COMMENT '产品大类' AFTER `order_code`;

update `tzb_product`  set `raise_count` = (select count(1) from `tzb_tree` where `tzb_tree`.`product_code` = `tzb_product`.code );

ALTER TABLE `try_adopt_order_tree` 
ADD COLUMN `owner_id` VARCHAR(32) NULL COMMENT '产权方编号' AFTER `category_code`;
update try_adopt_order_tree set owner_id = (select owner_id from tzb_product where try_adopt_order_tree.product_code = tzb_product.code);

ALTER TABLE `tsj_gift_order` 
ADD COLUMN `list_pic` VARCHAR(255) NULL COMMENT '列表图片' AFTER `price`;

ALTER TABLE `tsj_gift_order` 
CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL COMMENT '礼物描述' ;

ALTER TABLE `tsj_gift_order` 
ADD COLUMN `province` VARCHAR(32) NULL COMMENT '省' AFTER `receiver`,
ADD COLUMN `city` VARCHAR(32) NULL COMMENT '市' AFTER `province`,
ADD COLUMN `area` VARCHAR(32) NULL COMMENT '区' AFTER `city`;

ALTER TABLE `tsys_maintain_record` 
ADD COLUMN `maintain` VARCHAR(32) NULL COMMENT '养护方' AFTER `code`;

#V1.0.0 6-1
ALTER TABLE `tstd_sms` 
ADD COLUMN `object` VARCHAR(4) NULL COMMENT '对象类型(C:C端用户/O:产权方/M:养护方/A:代理商/P:平台方)' AFTER `type`;

ALTER TABLE `tstd_user` 
ADD COLUMN `union_id` VARCHAR(255) NULL COMMENT '联合编号' AFTER `status`,
ADD COLUMN `h5_open_id` VARCHAR(255) NULL COMMENT '公众号开放编号' AFTER `union_id`;

#V1.0.0 6-3
ALTER TABLE `tsj_gift_order` 
ADD COLUMN `to_user` VARCHAR(32) NULL COMMENT '被赠送人' AFTER `code`;


ALTER TABLE `tb_tool_use_record` 
ADD COLUMN `tool_code` VARCHAR(32) NULL COMMENT '道具编号' AFTER `code`;

#V1.0.0 6-4
ALTER TABLE `try_adopt_order_tree` 
ADD COLUMN `create_datetime` DATETIME NULL COMMENT '创建时间' AFTER `certificate_template`;

#V1.0.0 6-5
ALTER TABLE `try_group_adopt_order` 
ADD COLUMN `product_specs_code` VARCHAR(32) NULL COMMENT '规格编号' AFTER `product_code`;

ALTER TABLE `tzb_product` 
ADD COLUMN `identify_code` VARCHAR(32) NULL COMMENT '当前识别码' AFTER `now_count`,
ADD COLUMN `id_invalid_datetime` DATETIME NULL COMMENT '识别码失效时间' AFTER `identify_code`;

ALTER TABLE `try_group_adopt_order` 
ADD COLUMN `cny_deduct_amount` DECIMAL(64,0) NULL COMMENT '抵扣人民币金额' AFTER `jf_deduct_amount`;

ALTER TABLE `tzb_product` 
ADD COLUMN `specs_code` VARCHAR(32) NULL COMMENT '当前集体订单规格' AFTER `id_invalid_datetime`;

ALTER TABLE `try_group_adopt_order` 
ADD COLUMN `settle_status` VARCHAR(4) NULL COMMENT '结算状态(0 不结算 1 待结算 2 已结算)' AFTER `back_jf_amount`;

#V1.0.0 7-1
ALTER TABLE `try_adopt_order_tree` 
ADD COLUMN `order_type` VARCHAR(4) NULL COMMENT '订单类型' AFTER `code`;
update try_adopt_order_tree set order_type = (select type from try_adopt_order where order_code = try_adopt_order.code) where left(order_code,2) = 'AO';
update try_adopt_order_tree set order_type = 4 where left(order_code,3) = 'GAO';

#V1.0.0 7-2
ALTER TABLE `try_adopt_order` 
ADD COLUMN `product_specs_name` VARCHAR(255) NULL COMMENT '规格名称' AFTER `product_specs_code`;

update tsys_user set company_code = (select code from tsys_company where tsys_company.user_id = tsys_user.user_id);

ALTER TABLE `tzb_tree` 
ADD COLUMN `product_type` VARCHAR(4) NULL COMMENT '产品类型' AFTER `code`;
update tzb_tree set product_type = '0';

#V2.0.0 1-2
ALTER TABLE `tys_original_group` 
ADD COLUMN `receiving_quantity` INT(11) DEFAULT 0 COMMENT '提货中数量' AFTER `presell_quantity`;

ALTER TABLE `tzb_category` 
ADD COLUMN `type` VARCHAR(4) NULL COMMENT '类型' AFTER `code`;

ALTER TABLE `tys_presell_product` 
ADD COLUMN `parent_category_code` VARCHAR(32) NULL AFTER `owner_id`;

ALTER TABLE `tzb_category` 
ADD COLUMN `level` INT(11) DEFAULT 0 COMMENT '级别' AFTER `name`;

ALTER TABLE `tys_group_order` 
ADD COLUMN `owner_id` VARCHAR(32) NULL COMMENT '资产归属人' AFTER `group_code`;

ALTER TABLE `tys_derive_group` 
ADD COLUMN `variety` VARCHAR(255) NULL COMMENT '品种' AFTER `product_name`;

ALTER TABLE `tys_original_group` 
ADD COLUMN `specs_code` VARCHAR(32) NULL COMMENT '规格编号' AFTER `product_name`,
ADD COLUMN `specs_name` VARCHAR(255) NULL COMMENT '规格名称' AFTER `specs_code`;

ALTER TABLE `tys_derive_group` 
ADD COLUMN `specs_code` VARCHAR(32) NULL COMMENT '规格编号' AFTER `product_name`,
ADD COLUMN `specs_name` VARCHAR(255) NULL COMMENT '规格名称' AFTER `specs_code`;

ALTER TABLE `tys_group_order` 
ADD COLUMN `specs_code` VARCHAR(32) NULL COMMENT '规格编号' AFTER `product_name`,
ADD COLUMN `specs_name` VARCHAR(255) NULL COMMENT '规格名称' AFTER `specs_code`;

ALTER TABLE `tys_presell_product` 
ADD COLUMN `deliver_datetime` DATETIME NULL COMMENT '预计发货时间' AFTER `harvest_datetime`;

ALTER TABLE `tys_presell_product` 
DROP COLUMN `pack_weight`;

#V2.0.0 1-3
ALTER TABLE `tys_presell_product` 
CHANGE COLUMN `adopt_year` `adopt_year` FLOAT NULL DEFAULT '0' COMMENT '认养年限' ;

ALTER TABLE `tys_original_group` 
ADD COLUMN `belong_part_id` VARCHAR(32) NULL COMMENT '产权方编号' AFTER `code`;

update tys_original_group set belong_part_id = (select owner_id from tys_presell_product where code = tys_original_group.product_code);

#V2.0.0 1-4
ALTER TABLE `tsys_company` 
CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL COMMENT '简介' ;

ALTER TABLE `tys_original_group` 
ADD COLUMN `deliver_datetime` DATETIME NULL COMMENT '发货时间' AFTER `adopt_end_datetime`;

#V2.0.0 1-5
ALTER TABLE `tys_presell_specs` 
ADD COLUMN `interval_hours` INT NULL DEFAULT 1 COMMENT '时间间隔' AFTER `increase`,
ADD COLUMN `now_interval` INT NULL DEFAULT 0 COMMENT '当前间隔' AFTER `interval_hours`;

ALTER TABLE `tsc_cart` 
ADD COLUMN `amount` DECIMAL(18,8) NULL COMMENT '金额' AFTER `quantity`;

ALTER TABLE `tstd_sms` 
ADD COLUMN `publish_datetime` DATETIME NULL COMMENT '发布时间' AFTER `content`;

ALTER TABLE `tsc_cart` 
ADD COLUMN `shop_code` VARCHAR(32) NULL COMMENT '店铺编号' AFTER `code`;

ALTER TABLE `tys_presell_product` 
ADD COLUMN `output_unit` VARCHAR(32) NULL COMMENT '产量单位' AFTER `single_output`;

ALTER TABLE `tys_presell_product` 
ADD COLUMN `pack_weight` INT NULL AFTER `output_unit`;

ALTER TABLE `tsc_commodity_order_detail` 
ADD COLUMN `apply_user` VARCHAR(32) NULL COMMENT '下单人' AFTER `specs_name`;

ALTER TABLE `tstd_sms` 
CHANGE COLUMN `content` `content` TEXT NULL DEFAULT NULL COMMENT '内容' ;

ALTER TABLE `tsc_commodity_order_detail` 
ADD COLUMN `update_datetime` DATETIME NULL COMMENT '更新时间' AFTER `receiver_datetime`;

#V2.0.0 2-3
ALTER TABLE `tys_presell_order` 
ADD COLUMN `pack_count` INT NULL COMMENT '规格包装数量' AFTER `specs_name`;

ALTER TABLE `tsc_commodity` 
CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL COMMENT '描述' ;

ALTER TABLE `tsc_commodity_order_detail` 
ADD COLUMN `apply_datetime` DATETIME NULL COMMENT '下单时间' AFTER `apply_user`;

ALTER TABLE `tsc_after_sale` 
ADD COLUMN `shop_code` VARCHAR(32) NULL COMMENT '店铺编号' AFTER `code`;

#V2.0.0 3-1
ALTER TABLE `tsc_commodity` 
CHANGE COLUMN `name` `name` VARCHAR(255) NULL COMMENT '名称' ,
CHANGE COLUMN `parent_category_code` `parent_category_code` VARCHAR(32) NULL COMMENT '商品大类' ,
CHANGE COLUMN `category_code` `category_code` VARCHAR(32) NULL COMMENT '商品小类' ,
CHANGE COLUMN `deliver_place` `deliver_place` VARCHAR(64) NULL COMMENT '发货地' ,
CHANGE COLUMN `weight` `weight` VARCHAR(32) NULL COMMENT '重量' ,
CHANGE COLUMN `logistics` `logistics` VARCHAR(32) NULL COMMENT '物流方式' ,
CHANGE COLUMN `shop_code` `shop_code` VARCHAR(32) NULL COMMENT '店铺编号' ,
CHANGE COLUMN `status` `status` VARCHAR(4) NULL COMMENT '状态（0 草稿，1 已提交待审核，2 审核不通过，3 审核通过待上架，4 已上架待购买，5 已下架）' ,
ADD COLUMN `sell_user_id` VARCHAR(32) NULL AFTER `shop_code`;

ALTER TABLE `tsc_commodity_order` 
ADD COLUMN `logistics_company` VARCHAR(32) NULL COMMENT '物流公司' AFTER `address_code`,
ADD COLUMN `logistics_number` VARCHAR(255) NULL COMMENT '物流单号' AFTER `logistics_company`,
ADD COLUMN `deliver` VARCHAR(32) NULL COMMENT '发货人' AFTER `logistics_number`,
ADD COLUMN `deliver_datetime` DATETIME NULL COMMENT '发货时间' AFTER `deliver`,
ADD COLUMN `receiver` VARCHAR(32) NULL COMMENT '收货人' AFTER `deliver_datetime`,
ADD COLUMN `receiver_mobile` VARCHAR(45) NULL COMMENT '收货人手机号' AFTER `receiver`,
ADD COLUMN `receiver_datetime` DATETIME NULL COMMENT '收货时间' AFTER `receiver_mobile`;

ALTER TABLE `tsc_commodity_order_detail` 
CHANGE COLUMN `order_code` `order_code` VARCHAR(32) NULL COMMENT '订单编号' ,
CHANGE COLUMN `shop_code` `shop_code` VARCHAR(32) NULL COMMENT '店铺编号' ,
CHANGE COLUMN `commodity_code` `commodity_code` VARCHAR(32) NULL COMMENT '商品编号' ,
CHANGE COLUMN `specs_id` `specs_id` BIGINT(11) NULL COMMENT '规格编号' ,
CHANGE COLUMN `address_code` `address_code` VARCHAR(32) NULL COMMENT '收货地址编号' ,
CHANGE COLUMN `status` `status` VARCHAR(4) NULL COMMENT '状态（0 待发货，1 已发货待收货，2 已完成）' ;

ALTER TABLE `tsc_commodity` 
ADD COLUMN `original_price` VARCHAR(255) NULL COMMENT '原价' AFTER `weight`;

ALTER TABLE `tzb_product` 
ADD COLUMN `max_jfdk_rate` DOUBLE NULL DEFAULT NULL COMMENT '最大积分抵扣比例' AFTER `now_count`;

ALTER TABLE `tys_presell_product` 
ADD COLUMN `max_jfdk_rate` DOUBLE NULL COMMENT '最大积分抵扣比例' AFTER `now_count`;

ALTER TABLE `tsc_commodity` 
ADD COLUMN `max_jfdk_rate` DOUBLE NULL COMMENT '最大积分抵扣比例' AFTER `sell_user_id`;

ALTER TABLE `tsc_commodity_order` 
ADD COLUMN `shop_code` VARCHAR(32) NULL COMMENT '店铺编号' AFTER `code`;

ALTER TABLE `tsc_postage_template` 
CHANGE COLUMN `shop_code` `shop_code` VARCHAR(32) NULL COMMENT '店铺编号' ,
ADD COLUMN `shop_name` VARCHAR(255) NULL COMMENT '店铺名称' AFTER `shop_code`;

ALTER TABLE `tsc_commodity` 
CHANGE COLUMN `description` `description` LONGTEXT NULL DEFAULT NULL COMMENT '描述' ;

ALTER TABLE `tsc_commodity_order` 
ADD COLUMN `settle_status` VARCHAR(4) NULL COMMENT '结算状态' AFTER `status`;

ALTER TABLE `tsc_commodity_order` 
ADD COLUMN `cny_deduct_amount` DECIMAL(18,8) NULL COMMENT '人民币抵扣金额' AFTER `pay_amount`,
ADD COLUMN `jf_deduct_amount` DECIMAL(18,8) NULL COMMENT '积分抵扣金额' AFTER `cny_deduct_amount`,
ADD COLUMN `back_jf_amount` DECIMAL(18,8) NULL COMMENT '积分返点金额' AFTER `jf_deduct_amount`;

ALTER TABLE `tys_group_order` 
ADD COLUMN `presell_type` VARCHAR(4) NULL COMMENT '预售类型' AFTER `group_code`;


#V2.0.0 3-1
ALTER TABLE `tstd_user_ext` 
ADD COLUMN `id_pic` TEXT NULL COMMENT '身份证照' AFTER `pdf`,
ADD COLUMN `company_name` VARCHAR(255) NULL COMMENT '企业名称' AFTER `id_pic`,
ADD COLUMN `bussiness_license_id` VARCHAR(255) NULL COMMENT '营业执照号' AFTER `company_name`,
ADD COLUMN `company_introduce` TEXT NULL COMMENT '企业简介' AFTER `bussiness_license_id`,
ADD COLUMN `bussiness_license` TEXT NULL COMMENT '营业执照' AFTER `company_introduce`;

ALTER TABLE `tstd_user_ext` 
ADD COLUMN `person_auth_status` VARCHAR(4) NULL COMMENT '个人认证状态' AFTER `bussiness_license`,
ADD COLUMN `company_auth_status` VARCHAR(4) NULL COMMENT '企业认证状态' AFTER `person_auth_status`;

INSERT INTO `tstd_company_channel` (`company_code`, `company_name`, `channel_type`, `status`, `channel_company`, `private_key1`, `private_key2`, `system_code`) VALUES ('CD-SJD0000025', '时间岛', '35', '1', '1481010032', '3a9f4e501a7d8d43981a68d0978c58d8', 'wxb0df94ba24824c3e', 'CD-SJD0000025');

update tys_presell_product set pack_weight = 10;

ALTER TABLE `tstd_user_ext` 
ADD COLUMN `auth_status` VARCHAR(4) NULL COMMENT '认证状态' AFTER `bussiness_license`;

update tsj_article set collect_count = 0, point_count = 0, read_count = 0;

#V2.0.0 3-2
ALTER TABLE `tys_original_group` 
ADD COLUMN `received_quantity` INT(11) NULL DEFAULT 0 COMMENT '已提货数量' AFTER `receiving_quantity`;

#V2.0.0 3-4
ALTER TABLE `tzb_product` 
ADD COLUMN `collect_first_user` VARCHAR(32) NULL COMMENT '集体第一下单人' AFTER `specs_code`;

ALTER TABLE `tsys_company` 
CHANGE COLUMN `contract_template` `contract_template` LONGTEXT NULL DEFAULT NULL COMMENT '合同模板' ;

ALTER TABLE `tsys_config` 
CHANGE COLUMN `cvalue` `cvalue` LONGTEXT NULL DEFAULT NULL COMMENT 'value' ;

#V2.0.0 4-1
ALTER TABLE `tsc_commodity_order_detail` 
ADD COLUMN `max_jfdk_rate` DOUBLE NULL COMMENT '最大积分抵扣比例' AFTER `amount`;

ALTER TABLE `tsc_commodity_order` 
ADD COLUMN `shop_owner` VARCHAR(32) NULL COMMENT '店主编号' AFTER `shop_code`;

ALTER TABLE `tsys_cnavigate` 
ADD COLUMN `shop_code` VARCHAR(32) NULL COMMENT '店铺编号' AFTER `code`;

ALTER TABLE `tsc_commodity` 
CHANGE COLUMN `month_sell_count` `month_sell_count` BIGINT(20) NULL DEFAULT 0 COMMENT '月销量' ;

ALTER TABLE `tsc_commodity_order` 
ADD COLUMN `province` VARCHAR(255) NULL COMMENT '省' AFTER `address_code`,
ADD COLUMN `city` VARCHAR(255) NULL COMMENT '市' AFTER `province`,
ADD COLUMN `district` VARCHAR(255) NULL COMMENT '区' AFTER `city`,
ADD COLUMN `detail_address` VARCHAR(255) NULL COMMENT '详细地址' AFTER `district`;

ALTER TABLE `tsc_commodity_specs` 
ADD COLUMN `code` VARCHAR(32) NULL COMMENT '编号' FIRST;

#V2.0.0 4-2
ALTER TABLE `tsc_commodity_order` 
ADD COLUMN `postal_fee` DECIMAL(18,8) NULL COMMENT '邮费' AFTER `back_jf_amount`;

DROP TABLE IF EXISTS `tsc_default_postage`;
CREATE TABLE `tsc_default_postage` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `shop_code` varchar(32) DEFAULT NULL COMMENT '店铺编号',
  `shop_name` varchar(255) DEFAULT NULL COMMENT '店铺名称',
  `type` varchar(4) DEFAULT NULL COMMENT '类型（0同省/1跨省）',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `price` decimal(64,0) DEFAULT NULL COMMENT '价格',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='默认邮费模版';

ALTER TABLE `tsj_share_record` 
ADD COLUMN `content` VARCHAR(255) NULL COMMENT '分享内容' AFTER `channel`;

ALTER TABLE `tsc_commodity_order` 
CHANGE COLUMN `postal_fee` `postal_fee` DECIMAL(18,8) NULL DEFAULT 0 COMMENT '邮费' ;

ALTER TABLE `tys_derive_group` 
ADD COLUMN `original_quantity` INT(11) NULL COMMENT '初始数量' AFTER `price`;

update tys_derive_group set original_quantity = quantity;

#V2.0.0 4-3 商城上线
ALTER TABLE `tstd_sms` 
CHANGE COLUMN `content` `content` LONGTEXT NULL DEFAULT NULL COMMENT '内容' ;

ALTER TABLE `tsys_config` 
CHANGE COLUMN `cvalue` `cvalue` LONGTEXT NULL DEFAULT NULL COMMENT 'value' ;

#V2.0.0 4-4
ALTER TABLE `tstd_user_ext` 
ADD COLUMN `back_id_pic` TEXT NULL COMMENT '身份证反面' AFTER `id_pic`;

ALTER TABLE `tstd_user_ext` 
ADD COLUMN `company_address` VARCHAR(255) NULL COMMENT '企业地址' AFTER `company_name`,
ADD COLUMN `company_charger_name` VARCHAR(255) NULL COMMENT '企业法人姓名' AFTER `company_address`,
ADD COLUMN `company_charger_mobile` VARCHAR(255) NULL COMMENT '企业法人联系方式' AFTER `company_charger_name`,
ADD COLUMN `company_charger_id_no` VARCHAR(255) NULL COMMENT '企业法人身份证' AFTER `company_charger_mobile`,
ADD COLUMN `company_bank` VARCHAR(255) NULL COMMENT '企业开户行' AFTER `company_charger_id_no`,
ADD COLUMN `company_bank_number` VARCHAR(255) NULL COMMENT '企业开户行账号' AFTER `company_bank`,
ADD COLUMN `company_contact_name` VARCHAR(255) NULL COMMENT '企业联系人' AFTER `company_bank_number`,
ADD COLUMN `company_contact_mobile` VARCHAR(255) NULL COMMENT '企业联系人电话' AFTER `company_contact_name`,
ADD COLUMN `company_contact_address` VARCHAR(255) NULL COMMENT '企业联系人地址' AFTER `company_contact_mobile`,
ADD COLUMN `company_charger_id_pic` TEXT NULL COMMENT '企业法人身份证正面' AFTER `company_contact_address`,
ADD COLUMN `company_charger_back_id_pic` TEXT NULL COMMENT '企业法人身份证反面' AFTER `company_charger_id_pic`;

ALTER TABLE `tstd_user_relation` 
ADD COLUMN `remark` VARCHAR(255) NULL COMMENT '备注' AFTER `create_datetime`;

ALTER TABLE `tsc_commodity_order_detail` 
ADD COLUMN `after_sale_status` VARCHAR(4) NULL COMMENT '售后状态' AFTER `status`;

#V2.0.0 4-5
ALTER TABLE `tsc_commodity_order_detail` 
ADD COLUMN `cny_deduct_amount` DECIMAL(64,0) NULL COMMENT '抵扣人民币金额' AFTER `amount`,
ADD COLUMN `jf_deduct_amount` DECIMAL(64,0) NULL COMMENT '抵扣积分金额' AFTER `cny_deduct_amount`,
ADD COLUMN `back_jf_amount` DECIMAL(64,0) NULL COMMENT '返积分金额' AFTER `jf_deduct_amount`;

ALTER TABLE `tsc_commodity_order_detail` 
ADD COLUMN `pay_amount` DECIMAL(64,0) NULL AFTER `back_jf_amount`;

ALTER TABLE `tsc_after_sale` 
CHANGE COLUMN `logistics_company` `logistics_company` VARCHAR(255) NULL DEFAULT NULL COMMENT '物流公司' ;

ALTER TABLE `tzb_tree` 
ADD COLUMN `sell_type` VARCHAR(32) NULL COMMENT '销售类型' AFTER `tree_number`;

ALTER TABLE `tys_presell_order` 
ADD COLUMN `original_group_code` VARCHAR(32) NULL COMMENT '资产编号' AFTER `specs_name`;
