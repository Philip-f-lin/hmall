package com.hmall.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "訂單頁面VO")
public class OrderVO {
    @ApiModelProperty("訂單id")
    private Long id;
    @ApiModelProperty("總金額，單位為分")
    private Integer totalFee;
    @ApiModelProperty("支付類型，1、支付寶，2、微信，3、扣減餘額")
    private Integer paymentType;
    @ApiModelProperty("使用者id")
    private Long userId;
    @ApiModelProperty("订单的状态，1、未付款 2、已付款,未发货 3、已发货,未确认 4、确认收货，交易成功 5、交易取消，订单关闭 6、交易结束，已评价")
    private Integer status;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("支付时间")
    private LocalDateTime payTime;
    @ApiModelProperty("发货时间")
    private LocalDateTime consignTime;
    @ApiModelProperty("交易完成时间")
    private LocalDateTime endTime;
    @ApiModelProperty("交易关闭时间")
    private LocalDateTime closeTime;
    @ApiModelProperty("评价时间")
    private LocalDateTime commentTime;
}
