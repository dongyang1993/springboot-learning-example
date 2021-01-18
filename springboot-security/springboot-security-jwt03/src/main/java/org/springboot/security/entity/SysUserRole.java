package org.springboot.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("t_sys_user_role")
@Data
public class SysUserRole {
    /**
     * 主键ID
     */
    private long id;
    /**
     * 用户ID
     */
    private long userId;
    /**
     * 角色ID
     */
    private long roleId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
