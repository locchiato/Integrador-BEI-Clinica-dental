package com.dh.clinicadental.service;

import com.dh.clinicadental.model.dto.DomicilioDTO;
import com.dh.clinicadental.model.dto.OdontologoDTO;
import com.dh.clinicadental.model.dto.PacienteDTO;
import com.dh.clinicadental.model.dto.TurnoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class TurnoServiceTest {

    @Autowired
    TurnoService turnoService;

    @Autowired
    ObjectMapper mapper;

    @Test
    void createTurno() {
        int tam = turnoService.getAll().size();

        DomicilioDTO d = new DomicilioDTO();
        d.setCalle("Union Street");
        d.setNumero("2298");
        d.setLocalidad("Barran");
        d.setProvincia("Santa Fe");

        PacienteDTO p = new PacienteDTO();
        p.setNombre("Rogelio");
        p.setApellido("Diaz");
        p.setDni("3447123");
        p.setDomicilio(d);

        OdontologoDTO o = new OdontologoDTO();
        o.setNombre("Santiago");
        o.setApellido("Paz");
        o.setMatricula(3455647);

        TurnoDTO t = new TurnoDTO();
        t.setDate(new Date());
        t.setPaciente(p);
        t.setOdontologo(o);
        turnoService.createTurno(t);

        assertTrue(turnoService.getAll().size() > tam);
    }

    @Test
    void readTurno() {
    }

    @Test
    void updateTurno() {
    }

    @Test
    void deleteTurno() {
    }
}