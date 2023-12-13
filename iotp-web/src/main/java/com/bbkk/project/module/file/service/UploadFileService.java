package com.bbkk.project.module.file.service;

import cn.hutool.core.util.IdUtil;
import com.bbkk.project.module.file.config.FileStoreConfig;
import com.bbkk.project.module.file.data.UploadFileParams;
import com.bbkk.project.module.file.data.UploadFileVO;
import com.bbkk.project.module.file.entity.ResourceRecords;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 文件上传接口 业务类
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-13 19:38
 **/
@Service
@RequiredArgsConstructor
public class UploadFileService {

    private final IResourceRecordsService resourceRecordsService;
    private final FileStoreConfig fileStoreConfig;


    public UploadFileVO uploadFile(UploadFileParams params) {
        MultipartFile file = params.getFile();
        // 获取原始文件名字
        String originalFilename = file.getOriginalFilename();
        // 获取后缀 例如 ".exe" 或不存在后缀 ""
        String suffix = buildSuffix(originalFilename);
        // 保存文件上传记录
        ResourceRecords.ResourceRecordsBuilder builder = ResourceRecords.builder();
        builder.originalFileName(file.getOriginalFilename());
        builder.fileName(IdUtil.fastSimpleUUID() + suffix);
        builder.storeType(fileStoreConfig.getType());
        builder.tag(params.getTag());
        builder.createTime(new Date());
        builder.updateTime(new Date());
        resourceRecordsService.save(builder.build());
        return UploadFileVO.builder().fileUrl("https://oss.zizyy.com/temp").build();
    }

    private String buildSuffix(String originalFilename) {
        if (StringUtils.isBlank(originalFilename)) {
            return "";
        }
        String[] array = originalFilename.split("\\.");
        if (array.length < 2) {
            return "";
        }
        return "." + array[array.length - 1];
    }
}
