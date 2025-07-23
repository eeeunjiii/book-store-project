package com.spring.project.controller;

import com.spring.project.entity.Cart;
import com.spring.project.entity.CartItem;
import com.spring.project.entity.Item;
import com.spring.project.entity.User;
import com.spring.project.request.CartDto;
import com.spring.project.security.PrincipalDetails;
import com.spring.project.service.cart.CartItemService;
import com.spring.project.service.cart.CartService;
import com.spring.project.service.item.ItemService;
import com.spring.project.service.member.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final ItemService itemService;
    private final CartItemService cartItemService;

    @GetMapping("/{userId}/cart")
    public String cartForm(@PathVariable("userId") Long userId,
                           Model model) {
        Cart cart=cartService.findCartByUserId(userId);
        List<CartItem> cartItems = cartItemService.findCartItemsByCart(cart);

        int totalPrice=0;
        for(CartItem cartItem:cartItems) {
            totalPrice+=cartItem.getQuantity()*cartItem.getItem().getPrice();
        }

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cartItems", cartItems);

        return "user/cartForm";
    }

    @PostMapping("/items/{itemId}/cart/add")
    public ResponseEntity<String> addItemToCartForm(@PathVariable("itemId") Long itemId,
                                                    @AuthenticationPrincipal PrincipalDetails principal,
                                                    @RequestBody CartDto cartDto,
                                                    RedirectAttributes redirectAttributes) {
        User user=userService.findUserByEmail(principal.getUsername());
        Item item=itemService.findById(itemId);
        int quantity=cartDto.getQuantity();

        cartService.addItemToCart(user, item, quantity);

        redirectAttributes.addAttribute("itemId", itemId);

        return ResponseEntity.ok("장바구니 추가 성공");
    }

    @PostMapping("/items/{itemId}/cart/delete")
    public ResponseEntity<String> deleteItemFromCart(@PathVariable("itemId") Long iemId,
                                                     @AuthenticationPrincipal PrincipalDetails principal) {
        User user = userService.findUserByEmail(principal.getUsername());
        Long cartId = cartService.findCartByUserId(user.getId()).getId();
        Item item = itemService.findById(iemId);

        CartItem removeCartItem = cartItemService.findByCartIdAndItemId(cartId, item.getId());
        cartItemService.delete(removeCartItem);

        return ResponseEntity.ok("Success in removing item from cart");
    }
}
