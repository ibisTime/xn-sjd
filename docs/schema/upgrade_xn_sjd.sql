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

