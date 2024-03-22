package org.example.service;

import org.example.entity.Product;
import org.example.entity.Seller;
import org.example.exception.ProductException;
import org.example.exception.SellerException;
import org.example.repository.ProductRepository;
import org.example.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;
    SellerRepository sellerRepository;

    @Autowired
    public ProductService (ProductRepository productRepository,SellerRepository sellerRepository) {

        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(long productid,Product p) throws ProductException {
        Optional<Seller> optional = sellerRepository.findById(productid);
        Seller s;
        if (optional.isEmpty()) {
            throw new ProductException("No such seller available");
        } else {
            s = optional.get();
        }
        p.setSeller(s);
        Product savedProduct = productRepository.save(p);
        s.getProducts().add(savedProduct);
        sellerRepository.save(s);
        return savedProduct;
    }



    public void deleteProduct(Long id) throws ProductException {

        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            productRepository.delete(product);
        } else {
            throw new ProductException("Cannot find product with id: " + id);
        }
    }

    public Product updateProduct(long productId,Product updatedProduct) throws ProductException{
        Optional<Product> productOptional= productRepository.findById(productId);
        if(productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setProductName(updatedProduct.getProductName());
            product.setPrice(updatedProduct.getPrice());
            return productRepository.save(product);
        }else{
            throw new ProductException("No such product to update ..");
        }
    }

    public Product getProductById(long productId) throws ProductException {
        Optional<Product>productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new ProductException("no such product...");
        }
    }


//    public Product addProduct(long id,Product p) throws Exception {
//     // Check for valid product details
//        if (p.getProductName() == null || p.getPrice() < 0) {
//            throw new Exception("Invalid product details");
//        }
//     // Find the seller by id
//        Optional<Seller> optionalseller =sellerRepository.findById(id);
//        Seller seller;
//        if (optionalseller.isEmpty()) {
//            // If seller not found, throw an exception
//            throw new Exception("Seller does not exist");
//        }else {
//            seller = optionalseller.get();
//        }
//    // Set the found seller to the product
//        p.setSeller(seller);
//        Product product = productRepository.save(p);
//    // Save the product with the seller set
//        seller.getProducts().add(product);
//          sellerRepository.save(seller);
//        return productRepository.save(p);
//    }


//    public Product addProduct(long id, Product p) throws SellerException, ProductException{
//        Optional<Seller> optional = sellerRepository.findBysellerName(sellerName);
//        Seller s;
//        if(optional.isEmpty()){
//            throw new SellerException("No Seller Registered with ID: "+ id);
//        }else{
//            s = optional.get();
//        }
//        if (p.getPrice() <= 0){
//            throw new ProductException("Product Price Must Be Above Zero");
//        }
//        p.setSeller(s);
//        Product product = productRepository.save(p);
//        s.getProductList().add(product);
//        sellerRepository.save(s);
//        return product;
//    }


//    public Product addProduct(long id,Product p) throws Exception {
//        Optional<Seller> optional = sellerRepository.findById(id);
//        Seller seller;
//        if(optional.isEmpty()){
//            throw new SellerException("No Seller Registered with ID: "+ id);
//        }else{
//            seller = optional.get();
//        }
//        if (p.getProductName() == null || p.getPrice() < 0) {
//        //if (p.getProductName() == null || p.getSellerName() == null || p.getPrice() < 0) {
//            throw new Exception("Invalid product details");
//        }
//        if (p.getPrice() <= 0){
//            throw new ProductException("Product Price Must Be Above Zero");
//        }
//        p.setSeller(seller);
//        Product product = productRepository.save(p);
//        seller.getProducts().add(product);
//        sellerRepository.save(seller);
//        return product;
//    }



//        Optional<Seller> optionalSeller = sellerRepository.findSellerBySellerName(p.getSellerName());
//        if (!optionalSeller.isPresent()) {
//            throw new Exception("Seller does not exist");
//        }
//        Seller seller = optionalSeller.get();
//        p.setSeller(seller);
  //      return productRepository.save(p);
 //   }

        //seller = sellerRepository.findSellerBySellerName(p.getSellerName());
//        if(Seller seller== null){
//            throw new Exception("Seller does not exist");
//        }
//        p.setSeller(seller);
//        //p.setSellerName(seller.getSellerName());
//        return productRepository.save(p);
//    }


}
