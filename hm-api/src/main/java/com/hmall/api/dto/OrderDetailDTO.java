package com.hmall.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(description = "訂單明細條")
@Data
@Accessors(chain = true)
public class OrderDetailDTO {
    @ApiModelProperty("商品id")
    private Long itemId;
    @ApiModelProperty("商品購買數量")
    private Integer num;
}
