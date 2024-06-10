package com.example.productfetcher.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Shopper {
    @Id
    private String shopperId;

    @OneToMany(mappedBy = "shopper")
    private List<ShopperProduct> shelf;

}
