package com.bbkk.project.module.file.controller;

import com.bbkk.project.module.file.data.UploadFileParams;
import com.bbkk.project.module.file.data.UploadFileVO;
import com.bbkk.project.module.file.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件上传接口
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-13 19:36
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class UploadFileController {

    private final UploadFileService uploadFileService;

    @PostMapping("/upload")
    public UploadFileVO uploadFile(@Validated UploadFileParams params) {
        return uploadFileService.uploadFile(params);
    }

}
