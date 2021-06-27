package com.springboot.embeddeddb.repository;

import com.springboot.embeddeddb.entity.AddToCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddToCartRepository extends JpaRepository<AddToCart, Integer> {

    List<AddToCart> findByProductName(String productName);
    List<AddToCart> findByDescriptionContaining(String description);
    List<AddToCart> findByPriceBetween(int from, int to);
}
