/*
-- Query: SELECT * FROM dev_xn_sjd.tzb_category
LIMIT 0, 500

-- Date: 2018-10-05 01:18
*/
INSERT INTO `tzb_category` (`code`,`parent_code`,`name`,`pic`,`order_no`,`status`,`updater`,`update_datetime`,`remark`) VALUES ('C201809261759230899680','','果树预售','FoA-Kvbf5h0UOGkNjxK3VcCGSgjO',3,'1','UCOIN201700000000000001',now(),'');
INSERT INTO `tzb_category` (`code`,`parent_code`,`name`,`pic`,`order_no`,`status`,`updater`,`update_datetime`,`remark`) VALUES ('C201809261802436106811','','古树认养','Fg0899i5CbAW6IsQ5BTpopZgkx4l',0,'1','UCOIN201700000000000001','2018-09-29 16:30:56','');
INSERT INTO `tzb_category` (`code`,`parent_code`,`name`,`pic`,`order_no`,`status`,`updater`,`update_datetime`,`remark`) VALUES ('C201809281712490693791','','情感林','Fq6qwAUlcL23W8o_dAYL59VqAtkd',2,'1','UCOIN201700000000000001','2018-09-29 17:05:31','');
INSERT INTO `tzb_category` (`code`,`parent_code`,`name`,`pic`,`order_no`,`status`,`updater`,`update_datetime`,`remark`) VALUES ('C201809281715108994602','','水源林','FqDFP7uoQmZksmlX2E2YJ3hrs-ZA',1,'1','UCOIN201700000000000001','2018-09-29 16:31:08','');

/*
-- Query: SELECT `user_id`,`role_code`,`real_name`,`photo`,`mobile`,`login_name`,`login_pwd`,`login_pwd_strength`,`create_datetime`,`status`,`updater`, now() `update_datetime`,`remark`,`system_code` FROM tsys_user where user_id='UCOIN201700000000000001'
LIMIT 0, 500

-- Date: 2018-10-04 14:26
*/
INSERT INTO `tsys_user` (`user_id`,`role_code`,`real_name`,`photo`,`mobile`,`login_name`,`login_pwd`,`login_pwd_strength`,`create_datetime`,`status`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('UCOIN201700000000000001','COINSR201700000000000000',NULL,NULL,NULL,'admin','21218cca77804d2ba1922c33e0151105','1','2018-09-27 16:06:48','2','UCOIN201700000000000001',now(),'管理端系统方','CD-SJD0000025');

/*
-- Query: SELECT `code`,`name`,`updater`, now() `update_datetime`,`remark`,`system_code` FROM tsys_role
LIMIT 0, 500

-- Date: 2018-10-05 00:46
*/
INSERT INTO `tsys_role` (`code`,`name`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','超级管理员','UCOIN201700000000000001',now(),'','CD-SJD0000025');
INSERT INTO `tsys_role` (`code`,`name`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','产权方','UCOIN201700000000000001',now(),'不能删','CD-SJD0000025');
INSERT INTO `tsys_role` (`code`,`name`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','养护方','UCOIN201700000000000001',now(),'不能删','CD-SJD0000025');
INSERT INTO `tsys_role` (`code`,`name`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','分销商','UCOIN201700000000000001',now(),'不能删','CD-SJD0000025');
INSERT INTO `tsys_role` (`code`,`name`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749297484833','业务员','UCOIN201700000000000001',now(),'不能删','CD-SJD0000025');

/*
-- Query: SELECT `code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,now() `update_datetime`,`remark`,`system_code` FROM tsys_menu
LIMIT 0, 500

-- Date: 2018-10-05 05:55
*/
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281332308235420','COINSM201708241036442974134','产权方管理','1','#',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281333105776717','COINSM201708241036442974134','养护方管理','1','#',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281333268396256','COINSM201708241036442974134','用户管理','1','#',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281333495529073','COINSM201708241036442974134','认养管理','1','#',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281334143415355','COINSM201708241036442974134','情感频道','1','#',5,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281334368922792','COINSM201708241036442974134','道具管理','1','#',6,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281337033822979','CD201809281332308235420','产权方管理','1','/property/property.htm',0,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281339215821027','CD201809281332308235420','产品分类管理','1','/property/types.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281340108436007','CD201809281332308235420','古树查看','1','/property/trees.htm',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281340444287327','CD201809281332308235420','账户查询','1','/property/accounts.htm',5,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281341207491932','CD201809281332308235420','流水查询','1','/property/flows.htm',6,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281412579311374','CD201809281333105776717','养护方管理','1','/conserve/conserve.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281414036981928','CD201809281333105776717','养护记录管理','1','/conserve/records.htm',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281415006916050','CD201809281333105776717','账户查询','1','/conserve/accounts.htm',5,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281415493225175','CD201809281333105776717','流水查询','1','/conserve/flows.htm',6,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281417015938505','CD201809281333268396256','会员查询','1','user/users.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281418089346301','CD201809281333268396256','签到记录','1','/user/signIn.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281418432854755','CD201809281333268396256','分享记录','1','/user/shares.htm',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281420156671354','CD201809281333268396256','碳泡泡赠送记录','1','/user/gives.htm',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281420587413829','CD201809281333268396256','账户查询','1','/user/accounts.htm',5,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281421284805917','CD201809281333268396256','流水查询','1','user/flows.htm',6,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281423599168724','CD201809281333495529073','集体认养订单管理','1','/claim/groupOrders.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281433427681455','CD201809281333495529073','个人认养订单管理','1','/claim/personOrders.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281435058855469','CD201809281333495529073','认养权查询','1','/user/rights.htm',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281436474236536','CD201809281334143415355','文章管理','1','/emotion/articles.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281438083657884','CD201809281334368922792','道具管理','1','/prop/props.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281438500474627','CD201809281334368922792','道具购买记录','1','/prop/buyRecords.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281439423486134','CD201809281334368922792','道具使用记录','1','/prop/useRecords.htm',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281445158646612','CD201809281332308235420','产品管理','1','/property/products.htm',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281447437387798','COINSM201700000000000000','代理管理','1','#',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281448148951070','CD201809281447437387798','代理商管理','1','1',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281449323242764','CD201809281448148951070','代理商管理','1','/agent/agents.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281453042511813','CD201809281448148951070','认养结算','1','/agent/settlement.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281454011616057','CD201809281448148951070','业绩查询','1','/agent/achievement.htm',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281454358293875','CD201809281448148951070','账户查询','1','/agent/accounts.htm',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281455007294618','CD201809281448148951070','流水查询','1','/agent/flows.htm',5,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281458013408308','COINSM201612071021105964','平台账户','1','#',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281458246867562','COINSM201612071021105964','充值管理','1','#',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281458530008193','COINSM201612071021105964','线下取现','1','#',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281500541305442','CD201809281458013408308','分销规则设置','1','/platform/distributionRules.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281501543134548','CD201809281458013408308','级差设置','1','/platform/gradationRules.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281503133311331','CD201809281458013408308','碳泡泡规则','1','/platform/tppRules.htm',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281504013431237','CD201809281458013408308','账户查询','1','/platform/account.htm',5,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281504368347161','CD201809281458013408308','流水查询','1','/platform/flows.htm',6,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281505109934336','CD201809281458013408308','历史流水查询','1','/platform/historyFlows.htm',7,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281507086522935','CD201809281458246867562','线下充值','1','/recharge/recharges.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281507479361586','CD201809281458246867562','充值查询','1','/recharge/records.htm',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281509063426888','CD201809281458530008193','取现规则','1','/withdraw/rules.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281509450622929','CD201809281458530008193','线下取现','1','/withdraw/withdraw.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281510256651980','CD201809281458530008193','取现查询','1','/withdraw/records.htm',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281511286775328','COINSM201708241037322072730','政府报表','1','#',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281512395258846','CD201809281511286775328','各政府报表','1','/report/governments.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281513078377083','CD201809281511286775328','政府报表','1','/report/government.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281524552035409','COINSM201700000000000000','产权端','1','#',6,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281525176339018','COINSM201700000000000000','养护端','1','#',7,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281532112127542','CD201809281524552035409','业务管理','1','#',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281534450984732','CD201809281532112127542','产品列表','1','/own/products.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281536375836020','CD201809281532112127542','产品发布','1','/own/productEdit.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281536596532894','CD201809281524552035409','资金管理','1','#',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281537198586372','CD201809281524552035409','个人设置','1','#',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281538399237793','CD201809281532112127542','认养权查看','1','/own/claimOrders.htm',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281539263402954','CD201809281532112127542','养护方绑定','1','/own/claimBind.htm',5,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281540360608809','CD201809281536596532894','账户流水','1','/own/flows.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281541006849767','CD201809281536596532894','提现','1','/own/withdraw.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281541293989402','CD201809281537198586372','个人信息','1','/own/infos.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281542244127375','CD201809281537198586372','系统公告','1','/own/notices.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281543095998269','CD201809281537198586372','安全管理','1','/own/security.htm',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281543403572100','CD201809281537198586372','关于我们','1','/own/aboutus.htm',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281544254578215','CD201809281525176339018','业务管理','1','#',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281544424541778','CD201809281525176339018','资金管理','1','#',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281545050347940','CD201809281525176339018','个人设置','1','#',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281546102647104','CD201809281544254578215','养护任务列表','1','/curing/tasks.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281546510306609','CD201809281544254578215','养护项目管理','1','/curing/projects.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281547310926292','CD201809281544254578215','我的产权方','1','/curing/owns.htm',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281548217147067','CD201809281544424541778','账户流水','1','/curing/flows.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281548513237925','CD201809281544424541778','提现','1','/curing/withdraw.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281549437632845','CD201809281545050347940','个人信息','1','/curing/infos.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281552382061660','CD201809281545050347940','公告','1','/curing/notices.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281553079063797','CD201809281545050347940','安全管理','1','/curing/security.htm',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281556563159269','COINSM201700001000000002','数据字典管理','1','/system/dataDict.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281557164456982','CD201809281556563159269','新增','2','/add',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281557350347620','CD201809281556563159269','修改','2','/edit',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281600159073848','CD201809281337033822979','待申请','2','/add',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281601354502731','CD201809281337033822979','审批','2','/check',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281603097609334','CD201809281337033822979','注销','2','/rock',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281603390181603','CD201809281337033822979','账户查询','2','/account',5,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281604022243308','CD201809281337033822979','详情','2','/detail',6,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281604232265500','CD201809281337033822979','导出','2','/export',7,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809281606239999346','CD201809281339215821027','修改','2','/edit',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291046324415849','CD201809281445158646612','详情','2','/detail',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291047103941386','CD201809281340108436007','详情','2','/detail',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291125523667070','CD201809281534450984732','上架申请','2','/up',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291126493698385','CD201809281534450984732','编辑','2','/edit',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291128413943525','CD201809281534450984732','详情','2','/detail',5,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291358335612845','CD201809281445158646612','审核','2','/check',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291358505746334','CD201809281445158646612','上架','2','/up',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291359163209666','CD201809281445158646612','下架','2','/down',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291549251278322','CD201809281412579311374','待申请','2','/add',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291550430233493','CD201809281412579311374','审批','2','/check',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291552565631457','CD201809281412579311374','注销','2','/rock',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291553477088833','CD201809281412579311374','账户查询','2','/account',5,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291554101324245','CD201809281412579311374','详情','2','/detail',6,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291554340794612','CD201809281412579311374','导出','2','/export',7,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291636165421372','CD201809281333105776717','养护人查看','1','/conserve/users.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291637012299194','CD201809281333105776717','养护项目查看','1','/conserve/projects.htm',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291646387661233','CD201809281339215821027','上架','2','/up',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809291647197391698','CD201809281339215821027','下架','2','/down',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809292005595194892','COINSM201700001000000001','消息推送','1','#',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809292006538897361','CD201809292005595194892','公告管理','1','/public/notice.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809292027420924178','CD201809281332308235420','养护方绑定管理','1','/property/binds.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809292048113827639','CD201809292027420924178','待绑定','2','/add',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809292049045447894','CD201809292027420924178','审批','2','/check',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809292102317412562','CD201809292006538897361','新增','2','/add',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809292103266638972','CD201809292006538897361','发布','2','/edit',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809292104078866023','CD201809292006538897361','回撤','2','/back',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809292148536116032','CD201809281542244127375','打开','2','/detail',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809292213080933131','CD201809281412579311374','绑定产权方','2','/binds',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809292303328331577','CD201809281546510306609','新增','2','/add',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809292304016748141','CD201809281546510306609','修改','2','/edit',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809292304221538638','CD201809281546510306609','删除','2','/delete',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809292312228754026','CD201809281544254578215','养护人管理','1','/curing/users.htm',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809292312565746837','CD201809292312228754026','新增','2','/add',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809292313115904111','CD201809292312228754026','修改','2','/edit',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809292313291251235','CD201809292312228754026','删除','2','/delete',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809301937108456264','CD201809281532112127542','树木查看','1','/own/trees.htm',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809301937589417371','CD201809301937108456264','详情','2','/detail',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809302000530926106','CD201809281538399237793','详情','2','/detail',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809302034130899158','CD201809281423599168724','详情','2','/detail',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809302034333247347','CD201809281423599168724','导出','2','/export',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809302054513509944','CD201809281433427681455','详情','2','/detail',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201809302055175197053','CD201809281433427681455','导出','2','/export',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041300522801924','CD201809291637012299194','详情','2','/detail',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041301176569195','CD201809291637012299194','导出','2','/export',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041342315421642','CD201809281536596532894','我的账户','1','/own/accounts.htm',0,'U201810041152580266665','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041537001387008','CD201809281545050347940','关于我们','1','/curing/aboutus.htm',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041556097829068','CD201809281548217147067','详情','2','/detail',1,'U201810041423160933968','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041557338327792','CD201809281540360608809','详情','2','/detail',1,'U201810041423160933968','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041645132981135','CD201809281449323242764','申请','2','/add',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041645269711015','CD201809281449323242764','审批','2','/check',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041646184569469','CD201809281449323242764','注销','2','/rock',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041646422932186','CD201809281449323242764','账户查询','2','/accounts',5,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041647224871036','CD201809281449323242764','详情','2','/detail',6,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041647362571860','CD201809281449323242764','导出','2','/export',7,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041750488569802','COINSM201700000000000000','代理商','1','#',8,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041752035736055','COINSM201700000000000000','业务员','1','#',9,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041755083494718','CD201810041750488569802','代理商','1','#',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041759249399434','CD201810041755083494718','用户查询','1','/proxy/users.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041803048637010','CD201810041755083494718','下级代理查询','1','/proxy/subordinates.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041804088591891','CD201810041755083494718','已结算佣金','1','/proxy/settled.htm',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041804504807896','CD201810041755083494718','待结算佣金','1','/proxy/unsettled.htm',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041805299962185','CD201810041755083494718','账户流水','1','/proxy/flows.htm',5,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041805523393052','CD201810041755083494718','提现','1','/proxy/withdraw.htm',6,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041806562513906','CD201810041750488569802','业务员管理','1','#',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041807294438589','CD201810041750488569802','个人设置','1','#',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041810367472794','CD201810041806562513906','业务员查询','1','/proxy/salesmen.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041915350329887','CD201810041807294438589','个人信息','1','/proxy/infos.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041916031158607','CD201810041807294438589','公告','1','/proxy/notices.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041916503757635','CD201810041807294438589','安全管理','1','/proxy/security.htm',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041917204014266','CD201810041807294438589','关于我们','1','/proxy/aboutus.htm',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041918433946610','CD201810041752035736055','业务员','1','#',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041919432289348','CD201810041918433946610','我的业绩','1','/saleman/achievement.htm',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041920200089460','CD201810041918433946610','我的用户','1','/saleman/users.htm',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041920201538195','CD201810041918433946610','安全管理','1','/saleman/security.htm',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810041922447473309','CD201810041918433946610','公告','1','/saleman/notices.htm',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810042032241394590','CD201810041810367472794','添加业务员','2','/add',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810042217058134611','CD201809281453042511813','结算','2','/edit',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810042217257739327','CD201809281453042511813','导出','2','/export',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810042225082969763','CD201809281454011616057','导出','2','/export',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810042325430956759','CD201809281500541305442','修改','2','/edit',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810042326100774694','CD201809281501543134548','修改','2','/edit',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810042327114493874','CD201809281503133311331','修改','2','/edit',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810042352064116135','CD201809281458013408308','积分规则','1','/platform/integralRules.htm',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810042352267473712','CD201810042352064116135','修改','2','/edit',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810050104422483297','CD201809281507086522935','待申请','2','/add',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810050105195526885','CD201809281507086522935','详情','2','/detail',3,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810050105538468486','CD201809281507086522935','审核','2','/check',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810050106121877523','CD201809281507086522935','导出','2','/export',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810050154234414919','CD201809281507479361586','详情','2','/detail',1,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('CD201810050154373965588','CD201809281507479361586','导出','2','/export',2,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM201612071021105964','COINSM201700000000000000','财务管理','1','#',4,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM201700000000000000','','根目录','1','#',1,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM201700001000000001','COINSM201700000000000000','系统管理','1','#',1,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM201700001000000002','COINSM201700001000000001','运维管理','1','#',2,'admin','2018-10-04 21:55:26',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM201700001000000003','COINSM201700001000000002','菜单管理','1','/system/menu.htm',1,'admin','2018-10-04 21:55:26',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM201700001000000004','COINSM201700001000000003','新增','2','/add',1,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017032911200961325','COINSM201700001000000003','修改','2','/edit',2,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017033020005366333','COINSM201707251006045006005','banner管理','1','/public/banner.htm',1,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017033020015631166','COINSM2017033020005366333','新增','2','/add',1,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017033020021094115','COINSM2017033020005366333','修改','2','/edit',2,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017033020022649991','COINSM2017033020005366333','删除','2','/delete',3,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017033020024827112','COINSM2017033020005366333','详情','2','/detail',4,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM201707251006045006005','COINSM201700001000000001','广告位管理','1','#',5,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM201708241024194086655','COINSM201700001000000003','删除','2','/delete',3,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM201708241036442974134','COINSM201700000000000000','业务管理','1','#',2,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM201708241037322072730','COINSM201700000000000000','统计报表','1','#',5,'UCOIN201700000000000001','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017101716241339082','COINSM201700001000000001','运营管理','1','#',1,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017101716253866426','COINSM2017101716241339082','角色管理','1','/system/role.htm',1,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017101716261754674','COINSM2017101716241339082','会员查询','1','/system/user.htm',2,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017101716450533995','COINSM2017101716253866426','分配菜单','2','/change',4,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017101717551955993','COINSM2017101716253866426','新增','2','/add',1,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017101717560118734','COINSM2017101716253866426','修改','2','/edit',2,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017101717563661357','COINSM2017101716253866426','删除','2','/delete',3,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017101719082391126','COINSM2017101716261754674','新增','2','/add',1,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017101719094151894','COINSM2017101716261754674','重置密码','2','/reset',2,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017101719100760088','COINSM2017101716261754674','注销','2','/rock',4,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017101719110981215','COINSM2017101716261754674','设置角色','2','/assign',5,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017120610552303416','COINSM2017101716261754674','激活','2','/active',3,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017121215543215610','COINSM201700001000000001','文章管理','1','#',4,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSM2017121216045274832','COINSM2017121215543215610','关于我们','1','/public/aboutus_addedit.htm',1,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SM201711181218447587419','COINSM2017121215543215610','常见问题','1','/public/question_addedit.htm',4,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SM201711181220013316605','COINSM2017121215543215610','注册协议','1','/public/register_addedit.htm',5,'admin','2018-10-04 21:55:26','','CD-SJD0000025');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('SM201711272034567989636','COINSM2017121215543215610','联系客服','1','/public/service_addedit.htm',16,'admin','2018-10-04 21:55:26','','CD-SJD0000025');

/*
-- Query: SELECT `role_code`,`menu_code`,`updater`,now() as `update_datetime`,`remark`,`system_code` FROM tsys_menu_role
LIMIT 0, 500

-- Date: 2018-10-05 05:56
*/
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749297484833','COINSM201700000000000000','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749297484833','CD201810041752035736055','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749297484833','CD201810041918433946610','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749297484833','CD201810041919432289348','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749297484833','CD201810041920200089460','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749297484833','CD201810041920201538195','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749297484833','CD201810041922447473309','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','COINSM201700000000000000','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809281524552035409','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809281532112127542','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809281534450984732','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809291125523667070','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809291126493698385','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809291128413943525','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809281536375836020','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809301937108456264','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809301937589417371','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809281538399237793','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809302000530926106','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809281539263402954','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809281536596532894','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201810041342315421642','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809281540360608809','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201810041557338327792','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809281541006849767','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809281537198586372','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809281541293989402','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809281542244127375','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809292148536116032','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809281543095998269','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134241553541','CD201809281543403572100','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','COINSM201700000000000000','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809281525176339018','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809281544254578215','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809281546102647104','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809281546510306609','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809292303328331577','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809292304016748141','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809292304221538638','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809292312228754026','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809292312565746837','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809292313115904111','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809292313291251235','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809281547310926292','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809281544424541778','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809281548217147067','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201810041556097829068','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809281548513237925','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809281545050347940','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809281549437632845','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809281552382061660','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201809281553079063797','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201809301134504008291','CD201810041537001387008','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','COINSM201700000000000000','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','CD201810041750488569802','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','CD201810041755083494718','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','CD201810041759249399434','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','CD201810041803048637010','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','CD201810041804088591891','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','CD201810041804504807896','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','CD201810041805299962185','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','CD201810041805523393052','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','CD201810041806562513906','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','CD201810041810367472794','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','CD201810042032241394590','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','CD201810041807294438589','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','CD201810041915350329887','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','CD201810041916031158607','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','CD201810041916503757635','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('JS201810041749178264163','CD201810041917204014266','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281337033822979','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281332308235420','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281412579311374','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281417015938505','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281423599168724','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281436474236536','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281438083657884','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281448148951070','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281449323242764','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281458013408308','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281500541305442','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281509063426888','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281511286775328','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281512395258846','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281557164456982','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281600159073848','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281606239999346','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809291047103941386','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809291358335612845','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809291549251278322','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809292006538897361','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809292027420924178','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809292048113827639','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809292102317412562','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809302034130899158','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809302054513509944','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810041300522801924','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810041645132981135','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810042217058134611','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810042225082969763','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810042325430956759','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810042326100774694','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810042327114493874','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810042352267473712','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810050104422483297','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810050154234414919','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201700000000000000','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201700001000000001','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201700001000000003','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201700001000000004','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017033020005366333','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017033020015631166','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101716241339082','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101716253866426','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101717551955993','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101719082391126','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017121216045274832','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','SM201711272034567989636','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281333105776717','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281339215821027','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281418089346301','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281433427681455','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281438500474627','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281453042511813','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281458246867562','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281501543134548','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281507086522935','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281509450622929','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281513078377083','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281556563159269','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281557350347620','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281601354502731','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809291358505746334','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809291550430233493','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809291636165421372','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809291646387661233','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809292049045447894','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809292103266638972','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809302034333247347','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809302055175197053','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810041301176569195','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810041645269711015','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810042217257739327','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810050105538468486','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810050154373965588','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201700001000000002','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017032911200961325','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017033020021094115','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201708241036442974134','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101716261754674','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101717560118734','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101719094151894','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281333268396256','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281418432854755','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281435058855469','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281439423486134','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281445158646612','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281447437387798','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281454011616057','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281458530008193','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281507479361586','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281510256651980','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809291359163209666','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809291637012299194','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809291647197391698','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809292005595194892','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809292104078866023','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809292213080933131','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810042352064116135','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810050105195526885','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017033020022649991','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201708241024194086655','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101717563661357','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017120610552303416','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281333495529073','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281340108436007','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281414036981928','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281420156671354','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281454358293875','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281503133311331','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281603097609334','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809291046324415849','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809291552565631457','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810041646184569469','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810050106121877523','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201612071021105964','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017033020024827112','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101716450533995','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101719100760088','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017121215543215610','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','SM201711181218447587419','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281334143415355','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281340444287327','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281415006916050','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281420587413829','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281455007294618','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281504013431237','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281603390181603','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809291553477088833','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810041646422932186','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201707251006045006005','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM201708241037322072730','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','COINSM2017101719110981215','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','SM201711181220013316605','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281334368922792','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281341207491932','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281415493225175','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281421284805917','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281504368347161','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281604022243308','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809291554101324245','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810041647224871036','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281505109934336','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809281604232265500','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201809291554340794612','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('COINSR201700000000000000','CD201810041647362571860','UCOIN201700000000000001','2018-10-04 21:56:09',NULL,'CD-SJD0000025');

/*
-- Query: SELECT `type`,`ckey`,`cvalue`,`updater`,now() `update_datetime`,`remark` FROM tsys_config
LIMIT 0, 500

-- Date: 2018-10-05 00:50
*/
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('weight','WEIGHT_RATE1','0.5','admin',now(),'好友排行权重1');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('weight','WEIGHT_RATE2','0.4','admin',now(),'好友排行权重2');

INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('qiniu','qiniu_access_key','Dc0pMP8ImFm78-uk4iGsOPpB2-vHc64D07OsOQVi','admin',now(),'七牛云key1');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('qiniu','qiniu_secret_key','3NP-tpZP9-5fH-R-FhvKTfYpPPVFNvjFF3JXmrcq','admin',now(),'七牛云key1');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('qiniu','qiniu_bucket','test','admin',now(),'存储空间');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('qiniu','qiniu_domain','ounm8iw2d.bkt.clouddn.com','admin',now(),'访问域名');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('PAY_RULE','JF_DK_MAX_RATE','0.3','admin',now(),'积分抵扣比率');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('PAY_RULE','CNY2JF_RATE','10','admin',now(),'1人民币兑换多少积分');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('USER_LEVEL','USER_LEVEL_0','0','admin',now(),'初探翠林');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('USER_LEVEL','USER_LEVEL_1','100','admin',now(),'护树新秀');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('USER_LEVEL','USER_LEVEL_2','1500','admin',now(),'护树高手');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('USER_LEVEL','USER_LEVEL_3','6000','admin',now(),'育树林丰');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('USER_LEVEL','USER_LEVEL_4','15000','admin',now(),'愈林诗人');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('USER_LEVEL','USER_LEVEL_5','30000','admin',now(),'爱林天使');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('DIST_RATE','DIST_OWENER_RATE','0.7','admin',now(),'产权方的总额');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('DIST_RATE','DIST_MAINTAIN_RATE','0.05','admin',now(),'养护方的总额');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('DIST_RATE','DIST_PLAT_RATE','0.1','admin',now(),'平台方的总额');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('DIST_RATE','DIST_AGENT_RATE','0.1','admin',now(),'代理方的总额');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('DIST_RATE','DIST_USER_BACK_JF_RATE','0.05','UCOIN201700000000000001',now(),'用户返积分的总额');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('DIST_AGENT_RATE','DIST_AGENT1_RATE','1','admin',now(),'一级代理分成比例');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('DIST_AGENT_RATE','DIST_AGENT2_RATE','0.9','admin',now(),'二级代理分成比例');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('DIST_AGENT_RATE','DIST_AGENT3_RATE','0.8','admin',now(),'三级代理分成比例');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`) VALUES ('DIST_AGENT_RATE','DIST_AGENT4_RATE','0.7','admin',now(),'四级代理分成比例');

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
INSERT INTO `tstd_account` (`account_number`,`user_id`,`currency`,`type`,`status`,`amount`,`frozen_amount`,`md5`,`in_amount`,`out_amount`,`create_datetime`,`last_order`) VALUES ('SYS_ACOUNT_JF_POOL','SYS_USER','JF','P','0',0,0,'811a1aeb3577b3108caa1ff46244f333',0,0,now(),NULL);
INSERT INTO `tstd_account` (`account_number`,`user_id`,`currency`,`type`,`status`,`amount`,`frozen_amount`,`md5`,`in_amount`,`out_amount`,`create_datetime`,`last_order`) VALUES ('SYS_ACOUNT_OFFLINE_TG','SYS_USER','OFFLINE','P','0',0,0,'811a1aeb3577b3108caa1ff46244f333',0,0,now(),NULL);
INSERT INTO `tstd_account` (`account_number`,`user_id`,`currency`,`type`,`status`,`amount`,`frozen_amount`,`md5`,`in_amount`,`out_amount`,`create_datetime`,`last_order`) VALUES ('SYS_ACOUNT_ALIPAY_TG','SYS_USER','ALIPAY','P','0',0,0,'811a1aeb3577b3108caa1ff46244f333',0,0,now(),NULL);
INSERT INTO `tstd_account` (`account_number`,`user_id`,`currency`,`type`,`status`,`amount`,`frozen_amount`,`md5`,`in_amount`,`out_amount`,`create_datetime`,`last_order`) VALUES ('SYS_ACOUNT_WEIXIN_TG','SYS_USER','WEIXIN','P','0',0,0,'811a1aeb3577b3108caa1ff46244f333',0,0,now(),NULL);

INSERT INTO `tb_tool` (`code`,`name`,`type`,`pic`,`price`,`description`,`validity_term`,`status`,`order_no`,`updater`,`update_datetime`,`remark`) VALUES ('T1538479526','砖石罩','0','',1000.00,'砖石保护罩',12,'1','1','admin',now(),NULL);
INSERT INTO `tb_tool` (`code`,`name`,`type`,`pic`,`price`,`description`,`validity_term`,`status`,`order_no`,`updater`,`update_datetime`,`remark`) VALUES ('T1538479527','黄金罩','0','',5000.00,'黄金保护罩',12,'1','2','admin',now(),NULL);
INSERT INTO `tb_tool` (`code`,`name`,`type`,`pic`,`price`,`description`,`validity_term`,`status`,`order_no`,`updater`,`update_datetime`,`remark`) VALUES ('T1538479528','一键收取','1','',100.00,'一键全收取',12,'1','3','admin',now(),NULL);

/**
 * select `type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark` from tsys_dict;
 */ 
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'role_level','角色等级','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','role_level','1','运维','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','role_level','2','运营','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','role_level','3','客户','admin',now(),NULL);

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'pay_type','支付方式','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','pay_type','1','余额支付','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','pay_type','3','支付宝','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','pay_type','5','微信h5','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'user_level','用户等级','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','user_level','0','初探翠林','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','user_level','1','护树新秀','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','user_level','2','护树高手','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','user_level','3','育树林丰','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','user_level','4','愈林诗人','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','user_level','5','爱林天使','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'agent_level','代理等级','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','agent_level','1','一级代理(省代)','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','agent_level','2','二级代理(市代)','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','agent_level','3','三级代理(区代/县级)','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','agent_level','4','四级代理(镇代)','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'agent_user_status','代理状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','agent_user_status','-1','待填写资料','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','agent_user_status','0','待审核','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','agent_user_status','1','审核不通过','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','agent_user_status','2','正常','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','agent_user_status','3','已注销','admin',now(),'');

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

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'jour_status','流水状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','jour_status','1','待对账','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','jour_status','3','已对账且账已平','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','jour_status','4','帐不平待调账审批','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','jour_status','5','已对账且账不平','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','jour_status','6','无需对账','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'user_status','用户状态','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','user_status','0','正常','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','user_status','1','程序锁定','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','user_status','2','人工锁定','admin',now(),NULL);

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'sys_user_status','系统用户','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','sys_user_status','-1','待填写资料','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','sys_user_status','0','待审核','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','sys_user_status','1','审核不通过','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','sys_user_status','2','正常','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','sys_user_status','3','程序锁定','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','sys_user_status','4','人工锁定','admin',now(),NULL);

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'sell_type','销售类型','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','sell_type','1','个人','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','sell_type','2','定向','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','sell_type','3','捐赠','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','sell_type','4','集体','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'biz_type','流水业务类型','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','charge','充值','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','withdraw','取现','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','withdraw_fee','取现手续费','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','withdraw_trans_fee','取现转账费','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','withdraw_enter','提现回录','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','withdraw_frozen','取现冻结','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','withdraw_unfrozen','取现解冻','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','hc','红冲','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','lb','蓝补','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','agent_deduct','代理提成','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','maintain_deduct','养护提成','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','owner_profit','产权收益','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','adopt','认养','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','adopt_dist','认养分成','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','adopt_profit','认养收益','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','adopt_day_back','认养消费每日收取碳泡泡','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','share','分享','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','present','赠送','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','reg','注册送积分','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','bind_mobile','绑定手机','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','bind_email','绑定邮箱','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','upload_photo','上传头像','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','complete_info','完善用户信息','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','real_auth','实名认证','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','sign','签到','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','one_adopt_back','直推认养消费返积分','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','two_adopt_back','间推认养消费返积分','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','invite_user','邀请好友注册','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','adopt_buy_deduct','认养抵扣','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','adopt_pay_back','认养消费返利','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_type','tool_buy','购买道具','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'id_kind','证件类型','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','id_kind','1','身份证','admin',now(),NULL);

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'res_type','资源类型','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','res_type','1','菜单','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','res_type','2','按钮','admin',now(),NULL);

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'notice_status','公告状态','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','notice_status','0','未发布','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','notice_status','1','已发布','admin',now(),NULL);
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','notice_status','2','已下架','admin',now(),NULL);

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'bankcard_status','银行卡状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','bankcard_status','0','弃用','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','bankcard_status','1','启用','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'currency','货币','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','currency','CNY','人民币','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','currency','JF','积分','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','currency','TPP','碳泡泡','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'account_type','账户类型','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','account_type','C','C端用户','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','account_type','O','产权方','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','account_type','M','养护方','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','account_type','A','代理商','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','account_type','P','平台用户','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'account_status','账户状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','account_status','0','正常','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','account_status','1','程序锁定','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','account_status','2','人工锁定','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'channel_type','渠道类型','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','channel_type','0','内部账','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','channel_type','30','支付宝','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','channel_type','35','微信公众号支付','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','channel_type','90','人工线下','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'charge_status','充值订单状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','charge_status','1','待支付','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','charge_status','2','支付失败','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','charge_status','3','支付成功','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'withdraw_status','取现订单状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','withdraw_status','1','待审批','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','withdraw_status','2','审批不通过','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','withdraw_status','3','审批通过待支付','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','withdraw_status','4','支付失败','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','withdraw_status','5','支付成功','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'hl_status','红蓝订单状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','hl_status','1','待审批','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','hl_status','2','审批不通过','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','hl_status','3','审批通过','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'settle_status','结算状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','settle_status','0','待结算','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','settle_status','1','结算成功','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','settle_status','2','结算失败','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'category_status','类别状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','category_status','0','待上架','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','category_status','1','已上架','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'product_location','产品位置','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','product_location','0','普通','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','product_location','1','首页推荐','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'product_status','产品状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','product_status','0','草稿','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','product_status','1','已提交待审核','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','product_status','2','审核不通过','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','product_status','3','审核通过待上架','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','product_status','4','已上架待认养','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','product_status','5','已锁定','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','product_status','6','已认养可下架','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','product_status','7','已下架','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'adopt_order_status','订单状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','adopt_order_status','0','待支付','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','adopt_order_status','1','已取消','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','adopt_order_status','2','待认养','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','adopt_order_status','3','认养中','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','adopt_order_status','4','已到期','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'group_adopt_order_status','订单状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','group_adopt_order_status','0','待支付','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','group_adopt_order_status','1','已取消','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','group_adopt_order_status','2','已支付','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','group_adopt_order_status','3','已满标','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','group_adopt_order_status','4','未满标已退钱','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','group_adopt_order_status','5','认养中','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','group_adopt_order_status','6','已到期','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'pay_type','支付类型','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','pay_type','1','余额支付','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','pay_type','3','支付宝','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','pay_type','5','微信H5','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'adopt_settle_status','订单结算状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','adopt_settle_status','0','不结算','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','adopt_settle_status','1','待结算','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','adopt_settle_status','2','已结算','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'agent_user_level','代理用户等级','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','agent_user_level','1','一级代理','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','agent_user_level','2','二级代理','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','agent_user_level','3','三级代理','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','agent_user_level','4','四级代理','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'tree_status','树的状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','tree_status','1','待认养','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','tree_status','2','待支付','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','tree_status','3','已认养','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'adopt_order_tree_status','树的状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','adopt_order_tree_status','1','待认养','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','adopt_order_tree_status','2','认养中','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','adopt_order_tree_status','3','已到期','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'agent_user_type','代理用户类型','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','agent_user_type','0','代理商','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','agent_user_type','1','业务员','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'apply_bind_maintain_status','绑定养护方状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','apply_bind_maintain_status','1','待审核','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','apply_bind_maintain_status','2','审核不通过','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','apply_bind_maintain_status','3','已绑定','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','apply_bind_maintain_status','4','已解除','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'article_open_level','绑定养护方状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','article_open_level','1','公开','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','article_open_level','2','私密','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','article_open_level','3','仅好友可见','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'article_status','文章状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','article_status','1','草稿','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','article_status','2','待审核','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','article_status','3','审核不通过','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','article_status','4','待上架','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','article_status','5','已上架','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','article_status','6','已下架','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'article_type','文章类型','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','article_type','1','平台','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','article_type','2','用户','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'biz_log_type','业务日志类型','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_log_type','1','赠送','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_log_type','2','留言','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','biz_log_type','3','收取碳泡泡','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'carbon_bubble_order_status','业务日志类型','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','carbon_bubble_order_status','0','待收取','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','carbon_bubble_order_status','1','已收取','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','carbon_bubble_order_status','2','已过期','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'gift_order_status','礼物订单状态','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','gift_order_status','0','待认领','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','gift_order_status','1','已认领','admin',now(),'');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('0',NULL,'user_releation_type','用户关系类型','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','user_releation_type','0','拉黑','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','user_releation_type','1','信任','admin',now(),'');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`) VALUES ('1','user_releation_type','2','好友','admin',now(),'');

