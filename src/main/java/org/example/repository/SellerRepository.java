package org.example.repository;

import org.example.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller,
        Long>, CrudRepository<Seller, Long>
{
    //Optional<Seller> findSellerBySellerName(String sellerName); //
    // This method is used to find a seller by sellerName

    @Query("from Seller where sellerName=:sellerName")
    List<Seller> findSellerBySellerName(@Param("sellerName") String sellerName);

    @Query("from Seller where sellerName=:sellerName")
    List<Seller> deleteSeller(@Param("sellerName") String sellerName);
}

