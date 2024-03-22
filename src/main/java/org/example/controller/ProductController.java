package org.example.controller;

import org.example.entity.Product;
import org.example.exception.ProductException;
import org.example.service.ProductService;
import org.example.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    ProductService productService;
    SellerService sellerService;
    @Autowired
    public ProductController(ProductService productService){

        this.productService = productService;
    }


    //get all products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> product = productService.getAllProducts();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    //get products by id
    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id){
        try{
            Product p = productService.getProductById(id);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }catch (ProductException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //add product
//    @PostMapping("/products")
//    public ResponseEntity<Object> addProduct(@RequestBody Product p) {
//        try {
//
//            if (!sellerService.doesSellerExist(p.getSeller().getSellerName())) {
//                return new ResponseEntity<>("Seller does not exist", HttpStatus.NOT_FOUND);
//            }
//            Product addedProduct = productService.addProduct(id,p);
//            return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    //
    @PostMapping("sellers/{sellerId}/products")
    public ResponseEntity<Product> Product(@RequestBody Product p, @PathVariable long sellerId) throws Exception {
        Product product = productService.saveProduct(sellerId, p);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    //delete product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        try{
            productService.deleteProduct(id);
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        }
        catch(ProductException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


   //update product
    @PutMapping ("/products/{id}")
    public ResponseEntity<Product>updateProduct(@PathVariable long id,@RequestBody Product updatedProduct)  {

        try{
            Product product =productService.updateProduct(id,updatedProduct);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }catch (ProductException e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        }
    }

}
