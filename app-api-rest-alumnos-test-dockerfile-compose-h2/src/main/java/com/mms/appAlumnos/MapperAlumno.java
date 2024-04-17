package com.mms.appAlumnos;

import org.springframework.stereotype.Component;

@Component
public class MapperAlumno {

    public static Alumno toEntity(AlumnoDTO alumnoDTO){
            return Alumno.builder()
                    .id(alumnoDTO.getId())
                    .name(alumnoDTO.getName())
                    .ap1(alumnoDTO.getAp1())
                    .build();
    }
    public static AlumnoDTO toDTO(Alumno alumno){
        return AlumnoDTO.builder()
                .id(alumno.getId())
                .name(alumno.getName())
                .ap1(alumno.getAp1())
                .build();
    }

}
