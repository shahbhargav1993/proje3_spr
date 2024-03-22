package org.example.controller;

import org.example.entity.Product;
import org.example.entity.Seller;
import org.example.exception.ProductException;
import org.example.exception.SellerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.service.SellerService;

import java.util.List;

@RestController
public class SellerController
{
    SellerService sellerService;

    public SellerController(SellerService sellerService)
    {
        this.sellerService = sellerService;
    }

    @GetMapping("/sellers")
    public ResponseEntity<List<Seller>> getAllSellers(){
        List<Seller> sellers = sellerService.getAllSellers();
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    @PostMapping("/sellers")
    public ResponseEntity<Seller> addSeller(@RequestBody Seller s){
        Seller seller = sellerService.saveSeller(s);
        return new ResponseEntity<>(seller, HttpStatus.CREATED);
    }

    @GetMapping("/sellers/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable long id)
    {
        Seller seller;
        try
        {
            seller = sellerService.getSellerById(id);
        } catch (SellerException e)
        {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }


    @GetMapping("/sellers/{sellerName}/products")
    public ResponseEntity<List<Product>> getSellerProductsBySellerName(@PathVariable String sellerName)
    {

        List<Product> products;
        try {
            products = sellerService.getSellerProductsBySellerName(sellerName);
        } catch (SellerException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/sellers/{sellerName}")
    public ResponseEntity<String> deleteSeller(@PathVariable String sellerName){
        try{
            sellerService.deleteSeller(sellerName);
            return new ResponseEntity<>("Seller deleted successfully", HttpStatus.OK);
        }
        catch(SellerException e){
            throw new RuntimeException(e);
        }
    }
}
