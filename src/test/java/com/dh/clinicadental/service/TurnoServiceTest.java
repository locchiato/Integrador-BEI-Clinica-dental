package com.dh.clinicadental.service;

import com.dh.clinicadental.model.Paciente;
import com.dh.clinicadental.model.Turno;
import com.dh.clinicadental.model.dto.DomicilioDTO;
import com.dh.clinicadental.model.dto.OdontologoDTO;
import com.dh.clinicadental.model.dto.PacienteDTO;
import com.dh.clinicadental.model.dto.TurnoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TurnoServiceTest {

    DomicilioDTO domicilio = armarDomicilio("Union Street", 2298, "Barran", "Santa Fe");
    PacienteDTO paciente = armarPaciente("Rogelio", "Diaz", 34471213, domicilio);
    OdontologoDTO odontologo = armarOdontologo("Santiago", "Paz", 3455647);
    TurnoDTO turno = armarTurno(paciente, odontologo, "2021-09-10");

    @Autowired
    TurnoService turnoService;

    @Autowired
    ObjectMapper mapper;

    TurnoDTO turnoGuardado;

    @BeforeEach
    void setUp(){
        turnoService.createTurno(turno);
        turnoGuardado = turnoService.getAll().stream().collect(Collectors.toList()).get(0);
    }

    //hecho
    @Test
    void createTurno() {
        DomicilioDTO domicilio = armarDomicilio("Union Street", 2298, "Baaarran", "Santa Fe");
        PacienteDTO paciente = armarPaciente("Rogelio", "Diaaaz", 34471213, domicilio);
        OdontologoDTO odontologo = armarOdontologo("Santiago", "Paaaz", 3455647);
        TurnoDTO turno = armarTurno(paciente, odontologo, "2021-09-10");
        turnoService.createTurno(turno);

        assertEquals(2, turnoService.getAll().size());
    }

    @Test
    void readTurno() {
        assertNotNull(turnoService.readTurno(turnoGuardado.getId()));
    }


    @Test
    void updateTurno() {
        TurnoDTO turnoEncontrado = turnoService.readTurno(turnoGuardado.getId());

        assertEquals(turnoGuardado.getDate().getTime(), turnoEncontrado.getDate().getTime());

        Date hoy = new Date();
        turnoEncontrado.setDate(hoy);
        turnoService.updateTurno(turnoEncontrado);


        assertNotEquals(turnoGuardado.getDate().getTime(),
                turnoService.readTurno(turnoGuardado.getId()).getDate().getTime());

    }

    @Test
    void deleteTurno() {
        assertNotNull(turnoService.readTurno(turnoGuardado.getId()));
        turnoService.deleteTurno(turnoGuardado.getId());
        assertNull(turnoService.readTurno(turnoGuardado.getId()));
    }

    @AfterEach
    void setCleanup() {
        for (TurnoDTO turno : turnoService.getAll()) {
            turnoService.deleteTurno(turno.getId());
        }
    }


    private OdontologoDTO armarOdontologo(String nombre, String apellido, Integer matricula) {
        OdontologoDTO o = new OdontologoDTO();
        o.setNombre(nombre);
        o.setApellido(apellido);
        o.setMatricula(matricula);
        return o;
    }

    private PacienteDTO armarPaciente(String nombre, String apellido, Integer dni, DomicilioDTO domicilio) {
        PacienteDTO p = new PacienteDTO();
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setDni(dni.toString());
        p.setDomicilio(domicilio);

        return p;
    }

    private DomicilioDTO armarDomicilio(String calle, Integer numero, String localidad, String provincia) {
        DomicilioDTO d = new DomicilioDTO();
        d.setCalle(calle);
        d.setNumero(numero.toString());
        d.setLocalidad(localidad);
        d.setProvincia(provincia);
        return d;
    }

    private TurnoDTO armarTurno(PacienteDTO paciente, OdontologoDTO odontologo, String date ) {
        TurnoDTO t = new TurnoDTO();
        t.setPaciente(paciente);
        t.setOdontologo(odontologo);
        t.setDate(getDate(date));
        return t;
    }

    private Date getDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateNuevo = null;
        try {
            dateNuevo = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateNuevo;
    }

}