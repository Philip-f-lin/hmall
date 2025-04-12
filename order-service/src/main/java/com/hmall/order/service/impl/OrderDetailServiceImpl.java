package com.hmall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.order.domain.po.OrderDetail;
import com.hmall.order.mapper.OrderDetailMapper;
import com.hmall.order.service.IOrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 訂單詳情表 服務實現類
 * </p>
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
