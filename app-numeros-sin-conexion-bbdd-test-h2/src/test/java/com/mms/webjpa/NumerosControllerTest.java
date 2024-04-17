package com.mms.webjpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(NumerosController.class)
@ExtendWith(MockitoExtension.class)
class NumerosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NumerosService numerosServiceMock;

    @Test
    void obtener_lista_numeros_ok() throws Exception {

        // Arrange - [1, 15, 5, 7, 3]
        NumerosDTO num1 = NumerosDTO.builder().id(1L).numero(1).build();
        NumerosDTO num2 = NumerosDTO.builder().id(2L).numero(15).build();
        NumerosDTO num3 = NumerosDTO.builder().id(3L).numero(5).build();
        NumerosDTO num4 = NumerosDTO.builder().id(4L).numero(7).build();
        NumerosDTO num5 = NumerosDTO.builder().id(5L).numero(3).build();

        // Act
        when(numerosServiceMock.getListaNumeros())
                .thenReturn(List.of(num1, num2, num3, num4, num5));
    }

    @Test
    void obtener_lista_numeros_isOk() throws Exception {
        // [1, 15, 5, 7, 3]
        // [15, 7, 3, 5, 1]
        mockMvc.perform(get("/numeros")
                        //.content("[1, 15, 5, 7, 3]")
                )
                .andExpect(status().isOk())
                .andExpect( content().string("[15, 7, 3, 5, 1]"));

    }
    void obtener_lista_numeros_badRequest() throws Exception {
        mockMvc.perform(post("/numeros"))
                .andExpect(status().isBadRequest());
    }

}