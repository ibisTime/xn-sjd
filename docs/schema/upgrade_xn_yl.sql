##0.0.1
ALTER TABLE `tsj_article` 
ADD COLUMN `is_top` VARCHAR(4) NULL COMMENT '是否置顶' AFTER `order_no`;
