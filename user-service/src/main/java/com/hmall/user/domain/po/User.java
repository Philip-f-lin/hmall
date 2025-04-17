package com.hmall.user.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hmall.user.enums.UserStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 使用者表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 使用者名稱
     */
    private String username;

    /**
     * 密碼，加密存儲
     */
    private String password;

    /**
     * 註冊手機號碼
     */
    private String phone;

    /**
     * 創建時間
     */
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 使用狀態（1正常 2凍結）
     */
    private UserStatus status;

    /**
     * 帳戶餘額
     */
    private Integer balance;


}
