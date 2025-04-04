package com.hmall.domain.po;

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
 * 
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`order`")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 訂單id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 總金額，單位為分
     */
    private Integer totalFee;

    /**
     * 支付類型，1、支付寶，2、微信，3、扣減餘額
     */
    private Integer paymentType;

    /**
     * 使用者id
     */
    private Long userId;

    /**
     * 訂單的狀態，1、未付款 2、已付款,未發貨 3、已發貨,未確認 4、確認收貨，交易成功 5、交易取消，訂單關閉 6、交易結束，已評價
     */
    private Integer status;

    /**
     * 創建時間
     */
    private LocalDateTime createTime;

    /**
     * 支付時間
     */
    private LocalDateTime payTime;

    /**
     * 出貨時間
     */
    private LocalDateTime consignTime;

    /**
     * 交易完成時間
     */
    private LocalDateTime endTime;

    /**
     * 交易關閉時間
     */
    private LocalDateTime closeTime;

    /**
     * 評價時間
     */
    private LocalDateTime commentTime;

    /**
     * 更新時間
     */
    private LocalDateTime updateTime;


}
