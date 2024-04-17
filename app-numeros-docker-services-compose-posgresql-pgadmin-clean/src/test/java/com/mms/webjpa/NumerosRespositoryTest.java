package com.mms.webjpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class NumerosRespositoryTest {

    @Mock
    NumerosRespository numerosRespositoryMock;

    @DisplayName("Búsqueda de todos los números")
    @Test
    void buscar_todos_los_numeros(){

        // Arrange - [1, 15, 5, 7, 3]
        Numeros num1 = Numeros.builder().id(1L).numero(1).build();
        Numeros num2 = Numeros.builder().id(2L).numero(15).build();
        Numeros num3 = Numeros.builder().id(3L).numero(5).build();
        Numeros num4 = Numeros.builder().id(4L).numero(7).build();
        Numeros num5 = Numeros.builder().id(5L).numero(3).build();

        when(numerosRespositoryMock.findAll())
                .thenReturn(List.of(num1, num2, num3, num4, num5));

        // Act
        List<Numeros> listaNumeros = numerosRespositoryMock.findAll();

        // Asssert
        verify(numerosRespositoryMock, times(1)).findAll();

        Assertions.assertThat(listaNumeros).isNotNull();

        assertEquals(listaNumeros.get(0).getNumero(), 1);
        assertEquals(listaNumeros.get(1).getNumero(), 15);
        assertEquals(listaNumeros.get(2).getNumero(), 5);
        assertEquals(listaNumeros.get(3).getNumero(), 7);
        assertEquals(listaNumeros.get(4).getNumero(), 3);
    }

}