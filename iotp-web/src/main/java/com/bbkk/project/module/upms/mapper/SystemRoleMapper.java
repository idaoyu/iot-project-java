package com.bbkk.project.module.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bbkk.project.module.upms.data.RolePermissionDTO;
import com.bbkk.project.module.upms.entity.SystemRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023/11/29 18:57
 **/
@Mapper
public interface SystemRoleMapper extends BaseMapper<SystemRole> {

    /**
     * 查询全部的角色与权限集合
     *
     * @return RolePermissionDTO List
     */
    List<RolePermissionDTO> findAllRolePermission();

    /**
     * 根据用户id 查询用户拥有的角色
     *
     * @param userId 用户id
     * @return 角色集合
     */
    List<String> findRoleListByUserId(Long userId);

}