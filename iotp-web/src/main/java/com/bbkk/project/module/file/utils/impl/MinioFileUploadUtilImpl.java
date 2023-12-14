package com.bbkk.project.module.file.utils.impl;

import com.bbkk.project.exception.UploadFileException;
import com.bbkk.project.module.file.config.FileStoreConfig;
import com.bbkk.project.module.file.utils.FileUploadUtil;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传功能的 minio 实现
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-13 21:40
 **/
@Slf4j
@Component
@ConditionalOnProperty(value = "file-store.type", havingValue = "minio")
public class MinioFileUploadUtilImpl implements FileUploadUtil {

    private FileStoreConfig fileStoreConfig;
    private MinioClient minioClient;

    @Autowired
    public void setFileStoreConfig(FileStoreConfig fileStoreConfig) {
        this.fileStoreConfig = fileStoreConfig;
        // 在 fileStoreConfig 被注入时 创建 minioClient
        log.info("the current storage file mode used is minio");
        this.minioClient = MinioClient.builder()
                .endpoint(fileStoreConfig.getEndpoint())
                .credentials(fileStoreConfig.getAccessKey(), fileStoreConfig.getSecretKey())
                .build();
        log.info("minio client initialize success");
    }

    /**
     * 上传单个文件 并返回对应的访问 url
     *
     * @param file     文件对象
     * @param fileName 文件名字
     * @return 访问该文件的 url
     */
    @Override
    public String uploadFile(MultipartFile file, String fileName) {
        try {
            // 校验存储桶是否存在
            boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(fileStoreConfig.getBuckets()).build());
            // 如果不存在 则创建一个
            if (!exists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(fileStoreConfig.getBuckets()).build());
            }
            // 上传文件
            minioClient.putObject(
                    PutObjectArgs
                            .builder()
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .object(fileName)
                            .bucket(fileStoreConfig.getBuckets())
                            .contentType(file.getContentType())
                            .build()
            );
            // 对访问文件的 url 拼接
            return fileStoreConfig.getEndpoint() + "/" + fileStoreConfig.getBuckets() + "/" + fileName;
        } catch (MinioException minioException) {
            log.error("Error occurred: ", minioException);
            log.error("HTTP trace: " + minioException.httpTrace());
            throw new UploadFileException(minioException.getMessage());
        } catch (Exception exception) {
            log.error("上传文件至 minio 时产生错误", exception);
            throw new UploadFileException(exception.getMessage());
        }
    }
}
