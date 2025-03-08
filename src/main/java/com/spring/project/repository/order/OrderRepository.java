package com.spring.project.repository.order;

import com.spring.project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findListByUserId(Long id);
}
