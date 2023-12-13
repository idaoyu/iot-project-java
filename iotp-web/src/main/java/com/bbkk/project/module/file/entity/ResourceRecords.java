package com.bbkk.project.module.file.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 静态资源的记录表
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/12/13 19:35
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "resource_records")
public class ResourceRecords {

    /**
     * 资源id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 原始文件名字
     */
    @TableField(value = "original_file_name")
    private String originalFileName;

    /**
     * 在存储介质中的文件名字
     */
    @TableField(value = "file_name")
    private String fileName;

    /**
     * 存储类型（本地、minio、aliyun oss等）
     */
    @TableField(value = "store_type")
    private String storeType;

    /**
     * 完整的访问该资源的链接
     */
    @TableField(value = "url")
    private String url;

    /**
     * 标签
     */
    @TableField(value = "tag")
    private String tag;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}