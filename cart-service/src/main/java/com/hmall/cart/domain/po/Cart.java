package com.hmall.cart.domain.po;

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
 * 訂單詳情表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 購物車條目id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 使用者id
     */
    private Long userId;

    /**
     * sku商品id
     */
    private Long itemId;

    /**
     * 購買數量
     */
    private Integer num;

    /**
     * 商品標題
     */
    private String name;

    /**
     * 商品動態屬性鍵值集
     */
    private String spec;

    /**
     * 價格,單位：分
     */
    private Integer price;

    /**
     * 商品圖片
     */
    private String image;

    /**
     * 創建時間
     */
    private LocalDateTime createTime;

    /**
     * 更新時間
     */
    private LocalDateTime updateTime;


}
