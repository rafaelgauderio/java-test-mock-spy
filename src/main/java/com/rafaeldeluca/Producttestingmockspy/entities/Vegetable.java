package com.rafaeldeluca.Producttestingmockspy.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_vegetable")
public class Vegetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double price;

    public Vegetable () {

    }

    public Vegetable(Long id, String description, Double price) {
        this.id = id;
        this.description = description;
        this.price = price;
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vegetable vegetable = (Vegetable) o;
        return Objects.equals(id, vegetable.id) && Objects.equals(description, vegetable.description) && Objects.equals(price, vegetable.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price);
    }
}
