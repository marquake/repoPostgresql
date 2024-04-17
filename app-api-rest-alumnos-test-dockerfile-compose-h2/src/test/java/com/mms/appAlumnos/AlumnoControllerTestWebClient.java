package com.mms.appAlumnos;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlumnoControllerTestWebClient {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getAlumnos() {
         webTestClient
                .get()
                .uri("http://localhost:8080/api/v1/alumno")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                 //.jsonPath("$")
                 //   .value(hasSize(6))
                .expectBodyList(AlumnoDTO.class)
                .consumeWith(
                    response -> {
                        List<AlumnoDTO> alumnos = response.getResponseBody();
                            //Assertions.assertEquals(alumnos.size(), 1);
                            Assertions.assertNotNull(alumnos);
                        }
                 );
    }


    @Test
    void getAlumnoById() {
    }

    @Test
    void getAlumnoByName() {
    }

    @Test
    void getAlumnoByNameAndAp1Param() {
    }

    @Test
    void getAlumnoByNameAndAp1Path() {
    }

    @Test
    void altaAlumno() {
        AlumnoDTO alumnoDTO = AlumnoDTO.builder().name("PEPITO").ap1("PEREZ").build();
        webTestClient
                .post()
                .uri("http://localhost:8080/api/v1/alumno")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(alumnoDTO)
                .exchange()

                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                    .jsonPath("$.name").isEqualTo(alumnoDTO.getName())
                    .jsonPath("$.ap1").isEqualTo(alumnoDTO.getAp1())
                    .jsonPath("S")
                        .value(hasSize(1));
    }

    @Test
    void modificarAlumno() {
    }

    @Test
    void borrarAlumno() {
        webTestClient.delete()
                .uri("http://localhost:8080/api/v1/alumno/1")
                .exchange()
                .expectStatus().isOk();


        webTestClient.delete()
                .uri("http://localhost:8080/api/v1/alumno/1")
                .exchange()
                .expectStatus().is4xxClientError();

    }

}