package com.springboot.embeddeddb.repository;

import com.springboot.embeddeddb.entity.AddToWishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddToWishlistRepository extends JpaRepository<AddToWishlist, Integer> {
    List<AddToWishlist> findByProductName(String productName);

    List<AddToWishlist> findByQuantity(int quantity);

    List<AddToWishlist> findByPriceBetween(int from,int to);


}
