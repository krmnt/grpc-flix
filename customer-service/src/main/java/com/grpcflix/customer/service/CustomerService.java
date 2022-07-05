package com.grpcflix.customer.service;

import com.grpcflix.customer.entity.Customer;
import com.grpcflix.customer.repository.CustomerRepository;
import com.kierangelo.grpcflix.common.Category;
import com.kierangelo.grpcflix.customer.CustomerCategoryUpdateRequest;
import com.kierangelo.grpcflix.customer.CustomerResponse;
import com.kierangelo.grpcflix.customer.CustomerSearchRequest;
import com.kierangelo.grpcflix.customer.CustomerServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Locale;

@GrpcService
public class CustomerService extends CustomerServiceGrpc.CustomerServiceImplBase {

    @Autowired
    private CustomerRepository repository;


    @Override
    public void getCustomerCategory(CustomerSearchRequest request, StreamObserver<CustomerResponse> responseObserver) {
        CustomerResponse.Builder builder = CustomerResponse.newBuilder();
        this.repository.findById(request.getCustomerId())
                .ifPresent(customer -> {
                    builder.setCustomerName(customer.getCustomerName())
                            .setCustomerId(customer.getCustomerId())
                            .setCategory(Category.valueOf(customer.getCategory().toUpperCase()));
                });
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    @Transactional
    public void updateCustomerCategory(CustomerCategoryUpdateRequest request, StreamObserver<CustomerResponse> responseObserver) {
        CustomerResponse.Builder builder = CustomerResponse.newBuilder();

        this.repository.findById(request.getCustomerId())
                .ifPresent(customer -> {
                    customer.setCategory(request.getCategory().toString());
                    builder.setCustomerName(customer.getCustomerName())
                            .setCustomerId(customer.getCustomerId())
                            .setCategory(Category.valueOf(customer.getCategory().toUpperCase()));
                });
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
