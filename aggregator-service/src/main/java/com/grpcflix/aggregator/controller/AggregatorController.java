package com.grpcflix.aggregator.controller;

import com.grpcflix.aggregator.dto.CustomerCategory;
import com.grpcflix.aggregator.dto.RecommendedCart;
import com.grpcflix.aggregator.service.CustomerCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AggregatorController {

    @Autowired
    private CustomerCartService customerCartService;

    @GetMapping("/customer/{customerId}")
    public List<RecommendedCart> getCarts(@PathVariable Integer customerId){
        return this.customerCartService.getCustomerCartSuggestions(customerId);

    }

    @PutMapping("/customer")
    public void setCustomerCategory(@RequestBody CustomerCategory customerCategory){
        this.customerCartService.setCustomerCategory(customerCategory);

    }





}
