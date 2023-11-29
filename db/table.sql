create table if not exists system_permission
(
    id          bigint auto_increment comment '权限id'
        primary key,
    name        varchar(32)   null comment '权限名字',
    value       varchar(32)   null comment '权限在系统中的标识',
    uri         varchar(64)   null comment '所指定的接口路径',
    weight      int default 0 null comment '权重，通配符uri权重应低于完整uri',
    create_time datetime      null comment '创建时间',
    update_time datetime      null comment '修改时间'
)
    comment '权限表';

create table if not exists system_role
(
    id          bigint auto_increment comment '角色id'
        primary key,
    name        varchar(32) null comment '角色名字',
    value       varchar(32) null comment '角色在系统中的标识',
    description text        null comment '角色描述',
    create_time datetime    null comment '创建时间',
    update_time datetime    null comment '修改时间'
)
    comment '角色表';

create table if not exists system_role_permission
(
    role_id       bigint not null comment '角色id',
    permission_id bigint not null comment '权限id'
)
    comment '角色与权限关联表';

create table if not exists system_user
(
    id          bigint auto_increment comment '用户id'
        primary key,
    username    varchar(32)          null comment '用户名（登陆时使用）',
    password    varchar(128)         null comment '密码',
    enable      tinyint(1) default 1 null comment '是否启用',
    create_time datetime             null comment '创建时间',
    update_time datetime             null comment '修改时间',
    constraint system_user_pk2
        unique (username)
)
    comment '用户表';

create table if not exists system_user_role
(
    user_id bigint not null comment '用户id',
    role_id bigint not null comment '角色id'
)
    comment '用户与角色关联表';
