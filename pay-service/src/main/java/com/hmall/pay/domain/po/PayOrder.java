package com.hmall.pay.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 支付訂單
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pay_order")
public class PayOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 業務訂單編號
     */
    private Long bizOrderNo;

    /**
     * 支付單號
     */
    private Long payOrderNo;

    /**
     * 支付用戶id
     */
    private Long bizUserId;

    /**
     * 支付管道編碼
     */
    private String payChannelCode;

    /**
     * 支付金額，單位分
     */
    private Integer amount;

    /**
     * 支付類型，1：h5,2:小程序，3：公眾號，4：掃碼，5：餘額支付
     */
    private Integer payType;

    /**
     * 支付狀態，0：待提交，1:待支付，2：支付超時或取消，3：支付成功
     */
    private Integer status;

    /**
     * 拓展字段，用於傳遞不同管道單獨處理的字段
     */
    private String expandJson;

    /**
     * 第三方返回業務碼
     */
    private String resultCode;

    /**
     * 第三方回傳提示訊息
     */
    private String resultMsg;

    /**
     * 支付成功時間
     */
    private LocalDateTime paySuccessTime;

    /**
     * 支付超時時間
     */
    private LocalDateTime payOverTime;

    /**
     * 支付二維碼連結
     */
    private String qrCodeUrl;

    /**
     * 創建時間
     */
    private LocalDateTime createTime;

    /**
     * 更新時間
     */
    private LocalDateTime updateTime;

    /**
     * 創建人
     */
    private Long creater;

    /**
     * 更新人
     */
    private Long updater;

    /**
     * 邏輯刪除
     */
    private Boolean isDelete;


}
