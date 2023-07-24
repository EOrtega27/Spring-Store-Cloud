package com.ortega.store.shopping.entity;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.Positive;

@Data
@Entity
@Table(name = "tbl_invoice_items")
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Positive(message = "El stock debe ser mayor a 0")
    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "price")
    private Double price;

    @Column(name = "product_id")
    private Long productId;

    @Transient
    private String subTotal;

    public Double getSubTotal(){
        if (this.price >0  && this.quantity >0 ){
            return this.quantity * this.price;
        }else {
            return (double) 0;
        }
    }
    public InvoiceItem(){
        this.quantity=(double) 0;
        this.price=(double) 0;

    }
}