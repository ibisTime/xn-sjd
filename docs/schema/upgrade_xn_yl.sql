##0.0.1
ALTER TABLE `tsj_article` 
ADD COLUMN `is_top` VARCHAR(4) NULL COMMENT '是否置顶' AFTER `order_no`;

ALTER TABLE `tstd_user` 
ADD COLUMN `friend_count` INT DEFAULT 0 COMMENT '好友数量' AFTER `agent_id`;

ALTER TABLE `tstd_sms` 
ADD COLUMN `user_id` VARCHAR(32) NULL COMMENT '消息用户' AFTER `object`;

ALTER TABLE `tsc_commodity` 
ADD COLUMN `original_place` VARCHAR(255) NULL COMMENT '原产地' AFTER `original_price`;
