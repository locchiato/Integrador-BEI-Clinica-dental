package com.dh.clinicadental.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.StringJoiner;

@Getter @Setter
public class PacienteDTO {
    
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fechaIngreso;
    private DomicilioDTO domicilio;

    @Override
    public String toString() {
        return new StringJoiner(", ", PacienteDTO.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nombre='" + nombre + "'")
                .add("apellido='" + apellido + "'")
                .add("dni='" + dni + "'")
                .add("fechaIngreso=" + fechaIngreso)
                .add("domicilio=" + domicilio)
                .toString();
    }
}