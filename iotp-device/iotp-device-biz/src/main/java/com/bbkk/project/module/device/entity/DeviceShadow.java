package com.bbkk.project.module.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 设备影子
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/12/16 11:47
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "device_shadow")
public class DeviceShadow {

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 设备id
     */
    @TableField(value = "device_id")
    private String deviceId;

    /**
     * 影子数据
     */
    @TableField(value = "`data`")
    private String data;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}