package com.springboot.embeddeddb.controller;

import com.springboot.embeddeddb.entity.AddToCart;
import com.springboot.embeddeddb.repository.AddToCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class AddToCartRestController {

    @Autowired
    AddToCartRepository addToCartRepository;

    @GetMapping
    public ResponseEntity getAllCart() {
        List<AddToCart> list = addToCartRepository.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity getCartById(@PathVariable("id") int id) {
        Optional<AddToCart> list = addToCartRepository.findById(id);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping(value = "/productName/{productName}")
    public ResponseEntity getCartByProductName(@PathVariable("productName") String productName) {
        List<AddToCart> listResult = new ArrayList<>();
         addToCartRepository.findByProductName(productName).forEach(listResult::add);
        return new ResponseEntity(listResult, HttpStatus.OK);
    }

    @GetMapping(value = "/description/{description}")
    public ResponseEntity getCartByDescriptionContaining(@PathVariable("description") String description) {
        List<AddToCart> list = addToCartRepository.findByDescriptionContaining(description);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/{from}/{to}")
    public ResponseEntity getCartByPriceRange(@PathVariable("from") int from, @PathVariable("to")  int to) {
        List<AddToCart> list = addToCartRepository.findByPriceBetween(from, to);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addToCart(@RequestBody AddToCart addToCart){
        AddToCart createdCart = addToCartRepository.save(addToCart);
        return  new ResponseEntity(createdCart, HttpStatus.CREATED);
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity updateCartById(@PathVariable("id") int id, @RequestBody AddToCart addToCart) {
        Optional<AddToCart> cart= addToCartRepository.findById(id);
        if(cart.isPresent()){
            addToCart.setId(cart.get().getId());
            addToCartRepository.save(addToCart);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT, HttpStatus.OK);
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity deleteCartById(@PathVariable("id") int id) {
         addToCartRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT, HttpStatus.OK);
    }


}
