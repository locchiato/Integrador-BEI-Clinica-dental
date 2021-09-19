package com.dh.clinicadental.model.dto;

import com.dh.clinicadental.model.Domicilio;

import java.util.*;
import java.util.stream.Collectors;

public class DomicilioDto {
    
    private Long id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public static DomicilioDto from(Domicilio domicilio){
        DomicilioDto domicilioDto = new DomicilioDto();
        domicilioDto.setId(domicilio.getId());
        domicilioDto.setCalle(domicilio.getCalle());
        domicilioDto.setNumero(domicilio.getNumero());
        domicilioDto.setLocalidad(domicilio.getLocalidad());
        domicilioDto.setProvincia(domicilio.getProvincia());
        return domicilioDto;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DomicilioDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("calle='" + calle + "'")
                .add("numero='" + numero + "'")
                .add("localidad='" + localidad + "'")
                .add("provincia='" + provincia + "'")
                .toString();
    }
}