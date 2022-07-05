package com.grpcflix.customer.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@ToString
public class Customer {

    @Id
    private Integer customerId;
    private String customerName;
    private String category;


}
