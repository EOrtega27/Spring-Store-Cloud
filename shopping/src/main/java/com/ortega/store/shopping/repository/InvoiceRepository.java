package com.ortega.store.shopping.repository;

import com.ortega.store.shopping.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByCustomerId(Long customerId);

    Invoice findByNumberInvoice(String numberInvoice);

}