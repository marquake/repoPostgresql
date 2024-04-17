package com.mms.appAlumnos;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AlumnoRepositoryTest {

    @Mock
    AlumnoRepository alumnoRepository;


    @BeforeEach
    void setUp() {
        System.out.println("xx --");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll_alumnos(){
        // Arrange
        Alumno a1 = Alumno.builder().name("PEPE").ap1("AP1").build();
        Alumno a2 = Alumno.builder().name("JUAN").ap1("AP2").build();
        Alumno a3 = Alumno.builder().name("LUIS").ap1("AP3").build();

        when( alumnoRepository.findAll())
                .thenReturn(List.of(a1, a2, a3));

        // Act
        List<Alumno> listaAlumnos = alumnoRepository.findAll();

        // Assert
        Assertions.assertThat(listaAlumnos).isNotNull();
        verify(alumnoRepository, times(1)).findAll();
        assertEquals(listaAlumnos.get(1).getName(), "JUAN");
        assertEquals(listaAlumnos.get(2).getAp1(), "AP3");
    }

    @Test
    void findByName() {
    }
    @Test
    void findByName_getAlumno1FromLista_throws_ArrayIndexOutOfBoundsException() {
        // Arrange
        when( alumnoRepository.findAll())
                .thenReturn(List.of());

        // Act
        List<Alumno> listaAlumnos = alumnoRepository.findAll();

        // Assert
        Assertions.assertThat(listaAlumnos).isNotNull();
        verify(alumnoRepository, times(1)).findAll();

        assertThrows(ArrayIndexOutOfBoundsException.class, () ->  listaAlumnos.get(1));

    }

    @Test
    void findByNameAndAp1_data_not_found() {
        List<Alumno> listaAlumnos = alumnoRepository.findByNameAndAp1("pepe", "juan");
        Assertions.assertThat(listaAlumnos).isEmpty();
    }
    @Test
    void findByNameAndAp1_data_found() {
        // Arrange
        Alumno a1 = Alumno.builder().name("pepe").ap1("juan").build();

        // Act
        when(alumnoRepository.findByNameAndAp1("pepe", "juan"))
                .thenReturn(List.of(a1));

        // Assert
        List<Alumno> listaAlumnos = alumnoRepository.findByNameAndAp1("pepe", "juan");
        Assertions.assertThat(listaAlumnos).isNotEmpty();

        Assertions.assertThat(listaAlumnos.get(0).getName()).isEqualTo("pepe");
        Assertions.assertThat(listaAlumnos.get(0).getAp1()).isEqualTo("juan");

        assertEquals(listaAlumnos.get(0).getName(), "pepe");
        assertEquals(listaAlumnos.get(0).getAp1(), "juan");
    }

    @Test
    void saveAlumno_ok(){

        Alumno alumno = Alumno.builder().name("pepe").ap1("juan").build();

        when(alumnoRepository.save(alumno)).thenReturn(alumno);

        // Act
        Alumno alumnoSaved = alumnoRepository.save(alumno);

        // Assert
        Assertions.assertThat(alumnoSaved).isNotNull();
        assertEquals(alumnoSaved.getAp1(), "juan");

    }

    @Test
    void findByCustomeQueryNameAndAp1() {
    }
}