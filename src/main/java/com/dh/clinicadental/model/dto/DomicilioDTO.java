package com.dh.clinicadental.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;

@Getter
@Setter
public class DomicilioDTO {

    private Long id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    public DomicilioDTO(){

    }

    public DomicilioDTO(String calle, String numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DomicilioDTO.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("calle='" + calle + "'")
                .add("numero='" + numero + "'")
                .add("localidad='" + localidad + "'")
                .add("provincia='" + provincia + "'")
                .toString();
    }
}
