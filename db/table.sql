create table if not exists system_permission
(
    id          bigint auto_increment comment '权限id'
        primary key,
    name        varchar(32)   null comment '权限名字',
    value       varchar(32)   null comment '权限在系统中的标识',
    uri         varchar(64)   null comment '所指定的接口路径',
    weight      int default 0 null comment '权重，通配符uri权重应低于完整uri',
    create_time datetime      null comment '创建时间',
    update_time datetime null comment '修改时间',
    constraint system_permission_pk
        unique (value)
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
    update_time datetime null comment '修改时间',
    constraint system_role_pk
        unique (value)
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
    id            bigint auto_increment comment '用户id'
        primary key,
    username      varchar(32)          null comment '用户名（登陆时使用）',
    password      varchar(128)         null comment '密码',
    nickname      varchar(32)          null comment '昵称',
    enable        tinyint(1) default 1 null comment '是否启用',
    profile_photo varchar(128)         null comment '头像',
    create_time   datetime             null comment '创建时间',
    update_time   datetime             null comment '修改时间',
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

create table if not exists tsl_enum_value
(
    id          bigint auto_increment
        primary key,
    master_id   varchar(64) not null comment '关联的属性/方法/事件的id',
    source varchar(16) null comment '该条记录的来源',
    value       varchar(64) not null comment '值',
    description text        null comment '描述'
)
    comment '物模型枚举值';

create index tsl_enum_value_master_id_index
    on tsl_enum_value (master_id);

create table if not exists tsl_event
(
    id          varchar(64) not null comment '唯一标识'
        primary key,
    name        varchar(32) null comment '名称',
    description text        null comment '描述',
    type        varchar(32) null comment '事件类型',
    create_time datetime    null comment '创建时间',
    update_time datetime    null comment '修改时间'
)
    comment '物模型事件';

create table if not exists tsl_event_property
(
    event_id    varchar(32) null comment '事件id',
    property_id varchar(32) null comment '属性id'
)
    comment '物模型事件与属性关联表';

create table if not exists tsl_method
(
    id           varchar(32)          not null comment '方法唯一标识'
        primary key,
    name         varchar(64)          null comment '方法名字',
    description  text                 null comment '描述',
    asynchronous tinyint(1) default 0 null comment '是异步调用',
    create_time  datetime             null comment '创建时间',
    update_time  datetime             null comment '修改时间'
)
    comment '物模型方法';

create table if not exists tsl_method_params
(
    id         bigint auto_increment
        primary key,
    identifier varchar(32) null comment '参数唯一标识',
    method_id   varchar(32) null comment '方法id',
    name        varchar(32) null comment '参数名称',
    description text        null comment '参数描述',
    type        varchar(16) null comment '用来区分输入参数/输出参数',
    data_type   varchar(16) null comment '数据类型',
    min_value   varchar(32) null comment '最小值',
    max_value   varchar(32) null comment '最大值',
    step_size   varchar(32) null comment '步长',
    unit       varchar(32) null comment '单位',
    constraint tsl_method_params_method_id_identifier_type_uindex
        unique (method_id, identifier, type)
)
    comment '物模型方法输入/输出参数';

create table if not exists tsl_property
(
    id          varchar(64) not null comment '唯一标识'
        primary key,
    name        varchar(32) null comment '名称',
    description text        null comment '描述',
    data_type   varchar(16) null comment '数据类型（int:整数 float:浮点数 enum:枚举）',
    max_value   varchar(32) null comment '最大值，在dataType为数值型时有效',
    min_value   varchar(32) null comment '最小值，在dataType为数值型时有效',
    step_size   varchar(32) null comment '步长',
    unit        varchar(32) null comment '单位',
    only_read   tinyint(1)  null comment '是否是只读属性',
    create_time datetime    null comment '创建时间',
    update_time datetime    null comment '修改时间'
)
    comment '物模型属性';

