package com.hmall.service.impl;

import com.hmall.domain.po.OrderDetail;
import com.hmall.mapper.OrderDetailMapper;
import com.hmall.service.IOrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 訂單詳情表 服務實現類
 * </p>
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
