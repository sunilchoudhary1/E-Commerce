package com.project.ecommerce.service;

import com.project.ecommerce.dto.RequestDto.SellerRequestDto;
import com.project.ecommerce.dto.ResponseDto.SellerResponseDto;
import com.project.ecommerce.exception.EmailAlreadyPresentException;
import com.project.ecommerce.model.Seller;
import com.project.ecommerce.repository.SellerRepository;
import com.project.ecommerce.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException {

//        Seller seller = new Seller();
//        seller.setName(sellerRequestDto.getName());
//        seller.setEmailId(sellerRequestDto.getEmailId());
//        seller.setMobNo(sellerRequestDto.getMobNo());
//        seller.setAge(sellerRequestDto.getAge());

        if(sellerRepository.findByEmailId(sellerRequestDto.getEmailId())!=null)
            throw new EmailAlreadyPresentException("Email Id already registered");

        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);
        Seller savedSeller = sellerRepository.save(seller);

        // prepare response Dto
        SellerResponseDto sellerResponseDto = SellerTransformer.SellerToSellerResponseDto(savedSeller);
        return sellerResponseDto;

    }

    public Seller sellerByEmail(String email) {
        Seller seller = sellerRepository.findByEmailId(email);
        return seller;
    }

    public Seller getById(int id) {
        Seller seller = sellerRepository.findById(id).get();
        return seller;
    }

    public List<Seller> allseller() {
        List<Seller> sellers = sellerRepository.findAll();
        return sellers;
    }

    public SellerResponseDto updateByEmail(String email, SellerRequestDto sellerRequestDto) {
       Seller seller =  sellerRepository.findByEmailId(email);
       seller.setName(sellerRequestDto.getName());
       seller.setAge(sellerRequestDto.getAge());
       seller.setMobNo(sellerRequestDto.getMobNo());
       SellerResponseDto sellerResponseDto = new SellerResponseDto();
       sellerResponseDto.setAge(seller.getAge());
       sellerResponseDto.setName(seller.getName());
       sellerRepository.save(seller);
       return sellerResponseDto;
    }
}
