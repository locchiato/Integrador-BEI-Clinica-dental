package com.dh.clinicadental.dao;

import com.dh.clinicadental.model.Odontologo;
import com.dh.clinicadental.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {
}
