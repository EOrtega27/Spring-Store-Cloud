package com.ortega.store.product.entity;

import lombok.*;

import javax.persistence.*;
@Data
@Entity
@Table(name = "tbl_categories")
@AllArgsConstructor @NoArgsConstructor @Builder
public class Category {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

}