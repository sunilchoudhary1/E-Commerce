package com.project.ecommerce.controller;

import com.project.ecommerce.dto.RequestDto.CheckoutCartRequestDto;
import com.project.ecommerce.dto.RequestDto.ItemRequestDto;
import com.project.ecommerce.dto.ResponseDto.CartResponseDto;
import com.project.ecommerce.dto.ResponseDto.OrderResponseDto;
import com.project.ecommerce.model.Item;
import com.project.ecommerce.service.CartService;
import com.project.ecommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto) throws Exception {

        try {
            Item savedItem = itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.saveCart(itemRequestDto.getCustomerId(), savedItem);
            return new ResponseEntity(cartResponseDto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout")
    public OrderResponseDto checkOutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {

        return cartService.checkOutCart(checkoutCartRequestDto);
    }
}