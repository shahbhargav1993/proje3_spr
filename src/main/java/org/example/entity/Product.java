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
    public long productid;
    public String productname;

    public double price;

    public String sellerid;

    @JsonIgnore
    @ManyToOne
    @JsonIgnoreProperties("seller")
    public List<Seller> seller;
}
