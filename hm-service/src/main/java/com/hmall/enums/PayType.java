package com.hmall.enums;

import lombok.Getter;

@Getter
public enum PayType{
    JSAPI(1, "網頁支付JS"),
    MINI_APP(2, "小程式支付"),
    APP(3, "APP支付"),
    NATIVE(4, "掃碼支付"),
    BALANCE(5, "餘額支付"),
    ;
    private final int value;
    private final String desc;

    PayType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public boolean equalsValue(Integer value){
        if (value == null) {
            return false;
        }
        return getValue() == value;
    }
}
