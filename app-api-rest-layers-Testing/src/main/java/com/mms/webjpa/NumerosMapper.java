package com.mms.webjpa;

public class NumerosMapper {

    static NumerosDTO toNumeroDTO(Numeros numeros){
        return NumerosDTO
                .builder()
                .id(numeros.getId())
                .numero(numeros.getNumero())
                .build();
    }
    static Numeros toNumero(NumerosDTO numerosDTO){
        return Numeros
                .builder()
                .id(numerosDTO.getId())
                .numero(numerosDTO.getNumero())
                .build();
    }

}
