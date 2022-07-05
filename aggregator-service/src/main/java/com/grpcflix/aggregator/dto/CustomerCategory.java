package com.grpcflix.aggregator.dto;

import lombok.Data;

@Data
public class CustomerCategory {

    private Integer customerId;
    private String customerName;
    private String category;

}
