package com.dh.clinicadental.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TurnoDTO {

    private Long id;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;
    private Date date;
}
