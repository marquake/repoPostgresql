package com.mms.webjpa;

import java.util.List;

public class NumerosService {

    private NumerosRespository numerosRespository;

    public NumerosService(NumerosRespository numerosRespository){
        this.numerosRespository = numerosRespository;
    }

    public List<NumerosDTO> getListaNumeros(){
        return numerosRespository.findAll()
                .stream()
                .map(NumerosMapper::toNumeroDTO)
                .toList();
    }
}
