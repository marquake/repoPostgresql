package com.mms.appAlumnos;

import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository){
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public List<AlumnoDTO> getAlumnos() {
        //Alumno a1 = Alumno.builder().id(1L).name("Marcos").ap1("Melado").build();
        //Alumno a2 = Alumno.builder().id(2L).name("Fernando").ap1("Sánchez").build();
        //Alumno a3 = Alumno.builder().id(3L).name("juan").ap1("pepe").build();
        //return List.of(a1, a2, a3);
        return alumnoRepository.findAll().stream().map(MapperAlumno::toDTO).toList();
    }

    @Override
    public AlumnoDTO getAlumnoById(Long id) {
        return alumnoRepository.findById(id).map(MapperAlumno::toDTO).orElse(null);
    }

    @Override
    public List<AlumnoDTO> getAlumnoByName(String name) {
        return alumnoRepository.findByName(name).stream().map(MapperAlumno::toDTO).toList();
    }

    @Override
    public List<AlumnoDTO> getAlumnoByNameAndAp1(AlumnoDTO alumnoDTO) {
        return alumnoRepository.findByCustomeQueryNameAndAp1(alumnoDTO.getName(), alumnoDTO.getAp1())
                .stream()
                .map(MapperAlumno::toDTO)
                .toList();
    }

    @Override
    public AlumnoDTO altaAlumno(AlumnoDTO newAlumnoDTO) throws IllegalArgumentException {

        // busco si ya existe este alumno
        List<Alumno> alumnoRepetido = alumnoRepository.findByCustomeQueryNameAndAp1(newAlumnoDTO.getName(), newAlumnoDTO.getAp1());
        if (!alumnoRepetido.isEmpty()){
            throw new IllegalArgumentException("Alumno repetido ");
        }

        return MapperAlumno.toDTO(alumnoRepository.save(MapperAlumno.toEntity( newAlumnoDTO)));
    }

    @Override
    public AlumnoDTO modificarAlumno(AlumnoDTO alumnoDTO) throws IllegalArgumentException {

        Optional<Alumno> alumnoModificado = alumnoRepository.findById(alumnoDTO.getId());

        if (alumnoModificado.isEmpty()) {
            throw new IllegalArgumentException("No existe el alumno con id: " + alumnoDTO.getId());
        }

        alumnoModificado.get().setName(alumnoDTO.getName());
        alumnoModificado.get().setAp1(alumnoDTO.getAp1());

        return MapperAlumno.toDTO(alumnoRepository.save(alumnoModificado.get()));
    }

    @Override
    public void borrarAlumno(Long id) throws IllegalArgumentException{
        System.out.print("Alumno id: " + id);
        Optional<Alumno> alumnoDeleted = alumnoRepository.findById(id);

        alumnoDeleted.ifPresentOrElse(
                alumno -> {
                    System.out.print("borrando alumno " + id + " ...");
                    alumnoRepository.delete(alumno);
                },
                () -> {
                        System.out.print("No existe el alumno con id: " + id);
                        throw new IllegalArgumentException("No existe el alumno con id: " + id);
        });
    }
}
