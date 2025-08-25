package com.anunez.mcs.orders.service;

import java.util.List;
import java.util.Optional;

import com.anunez.mcs.orders.dto.OrderReq;
import com.anunez.mcs.orders.dto.OrderRes;

public interface OrderService {
    List<OrderRes> getAllOrders();
    Optional<OrderRes> getOrderById(Long id);
    OrderRes createOrder(OrderReq order);
    void deleteOrder(Long id);
}
