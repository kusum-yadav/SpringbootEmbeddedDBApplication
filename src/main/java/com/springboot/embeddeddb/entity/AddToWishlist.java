package com.springboot.embeddeddb.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="ADDTOWISHLIST")
public class AddToWishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishId;
    private String productName;
    private int quantity;
    private boolean returnable;
    private boolean replacement;
    private int price;

}
