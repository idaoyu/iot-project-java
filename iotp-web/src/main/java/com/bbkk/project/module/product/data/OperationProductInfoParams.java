package com.bbkk.project.module.product.data;

import com.bbkk.project.utils.ValidatedGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 操作产品信息接口入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 20:10
 **/
@Data
public class OperationProductInfoParams {

    @NotNull(message = "id不能为空", groups = {ValidatedGroup.UpdateGroup.class})
    private Long id;

    /**
     * 产品名字
     */
    @NotEmpty(message = "产品名字不能为空")
    private String name;

    /**
     * 产品描述
     */
    @NotEmpty(message = "产品描述不能为空")
    private String description;

    /**
     * 产品图片地址
     */
    @NotEmpty(message = "产品图片url不能为空")
    private String imageUrl;

    /**
     * 产品分类（存放分类表id）
     */
    private Long type;

    @Valid
    @NotEmpty(message = "关联物模型不能为空")
    private List<ProductInfoAndTslDTO> tslList;

}
