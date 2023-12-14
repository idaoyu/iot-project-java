package com.bbkk.project.module.file.utils;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件工具类
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-13 21:36
 **/
public interface FileUploadUtil {

    /**
     * 上传单个文件 并返回对应的访问 url
     *
     * @param file     文件对象
     * @param fileName 文件名字
     * @return 访问该文件的 url
     */
    String uploadFile(MultipartFile file, String fileName);

}
