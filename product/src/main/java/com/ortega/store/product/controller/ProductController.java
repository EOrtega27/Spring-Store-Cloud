package com.ortega.store.product.controller;

import com.ortega.store.product.entity.Product;
import com.ortega.store.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    private ResponseEntity<List<Product>> getProductList(){
        List<Product> products = new ArrayList<>();
        products = productService.listAllProduct();
        return ResponseEntity.ok(products);

    }

    @PostMapping("/save")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "error");
        }
        Product productCreate =  productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }
}
