package com.anunez.mcs.orders.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.anunez.mcs.orders.dto.BaseResponse;
import com.anunez.mcs.orders.dto.OrderReq;
import com.anunez.mcs.orders.dto.OrderRes;
import com.anunez.mcs.orders.model.Order;
import com.anunez.mcs.orders.repository.OrderRepository;
import com.anunez.mcs.orders.service.HttpClientService;
import com.anunez.mcs.orders.service.OrderService;
import com.anunez.mcs.orders.utils.OrderMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final HttpClientService httpClientService;

    public OrderServiceImpl(OrderRepository orderRepository, HttpClientService httpClientService) {
        this.orderRepository = orderRepository;
        this.httpClientService = httpClientService;
    }

    @Override
    public List<OrderRes> getAllOrders() {
        log.info("Fetching all orders");
        return orderRepository.findAll().stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderRes> getOrderById(Long id) {
        log.info("Fetching order with id: {}", id);
        return orderRepository.findById(id).map(OrderMapper::toDto);
    }

    @Override
    public OrderRes createOrder(OrderReq order) {
        log.info("Creating new order: {}", order);

        BaseResponse response = httpClientService.sendRequest(order);

        if (response == null || response.hasError()) {
            String errorMsg = (response == null) ? "No response from inventory service" : String.join(", ", response.errors());
            log.error("Failed to create order due to inventory service error: {}", errorMsg);
            throw new RuntimeException("Failed to create order: " + errorMsg);
        }

        Order newOrder = OrderMapper.toEntity(order);
        return OrderMapper.toDto(orderRepository.save(newOrder));
    }

    @Override
    public void deleteOrder(Long id) {
        log.info("Deleting order with id: {}", id);
        orderRepository.deleteById(id);
    }
    
}
