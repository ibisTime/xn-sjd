ALTER TABLE `tzb_product` 
ADD COLUMN `parent_category_code` VARCHAR(32) NULL COMMENT '产品大类' AFTER `owner_id`;

ALTER TABLE `try_adopt_order_tree` 
ADD COLUMN `parent_category_code` VARCHAR(32) NULL COMMENT '产品大类' AFTER `order_code`;

update `tzb_product`  set `raise_count` = (select count(1) from `tzb_tree` where `tzb_tree`.`product_code` = `tzb_product`.code )

ALTER TABLE `try_adopt_order_tree` 
ADD COLUMN `owner_id` VARCHAR(32) NULL COMMENT '产权方编号' AFTER `category_code`;
update try_adopt_order_tree set owner_id = (select owner_id from tzb_product where try_adopt_order_tree.product_code = tzb_product.code);

ALTER TABLE `tsj_gift_order` 
ADD COLUMN `list_pic` VARCHAR(255) NULL COMMENT '列表图片' AFTER `price`;

ALTER TABLE `tsj_gift_order` 
CHANGE COLUMN `description` `description` TEXT NULL DEFAULT NULL COMMENT '礼物描述' ;
