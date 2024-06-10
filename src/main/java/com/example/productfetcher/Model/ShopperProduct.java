package com.example.productfetcher.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
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

    private float relevancyScore;

}
