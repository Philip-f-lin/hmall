package com.hmall.user.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "收貨地址實體")
public class AddressDTO {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("省")
    private String province;
    @ApiModelProperty("市")
    private String city;
    @ApiModelProperty("縣/區")
    private String town;
    @ApiModelProperty("手機")
    private String mobile;
    @ApiModelProperty("詳細地址")
    private String street;
    @ApiModelProperty("聯絡人")
    private String contact;
    @ApiModelProperty("是否為預設 1預設 0否")
    private Integer isDefault;
    @ApiModelProperty("備註")
    private String notes;
}
