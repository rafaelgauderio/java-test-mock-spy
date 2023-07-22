package com.rafaeldeluca.Producttestingmockspy.services;

import com.rafaeldeluca.Producttestingmockspy.dto.VegetableDTO;
import com.rafaeldeluca.Producttestingmockspy.entities.Vegetable;
import com.rafaeldeluca.Producttestingmockspy.repositories.VegetableRepository;
import com.rafaeldeluca.Producttestingmockspy.services.exceptions.InvalidDataException;
import com.rafaeldeluca.Producttestingmockspy.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VegetableService {

    @Autowired
    private VegetableRepository repository;

    @Transactional(readOnly=false)
    public VegetableDTO insert(VegetableDTO dto) {
        validateVegetableData(dto);
        Vegetable entity = new Vegetable();
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity = repository.save(entity);
        return new VegetableDTO(entity);
    }

    @Transactional(readOnly = false)
    public VegetableDTO update (Long id, VegetableDTO dto) {


        try {
            validateVegetableData(dto);
            Vegetable entity = new Vegetable();
            entity = repository.getReferenceById(id);
            entity.setDescription(dto.getDescription());
            entity.setPrice(dto.getPrice());
            entity = repository.save(entity);

            return new VegetableDTO(entity);
        } catch (EntityNotFoundException error) {
            throw new ResourceNotFoundException("Resource not found with the id:" + id);
        }

    }

    protected void validateVegetableData(VegetableDTO dto) {
        if(dto.getDescription().isBlank()) {
            throw new InvalidDataException("Field description can not be empty or blank!");
        }
        if(dto.getPrice() == null || dto.getPrice() <= 0) {
            throw new InvalidDataException("Field price have to greater than zero and not null!");
        }
    }


}
