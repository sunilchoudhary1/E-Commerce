package com.project.ecommerce.controller;

import com.project.ecommerce.dto.RequestDto.CustomerRequestDto;
import com.project.ecommerce.dto.ResponseDto.CustomerResponseDto;
import com.project.ecommerce.exception.MobileNoAlreadyPresentException;
import com.project.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws MobileNoAlreadyPresentException {

        return customerService.addCustomer(customerRequestDto);
    }
}
