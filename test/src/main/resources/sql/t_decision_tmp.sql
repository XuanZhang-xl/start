CREATE TABLE `t_decision_tmp` (
    `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
    `batch_no`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '批次号' ,
    `shop_id`  bigint(20) NULL DEFAULT NULL COMMENT '店铺id' ,
    `decision_level`  varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '决策层级（SPU：SPU SKU:SKU）' ,
    `begin_time`  timestamp NULL DEFAULT NULL COMMENT '开始时间' ,
    `end_time`  timestamp NULL DEFAULT NULL COMMENT '结束时间' ,
    `goods_no`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '商品编号' ,
    `sku_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'sku id' ,
    `acc_sale_count`  bigint(20) NULL DEFAULT NULL COMMENT '累计销量' ,
    `acc_pay_amount`  bigint(20) NULL DEFAULT NULL COMMENT '累计支付金额' ,
    `avg_sale_count`  bigint(20) NULL DEFAULT NULL COMMENT '日均销量' ,
    `avg_pay_amount`  bigint(20) NULL DEFAULT NULL COMMENT '日均销售额' ,
    `create_time`  timestamp NULL DEFAULT NULL COMMENT '创建日期' ,
    PRIMARY KEY (`id`),
    INDEX `idx_goods_no` (`goods_no`) USING BTREE ,
    INDEX `idx_shop_id` (`shop_id`) USING BTREE ,
    INDEX `t_decision_tmp_sku_id_IDX` (`sku_id`) USING BTREE
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
  COMMENT='决策中间表'
  ROW_FORMAT=DYNAMIC
;

