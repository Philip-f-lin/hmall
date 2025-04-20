package com.hmall.pay.enums;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

@Getter
public enum PayChannel {
    wxPay("微信支付"),
    aliPay("支付寶支付"),
    balance("餘額支付"),
    ;

    private final String desc;

    PayChannel(String desc) {
        this.desc = desc;
    }

    public static String desc(String value){
        if (StrUtil.isBlank(value)) {
            return "";
        }
        return PayChannel.valueOf(value).getDesc();
    }
}
