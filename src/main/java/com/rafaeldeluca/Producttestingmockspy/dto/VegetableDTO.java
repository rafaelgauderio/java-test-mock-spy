package com.rafaeldeluca.Producttestingmockspy.dto;

import com.rafaeldeluca.Producttestingmockspy.entities.Vegetable;

public class VegetableDTO {

    private Long id;
    private String description;
    private Double price;

    public VegetableDTO () {

    }

    public VegetableDTO(Long id, String description, Double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public VegetableDTO (Vegetable entity) {
        id = entity.getId();
        description = entity.getDescription();
        price = entity.getPrice();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}