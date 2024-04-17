package com.mms.appAlumnos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AlumnoController.class)
@ExtendWith(MockitoExtension.class)
class AlumnoControllerTest {

//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void getAlumnos() {
//    }
//
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    //private AlumnoService alumnoService;
    private AlumnoServiceImpl alumnoService;

    @Autowired
    private AlumnoServiceImpl alumnoServiceAutowired;

    @Test
    public void testGetAlumnoById() throws Exception {
        // Arrange
        AlumnoDTO alumnoDTO = AlumnoDTO.builder().id(1L).name("Juan").ap1("Perez").build();

        // Act
        when(alumnoService.getAlumnoById(1L)).thenReturn(alumnoDTO);
//
//        // Assert
//        mockMvc.perform(get("/api/v1/alumno/1"))
//                .andExpect(status().isOk()) // Verificar que se recibe un código de estado 200 OK
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)) // Verificar que se recibe un JSON en la respuesta
//                .andExpect(jsonPath("$.id").value(1)) // Verificar que el JSON contiene el campo "id" con el valor 1
//                .andExpect(jsonPath("$.name").value("Juan")) // Verificar que el JSON contiene el campo "nombre" con el valor "Juan"
//                .andExpect(jsonPath("$.ap1").value("Perez")); // Verificar que el JSON contiene el campo "apellido" con el valor "Perez"

        // Verificar que el método getAlumnoById(1L) del servicio se llama una vez
        verify(alumnoService, times(1)).getAlumnoById(1L);
    }

    @Test
    void getAlumnoById_ok() throws Exception {

        // Arrange
        AlumnoDTO alumnoDTO = AlumnoDTO.builder().name("pepe").ap1("juan").build();

        // Act
        when(alumnoService.getAlumnoById(1L)).thenReturn(alumnoDTO);

        // Assert
        mockMvc.perform(get("/api/v1/alumno/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("pepe"))
                .andExpect(jsonPath("$.ap1").value("juan"));
                //.andExpect(ResultMatcher)
                //.andExpect(ResultActions);
    }



//    @Test
//    void getAlumnoById_Bad_Request() {
////        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id enviado incorrecto: " + id);
////        return ResponseEntity.status(HttpStatus.OK).body(alumnoDTO);
////        return ResponseEntity.notFound().build();
//    }
//
//    void getAlumnoById_not_found() throws Exception {
//        mockMvc.perform(get("/api/v1/alumno/1"))
//                .andExpect(status().isOk())
//                //.andExpect(content().string("juan"))
//                //.andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$[0].name").value("pepe"))
//                .andExpect(jsonPath("$[0].ap1").value("juan"));
//    }
//
//    @Test
//    void getAlumnoByName() {
//    }
//
//    @Test
//    void getAlumnoByNameAndAp1Param() {
//    }
//
//    @Test
//    void getAlumnoByNameAndAp1Path() {
//    }
//
//    @Test
//    void altaAlumno() {
//    }
//
//    @Test
//    void modificarAlumno() {
//    }
//
//    @Test
    void borrarAlumno() throws Exception {
        mockMvc.perform(delete("alumno/18"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

    }
}