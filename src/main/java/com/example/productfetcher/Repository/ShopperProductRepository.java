package com.example.productfetcher.Repository;

import com.example.productfetcher.Model.ShopperProduct;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopperProductRepository extends JpaRepository<ShopperProduct, Long> {

    //Implemented caching on get methods to optimize performance.
    //TODO:Implement specification to dynamically handle GET queries.
    @Cacheable("shopperProductsByShopperId")
    List<ShopperProduct> findByShopper_ShopperId(String shopperId, Pageable pageable);

    @Cacheable("shopperProductsByCategory")
    List<ShopperProduct> findByShopper_ShopperIdAndProduct_Category(String shopperId, String category, Pageable pageable);

    @Cacheable("shopperProductsByBrand")
    List<ShopperProduct> findByShopper_ShopperIdAndProduct_Brand(String shopperId, String brand, Pageable pageable);

    @Cacheable("shopperProductsByCategoryAndBrand")
    List<ShopperProduct> findByShopper_ShopperIdAndProduct_CategoryAndProduct_Brand(String shopperId, String category, String brand, Pageable pageable);

    Boolean existsByShopper_ShopperIdAndProduct_ProductId(String shopperId, String productId);
}

