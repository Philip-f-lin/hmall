package com.hmall.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 订单详情表
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
@Data
@ApiModel(description = "購物車VO實體")
public class CartVO {
    @ApiModelProperty("購物車條目id ")
    private Long id;
    @ApiModelProperty("sku商品id")
    private Long itemId;
    @ApiModelProperty("購買數量")
    private Integer num;
    @ApiModelProperty("商品標題")
    private String name;
    @ApiModelProperty("商品動態屬性鍵值集")
    private String spec;
    @ApiModelProperty("價格,單位：分")
    private Integer price;
    @ApiModelProperty("商品最新價格")
    private Integer newPrice;
    @ApiModelProperty("商品最新狀態")
    private Integer status = 1;
    @ApiModelProperty("商品最新庫存")
    private Integer stock = 10;
    @ApiModelProperty("商品圖片")
    private String image;
    @ApiModelProperty("創建時間")
    private LocalDateTime createTime;

}
