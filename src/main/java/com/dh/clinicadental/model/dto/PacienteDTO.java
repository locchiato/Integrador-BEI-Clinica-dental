package com.dh.clinicadental.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PacienteDTO {
    
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private DomicilioDTO domicilio;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PacienteDto{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", apellido='").append(apellido).append('\'');
        sb.append(", dni='").append(dni).append('\'');
        sb.append(", domicilio=").append(domicilio);
        sb.append('}');
        return sb.toString();
    }

}