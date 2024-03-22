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
public class SellerService
{
    SellerRepository sellerRepository;
    ProductRepository productRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository, ProductRepository productRepository)
    {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }

    public List<Seller> getAllSellers()
    {
        return sellerRepository.findAll();
    }


    public Seller saveSeller(Seller s)
    {
        return sellerRepository.save(s);
    }


    public void deleteSeller(String sellerName) throws SellerException
    {
        List<Seller> seller = sellerRepository.findSellerBySellerName(sellerName);
        if (seller != null)
        {
            List<Product> products = productRepository.getProductsBySellerName(sellerName);
            if (products.isEmpty())
            {
                sellerRepository.deleteSeller(sellerName);
            }
            else
            {
                throw new SellerException("Cannot delete seller with sellerName: " + sellerName + " because it has products");
            }
        }
        else
        {
            throw new SellerException("Cannot find seller with sellerName: " + sellerName);
        }
    }



    public Seller getSellerById(long id) throws SellerException
    {
        Optional<Seller> sellerOptional = sellerRepository.findById(id);
        if (sellerOptional.isPresent())
        {
            return sellerOptional.get();
        }
        else
        {
            throw new SellerException("Cannot find seller with id: " + id);
        }
    }

    public List<Product> getSellerProductsBySellerName(String sellerName) throws SellerException
    {
        //List<Seller> sellerOptional = sellerRepository.findSellerBySellerName(sellerName);

        List<Seller> sellers =
                sellerRepository.findSellerBySellerName(sellerName);

        Seller seller = sellers.get(0);

        if (seller != null)
        {
            List<Product> products = productRepository.getProductsBySellerName(sellerName);
            if (products.isEmpty())
            {
                throw new SellerException("No products associated " +
                        "with seller: " + sellerName);
            }
            return products;
        }
        else
        {
            throw new SellerException("Cannot find seller with " +
                    "sellerName: " + sellerName);
        }
    }

}