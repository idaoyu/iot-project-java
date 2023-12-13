package com.bbkk.project.module.file.data;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件接口入参
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-13 19:40
 **/
@Data
public class UploadFileParams {

    /**
     * 要上传的文件
     */
    @NotNull(message = "要上传的文件不能为空")
    private MultipartFile file;

    /**
     * 给文件的标签
     */
    private String tag;

}
