package com.hmall.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "登入表單實體")
public class LoginFormDTO {
    @ApiModelProperty(value = "使用者名稱", required = true)
    @NotNull(message = "使用者名稱不能為空")
    private String username;
    @NotNull(message = "密碼不能為空")
    @ApiModelProperty(value = "使用者名稱", required = true)
    private String password;
    @ApiModelProperty(value = "是否记住我", required = false)
    private Boolean rememberMe = false;
}
