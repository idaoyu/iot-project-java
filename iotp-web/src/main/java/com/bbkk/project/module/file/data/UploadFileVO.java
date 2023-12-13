package com.bbkk.project.module.file.data;

import lombok.Builder;
import lombok.Data;

/**
 * 上传文件 vo
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-13 19:39
 **/
@Data
@Builder
public class UploadFileVO {

    /**
     * 文件路径
     */
    private String fileUrl;

}
