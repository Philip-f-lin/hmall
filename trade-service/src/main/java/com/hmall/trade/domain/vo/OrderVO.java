package com.hmall.trade.domain.vo;

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
    @ApiModelProperty("訂單的狀態，1、未付款 2、已付款,未發貨 3、已發貨,未確認 4、確認收貨，交易成功 5、交易取消，訂單關閉 6、交易結束，已評價")
    private Integer status;
    @ApiModelProperty("創建時間")
    private LocalDateTime createTime;
    @ApiModelProperty("支付時間")
    private LocalDateTime payTime;
    @ApiModelProperty("出貨時間")
    private LocalDateTime consignTime;
    @ApiModelProperty("交易完成時間")
    private LocalDateTime endTime;
    @ApiModelProperty("交易關閉時間")
    private LocalDateTime closeTime;
    @ApiModelProperty("評價時間")
    private LocalDateTime commentTime;
}
