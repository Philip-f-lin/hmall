package com.hmall.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "新增購物車商品表單實體")
public class CartFormDTO {
    @ApiModelProperty("商品id")
    private Long itemId;
    @ApiModelProperty("商品標題")
    private String name;
    @ApiModelProperty("商品動態屬性鍵值集")
    private String spec;
    @ApiModelProperty("價格,單位：分")
    private Integer price;
    @ApiModelProperty("商品圖片")
    private String image;
}
