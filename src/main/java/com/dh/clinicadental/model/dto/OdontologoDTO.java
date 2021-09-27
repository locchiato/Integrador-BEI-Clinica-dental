package com.dh.clinicadental.model.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OdontologoDTO {
    
    private Long id;
    private String nombre;
    private String apellido;
    private Integer matricula;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PacienteDto{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", apellido='").append(apellido).append('\'');
        sb.append(", matricula='").append(matricula).append('\'');
        sb.append('}');
        return sb.toString();
    }
}