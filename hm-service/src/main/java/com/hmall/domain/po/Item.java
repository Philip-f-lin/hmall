package com.hmall.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * SKU名稱
     */
    private String name;

    /**
     * 價格（分）
     */
    private Integer price;

    /**
     * 庫存數量
     */
    private Integer stock;

    /**
     * 商品圖片
     */
    private String image;

    /**
     * 類別目名稱
     */
    private String category;

    /**
     * 品牌名稱
     */
    private String brand;

    /**
     * 規格
     */
    private String spec;

    /**
     * 銷量
     */
    private Integer sold;

    /**
     * 評論數
     */
    private Integer commentCount;

    /**
     * 是否為推廣廣告，true/false
     */
    @TableField("isAD")
    private Boolean isAD;

    /**
     * 商品狀態 1-正常，2-下架，3-刪除
     */
    private Integer status;

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
     * 修改人
     */
    private Long updater;


}
