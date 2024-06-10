package com.example.productfetcher.Service;

import com.example.productfetcher.Exceptions.CustomDataAccessException;
import com.example.productfetcher.Exceptions.DuplicateShopperProductException;
import com.example.productfetcher.Model.Product;
import com.example.productfetcher.Model.Shopper;
import com.example.productfetcher.Model.ShopperProduct;
import com.example.productfetcher.Repository.ProductRepository;
import com.example.productfetcher.Repository.ShopperProductRepository;
import com.example.productfetcher.Repository.ShopperRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopperService {

    @Autowired
    private ShopperRepository shopperRepository;

    @Autowired
    private ShopperProductRepository shopperProductRepository;

    @Autowired
    private ProductService productService;


    public void saveShopper(Shopper shopper) {
        for (ShopperProduct shopperProduct : shopper.getShelf()) {
            String shopperId = shopper.getShopperId();
            String productId = shopperProduct.getProduct().getProductId();
            Optional<Product> product = productService.getProductById(productId);

            if (product.isEmpty()) {
                throw CustomDataAccessException.noProductsFoundException("Product not found with ID: " + productId);
            }


            // Check if a shopper-product entry already exists in the Db for the combination of shopperId and productId
            boolean exists = shopperProductRepository.existsByShopper_ShopperIdAndProduct_ProductId(shopperId, productId);
            if (exists) {
                throw new DuplicateShopperProductException("Duplicate shopper product entry found for shopper ID: " +
                    shopperId + " and product ID: " + productId);
            }
        }


        shopperRepository.save(shopper);
        for (ShopperProduct shopperProduct : shopper.getShelf()) {
            shopperProduct.setShopper(shopper);
            shopperProductRepository.save(shopperProduct);
        }
    }


    public List<ShopperProduct> getShopperProducts(String shopperId, String category, String brand, int limit) {

        //Validate the limit variable: allowed MAX value is 100.
        if (limit > 100) {
            throw new IllegalArgumentException("Limiter cannot exceed 100");
        }

        //Pagination to improve performance to handle large data sets.
        Pageable pageable = PageRequest.of(0, limit);
        List<ShopperProduct> products;


        //Used by external APIs to retrieve shopper products with filters.
        if (category != null && brand != null) {
            products = shopperProductRepository.findByShopper_ShopperIdAndProduct_CategoryAndProduct_Brand(shopperId, category, brand, pageable);
        } else if (category != null) {
            products = shopperProductRepository.findByShopper_ShopperIdAndProduct_Category(shopperId, category, pageable);
        } else if (brand != null) {
            products = shopperProductRepository.findByShopper_ShopperIdAndProduct_Brand(shopperId, brand, pageable);
        } else {
            products = shopperProductRepository.findByShopper_ShopperId(shopperId, pageable);
        }

        if (products.isEmpty()) {
            throw CustomDataAccessException.noProductsFoundException("No products found for the specified filter criteria.");
        }
        return products;
    }

}