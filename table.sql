-- drop table shipping_user;
CREATE TABLE `shipping_user` (
                                 `id` int(10) Auto_Increment NOT NULL COMMENT 'ID',
                                 `openid` varchar(64) NOT NULL COMMENT 'openid',
                                 `user_name` varchar(64) NOT NULL COMMENT '名称',
                                 `phone` varchar(64) NOT NULL COMMENT '手机号',
                                 `avatar` varchar(64) NULL COMMENT '头像',
                                 `create_time` datetime NOT NULL COMMENT '创建时间',
                                 `deleted` int(1) NOT NULL COMMENT '删除标志',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流用户';

-- # drop table shipping_order;
create table shipping_order (
                                `id` int(10) Auto_Increment NOT NULL COMMENT 'ID',
                                `remark`          varchar(256)  null comment '描述',
                                `tracking_number` varchar(64)   not null comment '快递单号',
                                `address`         varchar(256)  not null comment '地址',
                                `openid`          varchar(64)   not null comment '创建者',
                                `create_time`     datetime      null comment '创建时间',
                                `price`           decimal(9, 2) null comment '金额',
                                `order_number`    varchar(64)   null comment '国际单号',
                                `image`           varchar(64)   null comment '图片key',
                                `status`        int(1)        not null comment '订单状态',
                                `deleted`         int(1)        not null comment '删除标志',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流订单';
