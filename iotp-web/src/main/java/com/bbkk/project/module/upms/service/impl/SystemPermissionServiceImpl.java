package com.bbkk.project.module.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bbkk.project.module.upms.entity.SystemPermission;
import com.bbkk.project.module.upms.mapper.SystemPermissionMapper;
import com.bbkk.project.module.upms.service.ISystemPermissionService;
import org.springframework.stereotype.Service;

/**
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-11-29 19:00
 **/
@Service
public class SystemPermissionServiceImpl extends ServiceImpl<SystemPermissionMapper, SystemPermission>
        implements ISystemPermissionService {
}
