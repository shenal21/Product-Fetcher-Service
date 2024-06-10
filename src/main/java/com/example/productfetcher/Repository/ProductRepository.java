package com.example.productfetcher.Repository;

import com.example.productfetcher.Model.Product;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product, String> {

}
