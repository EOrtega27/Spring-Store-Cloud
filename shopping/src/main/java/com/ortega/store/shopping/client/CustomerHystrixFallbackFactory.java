package com.ortega.store.shopping.client;

import com.ortega.store.shopping.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CustomerHystrixFallbackFactory implements CustomerClient{
    @Override
    public ResponseEntity<List<Customer>> listAllCustomers(Long regionId) {
        return null;
    }

    @Override
    public ResponseEntity<Customer> getCustomer(long id) {
        Customer customer = Customer.builder()
                .firstName("none")
                .lastName("none")
                .email("none")
                .photoUrl("none")
                .build();
        return ResponseEntity.ok(customer);
    }
}
