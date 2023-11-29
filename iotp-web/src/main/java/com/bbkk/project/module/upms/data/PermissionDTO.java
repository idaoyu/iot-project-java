package com.bbkk.project.module.upms.data;

import lombok.Data;

/**
 * 权限 dto
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 20:06
 **/
@Data
public class PermissionDTO {

    /**
     * 权限值
     */
    private String value;

    /**
     * 权重
     */
    private Integer weight;

}
