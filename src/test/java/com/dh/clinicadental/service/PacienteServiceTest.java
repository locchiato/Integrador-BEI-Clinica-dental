package com.dh.clinicadental.service;

import com.dh.clinicadental.model.Domicilio;
import com.dh.clinicadental.model.Paciente;
import com.dh.clinicadental.model.dto.DomicilioDTO;
import com.dh.clinicadental.model.dto.PacienteDTO;
import com.dh.clinicadental.model.dto.TurnoDTO;
import com.dh.clinicadental.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
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

    PacienteDTO pacienteGuardado;

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

        pacienteGuardado = pacienteService.createPaciente(o);

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
    void eliminarPaciente() {
        assertNotNull(pacienteService.readPaciente(pacienteGuardado.getId()));
        pacienteService.deletePaciente(pacienteGuardado.getId());
        assertNull(pacienteService.readPaciente(pacienteGuardado.getId()));
    }

    @AfterEach
    void setCleanup() {
        for (PacienteDTO paciente : pacienteService.getAll()) {
            pacienteService.deletePaciente(paciente.getId());
        }
    }

}
