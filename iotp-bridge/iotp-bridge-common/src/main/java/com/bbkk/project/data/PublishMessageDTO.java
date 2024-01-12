package com.bbkk.project.data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 设备发布消息 dto
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @version 1.0
 * @since 2023-12-21 19:15
 **/
@Data
public class PublishMessageDTO {

    /**
     * 产品id
     */
    private String productId;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 发布的数据类型 属性上报/触发事件/执行方法 等
     */
    private String type;

    /**
     * 设备测发布的事件戳
     */
    @NotNull(message = "timestamp 不能为空")
    private Long timestamp;

    /**
     * 上报数据内容
     */
    @NotEmpty(message = "payload 不能为空")
    private String payload;

}
