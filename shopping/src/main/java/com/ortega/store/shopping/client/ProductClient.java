package com.ortega.store.shopping.client;

import com.ortega.store.shopping.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service", path = "/products")
public interface ProductClient {
    @GetMapping
    public ResponseEntity<List<Product>> lstProduct(@RequestParam(name = "categoryId", required = false) Long categoryId);
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id);
    @PutMapping(value = "/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable Long id , @RequestParam(name = "quantity", required = true) Double quantity);
}
