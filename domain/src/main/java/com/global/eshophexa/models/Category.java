package com.global.eshophexa.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

    private String name;
    private Long id;

    public Category(Long id, String name) {
        this.name = name;
        this.id = id;
    }
}
