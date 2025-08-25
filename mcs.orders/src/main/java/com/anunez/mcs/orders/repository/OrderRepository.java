package com.anunez.mcs.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anunez.mcs.orders.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
