package com.dh.clinicadental.controller;

import com.dh.clinicadental.model.dto.DomicilioDTO;
import com.dh.clinicadental.model.dto.OdontologoDTO;
import com.dh.clinicadental.model.dto.PacienteDTO;
import com.dh.clinicadental.model.dto.TurnoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static com.dh.clinicadental.util.Builder.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TurnoIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void createTurnoAPI() throws Exception
    {

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        DomicilioDTO domicilio = armarDomicilio("Union Street", 2298, "Baaarran", "Santa Fe");
        PacienteDTO paciente = armarPaciente("Rogelio", "Diaaaz", 34471213, domicilio);
        OdontologoDTO odontologo = armarOdontologo("Santiago", "Paaaz", 3455647);
        TurnoDTO turno = armarTurno(paciente, odontologo, "2021-09-10");

        String payloadJson = writer.writeValueAsString(turno);

        mockMvc.perform( MockMvcRequestBuilders
                        .get("/turnos")
                        .content(payloadJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
