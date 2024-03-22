package org.example.repository;

import org.example.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    //Optional<Seller> findBySellerID (long sellerID);
    boolean existsBySellerName(String sellerName);
    //Seller findSellerBySellerName (String sellerName);

}
