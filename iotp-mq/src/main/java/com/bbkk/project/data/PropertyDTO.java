package com.bbkk.project.data;

import lombok.Data;

/**
 * 属性与值 dto
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-25 18:13
 **/
@Data
public class PropertyDTO {

    /**
     * 属性名字
     */
    private String name;

    /**
     * 属性值
     */
    private String value;

}
