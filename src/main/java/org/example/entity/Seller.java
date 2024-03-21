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

public class Seller
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // This is the primary key
    private String sellerName; // This is a regular attribute

    @JsonIgnore
    @OneToMany
    @JoinColumn(name="seller_fk")

    public List<Product> products;

}
