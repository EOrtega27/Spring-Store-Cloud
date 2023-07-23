package com.ortega.store.product;

import com.ortega.store.product.entity.Category;
import com.ortega.store.product.entity.Product;
import com.ortega.store.product.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByCategory_thenReturnListProduct(){
        Product product01 = Product.builder()
                .name("computer")
                .id(4l)
                .category(Category.builder().id(4l).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1245.89"))
                .status("Created")
                .createAt(new Date()).build();
        productRepository.save(product01);

        List<Product> found = productRepository.findByCategory(product01.getCategory());
        Assertions.assertEquals(found.size(),3);
    }
}
