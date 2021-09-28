package com.dh.clinicadental.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.StringJoiner;

@Getter
@Setter
public class TurnoDTO {

    private Long id;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;
    private Date date;

    @Override
    public String toString() {
        return new StringJoiner(", ", TurnoDTO.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("paciente=" + paciente)
                .add("odontologo=" + odontologo)
                .add("date=" + date)
                .toString();
    }
}
