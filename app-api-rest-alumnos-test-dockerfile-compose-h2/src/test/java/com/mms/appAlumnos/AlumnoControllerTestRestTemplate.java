package com.mms.appAlumnos;

import org.apache.coyote.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlumnoControllerTestRestTemplate {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getAlumnos() {

        ResponseEntity<AlumnoDTO[]> response = testRestTemplate.getForEntity("http://localhost:8080/api/v1/alumno", AlumnoDTO[].class);

        List<AlumnoDTO> listaEmpleados = Arrays.asList(response.getBody());

        System.out.println("listaEmpleados: " + listaEmpleados);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);

        assertEquals(listaEmpleados.get(1).getName(), "JUAN");
        assertEquals(listaEmpleados.get(1).getAp1(), "MARIN");

        //assertEquals(listaEmpleados.get(2).getName(), "JUAN");
        //assertEquals(listaEmpleados.get(1).getAp1(), "MARIN");
    }

    @Test
    void getAlumnoById() {
        //ResponseEntity<?> response = testRestTemplate.getForEnity("http://localhost:8080/api/v1/alumno/5");
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

    // Para que luego me funcione el borrado, primero doy de alta.
    @Test
    @Order(1)
    void altaAlumno() {
        // Este test va a fallar si no ejecuto un delete de este usuario antes
        // sería interesante ejecutar esto y luego un borrado, para poder seguir insertando nuevos usuarios
        // como este.
        AlumnoDTO nuevoAlumnoDTO = AlumnoDTO.builder().name("SERGIO").ap1("FERNI").build();

        ResponseEntity response = testRestTemplate
                .postForEntity("http://localhost:8080/api/v1/alumno", nuevoAlumnoDTO, AlumnoDTO.class);

        System.out.println("response: " + response);
        AlumnoDTO alumnoCreadoDTO = (AlumnoDTO) response.getBody();
        System.out.println("alumnoCreadoDTO: " + alumnoCreadoDTO);
    }

    @Test
    void modificarAlumno() {
    }

    // Me funciona el borrado porque primero ejecuto el alta
    @Test
    @Order(2)
    void borrarAlumno() {
        // Realizar la solicitud GET para ver el id del elemento que quiero borrar.
        ResponseEntity<AlumnoDTO[]> responseGet = testRestTemplate.exchange(
                "http://localhost:8080/api/v1/alumno/nameAp1/{name}/{ap1}",
                HttpMethod.GET,
                null,
                AlumnoDTO[].class,
                "SERGIO", // Nombre del alumno a buscar
                "FERNI" // Apellido paterno del alumno a buscar
        );

        System.out.println(" responseGet.getBody()! : " +  responseGet.getBody());
        List<AlumnoDTO> listaAlumnos = Arrays.asList(responseGet.getBody());
        System.out.println(" listaAlumnos : " +  listaAlumnos);
        AlumnoDTO alumnoDTO = listaAlumnos.get(0);

        // Creo una petición en la que le indico que voy a hacer un delete, que no espero nada de vuelta,
        // y las variables que se corresponden a los parámetros de la url.
        // Este path no tiene por qué ser un Hashmap, podría ir una variable detrás de otra como en el paso anterior.
        Map<String, Long> pathVariables = new HashMap<>();
        pathVariables.put("id", alumnoDTO.getId());
            ResponseEntity response = testRestTemplate.exchange("http://localhost:8080/api/v1/alumno/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                pathVariables);

        System.out.println("response: " +response);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }
}