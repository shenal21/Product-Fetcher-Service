package com.example.productfetcher.Controller;

import com.example.productfetcher.Model.Product;
import com.example.productfetcher.Model.Shopper;
import com.example.productfetcher.Model.ShopperProduct;
import com.example.productfetcher.Service.ProductService;
import com.example.productfetcher.Service.ShopperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ShopperController {

    @Autowired
    private ShopperService shopperService;

    @Autowired
    private ProductService productService;


    //Internal product controller - used to create and update products.
    @PostMapping("/api/internal/product")
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    //Internal shopper-products controller - used to persist shopper-products.
    @PostMapping("api/internal/shopper-products")
    public void saveShopper(@RequestBody Shopper shopper) {
        List<ShopperProduct> shelf = shopper.getShelf();
        for (ShopperProduct shopperProduct : shelf) {
            Product product = productService.getProductById(shopperProduct.getProduct().getProductId()).orElse(null);
            if (product != null) {
                shopperProduct.setProduct(product);
            }
        }
        shopperService.saveShopper(shopper);
    }

    //External shopper-produts controller - returns shopper-product with product meta data.
    //Supports multiple filters as request params.
    @GetMapping("/api/external/shopper-products")
    public ResponseEntity<List<ShopperProduct>> getShopperProducts(
        @RequestParam String shopperId,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String brand,
        @RequestParam(defaultValue = "10") int limit) {

        return ResponseEntity.ok(shopperService.getShopperProducts(shopperId, category, brand, limit));
    }
}
