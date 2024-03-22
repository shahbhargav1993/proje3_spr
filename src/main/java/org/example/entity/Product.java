package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productid;
    private String productName;

    private double price;

    //private String sellerName;

    //@JsonIgnore
    @ManyToOne
    @JsonIgnoreProperties("products")
    //@JoinColumn(name = "seller_id")

    public Seller seller;
}
