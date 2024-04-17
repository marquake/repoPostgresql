package com.mms.appAlumnos;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class AlumnoController {

    AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService){
        this.alumnoService = alumnoService;
    }

    /**
        1. **GET (Obtener)**:

        - 200 OK: La solicitud se completó con éxito y el servidor devuelve los datos solicitados.
        - 404 Not Found: El recurso solicitado no fue encontrado en el servidor.
        - 400 Bad Request: La solicitud no pudo ser entendida por el servidor debido a una sintaxis incorrecta o parámetros inválidos.
    */
    @GetMapping(value = "alumno", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> getAlumnos(){
        return ResponseEntity.ok(alumnoService.getAlumnos());
    }

    /**
     1. **GET (Obtener)**:

     - 200 OK: La solicitud se completó con éxito y el servidor devuelve los datos solicitados.
     - 404 Not Found: El recurso solicitado no fue encontrado en el servidor.
     - 400 Bad Request: La solicitud no pudo ser entendida por el servidor debido a una sintaxis incorrecta o parámetros inválidos.
     */
    @GetMapping(value = "alumno/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAlumnoById(@PathVariable Long id){
        System.out.println("getAlumnoById: " + id);
        if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id enviado incorrecto: " + id);
        }

        AlumnoDTO alumnoDTO = alumnoService.getAlumnoById(id);

        if (alumnoDTO != null){
            return ResponseEntity.status(HttpStatus.OK).body(alumnoDTO);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    /**
     1. **GET (Obtener)**:

     - 200 OK: La solicitud se completó con éxito y el servidor devuelve los datos solicitados.
     - 404 Not Found: El recurso solicitado no fue encontrado en el servidor.
     - 400 Bad Request: La solicitud no pudo ser entendida por el servidor debido a una sintaxis incorrecta o parámetros inválidos.
     */
    @GetMapping(value="alumno/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAlumnoByName(@RequestParam("name") String name){

        System.out.println("getAlumnoByName: " + name);

        if (name == null || name.isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre es vacío");
        }

        List<AlumnoDTO> alumnosEncontrados = alumnoService.getAlumnoByName(name);

        if (alumnosEncontrados.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno con nombre " + name + " no encontrado" );
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.getAlumnoByName(name));
        }
    }

    /**
     1. **GET (Obtener)**:

     - 200 OK: La solicitud se completó con éxito y el servidor devuelve los datos solicitados.
     - 404 Not Found: El recurso solicitado no fue encontrado en el servidor.
     - 400 Bad Request: La solicitud no pudo ser entendida por el servidor debido a una sintaxis incorrecta o parámetros inválidos.
     */
    @GetMapping(value="alumno/nameAp1", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAlumnoByNameAndAp1Param(@RequestParam("name") String name, @RequestParam("ap1") String ap1){

        // Esto podría ser más flexible, pero he preferido hacer los dos datos obligatorios para usar este método.
        if (name == null || name.isBlank() || ap1 == null || ap1.isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Faltan datos por informar. name: " + name + " ap1: " + ap1);
        }

        List<AlumnoDTO> alumnosEncontrados = alumnoService
                .getAlumnoByNameAndAp1(AlumnoDTO.builder().name(name).ap1(ap1).build());

        if (alumnosEncontrados.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Alumno con nombre/ap1 " + name + " " + ap1 + " no encontrado" );
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.getAlumnoByName(name));
        }
    }

    /**
     1. **GET (Obtener)**:

     - 200 OK: La solicitud se completó con éxito y el servidor devuelve los datos solicitados.
     - 404 Not Found: El recurso solicitado no fue encontrado en el servidor.
     - 400 Bad Request: La solicitud no pudo ser entendida por el servidor debido a una sintaxis incorrecta o parámetros inválidos.
     */
    @GetMapping(value="alumno/nameAp1/{name}/{ap1}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAlumnoByNameAndAp1Path(@PathVariable("name") String name, @PathVariable("ap1") String ap1){

        // Esto podría ser más flexible, pero he preferido hacer los dos datos obligatorios para usar este método.
        if (name == null || name.isBlank() || ap1 == null || ap1.isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Faltan datos por informar. name: " + name + " ap1: " + ap1);
        }

        List<AlumnoDTO> alumnosEncontrados = alumnoService
                .getAlumnoByNameAndAp1(AlumnoDTO.builder().name(name).ap1(ap1).build());

        if (alumnosEncontrados.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Alumno con nombre/ap1 " + name + " " + ap1 + " no encontrado" );
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.getAlumnoByName(name));
        }
    }

    /**
        3. **POST (Crear)**:

        - 201 Created: La solicitud de creación se realizó correctamente y se creó un nuevo recurso.
        - 400 Bad Request: La solicitud de creación no pudo ser procesada debido a datos incorrectos o
                            parámetros inválidos.
        - 409 Conflict: La solicitud de creación entraría en conflicto con el estado actual del servidor
                        (por ejemplo, intentar crear un recurso que ya existe).
    */
    @PostMapping(value ="alumno", produces = MediaType.APPLICATION_JSON_VALUE,
                                  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> altaAlumno(@RequestBody AlumnoDTO alumnoDTO){

        if (alumnoDTO == null || alumnoDTO.getName() == null){
            return ResponseEntity.badRequest()
                                 .body("Los datos de entrada no vienen informados correctamente: " + alumnoDTO);
        }

        AlumnoDTO newAlumno;
        try {
            newAlumno = alumnoService.altaAlumno(alumnoDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un recurso con los datos: " + alumnoDTO);
        }

        if (newAlumno == null){
            return ResponseEntity.internalServerError().body("Error interno: Alumno no creado.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newAlumno);
    }

    /**
        2. **PUT (Actualizar)**:

        - 200 OK: La solicitud de actualización se realizó correctamente.
        - 201 Created: Se creó un nuevo recurso como resultado de la solicitud de actualización.
        - 404 Not Found: El recurso que se intenta actualizar no fue encontrado.
        - 400 Bad Request: La solicitud de actualización no pudo ser procesada debido a datos incorrectos o
                            parámetros inválidos.
    */
    @PutMapping(value ="alumno", produces = MediaType.APPLICATION_JSON_VALUE,
                                 consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modificarAlumno(@RequestBody AlumnoDTO alumnoDTO){

        if (alumnoDTO == null || alumnoDTO.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La petición no es correcta " + alumnoDTO);
        }

        AlumnoDTO newAlumno;
        try {
            newAlumno = alumnoService.modificarAlumno(alumnoDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error interno: No se ha podido modificar alumno.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(newAlumno);
    }

    /**
        4. **DELETE (Eliminar)**:

        - 200 OK: La solicitud de eliminación se realizó correctamente.
        - 204 No Content: La solicitud de eliminación se realizó correctamente y no hay contenido para devolver.
        - 404 Not Found: El recurso que se intenta eliminar no fue encontrado.
        - 400 Bad Request: La solicitud de eliminación no pudo ser procesada debido a datos incorrectos o
                            parámetros inválidos.
     */
    @DeleteMapping(value ="alumno/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> borrarAlumno(@PathVariable Long id){

        if (id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id incorrecto: " + id);
        }

        try {
            alumnoService.borrarAlumno(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el alumno id: " + id);
        }

        return ResponseEntity.ok().body("borrado alumno con id: " + id);
    }

}
