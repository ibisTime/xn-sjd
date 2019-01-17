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

