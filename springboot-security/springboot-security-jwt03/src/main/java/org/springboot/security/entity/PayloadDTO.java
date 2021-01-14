package org.springboot.security.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class PayloadDTO {
    @ApiModelProperty("主题")
    private String sub;
    @ApiModelProperty("签发人")
    private String iss;
    @ApiModelProperty("JWT的ID")
    private String jti;
    @ApiModelProperty("用户名称")
    private String username;
    @ApiModelProperty("受众")
    private String aud;
    @ApiModelProperty("签发时间")
    private Long iat;
    @ApiModelProperty("生效时间")
    private String nbf;
    @ApiModelProperty("过期时间")
    private Long exp;
}
