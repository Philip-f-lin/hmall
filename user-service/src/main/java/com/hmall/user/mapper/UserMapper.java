package com.hmall.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hmall.user.domain.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 用戶表 Mapper 介面
 * </p>
 */
public interface UserMapper extends BaseMapper<User> {
    @Update("update user set balance = balance - ${totalFee} where id = #{userId}")
    void updateMoney(@Param("userId") Long userId, @Param("totalFee") Integer totalFee);
}
