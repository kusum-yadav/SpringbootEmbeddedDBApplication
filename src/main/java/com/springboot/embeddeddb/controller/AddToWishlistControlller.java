package com.springboot.embeddeddb.controller;


import com.springboot.embeddeddb.entity.AddToWishlist;
import com.springboot.embeddeddb.repository.AddToWishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wishlist")
public class AddToWishlistControlller {

    @Autowired
    AddToWishlistRepository wishListRepository;

    @GetMapping
    ResponseEntity getAllWishlistItem() {
        List<AddToWishlist> list = wishListRepository.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping(value = "/quantity/{quantity}")
    public ResponseEntity getCartById(@PathVariable("quantity") int quantity) {
        List<AddToWishlist> list = wishListRepository.findByQuantity(quantity);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping(value = "/productName")
    public List<AddToWishlist> getCartByProductName(@PathParam("productName") String productName) {
        List<AddToWishlist> list = wishListRepository.findByProductName(productName);
        return list;
    }

    @GetMapping(value = "/byPrice")
    public List<AddToWishlist> getCartByProductName(@PathParam("from") int from, @PathParam("to") int to) {
        List<AddToWishlist> list = wishListRepository.findByPriceBetween(from, to);
        return list;
    }

    @PostMapping
    public ResponseEntity createWishList(@RequestBody AddToWishlist addToWishlist){
        AddToWishlist dbWishList= wishListRepository.save(addToWishlist);
        return new ResponseEntity(dbWishList,HttpStatus.CREATED) ;
    }

    @PutMapping
    public int updatePriceByPercent(@PathParam("wishId") int wishId,@PathParam("byPercent") int byPercent){
        Optional<AddToWishlist> wishlist=wishListRepository.findById(wishId);
        if(wishlist.isPresent())
        {
            int price=wishlist.get().getPrice();
            int updatedPrice=price+price*byPercent/100;
            AddToWishlist addToWishlist=wishlist.get();
            addToWishlist.setPrice(updatedPrice);
            return wishListRepository.save(addToWishlist).getPrice();
        }
        return 0;
    }

}
