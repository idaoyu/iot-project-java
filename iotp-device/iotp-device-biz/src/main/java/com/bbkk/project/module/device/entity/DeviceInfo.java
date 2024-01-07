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
 * 设备信息
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/12/14 20:26
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "device_info")
public class DeviceInfo {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 产品id
     */
    @TableField(value = "product_id")
    private Long productId;

    /**
     * 设备名字
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

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