package com.bbkk.project.module.product.data;

import com.bbkk.project.utils.ValidatedGroup;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 描述产品信息和物模型信息的关联对象
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 20:16
 **/
@Data
public class ProductInfoAndTslDTO {

    /**
     * 物模型id
     */
    @NotEmpty(message = "物模型id不能为空", groups = {ValidatedGroup.UpdateGroup.class, ValidatedGroup.CreateGroup.class})
    private String tslId;

    /**
     * 物模型类别（属性、方法、事件）
     */
    @NotEmpty(message = "物模型类别不能为空", groups = {ValidatedGroup.UpdateGroup.class, ValidatedGroup.CreateGroup.class})
    private String type;

}
