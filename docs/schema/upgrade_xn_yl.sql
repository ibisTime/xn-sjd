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