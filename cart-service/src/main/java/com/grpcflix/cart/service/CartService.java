package com.grpcflix.cart.service;

import com.grpcflix.cart.repository.CartRepository;
import com.kierangelo.grpcflix.cart.CartDto;
import com.kierangelo.grpcflix.cart.CartSearchRequest;
import com.kierangelo.grpcflix.cart.CartSearchResponse;
import com.kierangelo.grpcflix.cart.CartServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class CartService extends CartServiceGrpc.CartServiceImplBase {

    @Autowired
    private CartRepository repository;

    @Override
    public void getCarts(CartSearchRequest request, StreamObserver<CartSearchResponse> responseObserver) {
        List<CartDto> cartDtoList = this.repository.getCartByCategoryOrderByProductName(request.getCategory().toString())
                .stream()
                .map(cart -> CartDto.newBuilder()
                        .setCustomerName(cart.getCustomerName())
                        .setProductName(cart.getProductName())
                        .setQuantity(cart.getQuantity())
                        .build()
                )
                .collect(Collectors.toList());
        responseObserver.onNext(CartSearchResponse.newBuilder().addAllCart(cartDtoList).build());
        responseObserver.onCompleted();

    }
}
