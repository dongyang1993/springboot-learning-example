package org.springboot.security.common.constant;

import lombok.Getter;

/**
 * @Author DongYang
 * @Description 系统用户状态
 * @Date 2021/1/14 11:31
 **/
@Getter
public enum SysUserStatusEnum {
    UNABLE("账号未启用", 0),
    NORMAL("账号正常", 1),
    LOCKED("账号锁定", 2),
    EXPIRED("账号过期", 3),
    PASSWORD_EXPIRED("密码过期", 4);

    private final String desc;
    private final int code;

    SysUserStatusEnum(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }
}
