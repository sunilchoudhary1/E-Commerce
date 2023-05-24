package com.project.ecommerce.controller;

import com.project.ecommerce.dto.RequestDto.OrderRequestDto;
import com.project.ecommerce.dto.ResponseDto.OrderResponseDto;
import com.project.ecommerce.repository.OrderedRepository;
import com.project.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    // API to order and item individually
    @Autowired
    OrderService orderService;
    @Autowired
    private OrderedRepository orderedRepository;

    @PostMapping("/place")
    public OrderResponseDto placeDirectOrder(@RequestBody OrderRequestDto orderRequestDto) throws Exception {

        return orderService.placeOrder(orderRequestDto);
    }
}