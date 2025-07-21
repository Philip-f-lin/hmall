package com.hmall.pay.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.api.client.UserClient;
import com.hmall.common.exception.BizIllegalException;
import com.hmall.common.utils.BeanUtils;
import com.hmall.common.utils.UserContext;
import com.hmall.pay.domain.dto.PayApplyDTO;
import com.hmall.pay.domain.dto.PayOrderFormDTO;
import com.hmall.pay.domain.po.PayOrder;
import com.hmall.pay.enums.PayStatus;
import com.hmall.pay.mapper.PayOrderMapper;
import com.hmall.pay.service.IPayOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 支付訂單 服務實現類
 * </p>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements IPayOrderService {

    private final UserClient userClient;

    // private final TradeClient tradeClient;

    private final RabbitTemplate rabbitTemplate;

    @Override
    public String applyPayOrder(PayApplyDTO applyDTO) {
        // 1.冪等性校驗
        PayOrder payOrder = checkIdempotent(applyDTO);
        // 2.回傳結果
        return payOrder.getId().toString();
    }

    @Override
    @Transactional
    public void tryPayOrderByBalance(PayOrderFormDTO payOrderFormDTO) {
        // 1.查詢支付單
        PayOrder po = getById(payOrderFormDTO.getId());
        // 2.判斷狀態
        if(!PayStatus.WAIT_BUYER_PAY.equalsValue(po.getStatus())){
            // 訂單不是未支付，狀態異常
            throw new BizIllegalException("交易已支付或關閉！");
        }
        // 3.嘗試扣減餘額
        userClient.deductMoney(payOrderFormDTO.getPw(), po.getAmount());
        // 4.修改支付單狀態
        boolean success = markPayOrderSuccess(payOrderFormDTO.getId(), LocalDateTime.now());
        if (!success) {
            throw new BizIllegalException("交易已支付或關閉！");
        }
        // 5.修改訂單狀態
        // tradeClient.markOrderPaySuccess(po.getBizOrderNo());
        try {
            rabbitTemplate.convertAndSend("pay.direct", "pay.success", po.getBizOrderNo());
        } catch (Exception e) {
            log.error("發送支付狀態通知失敗，訂單 id: {}", po.getBizOrderNo(), e);
        }
    }

    public boolean markPayOrderSuccess(Long id, LocalDateTime successTime) {
        return lambdaUpdate()
                .set(PayOrder::getStatus, PayStatus.TRADE_SUCCESS.getValue())
                .set(PayOrder::getPaySuccessTime, successTime)
                .eq(PayOrder::getId, id)
                // 支付狀態的樂觀鎖判斷
                .in(PayOrder::getStatus, PayStatus.NOT_COMMIT.getValue(), PayStatus.WAIT_BUYER_PAY.getValue())
                .update();
    }


    private PayOrder checkIdempotent(PayApplyDTO applyDTO) {
        // 1.首先查詢支付單
        PayOrder oldOrder = queryByBizOrderNo(applyDTO.getBizOrderNo());
        // 2.判斷是否存在
        if (oldOrder == null) {
            // 不存在支付單，說明是第一次，寫入新的支付單並返回
            PayOrder payOrder = buildPayOrder(applyDTO);
            payOrder.setPayOrderNo(IdWorker.getId());
            save(payOrder);
            return payOrder;
        }
        // 3.舊單已經存在，判斷是否支付成功
        if (PayStatus.TRADE_SUCCESS.equalsValue(oldOrder.getStatus())) {
            // 已經支付成功，拋出異常
            throw new BizIllegalException("訂單已經支付！");
        }
        // 4.舊單已經存在，判斷是否已關閉
        if (PayStatus.TRADE_CLOSED.equalsValue(oldOrder.getStatus())) {
            // 已經關閉，拋出例外
            throw new BizIllegalException("訂單已關閉");
        }
        // 5.舊單已經存在，判斷支付管道是否一致
        if (!StringUtils.equals(oldOrder.getPayChannelCode(), applyDTO.getPayChannelCode())) {
            // 支付管道不一致，需要重置數據，然後重新申請支付單
            PayOrder payOrder = buildPayOrder(applyDTO);
            payOrder.setId(oldOrder.getId());
            payOrder.setQrCodeUrl("");
            updateById(payOrder);
            payOrder.setPayOrderNo(oldOrder.getPayOrderNo());
            return payOrder;
        }
        // 6.舊單已經存在，且可能是未支付或未提交，且支付管道一致，直接返回舊數據
        return oldOrder;
    }

    private PayOrder buildPayOrder(PayApplyDTO payApplyDTO) {
        // 1.資料轉換
        PayOrder payOrder = BeanUtils.toBean(payApplyDTO, PayOrder.class);
        // 2.初始化數據
        payOrder.setPayOverTime(LocalDateTime.now().plusMinutes(120L));
        payOrder.setStatus(PayStatus.WAIT_BUYER_PAY.getValue());
        payOrder.setBizUserId(UserContext.getUser());
        return payOrder;
    }
    public PayOrder queryByBizOrderNo(Long bizOrderNo) {
        return lambdaQuery()
                .eq(PayOrder::getBizOrderNo, bizOrderNo)
                .one();
    }
}
