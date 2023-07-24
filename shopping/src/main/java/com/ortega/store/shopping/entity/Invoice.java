package com.ortega.store.shopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "number_invoice")
    private String numberInvoice;

    @Column(name = "description")
    private String description;

    @Column(name = "customer_id")
    private Long customerId;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItem> items;

    @Column(name = "state")
    private String state;

    public Invoice() {
        this.items = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();

    }
}