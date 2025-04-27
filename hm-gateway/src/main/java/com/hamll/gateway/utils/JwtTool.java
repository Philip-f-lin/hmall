package com.hamll.gateway.utils;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.hmall.common.exception.UnauthorizedException;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.time.Duration;
import java.util.Date;

@Component
public class JwtTool {
    private final JWTSigner jwtSigner;

    public JwtTool(KeyPair keyPair) {
        this.jwtSigner = JWTSignerUtil.createSigner("rs256", keyPair);
    }

    /**
     * 創建 access-token
     *
     * @param userId 使用者資訊
     * @return access-token
     */
    public String createToken(Long userId, Duration ttl) {
        // 1.生成 jws
        return JWT.create()
                .setPayload("user", userId)
                .setExpiresAt(new Date(System.currentTimeMillis() + ttl.toMillis()))
                .setSigner(jwtSigner)
                .sign();
    }

    /**
     * 解析 token
     *
     * @param token token
     * @return 解析刷新 token 得到的使用者訊息
     */
    public Long parseToken(String token) {
        // 1.校驗 token 是否為空
        if (token == null) {
            throw new UnauthorizedException("未登入");
        }
        // 2.校驗並解析 jwt
        JWT jwt;
        try {
            jwt = JWT.of(token).setSigner(jwtSigner);
        } catch (Exception e) {
            throw new UnauthorizedException("無效的 token", e);
        }
        // 2.校驗 jwt 是否有效
        if (!jwt.verify()) {
            // 驗證失敗
            throw new UnauthorizedException("無效的 token");
        }
        // 3.校驗是否過期
        try {
            JWTValidator.of(jwt).validateDate();
        } catch (ValidateException e) {
            throw new UnauthorizedException("token 已經過期");
        }
        // 4.資料格式校驗
        Object userPayload = jwt.getPayload("user");
        if (userPayload == null) {
            // 數據為空
            throw new UnauthorizedException("無效的 token");
        }

        // 5.資料解析
        try {
           return Long.valueOf(userPayload.toString());
        } catch (RuntimeException e) {
            // 資料格式有誤
            throw new UnauthorizedException("無效的 token");
        }
    }
}