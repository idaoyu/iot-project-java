package com.bbkk.project.data.v1;

import com.bbkk.project.data.common.BasePublishMessageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 设备发布消息 dto
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @version 1.0
 * @since 2023-12-21 19:15
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class V1PublishMessageDTO extends BasePublishMessageDTO {

    /**
     * 发布的数据类型 属性上报/触发事件/执行方法 等
     */
    private String type;

    /**
     * 消息签名
     */
    private String sign;

    /**
     * 设备测发布的事件戳
     */
    private Long timestamp;

    /**
     * 上报数据内容
     */
    private String payload;

}
