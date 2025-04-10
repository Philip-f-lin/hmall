package com.hmall.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "商品實體")
public class ItemDTO {
    @ApiModelProperty("商品編號")
    private Long id;
    @ApiModelProperty("SKU名稱")
    private String name;
    @ApiModelProperty("價格（分）")
    private Integer price;
    @ApiModelProperty("庫存數量")
    private Integer stock;
    @ApiModelProperty("商品圖片")
    private String image;
    @ApiModelProperty("類別目名稱")
    private String category;
    @ApiModelProperty("品牌名稱")
    private String brand;
    @ApiModelProperty("規格")
    private String spec;
    @ApiModelProperty("銷售量")
    private Integer sold;
    @ApiModelProperty("評論數")
    private Integer commentCount;
    @ApiModelProperty("是否為推廣廣告，true/false")
    private Boolean isAD;
    @ApiModelProperty("商品狀態 1-正常，2-下架，3-刪除")
    private Integer status;
}
