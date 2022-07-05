package com.grpcflix.cart.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@ToString
public class Cart {

    @Id
    private Integer id;
    private String productName;
    private String customerName;
    private String category;
    private Integer quantity;


}
