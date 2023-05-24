package com.project.ecommerce.repository;

import com.project.ecommerce.Enum.ProductCategory;
import com.project.ecommerce.model.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductCategory(ProductCategory productCategory);

    @Query(value = "select p from Product p where p.price > :price and p.productCategory=:category")
    List<Product> getAllProductsByPriceAndCategory(int price, String category);
//    @Query(value = "SELECT p FROM Product p ORDER BY p.price ASC")
//    List<Product> cheapestfiveproduct(PageRequest pageable);
//    @Query(value = "select p from Product p order by p.price DESC")
//    List<Product> costliestFive();
    @Query(value = "select p from Product p where p.quantity < 10")
    List<Product> productLessThanTen();
    @Query(value = "SELECT p FROM Product ORDER BY p.price ASC LIMIT 1", nativeQuery = true)
    Product cheapestInCategory(String  category);
    @Query(value = "SELECT * FROM Product ORDER BY Product.price DESC LIMIT 1", nativeQuery = true)
    Product highestInCategory(String category);
}
