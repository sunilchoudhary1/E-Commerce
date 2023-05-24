package com.project.ecommerce.controller;

import com.project.ecommerce.Enum.ProductCategory;
import com.project.ecommerce.dto.RequestDto.ProductRequestDto;
import com.project.ecommerce.dto.RequestDto.SellerRequestDto;
import com.project.ecommerce.dto.ResponseDto.ProductResponseDto;
import com.project.ecommerce.dto.ResponseDto.SellerResponseDto;
import com.project.ecommerce.exception.InvalidSellerException;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.model.Seller;
import com.project.ecommerce.service.ProductService;
import com.project.ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        try{
            SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-seller-by-email")
    public Seller sellerByEmail(@RequestParam String email)
    {
        return sellerService.sellerByEmail(email);
    }
    @GetMapping("/get-by-id/{id}")
    public Seller getById(@PathVariable(value = "id") int id)
    {
        return sellerService.getById(id);
    }
    @GetMapping("/get-all-seller")
    public List<Seller> allseller()
    {
        return sellerService.allseller();
    }
    @PutMapping("/update-by-sellerid")
    public SellerResponseDto updateByEmail(@RequestParam String email, @RequestBody SellerRequestDto sellerRequestDto)
    {
        return sellerService.updateByEmail(email, sellerRequestDto);
    }

}
