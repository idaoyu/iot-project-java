package com.bbkk.project.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统默认值枚举
 *
 * @author 一条秋刀鱼zz qchenzexuan@vip.qq.com
 * @since 2023-12-03 12:00
 **/
@Getter
@AllArgsConstructor
public enum SystemDefaultValueConstant {

    /**
     * 默认的用户头像
     */
    PROFILE_PHOTO_URL("https://oss.zizyy.com/public-images/user-default.png");

    private final String value;

}
