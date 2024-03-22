package org.example.service;

import org.example.entity.Product;
import org.example.entity.Seller;
import org.example.exception.ProductException;
import org.example.exception.SellerException;
import org.example.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService
{
    SellerRepository sellerRepository;
    @Autowired
    public SellerService(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }
    public List<Seller> getAllSellers(){
        return sellerRepository.findAll();
    }
    public Seller saveSeller(Seller s){
        return sellerRepository.save(s);
    }


    public void deleteSeller(Long id) throws SellerException {

        Optional<Seller> sellerOptional = sellerRepository.findById(id);
        if (sellerOptional.isPresent()) {
            Seller seller = sellerOptional.get();
            sellerRepository.delete(seller);
        } else {
            throw new SellerException("Cannot find seller with id: " + id);
        }
    }

    public boolean doesSellerExist(String sellerName) {
        return sellerRepository.existsBySellerName(sellerName);

    }



}