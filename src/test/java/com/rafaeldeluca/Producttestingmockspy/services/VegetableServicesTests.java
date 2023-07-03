package com.rafaeldeluca.Producttestingmockspy.services;

import com.rafaeldeluca.Producttestingmockspy.repositories.VegetableRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) // annotation indica que não é necessário carregar o contexto da API
public class VegetableServicesTests {

    // injetar (instancia) o componente da classe a ser testada
    @InjectMocks
    private VegetableService service;

    // simular o comportamento do repository e não injetar a dependencia
    @Mock
    private VegetableRepository repository;


}
