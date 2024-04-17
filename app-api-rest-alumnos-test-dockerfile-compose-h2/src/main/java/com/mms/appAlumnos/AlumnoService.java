package com.mms.appAlumnos;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AlumnoService {

    List<AlumnoDTO> getAlumnos();

    AlumnoDTO getAlumnoById(Long id);

    List<AlumnoDTO> getAlumnoByName(String name);
    public List<AlumnoDTO> getAlumnoByNameAndAp1(AlumnoDTO alumnoDTO) ;

    AlumnoDTO altaAlumno(AlumnoDTO newAlumnoDTO) throws IllegalArgumentException;

    AlumnoDTO modificarAlumno(AlumnoDTO alumnoDTO) throws IllegalArgumentException;

     void borrarAlumno(Long id) throws IllegalArgumentException;
}
