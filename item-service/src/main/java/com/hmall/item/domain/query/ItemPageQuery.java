package com.hmall.item.domain.query;

import com.hmall.common.domain.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "商品分頁查詢條件")
public class ItemPageQuery extends PageQuery {
    @ApiModelProperty("搜尋關鍵字")
    private String key;
    @ApiModelProperty("商品分類")
    private String category;
    @ApiModelProperty("商品品牌")
    private String brand;
    @ApiModelProperty("價格最小值")
    private Integer minPrice;
    @ApiModelProperty("價格最大值")
    private Integer maxPrice;
}
