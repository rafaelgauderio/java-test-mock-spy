package com.rafaeldeluca.Producttestingmockspy.services;

import com.rafaeldeluca.Producttestingmockspy.dto.VegetableDTO;
import com.rafaeldeluca.Producttestingmockspy.entities.Vegetable;
import com.rafaeldeluca.Producttestingmockspy.repositories.VegetableRepository;
import com.rafaeldeluca.Producttestingmockspy.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class) // annotation indica que não é necessário carregar o contexto da API
public class VegetableServicesTests {

    // injetar (instancia) o componente da classe a ser testada
    @InjectMocks
    private VegetableService service;

    // simular o comportamento do repository e não injetar a dependencia
    @Mock
    private VegetableRepository repository;

    private Vegetable vegetable;
    private VegetableDTO vegetableDTO;

    private Long existingId, nonExistingId;

    // agrupar o que for comum a todos os métodos

    @BeforeEach
    void setUp() throws Exception {

        existingId  = 2L;
        nonExistingId = 10L;

        vegetable = new Vegetable(2L,"Abacaxi Orgânico",8.90);
        vegetableDTO = new VegetableDTO(vegetable);

        // simular o comportamento de salvar um produto (vegetable)
        Mockito.when(repository.save(any())).thenReturn(vegetable);

        // simulando buscar um vegetale por Id existente e não existente no banco
        Mockito.when(repository.getReferenceById(existingId)).thenReturn(vegetable);
        Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(ResourceNotFoundException.class);


    }

    // simular getReferenceById quando existe quando não existe o Id do vegetable no banco de dados

}
