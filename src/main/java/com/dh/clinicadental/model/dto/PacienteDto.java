package com.dh.clinicadental.model.dto;

import com.dh.clinicadental.model.Paciente;

import java.util.Date;
import java.util.Objects;

public class PacienteDto {
    
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fechaIngreso;
    private DomicilioDto domicilio;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DomicilioDto getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioDto domicilio){
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PacienteDto{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", apellido='").append(apellido).append('\'');
        sb.append(", dni='").append(dni).append('\'');
        sb.append(", fechaIngreso=").append(fechaIngreso);
        sb.append(", domicilio=").append(domicilio);
        sb.append('}');
        return sb.toString();
    }

    public static PacienteDto from(Paciente paciente){
        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.setId(paciente.getId());
        pacienteDto.setNombre(paciente.getNombre());
        pacienteDto.setApellido(paciente.getApellido());
        pacienteDto.setDni(paciente.getDni());
        pacienteDto.setFechaIngreso(paciente.getFechaIngreso());
        if(Objects.nonNull(paciente.getDomicilio())){
            pacienteDto.setDomicilio(DomicilioDto.from(paciente.getDomicilio()));
        }
        return pacienteDto;
    }
}