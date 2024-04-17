package com.mms.webjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class NumerosController {
    private NumerosService numerosService;

    /**
     * Implementa un endpoint REST que reciba como parámetro de entrada una lista de enteros.
     * El endpoint deberá devolver la lista de enteros ordenada en base a los siguientes criterios:
     *
     * Cuanto mayor sea el número de unos en la representación binaria del número entero,
     * más cerca estará el elemento del índice 0.
     *
     * En caso de que dos o más números tengan el mismo número de unos en su representación binaria,
     * el número decimal más pequeño aparece primero en la lista ordenada.
     *
     * Ejemplo:
     * Para la entrada: [1, 15, 5, 7, 3] la salida deberá de ser: [15, 7, 3, 5, 1]
     *
     * Explicación:
     * El número decimal cuya representación binaria contiene más unidades es 15 (1111 en binario),
     * por lo que va primero (índice = 0). El siguiente es el 7, con tres unos en su representación binaria.
     * Luego hay 2 números cuya representación binaria contiene la misma cantidad de unos, estos decimales
     * son 5 y 3, ambos con 2 unos. En este caso, el número 3 va primero (más cerca del índice = 0)
     * porque su representación decimal es más pequeña (3 < 5).
     * @param numerosService
     */
    public NumerosController(NumerosService numerosService){
        this.numerosService = numerosService;
    }

    @GetMapping("/numeros")
    public ResponseEntity<?> getNumeros(){

        // Si no vienen números, error bad request.

        return ResponseEntity.ok(numerosService.getListaNumeros() );
    }

    //POST
    //http://localhost:8080/ordenar
    //BODY REQUEST
    //[1, 15, 5, 7, 3]
    @PostMapping(value = "/ordenar", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Integer>> ordenarListaDefinitiva(@RequestBody List<Integer> numeros) {

        if (numeros == null){
            return ResponseEntity.badRequest().build();
        }

        numeros.sort((num1, num2) -> {
            int chars1 = (int) Integer.toBinaryString(num1).chars().filter(c -> c == '1').count();
            int chars2 = (int) Integer.toBinaryString(num2).chars().filter(c -> c == '1').count();

            if (chars1 != chars2) {     // comparación binaria caracteres
                return chars2 - chars1;
            } else {                    // comparación decimal
                return num1 - num2;
            }
        });

        //return numeros;
        return ResponseEntity.ok(numeros);
    }

}
