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
    public ProductService (ProductRepository productRepository,
                           SellerRepository sellerRepository) {

        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public Product saveProduct(Product p) throws SellerException{

        Product newProduct = productRepository.save(p);

        String sellerName = p.getSellerName();

        List<Seller> sellers =
                sellerRepository.findSellerBySellerName(sellerName);

        Seller seller = sellers.get(0);

        if(seller != null)
        {
            seller.products.add(p);
        }
        else
        {
            throw new SellerException("Cannot find seller with " +
                    "sellerName: " + sellerName);
        }
//Made this a Query under ProductRepository
        /*if (sellerOptional.isPresent()) {
            Seller seller = sellerOptional.get();
            seller.products.add(p);
        } else {
            throw new SellerException("Cannot find product with " +
                    "sellerName: " + sellerName);
        }*/

        return newProduct;
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
}
