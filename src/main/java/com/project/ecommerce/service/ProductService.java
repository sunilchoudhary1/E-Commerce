package com.project.ecommerce.service;

import com.project.ecommerce.Enum.ProductCategory;
import com.project.ecommerce.Enum.ProductStatus;
import com.project.ecommerce.dto.RequestDto.ProductRequestDto;
import com.project.ecommerce.dto.ResponseDto.ProductResponseDto;
import com.project.ecommerce.exception.InvalidSellerException;
import com.project.ecommerce.model.Item;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.model.Seller;
import com.project.ecommerce.repository.ProductRepository;
import com.project.ecommerce.repository.SellerRepository;
import com.project.ecommerce.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {

        Seller seller;
        try{
            seller =  sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch (Exception e){
            throw new InvalidSellerException("Seller doesn't exist");
        }

        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        // add product to current products of seller
        seller.getProducts().add(product);
        sellerRepository.save(seller);  // saves both seller and product

        // prepare Response Dto
        return ProductTransformer.ProductToProductResponseDto(product);
    }

    public List<ProductResponseDto> getAllProductsByCategory(ProductCategory category){

        List<Product> products = productRepository.findByProductCategory(category);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product: products){
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponseDtos;
    }

    public List<ProductResponseDto> getAllProductsByPriceAndCategory(int price, String category){

        List<Product> products = productRepository.getAllProductsByPriceAndCategory(price,category);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product: products){
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponseDtos;
    }

    public void decreaseProductQuantity(Item item) throws Exception {

        Product product = item.getProduct();
        int quantity = item.getRequiredQuantity();
        int currentQuantity = product.getQuantity();
        if(quantity>currentQuantity){
            throw new Exception("Out of stock");
        }
        product.setQuantity(currentQuantity-quantity);
        if(product.getQuantity()==0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
    }

    public List<Product> getBysellerid(int id) {
        Seller seller = sellerRepository.findById(id).get();
        List<Product> products = seller.getProducts();
        return products;
    }

    public void deletebyid(int sellerid, int productid) {
        Seller seller = sellerRepository.findById(sellerid).get();
        Product p = productRepository.findById(productid).get();
        seller.getProducts().remove(p);
    }

    public List<Product> getCheapest() {
        List<Product> products = productRepository.findAll();
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });
        return products.subList(0,5);
    }

    public List<Product> getCostleast() {
        List<Product> products = productRepository.findAll();
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });
        return products.subList(products.size(),products.size()-5);
    }

    public List<Product> outOfstock() {
        List<Product> products = productRepository.findAll();
        List<Product> ans = new ArrayList<>();
        for(Product p : products)

        {
            if(p.getProductStatus()== ProductStatus.OUT_OF_STOCK)
            {
                ans.add(p);
            }
        }
        return ans;
    }

    public List<Product> availableProducts() {
        List<Product> products = productRepository.findAll();
        List<Product> ans = new ArrayList<>();
        for (Product p : products)
        {
            if(p.getProductStatus()==ProductStatus.AVAILABLE)
            {
                ans.add(p);
            }
        }
        return ans;
    }

    public List<Product> quantitylessThanten() {
        List<Product> products = productRepository.findAll();
        List<Product> ans = new ArrayList<>();
        for(Product p : products)
        {
            if(p.getQuantity()<10)
            {
                ans.add(p);
            }
        }
        return ans;
    }

    public Product cheapestInCategory(String category) {
        Product  p = productRepository.cheapestInCategory(category);
        return p;
    }

    public Product costlyInCategory(String category) {
        Product p  = productRepository.highestInCategory(category);
        return p;
    }
}

