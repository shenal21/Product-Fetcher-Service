package com.example.productfetcher.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
//Indexing on columns used for filtering and references to improve performance of queries.
@Table(name = "product", indexes = {
    @Index(name = "idx_product_category", columnList = "category"),
    @Index(name = "idx_product_brand", columnList = "brand")
})
public class Product {
    @Id
    @NotNull(message = "Id cannot be null")
    @NotBlank(message = "Id cannot be blank")
    private String productId;

    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String brand;
}