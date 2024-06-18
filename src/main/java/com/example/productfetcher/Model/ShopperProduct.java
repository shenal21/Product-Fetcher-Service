package com.example.productfetcher.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(
    name = "shopper_product",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"shopper_id", "product_id"})}
)
public class ShopperProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "shopperId")
    private Shopper shopper;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @NotNull(message = "relevencyScore cannot be null")
    private Float relevancyScore;

}
