package com.project.ecommerce.transformer;

import com.project.ecommerce.dto.RequestDto.CustomerRequestDto;
import com.project.ecommerce.dto.ResponseDto.CustomerResponseDto;
import com.project.ecommerce.model.Customer;

public class CustomerTransformer {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){


        return Customer.builder()
                .age(customerRequestDto.getAge())
                .name(customerRequestDto.getName())
                .address(customerRequestDto.getAddress())
                .emailId(customerRequestDto.getEmailId())
                .mobNo(customerRequestDto.getMobNo())
                .build();
    }

    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){

        return CustomerResponseDto.builder()
                .name(customer.getName())
                .message("Welcome" + customer.getName()+ " to Amazon !!!")
                .build();
    }
}
