package com.hmall.pay.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 支付訂單
 * </p>
 */
@Data
@ApiModel(description = "支付單vo實體")
public class PayOrderVO {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("業務訂單編號")
    private Long bizOrderNo;
    @ApiModelProperty("支付單號")
    private Long payOrderNo;
    @ApiModelProperty("支付用戶id")
    private Long bizUserId;
    @ApiModelProperty("支付管道編碼")
    private String payChannelCode;
    @ApiModelProperty("支付金額，單位分")
    private Integer amount;
    @ApiModelProperty("付款類型，1：h5,2:小程序，3：公眾號，4：掃碼，5：餘額支付")
    private Integer payType;
    @ApiModelProperty("付狀態，0：待提交，1:待支付，2：支付超時或取消，3：支付成功")
    private Integer status;
    @ApiModelProperty("拓展字段，用於傳遞不同管道單獨處理的字段")
    private String expandJson;
    @ApiModelProperty("第三方返回業務碼")
    private String resultCode;
    @ApiModelProperty("第三方回傳提示訊息")
    private String resultMsg;
    @ApiModelProperty("支付成功時間")
    private LocalDateTime paySuccessTime;
    @ApiModelProperty("支付超時時間")
    private LocalDateTime payOverTime;
    @ApiModelProperty("支付二維碼連結")
    private String qrCodeUrl;
    @ApiModelProperty("創建時間")
    private LocalDateTime createTime;
    @ApiModelProperty("更新時間")
    private LocalDateTime updateTime;
}
