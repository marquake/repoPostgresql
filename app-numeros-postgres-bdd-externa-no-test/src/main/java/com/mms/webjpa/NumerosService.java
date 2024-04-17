package com.mms.webjpa;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumerosService {

    private NumerosRespository numerosRespository;

    public NumerosService(NumerosRespository numerosRespository){
        this.numerosRespository = numerosRespository;
    }

    public List<NumerosDTO> getListaNumeros(){
        System.out.println("getListaNumeros");
        return numerosRespository.findAll()
                .stream()
                .map(NumerosMapper::toNumeroDTO)
                .toList();
    }
}
