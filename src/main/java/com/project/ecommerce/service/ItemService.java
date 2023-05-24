package com.project.ecommerce.service;

import com.project.ecommerce.Enum.ProductStatus;
import com.project.ecommerce.dto.RequestDto.ItemRequestDto;
import com.project.ecommerce.exception.InvalidCustomerException;
import com.project.ecommerce.exception.InvalidProductException;
import com.project.ecommerce.model.Customer;
import com.project.ecommerce.model.Item;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.repository.CustomerRepository;
import com.project.ecommerce.repository.ItemRepository;
import com.project.ecommerce.repository.ProductRepository;
import com.project.ecommerce.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ProductRepository productRepository;

    public Item addItem(ItemRequestDto itemRequestDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer Id is invalid !!");
        }

        Product product;
        try{
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        }
        catch(Exception e){
            throw new InvalidProductException("Product doesn't exist");
        }

        if(itemRequestDto.getRequiredQuantity()>product.getQuantity() || product.getProductStatus()!= ProductStatus.AVAILABLE){
            throw new Exception("Product out of Stock");
        }

        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto);
        // item.setCart(customer.getCart());
        item.setProduct(product);

        product.getItemList().add(item);
        return itemRepository.save(item);
    }
}