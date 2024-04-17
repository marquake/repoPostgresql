package com.mms.webjpa;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NumerosController {
    private NumerosService numerosService;

    public NumerosController(NumerosService numerosService){
        this.numerosService = numerosService;
    }

    // GET: http://localhost:8080/numeros
    @GetMapping("/numeros")
    public ResponseEntity<?> getNumeros(){
        // Si no vienen números, error bad request.
        return ResponseEntity.ok(numerosService.getListaNumeros() );
    }

    // GET: http://localhost:8080/ordenar-num-bbdd
    @GetMapping("/ordenar-num-bbdd")
    public ResponseEntity<?> ordenarNumerosBBDD(){
        System.out.println("ordenarNumerosBBDD" );
        return ResponseEntity.ok(
                ordenarListaNumeros(
                        numerosService.getListaNumeros().stream().map(NumerosDTO::getNumero).toList()
                )
        );
    }

    // POST: http://localhost:8080/ordenar
    // BODY REQUEST: [1, 15, 5, 7, 3]
    @PostMapping(value = "/ordenar", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Integer>> ordenarListaDefinitiva(@RequestBody List<Integer> numeros) {

        System.out.println("ordenarListaDefinitiva" );
        if (numeros == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok( ordenarListaNumeros(numeros) );
    }

    public List<Integer> ordenarListaNumeros(List<Integer> numeros){

        System.out.println("ordenarListaNumeros: numeros: " + numeros );
        numeros.stream().forEach(System.out::println);

        List<Integer> numerosMutable = new ArrayList<>(numeros);

        numerosMutable.sort((num1, num2) -> {
            int chars1 = (int) Integer.toBinaryString(num1).chars().filter(c -> c == '1').count();
            int chars2 = (int) Integer.toBinaryString(num2).chars().filter(c -> c == '1').count();

            if (chars1 != chars2) {     // comparación binaria caracteres
                return chars2 - chars1;
            } else {                    // comparación decimal
                return num1 - num2;
            }
        });

        return numerosMutable;
    }
}
