package com.dh.clinicadental.service;

import com.dh.clinicadental.model.Domicilio;
import com.dh.clinicadental.model.dto.DomicilioDTO;
import com.dh.clinicadental.model.dto.PacienteDTO;
import com.dh.clinicadental.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    public void beforeAll() throws Exception {

        DomicilioDTO dom = new DomicilioDTO();
        dom.setCalle("Union Street");
        dom.setNumero("2298");
        dom.setLocalidad("Barran");
        dom.setProvincia("Chaco");

        PacienteDTO o = new PacienteDTO();
        o.setNombre("Santiago");
        o.setApellido("Paz");
        o.setDni("3455647");
        o.setDomicilio(dom);

        pacienteService.createPaciente(o);

    }

    @Test
    public void traerTodos() {
        Collection<PacienteDTO> pacientes = pacienteService.getAll();
        assertTrue(!pacientes.isEmpty());
        System.out.println(pacientes);
    }

    @Test
    public void guardarPaciente() {
        Integer tam = pacienteService.getAll().size();

        PacienteDTO o = new PacienteDTO();
        o.setNombre("Santia1go");
        o.setApellido("Paz");
        o.setDni("3455641117");
        o.setDomicilio(new DomicilioDTO("Union S9treet", "22989", "9Barran", "Buenos Aires"));
        pacienteService.createPaciente(o);

        assertTrue(pacienteService.getAll().size() > tam);

    }

    @Test
    public void eliminarPaciente() {
        Collection<PacienteDTO> pacientes = pacienteService.getAll();
        PacienteDTO paciente = pacientes.stream().collect(Collectors.toList()).get(0);

        assertTrue(pacienteService.readPaciente(paciente.getId()) != null);
        pacienteService.deletePaciente(paciente.getId());
        assertTrue(pacienteService.readPaciente(paciente.getId()) == null);

    }



}
