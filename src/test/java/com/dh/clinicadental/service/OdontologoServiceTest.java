package com.dh.clinicadental.service;


import com.dh.clinicadental.model.dto.OdontologoDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    OdontologoDTO odontologoGuardado;


    @BeforeEach
    public void beforeAll() throws Exception {

        // new OdontologoDTO( "nombre", "apellido", 1234)
        OdontologoDTO o = new OdontologoDTO();
        o.setNombre("Santiago");
        o.setApellido("Paz");
        o.setMatricula(3455647);
        odontologoService.createOdontologo(o);

        odontologoGuardado = odontologoService.getAll().stream().collect(Collectors.toList()).get(0);

    }

    @Test
    void createOdontologo() throws Exception {
        OdontologoDTO odontologo = armarOdontologo("Santiago", "Paaaz", 3455647);
        odontologoService.createOdontologo(odontologo);

        assertEquals(2, odontologoService.getAll().size());
    }

    @Test
    public void traerTodos() {
        Collection<OdontologoDTO> odontologos = odontologoService.getAll();
        assertFalse(odontologos.isEmpty());
        System.out.println(odontologos);
    }

    @Test
    public void guardarOdontologo() {
        Integer tam = odontologoService.getAll().size();

        OdontologoDTO o = new OdontologoDTO();
        o.setNombre("Santiago");
        o.setApellido("Paz");
        o.setMatricula(3455647);
        odontologoService.createOdontologo(o);

        assertTrue(odontologoService.getAll().size() > tam);

    }

    @Test
    public void eliminarodontologo() {
        Collection<OdontologoDTO> odontologos = odontologoService.getAll();
        OdontologoDTO odontologo = odontologos.stream().collect(Collectors.toList()).get(0);

        assertNotNull(odontologoService.readOdontologo(odontologo.getId()));
        odontologoService.deleteOdontologo(odontologo.getId());
        assertNull(odontologoService.readOdontologo(odontologo.getId()));

    }

    @AfterEach
    void setCleanup() {
        for (OdontologoDTO odontologo : odontologoService.getAll()) {
            odontologoService.deleteOdontologo(odontologo.getId());
        }
    }

    private OdontologoDTO armarOdontologo(String nombre, String apellido, Integer matricula) {
        OdontologoDTO o = new OdontologoDTO();
        o.setNombre(nombre);
        o.setApellido(apellido);
        o.setMatricula(matricula);
        return o;
    }

}
