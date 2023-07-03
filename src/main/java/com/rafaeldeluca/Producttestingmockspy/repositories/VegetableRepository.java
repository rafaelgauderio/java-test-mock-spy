package com.rafaeldeluca.Producttestingmockspy.repositories;

import com.rafaeldeluca.Producttestingmockspy.entities.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VegetableRepository extends JpaRepository<Vegetable, Long> {
}
