package com.rafaeldeluca.Producttestingmockspy.services;

import com.rafaeldeluca.Producttestingmockspy.dto.VegetableDTO;
import com.rafaeldeluca.Producttestingmockspy.entities.Vegetable;
import com.rafaeldeluca.Producttestingmockspy.repositories.VegetableRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VegetableService {

    @Autowired
    private VegetableRepository repository;

    @Transactional(readOnly=false)
    public VegetableDTO insert(VegetableDTO dto) {
        Vegetable entity = new Vegetable();
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity = repository.save(entity);
        return new VegetableDTO(entity);
    }

    @Transactional(readOnly = false)
    public VegetableDTO update (Long id, VegetableDTO dto) {
        Vegetable entity = new Vegetable();
        entity = repository.getReferenceById(id);
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity = repository.save(entity);

        return new VegetableDTO(entity);
    }


}
