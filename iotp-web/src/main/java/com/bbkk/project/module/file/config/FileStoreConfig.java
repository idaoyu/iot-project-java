package com.bbkk.project.module.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置文件存储方式
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-13 19:54
 **/
@Data
@Component
@ConfigurationProperties("file-store")
public class FileStoreConfig {

    /**
     * 文件存储类型 minio/alibaba-oss 等
     */
    private String type;

    /**
     * 使用 minio 或 s3 oss 时 的服务器端点
     */
    private String endpoint;

    /**
     * access key
     */
    private String accessKey;

    /**
     * secret key
     */
    private String secretKey;

    /**
     * 存储桶名称
     */
    private String buckets;

}
