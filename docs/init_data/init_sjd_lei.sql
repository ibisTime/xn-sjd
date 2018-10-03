
INSERT INTO `tb_tool` (`code`,`name`,`type`,`pic`,`price`,`description`,`validity_term`,`status`,`order_no`,`updater`,`update_datetime`,`remark`) VALUES ('T1538479526','砖石罩','0','',1000.00,'砖石保护罩',12,'1','1','admin',now(),NULL);
INSERT INTO `tb_tool` (`code`,`name`,`type`,`pic`,`price`,`description`,`validity_term`,`status`,`order_no`,`updater`,`update_datetime`,`remark`) VALUES ('T1538479527','黄金罩','0','',5000.00,'黄金保护罩',12,'1','2','admin',now(),NULL);
INSERT INTO `tb_tool` (`code`,`name`,`type`,`pic`,`price`,`description`,`validity_term`,`status`,`order_no`,`updater`,`update_datetime`,`remark`) VALUES ('T1538479528','一键收取','1','',100.00,'一键全收取',12,'1','3','admin',now(),NULL);


INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'tool_order_status','道具订单状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','tool_order_status','0','未使用','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','tool_order_status','1','已使用','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'tool_status','道具状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','tool_status','0','已下架','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','tool_status','1','已上架','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'tool_type','道具类型','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','tool_type','0','保护罩','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','tool_type','1','一件收取','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'tool_use_record_type','道具记录生效状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','tool_use_record_type','0','已失效','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','tool_use_record_type','1','生效中','admin',now(),'');