package org.springboot.security.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springboot.security.common.constant.SysUserStatusEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@TableName("t_sys_user")
public class SysUser implements UserDetails {

    private static long serialVersionUID = 1L;

    @TableId("id")
    private long id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    /**
     * 账号状态
     * 0 未启用
     * 1 正常
     * 2 锁定
     * 3 账号过期
     * 4 密码过期
     */
    @TableField("status")
    private int status;
    @TableField(exist = false)
    private Set<GrantedAuthority> authorities;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    private LocalDateTime createTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    private LocalDateTime updateTime;

    /**
     * Non --> not on
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return SysUserStatusEnum.EXPIRED.getCode() != status;
    }

    @Override
    public boolean isAccountNonLocked() {
        return SysUserStatusEnum.LOCKED.getCode() != status;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return SysUserStatusEnum.PASSWORD_EXPIRED.getCode() != status;
    }

    @Override
    public boolean isEnabled() {
        return SysUserStatusEnum.UNABLE.getCode() != status;
    }
}
