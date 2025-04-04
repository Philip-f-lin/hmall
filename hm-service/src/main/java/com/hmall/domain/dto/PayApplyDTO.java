package com.hmall.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@ApiModel(description = "支付下單表單實體")
public class PayApplyDTO {
    @ApiModelProperty("業務訂單id不能為空")
    @NotNull(message = "業務訂單id不能為空")
    private Long bizOrderNo;
    @ApiModelProperty("支付金額必須為正數")
    @Min(value = 1, message = "支付金額必須為正數")
    private Integer amount;
    @ApiModelProperty("支付管道編碼不能為空")
    @NotNull(message = "支付管道編碼不能為空")
    private String payChannelCode;
    @ApiModelProperty("付款方式不能為空")
    @NotNull(message = "付款方式不能為空")
    private Integer payType;
    @ApiModelProperty("訂單中的商品資訊不能為空")
    @NotNull(message = "訂單中的商品資訊不能為空")
    private String orderInfo;
}