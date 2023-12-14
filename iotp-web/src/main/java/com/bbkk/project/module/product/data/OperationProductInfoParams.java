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
    @NotEmpty(message = "产品名字不能为空", groups = {ValidatedGroup.UpdateGroup.class, ValidatedGroup.CreateGroup.class})
    private String name;

    /**
     * 产品描述
     */
    @NotEmpty(message = "产品描述不能为空", groups = {ValidatedGroup.UpdateGroup.class, ValidatedGroup.CreateGroup.class})
    private String description;

    /**
     * 产品图片地址
     */
    @NotEmpty(message = "产品图片url不能为空", groups = {ValidatedGroup.UpdateGroup.class, ValidatedGroup.CreateGroup.class})
    private String imageUrl;

    /**
     * 需要认证
     */
    @NotNull(message = "needAuth 不能为空", groups = {ValidatedGroup.UpdateGroup.class, ValidatedGroup.CreateGroup.class})
    private Boolean needAuth;

    /**
     * 认证类型
     */
    @NotEmpty(message = "认证类型不能为空", groups = {ValidatedGroup.UpdateGroup.class, ValidatedGroup.CreateGroup.class})
    private String authType;

    /**
     * 产品状态
     */
    private String status;

    /**
     * 产品分类（存放分类表id）
     */
    private Long type;

    @Valid
    @NotEmpty(message = "关联物模型不能为空", groups = {ValidatedGroup.UpdateGroup.class, ValidatedGroup.CreateGroup.class})
    private List<ProductInfoAndTslDTO> tslList;

}
