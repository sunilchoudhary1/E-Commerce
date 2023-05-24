package com.project.ecommerce.service;

import com.project.ecommerce.dto.RequestDto.CustomerRequestDto;
import com.project.ecommerce.dto.ResponseDto.CustomerResponseDto;
import com.project.ecommerce.exception.MobileNoAlreadyPresentException;
import com.project.ecommerce.model.Cart;
import com.project.ecommerce.model.Customer;
import com.project.ecommerce.repository.CustomerRepository;
import com.project.ecommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNoAlreadyPresentException {

        if(customerRepository.findByMobNo(customerRequestDto.getMobNo())!=null)
            throw new MobileNoAlreadyPresentException("Sorry! Customer already exists!");

        // request dto -> customer
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);
        Cart cart = Cart.builder()
                .cartTotal(0)
                .numberOfItems(0)
                .customer(customer)
                .build();
        customer.setCart(cart);



        Customer savedCustomer = customerRepository.save(customer);  // customer and cart

        // prepare response dto
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);
    }
}
