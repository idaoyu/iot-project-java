package com.bbkk.project.module.upms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

/**
 * 用户表 实体
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/11/29 18:55
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`system_user`")
public class SystemUser implements UserDetails {

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 用户名（登陆时使用）
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    @TableField(value = "`password`")
    private String password;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 是否启用
     */
    @TableField(value = "`enable`")
    private Boolean enable;

    /**
     * 头像
     */
    @TableField(value = "profile_photo")
    private String profilePhoto;

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

    @TableField(exist = false)
    private List<? extends GrantedAuthority> authorities;

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}