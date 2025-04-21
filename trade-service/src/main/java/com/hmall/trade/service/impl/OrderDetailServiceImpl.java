package com.hmall.trade.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.trade.domain.po.OrderDetail;
import com.hmall.trade.mapper.OrderDetailMapper;
import com.hmall.trade.service.IOrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 訂單詳情表 服務實現類
 * </p>
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
