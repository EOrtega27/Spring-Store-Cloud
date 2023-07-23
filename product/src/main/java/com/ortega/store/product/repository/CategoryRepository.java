package com.ortega.store.product.repository;

import com.ortega.store.product.entity.Category;
import com.ortega.store.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}