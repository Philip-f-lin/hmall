package com.hmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmall.order.domain.dto.OrderFormDTO;
import com.hmall.order.domain.po.Order;

/**
 * <p>
 *  服務類
 * </p>
 */
public interface IOrderService extends IService<Order> {

    Long createOrder(OrderFormDTO orderFormDTO);

    void markOrderPaySuccess(Long orderId);
}
