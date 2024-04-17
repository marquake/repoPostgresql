package com.mms.appAlumnos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    List<Alumno> findByName(String name);

    List<Alumno> findByNameAndAp1(@Param("name") String name, @Param("ap1") String ap1);

    @Query("SELECT a FROM Alumno a WHERE a.name = :name AND a.ap1 = :ap1")
    List<Alumno> findByCustomeQueryNameAndAp1(@Param("name") String name, @Param("ap1") String ap1);

}
