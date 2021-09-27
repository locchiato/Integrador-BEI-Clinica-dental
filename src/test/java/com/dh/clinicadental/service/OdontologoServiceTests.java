package com.dh.clinicadental.service;


import com.dh.clinicadental.model.Odontologo;
import com.dh.clinicadental.model.dto.OdontologoDTO;
import com.dh.clinicadental.service.OdontologoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OdontologoServiceTests {

    @Autowired
    private OdontologoService odontologoService;

    @BeforeEach
    public void beforeAll() throws Exception {

        OdontologoDTO o = new OdontologoDTO();
        o.setNombre("Santiago");
        o.setApellido("Paz");
        o.setMatricula(3455647);
        odontologoService.createOdontologo(o);

        OdontologoDTO o1 = new OdontologoDTO();
        o1.setNombre("Rogelio");
        o1.setApellido("Diaz");
        o1.setMatricula(3447);
        odontologoService.createOdontologo(o1);

    }

    @Test
    public void traerTodos() {
        Collection<OdontologoDTO> odontologos = odontologoService.getAll();
        assertTrue(!odontologos.isEmpty());
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
    public void eliminarOdontologoTest() {
        Collection<OdontologoDTO> odontologos = odontologoService.getAll();
        OdontologoDTO odontologo = odontologos.stream().collect(Collectors.toList()).get(0);

        assertTrue(odontologoService.readOdontologo(odontologo.getId()) != null);
        odontologoService.deleteOdontologo(odontologo.getId());
        assertTrue(odontologoService.readOdontologo(odontologo.getId()) == null);

    }

}
