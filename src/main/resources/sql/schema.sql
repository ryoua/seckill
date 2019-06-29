create database seckill;
use seckill;

create table seckill (
    seckill_id bigint not null auto_increment comment '商品库存id',
    name varchar(120) not null comment '商品名称',
    number int not null comment '库存数量',
    create_time timestamp not null default current_timestamp comment '创建时间',
    start_time timestamp not null comment '开始时间',
    end_time timestamp not null comment '结束时间',
    primary key (seckill_id),
    key index_start_time (start_time),
    key index_end_time(end_time),
    key index_create_time(create_time)
) engine = innodb auto_increment = 1000 default charset utf8 comment '秒杀库存表'

insert into
    seckill(name, number, start_time, end_time)
values
('1000元秒杀iphone6', 100, '2015-11-01 00:00:00', '2015-11-02 00:00:00'),
('500元秒杀ipad2', 200, '2015-11-01 00:00:00', '2015-11-02 00:00:00'),
('300元秒杀小米4', 300, '2015-11-01 00:00:00', '2015-11-02 00:00:00'),
('200元秒杀红米note', 400, '2015-11-01 00:00:00', '2015-11-02 00:00:00');


create table success_killed(
    seckill_id bigint not null comment '商品库存id',
    user_phone bigint not null comment '用户手机号',
    state tinyint not null default -1 comment '状态信息：-1无效，0成功，1已付款，2已发货',
    create_time timestamp not null comment '创建时间',
    primary key (seckill_id, user_phone),/*联合主键*/
    key idx_create_time (create_time)
)engine = InnoDB default charset = utf8 comment = '秒杀库存表'