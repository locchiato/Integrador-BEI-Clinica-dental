package com.dh.clinicadental.dao;

import com.dh.clinicadental.model.Domicilio;
import com.dh.clinicadental.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDomicilioRepository extends JpaRepository<Domicilio, Long> {

    @Query("select d from Domicilio d where d.numero=?1")
    List<Domicilio> buscarDomicilio(String numero);
}
