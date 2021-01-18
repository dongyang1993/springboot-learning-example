package org.springboot.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;

@TableName("t_sys_permission")
@Data
public class SysPermission {
    /**
     * 主键ID
     */
    private long id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 路径
     */
    private String path;
    /**
     * 请求方法
     */
    private HttpMethod method;
    /**
     * 创建时间
     */
    private LocalDateTime create_time;
    /**
     * 更新时间
     */
    private LocalDateTime update_time;
}
