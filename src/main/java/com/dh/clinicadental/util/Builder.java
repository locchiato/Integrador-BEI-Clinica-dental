package com.dh.clinicadental.util;

import com.dh.clinicadental.model.dto.DomicilioDTO;
import com.dh.clinicadental.model.dto.OdontologoDTO;
import com.dh.clinicadental.model.dto.PacienteDTO;
import com.dh.clinicadental.model.dto.TurnoDTO;

import static com.dh.clinicadental.util.Util.stringToDate;

public class Builder {


    public static OdontologoDTO armarOdontologo(String nombre, String apellido, Integer matricula) {
        OdontologoDTO o = new OdontologoDTO();
        o.setNombre(nombre);
        o.setApellido(apellido);
        o.setMatricula(matricula);
        return o;
    }

    public static PacienteDTO armarPaciente(String nombre, String apellido, Integer dni, DomicilioDTO domicilio) {
        PacienteDTO p = new PacienteDTO();
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setDni(dni.toString());
        p.setDomicilio(domicilio);
        return p;
    }

    public static DomicilioDTO armarDomicilio(String calle, Integer numero, String localidad, String provincia) {
        DomicilioDTO d = new DomicilioDTO();
        d.setCalle(calle);
        d.setNumero(numero.toString());
        d.setLocalidad(localidad);
        d.setProvincia(provincia);
        return d;
    }

    public static TurnoDTO armarTurno(PacienteDTO paciente, OdontologoDTO odontologo, String date ) {
        TurnoDTO t = new TurnoDTO();
        t.setPaciente(paciente);
        t.setOdontologo(odontologo);
        t.setFecha(stringToDate(date));
        return t;
    }

}
