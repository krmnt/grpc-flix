package com.grpcflix.aggregator.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendedCart {

    private Integer id;
    private String productName;
    private String customerName;
    private String category;
    private Integer quantity;

}
