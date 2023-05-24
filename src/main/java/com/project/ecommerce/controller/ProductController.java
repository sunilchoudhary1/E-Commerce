package com.project.ecommerce.controller;

import com.project.ecommerce.Enum.ProductCategory;
import com.project.ecommerce.dto.RequestDto.ProductRequestDto;
import com.project.ecommerce.dto.ResponseDto.ProductResponseDto;
import com.project.ecommerce.exception.InvalidSellerException;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) throws InvalidSellerException {

        return productService.addProduct(productRequestDto);
    }

    // get all products of a particular category
    @GetMapping("/get/{category}")
    public List<ProductResponseDto> getAllProductsByCategory(@PathVariable("category") ProductCategory category) {
        return productService.getAllProductsByCategory(category);
    }

    @GetMapping("/get/{price}/{productcategory}")
    public List<ProductResponseDto> getAllProductsByPriceAndCategory(
            @PathVariable("price") int price,
            @PathVariable("productcategory") String productCategory) {

        return productService.getAllProductsByPriceAndCategory(price, productCategory);
    }
    @GetMapping("get-by-seller-id/{id}")
    public List<Product> getBySellerid(@PathVariable(value = "id") int id)
    {
        return productService.getBysellerid(id);
    }
    @DeleteMapping("deleteBysellerid/{sellerid}/{productid}")
    public void deletebyid(@PathVariable(value = "sellerid") int sellerid, @PathVariable(value = "productid") int productid)
    {
         productService.deletebyid(sellerid,productid);
    }
    @GetMapping("/top-5-cheapest")
    public List<Product> getCheapest()
    {
        return productService.getCheapest();
    }
    @GetMapping("/top-5-costly")
    public List<Product> getCostleast()
    {
        return productService.getCostleast();
    }
    @GetMapping("/out-of-stock")
    public List<Product> outOfstock()
    {
        return productService.outOfstock();
    }
    @GetMapping("/available-products")
    public List<Product> availableProducts()
    {
        return productService.availableProducts();
    }
    @GetMapping("/quantity-less-than-ten")
    public List<Product> quantitylessThanten()
    {
        return productService.quantitylessThanten();
    }
    @GetMapping("/cheapest-in-category")
    public Product cheapestInCategory(@RequestParam String category)
    {
        return productService.cheapestInCategory(category);
    }
    @GetMapping("/costly-in-category")
    public Product costlyInCategory(@RequestParam String category)
    {
        return productService.costlyInCategory(category);
    }
}
