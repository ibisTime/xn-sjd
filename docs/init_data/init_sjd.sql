INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('PAY_RULE','JF_DK_MAX_RATE','0.3','admin',now(),'积分抵扣比率');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('PAY_RULE','CNY2JF_RATE','10','admin',now(),'1人民币兑换多少积分');

/*
-- Query: SELECT bank_code,bank_name,channel_type,status,channel_bank,max_order,order_amount,day_amount,month_amount,remark FROM tsys_channel_bank
-- Date: 2017-08-24 12:26
*/
INSERT INTO `tsys_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('CMBC','中国民生银行','40','1','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('ZJTLCB','浙江泰隆商业银行','40','1','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('ZJCZCB','浙江稠州商业银行','40','1','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('CMB','招商银行','40','1','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('SHB','上海银行','40','1','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('PAB','平安银行','40','1','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('SPDB','浦发银行','40','1','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('CIB','兴业银行','40','1','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('ICBC','中国工商银行','40','1','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('CEB','中国光大银行','40','1','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('CCB','中国建设银行','40','1','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('BCM','中国交通银行','40','1','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('ABC','中国农业银行','40','1','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('BOC','中国银行','40','1','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('PSBC','中国邮政储蓄银行','40','1','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('CITIC','中信银行','40','1','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES ('alipay','支付宝','40','1','',NULL,NULL,NULL,NULL,NULL);

INSERT INTO `tstd_account` (`account_number`,`user_id`,`currency`,`type`,`status`,`amount`,`frozen_amount`,`md5`,`in_amount`,`out_amount`,`create_datetime`,`last_order`) VALUES ('SYS_ACOUNT_CNY','SYS_USER','CNY','P','0',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',0,0,now(),NULL);
INSERT INTO `tstd_account` (`account_number`,`user_id`,`currency`,`type`,`status`,`amount`,`frozen_amount`,`md5`,`in_amount`,`out_amount`,`create_datetime`,`last_order`) VALUES ('SYS_ACOUNT_TPP','SYS_USER','TPP','P','0',0,0,'b99e0407fedc3d160f73fec8d1fa9a0c',0,0,now(),NULL);
INSERT INTO `tstd_account` (`account_number`,`user_id`,`currency`,`type`,`status`,`amount`,`frozen_amount`,`md5`,`in_amount`,`out_amount`,`create_datetime`,`last_order`) VALUES ('SYS_ACOUNT_JF','SYS_USER','JF','P','0',0,0,'811a1aeb3577b3108caa1ff46244f333',0,0,now(),NULL);
INSERT INTO `tstd_account` (`account_number`,`user_id`,`currency`,`type`,`status`,`amount`,`frozen_amount`,`md5`,`in_amount`,`out_amount`,`create_datetime`,`last_order`) VALUES ('SYS_ACOUNT_OFFLINE_TG','SYS_USER','WX','P','0',0,0,'811a1aeb3577b3108caa1ff46244f333',0,0,now(),NULL);
INSERT INTO `tstd_account` (`account_number`,`user_id`,`currency`,`type`,`status`,`amount`,`frozen_amount`,`md5`,`in_amount`,`out_amount`,`create_datetime`,`last_order`) VALUES ('SYS_ACOUNT_ALIPAY_TG','SYS_USER','ALIPAY','P','0',0,0,'811a1aeb3577b3108caa1ff46244f333',0,0,now(),NULL);
INSERT INTO `tstd_account` (`account_number`,`user_id`,`currency`,`type`,`status`,`amount`,`frozen_amount`,`md5`,`in_amount`,`out_amount`,`create_datetime`,`last_order`) VALUES ('SYS_ACOUNT_WEIXIN_TG','SYS_USER','WEIXIN','P','0',0,0,'811a1aeb3577b3108caa1ff46244f333',0,0,now(),NULL);
