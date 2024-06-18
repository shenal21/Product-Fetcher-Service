package com.example.productfetcher.Service;

import com.example.productfetcher.Model.Product;
import com.example.productfetcher.Repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> getProductById(String productId){
        return productRepository.findById(productId);
    }

    public ResponseEntity<Object> saveProduct(Product product) {
        String productId = product.getProductId();
        Optional<Product> existingProductOptional = getProductById(productId);

        //Update product meta-data for an existing product.
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setCategory(product.getCategory());
            existingProduct.setBrand(product.getBrand());
            productRepository.save(existingProduct);
            return ResponseEntity.ok("Updated product Meta-data with product ID: " + productId);

        } else {

            productRepository.save(product);
            return ResponseEntity.ok("Persisted new product with product ID: " + productId);
        }
    }
}
