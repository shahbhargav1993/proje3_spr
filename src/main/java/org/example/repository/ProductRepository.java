package org.example.repository;

import org.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product,Long> {

    @Query("from Product where sellerName=:sellerName")
    List<Product> getProductsBySellerName(@Param("sellerName") String sellerName);

}
