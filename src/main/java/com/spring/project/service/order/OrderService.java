package com.spring.project.service.order;

import com.spring.project.entity.Item;
import com.spring.project.entity.Order;
import com.spring.project.entity.OrderItem;
import com.spring.project.entity.User;
import com.spring.project.repository.order.OrderItemRepository;
import com.spring.project.repository.order.OrderRepository;
import com.spring.project.request.OrderItemDto;
import com.spring.project.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemService itemService;

    @Transactional
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public Order createOrder(User user, Long itemId, int quantity) {
        Order order=Order.createOrder(user);
        orderRepository.save(order);

        Item item=itemService.findById(itemId);

        int totalPrice=quantity*item.getPrice();

        OrderItem orderItem= OrderItem.builder()
                .order_count(quantity)
                .order(order)
                .item(item)
                .build();
        orderItemRepository.save(orderItem);
        order.getOrderItems().add(orderItem);

        order.updateTotalPrice(totalPrice);
        orderRepository.save(order);

        return order;
    }

    @Transactional
    public Order createOrder(User user, List<OrderItemDto> orderItemList) {
        Order order=Order.createOrder(user);
        orderRepository.save(order);

        int totalPrice=0;
        for(OrderItemDto dto:orderItemList) {
            Item item=itemService.findById(dto.getItemId());

            totalPrice+=dto.getQuantity()* item.getPrice();

            OrderItem orderItem = dto.toEntity(order, item);
            orderItemRepository.save(orderItem);
            order.getOrderItems().add(orderItem);
        }
        order.updateTotalPrice(totalPrice);
        orderRepository.save(order);

        return order;
    }

    public List<Order> findOrderListByUser(User user) {
        return orderRepository.findListByUserId(user.getId());
    }
}
