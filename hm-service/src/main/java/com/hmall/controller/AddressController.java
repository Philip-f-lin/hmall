package com.hmall.controller;


import com.hmall.common.exception.BadRequestException;
import com.hmall.common.utils.BeanUtils;
import com.hmall.common.utils.CollUtils;
import com.hmall.common.utils.UserContext;
import com.hmall.domain.dto.AddressDTO;
import com.hmall.domain.po.Address;
import com.hmall.service.IAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
@Api(tags = "收貨地址管理接口")
public class AddressController {

    private final IAddressService addressService;

    @ApiOperation("根據id查詢地址")
    @GetMapping("{addressId}")
    public AddressDTO findAddressById(@ApiParam("地址id") @PathVariable("addressId") Long id) {
        // 1.根據id查詢
        Address address = addressService.getById(id);
        // 2.判斷當前用戶
        Long userId = UserContext.getUser();
        if(!address.getUserId().equals(userId)){
            throw new BadRequestException("地址不屬於目前登入用戶");
        }
        return BeanUtils.copyBean(address, AddressDTO.class);
    }
    @ApiOperation("查詢目前使用者地址列表")
    @GetMapping
    public List<AddressDTO> findMyAddresses() {
        // 1.查詢列表
        List<Address> list = addressService.query().eq("user_id", UserContext.getUser()).list();
        // 2.判空
        if (CollUtils.isEmpty(list)) {
            return CollUtils.emptyList();
        }
        // 3.轉vo
        return BeanUtils.copyList(list, AddressDTO.class);
    }
}
