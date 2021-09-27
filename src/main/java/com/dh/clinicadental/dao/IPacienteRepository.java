package com.dh.clinicadental.dao;

import com.dh.clinicadental.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {


    @Query("from Paciente p where p.apellido like %:apellido%")
    Set<Paciente> getPacienteByApellidoLike(@Param("apellido")String apellido);

}
