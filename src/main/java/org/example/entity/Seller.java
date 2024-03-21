package org.example.entity;

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
    public long id; // This is the primary key
    public String sellername; // This is a regular attribute
    @OneToMany
    @JoinColumn(name="seller_fk")
    public List<Product> products;

}
