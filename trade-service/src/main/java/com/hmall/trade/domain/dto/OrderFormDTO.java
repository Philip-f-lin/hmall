package com.hmall.trade.domain.dto;

import com.hmall.api.dto.OrderDetailDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "交易下單表單實體")
public class OrderFormDTO {
    @ApiModelProperty("收貨地址id")
    private Long addressId;
    @ApiModelProperty("支付類型")
    private Integer paymentType;
    @ApiModelProperty("下單商品列表")
    private List<OrderDetailDTO> details;
}
