package com.spring.project.service.order;

import com.spring.project.entity.OrderItem;
import com.spring.project.repository.order.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> findOrderItemListByOrderId(Long orderId) {
        return orderItemRepository.findOrderItemByOrderId(orderId);
    }
}
