package com.rafaeldeluca.Producttestingmockspy.services;

import com.rafaeldeluca.Producttestingmockspy.dto.VegetableDTO;
import com.rafaeldeluca.Producttestingmockspy.entities.Vegetable;
import com.rafaeldeluca.Producttestingmockspy.repositories.VegetableRepository;
import com.rafaeldeluca.Producttestingmockspy.services.exceptions.InvalidDataException;
import com.rafaeldeluca.Producttestingmockspy.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    // testando inserir um produto com dados validos
    @Test
    public void insertShouldReturnProductDTOWhenValidaDataIsValid (){
        // não fazer nada quando os dados forem validos
        // não ó possivel mockar metodos de dentro da propria classe service
        // sendo que é a classe service que está sendo testada,
        // @spy permite encapsular uma estância de um objeto
        // usar o spy para mockar o método validaData da propria classe service
        VegetableService serviceSpy = Mockito.spy(service);
        Mockito.doNothing().when(serviceSpy).validateVegetableData(vegetableDTO);

        VegetableDTO result = serviceSpy.insert(vegetableDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getDescription(),"Abacaxi Orgânico");
        Assertions.assertEquals(result.getPrice(),8.90);
    }

    @Test
    public void insertShouldReturnInvalidDataExceptionWhenVegetableDescriptionIsBlank () {

        vegetableDTO.setDescription("");

        VegetableService serviceSpy = Mockito.spy(service);
        Mockito.doThrow(InvalidDataException.class).when(serviceSpy).validateVegetableData(vegetableDTO);

        Assertions.assertThrows(InvalidDataException.class, () -> {
           VegetableDTO result = serviceSpy.insert(vegetableDTO);
        });
    }

    @Test
    public void insertShouldReturnInvalidDataExceptionWhenVegetablePriceIsNegative () {
        vegetableDTO.setPrice(-9.5);

        VegetableService serviceSpy = Mockito.spy(service);
        Mockito.doThrow(InvalidDataException.class).when(serviceSpy).validateVegetableData(vegetableDTO);

        Assertions.assertThrows(InvalidDataException.class, () -> {
            VegetableDTO result = serviceSpy.insert(vegetableDTO);
        });
    }

    @Test
    public void insertShouldReturnInvalidDataExceptionWhenVegetablePriceIsZero () {
        vegetableDTO.setPrice(0.0);

        VegetableService serviceSpy = Mockito.spy(service);
        Mockito.doThrow(InvalidDataException.class).when(serviceSpy).validateVegetableData(vegetableDTO);

        Assertions.assertThrows(InvalidDataException.class, () -> {
            VegetableDTO result = serviceSpy.insert(vegetableDTO);
        });
    }

    @Test
    public void updateShouldReturnProductDTOWhenDataIsValidAndIdExists () {
        VegetableService serviceSpy = Mockito.spy(service);
        Mockito.doNothing().when(serviceSpy).validateVegetableData(vegetableDTO);

        VegetableDTO updateResult = serviceSpy.update(existingId, vegetableDTO);

        Assertions.assertNotNull(updateResult);
        Assertions.assertEquals(updateResult.getId(), existingId);
        Assertions.assertEquals(updateResult.getDescription(), vegetableDTO.getDescription());
        Assertions.assertEquals(updateResult.getPrice(),vegetableDTO.getPrice());
    }


}
