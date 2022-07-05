package com.grpcflix.aggregator.service;


import com.grpcflix.aggregator.dto.CustomerCategory;
import com.grpcflix.aggregator.dto.RecommendedCart;
import com.kierangelo.grpcflix.cart.CartSearchRequest;
import com.kierangelo.grpcflix.cart.CartSearchResponse;
import com.kierangelo.grpcflix.cart.CartServiceGrpc;
import com.kierangelo.grpcflix.common.Category;
import com.kierangelo.grpcflix.customer.CustomerCategoryUpdateRequest;
import com.kierangelo.grpcflix.customer.CustomerResponse;
import com.kierangelo.grpcflix.customer.CustomerSearchRequest;
import com.kierangelo.grpcflix.customer.CustomerServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class CustomerCartService {

    @GrpcClient("customer-service")
    private CustomerServiceGrpc.CustomerServiceBlockingStub customerStub;

    @GrpcClient("cart-service")
    private CartServiceGrpc.CartServiceBlockingStub cartStub;

    public List<RecommendedCart> getCustomerCartSuggestions(Integer customerId){

        CustomerSearchRequest customerSearchRequest = CustomerSearchRequest.newBuilder().setCustomerId(customerId).build();
        CustomerResponse customerResponse = this.customerStub.getCustomerCategory(customerSearchRequest);
        CartSearchRequest cartSearchRequest = CartSearchRequest.newBuilder().setCategory(customerResponse.getCategory()).build();
        CartSearchResponse cartSearchResponse = this.cartStub.getCarts(cartSearchRequest);
        return cartSearchResponse.getCartList()
                .stream()
                .map(cartDto ->  new RecommendedCart(cartDto.getId(), cartDto.getProductName(), cartDto.getCustomerName(), cartDto.getCategory(), cartDto.getQuantity()))
                .collect(Collectors.toList());

    }

    public void setCustomerCategory(CustomerCategory customerCategory){
        CustomerCategoryUpdateRequest customerCategoryUpdateRequest = CustomerCategoryUpdateRequest.newBuilder()
                .setCustomerId(customerCategory.getCustomerId())
                .setCategory(Category.valueOf(customerCategory.getCategory().toUpperCase()))
                .build();
        CustomerResponse customerResponse = this.customerStub.updateCustomerCategory(customerCategoryUpdateRequest);
    }
}
