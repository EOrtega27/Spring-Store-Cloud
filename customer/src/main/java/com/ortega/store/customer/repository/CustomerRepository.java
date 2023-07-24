package com.ortega.store.customer.repository;

import com.ortega.store.customer.entity.Customer;
import com.ortega.store.customer.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer findByNumberID(String numberID);

    List<Customer> findByLastName(String lastName);

    List<Customer> findByRegion(Region region);


}