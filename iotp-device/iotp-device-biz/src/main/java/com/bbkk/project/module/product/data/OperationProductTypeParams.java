package com.bbkk.project.module.product.data;

import com.bbkk.project.utils.ValidatedGroup;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 操作产品类目接口入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-12 18:53
 **/
@Data
public class OperationProductTypeParams {

    /**
     * 类目id 只有修改操作时必传 创建时无意义
     */
    @NotNull(message = "id 不能为空", groups = {ValidatedGroup.UpdateGroup.class})
    private Long id;

    /**
     * 类目名字
     */
    @NotEmpty(message = "类目名字不能为空")
    private String name;

    /**
     * 类目描述
     */
    @NotEmpty(message = "类目描述不能为空")
    private String description;

}
