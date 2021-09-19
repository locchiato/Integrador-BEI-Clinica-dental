package com.dh.clinicadental.dao;

import com.dh.clinicadental.model.Domicilio;
import com.dh.clinicadental.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
}
