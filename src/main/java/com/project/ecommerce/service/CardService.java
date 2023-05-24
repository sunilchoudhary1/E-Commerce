package com.project.ecommerce.service;

import com.project.ecommerce.dto.RequestDto.CardRequestDto;
import com.project.ecommerce.dto.ResponseDto.CardResponseDto;
import com.project.ecommerce.exception.InvalidCustomerException;
import com.project.ecommerce.model.Card;
import com.project.ecommerce.model.Customer;
import com.project.ecommerce.repository.CustomerRepository;
import com.project.ecommerce.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    CustomerRepository customerRepository;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {

        Customer customer = customerRepository.findByMobNo(cardRequestDto.getMobNo());
        if(customer==null){
            throw new InvalidCustomerException("Sorry! The customer doesn't exists");
        }

        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);

        customer.getCards().add(card);
        customerRepository.save(customer);

        // response dto
        return CardResponseDto.builder()
                .customerName(customer.getName())
                .cardNo(card.getCardNo())
                .build();

    }
}
