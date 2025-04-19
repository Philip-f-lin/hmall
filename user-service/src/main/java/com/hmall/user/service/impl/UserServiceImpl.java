package com.hmall.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.common.exception.BadRequestException;
import com.hmall.common.exception.BizIllegalException;
import com.hmall.common.exception.ForbiddenException;
import com.hmall.common.utils.UserContext;
import com.hmall.user.config.JwtProperties;
import com.hmall.user.domain.dto.LoginFormDTO;
import com.hmall.user.domain.po.User;
import com.hmall.user.domain.vo.UserLoginVO;
import com.hmall.user.enums.UserStatus;
import com.hmall.user.mapper.UserMapper;
import com.hmall.user.service.IUserService;
import com.hmall.user.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * <p>
 * 使用者表 服務實作類別
 * </p>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final PasswordEncoder passwordEncoder;

    private final JwtTool jwtTool;

    private final JwtProperties jwtProperties;

    @Override
    public UserLoginVO login(LoginFormDTO loginDTO) {
        // 1.資料校驗
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        // 2.依使用者名稱或手機號碼查詢
        User user = lambdaQuery().eq(User::getUsername, username).one();
        Assert.notNull(user, "使用者名稱錯誤");
        // 3.校驗是否停用
        if (user.getStatus() == UserStatus.FROZEN) {
            throw new ForbiddenException("用戶被凍結");
        }
        // 4.校驗密碼
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("使用者名稱或密碼錯誤");
        }
        // 5.生成 TOKEN
        String token = jwtTool.createToken(user.getId(), jwtProperties.getTokenTTL());
        // 6.封裝 VO 返回
        UserLoginVO vo = new UserLoginVO();
        vo.setUserId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setBalance(user.getBalance());
        vo.setToken(token);
        return vo;
    }

    @Override
    public void deductMoney(String pw, Integer totalFee) {
        log.info("開始扣款");
        // 1.校驗密碼
        User user = getById(UserContext.getUser());
        if(user == null || !passwordEncoder.matches(pw, user.getPassword())){
            // 密碼錯誤
            throw new BizIllegalException("使用者密碼錯誤");
        }

        // 2.試試扣款
        try {
            baseMapper.updateMoney(UserContext.getUser(), totalFee);
        } catch (Exception e) {
            throw new RuntimeException("扣款失敗，可能是餘額不足！", e);
        }
        log.info("扣款成功");
    }
}
