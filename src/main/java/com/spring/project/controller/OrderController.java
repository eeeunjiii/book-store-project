package com.spring.project.controller;

import com.spring.project.constant.Payment;
import com.spring.project.entity.*;
import com.spring.project.request.OrderInfoDto;
import com.spring.project.request.OrderItemDto;
import com.spring.project.security.PrincipalDetails;
import com.spring.project.service.cart.CartService;
import com.spring.project.service.item.ItemService;
import com.spring.project.service.member.UserService;
import com.spring.project.service.order.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;
    private final ItemService itemService;

    @ModelAttribute("payments")
    public Payment[] payments() {
        return Payment.values();
    }

    @GetMapping("/items/order")
    public String orderFormFromItem(Model model, @RequestParam("itemId") Long itemId,
                                    @RequestParam("quantity") int quantity,
                                    @AuthenticationPrincipal PrincipalDetails principal) {
        User user=userService.findUserByEmail(principal.getUsername());
        Item item=itemService.findById(itemId);

        model.addAttribute("user", user);
        model.addAttribute("userPoint", user.getPoint());
        model.addAttribute("item", item);
        model.addAttribute("quantity", quantity);
        model.addAttribute("totalPrice", quantity*item.getPrice());
        model.addAttribute("infoForm", new OrderInfoDto());

        return "items/orderFromItemForm";
    }

    @PostMapping("/items/order")
    public String createOrderFromItem(@AuthenticationPrincipal PrincipalDetails principal,
                                      @Validated @ModelAttribute("infoForm") OrderInfoDto infoForm,
                                      BindingResult bindingResult,
                                      @RequestParam("quantity") int quantity, @RequestParam("itemId") Long itemId) {
        if(bindingResult.hasErrors()) {
            return "items/orderFromItemForm";
        }

        User user=userService.findUserByEmail(principal.getUsername());
        Order order=orderService.createOrder(user, itemId, quantity);
        order.updateOrderInfo(infoForm);
        orderService.save(order);

        return "items/orderFromItemForm";
    }

    @GetMapping("/items/cart/order")
    public String orderFormFromCart(Model model, @AuthenticationPrincipal PrincipalDetails principal, HttpSession session) { // 아직 OrderItem으로 저장하기 전 -> dto로 미리 주문 내역 출력
        User user=userService.findUserByEmail(principal.getUsername());
        List<OrderItemDto> orderItemDtoList=(List<OrderItemDto>) session.getAttribute("orderItems");

        int totalPrice=0;
        for(OrderItemDto dto:orderItemDtoList) {
            Item item=itemService.findById(dto.getItemId());
            totalPrice+=dto.getQuantity()*item.getPrice();
        }

        model.addAttribute("cartItems", orderItemDtoList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("infoForm", new OrderInfoDto());
        model.addAttribute("user", user);
        model.addAttribute("userPoint", user.getPoint());

        return "items/orderFromCartForm";
    }

    @PostMapping("/items/cart/order")
    public String createOrderFromCart(@AuthenticationPrincipal PrincipalDetails principal,
                                      @Validated @ModelAttribute("infoForm") OrderInfoDto infoForm,
                                      BindingResult bindingResult,
                                      HttpServletRequest request) { // OrderItem으로 저장하는 과정
        if(bindingResult.hasErrors()) {
            return "items/orderFromCartForm";
        }

        User user=userService.findUserByEmail(principal.getUsername());

        HttpSession session=request.getSession();
        List<OrderItemDto> orderItemDtoList=(List<OrderItemDto>) session.getAttribute("orderItems");

        Order order=orderService.createOrder(user, orderItemDtoList);
        order.updateOrderInfo(infoForm);
        orderService.save(order);

        return "items/orderFromCartForm";
    }

    @PostMapping("/items/add/order")
    public ResponseEntity<String> addToOrder(HttpServletRequest request,
                                             @RequestBody OrderItemDto orderItemDto) {
        HttpSession session=request.getSession();
        List<OrderItemDto> orderItemDtoList=(List<OrderItemDto>) session.getAttribute("orderItems");

        if(orderItemDtoList==null) {
            orderItemDtoList=new ArrayList<>();
        }

        if(!orderItemDtoList.contains(orderItemDto)) {
            orderItemDtoList.add(orderItemDto);
        }

        log.info("orderItemDtoList: {}", orderItemDtoList);

        session.setAttribute("orderItems", orderItemDtoList);
        return ResponseEntity.ok("Success add to order");
    }

    @PostMapping("/items/delete/order")
    public ResponseEntity<String> deleteFromOrder(HttpServletRequest request,
                                                  @RequestBody OrderItemDto orderItemDto) {
        HttpSession session=request.getSession();
        List<OrderItemDto> orderItemDtoList=(List<OrderItemDto>) session.getAttribute("orderItems");

        if(orderItemDtoList!=null) {
            orderItemDtoList.removeIf(orderItem -> orderItem.getItemId().equals(orderItemDto.getItemId()));
            session.setAttribute("orderItems", orderItemDtoList);
        }

        return ResponseEntity.ok("Success remove from order");
    }
}
