package org.springboot.security.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@TableName("t_user")
public class MineUser implements UserDetails {

    private static long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
    @TableField("account_non_expired")
    private boolean accountNonExpired;
    @TableField("account_non_locked")
    private boolean accountNonLocked;
    @TableField("credentials_non_expired")
    private boolean credentialsNonExpired;
    @TableField("enabled")
    private boolean enabled;
    @TableField(exist = false)
    private Set<GrantedAuthority> authorities;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    private LocalDateTime updateTime;
}
