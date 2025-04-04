package com.hmall.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@ApiModel(description = "支付確認表單實體")
public class PayOrderFormDTO {
    @ApiModelProperty("支付訂單id不能為空")
    @NotNull(message = "支付訂單id不能為空")
    private Long id;
    @ApiModelProperty("支付密碼")
    @NotNull(message = "支付密碼")
    private String pw;
}