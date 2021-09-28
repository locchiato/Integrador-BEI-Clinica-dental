package com.dh.clinicadental.service;

import com.dh.clinicadental.dao.IDomicilioRepository;
import com.dh.clinicadental.dao.IOdontologoRepository;
import com.dh.clinicadental.dao.IPacienteRepository;
import com.dh.clinicadental.dao.ITurnoRepository;
import com.dh.clinicadental.model.Domicilio;
import com.dh.clinicadental.model.Odontologo;
import com.dh.clinicadental.model.Paciente;
import com.dh.clinicadental.model.Turno;
import com.dh.clinicadental.model.dto.DomicilioDTO;
import com.dh.clinicadental.model.dto.OdontologoDTO;
import com.dh.clinicadental.model.dto.PacienteDTO;
import com.dh.clinicadental.model.dto.TurnoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TurnoService {

    @Autowired
    ITurnoRepository turnoRepository;

    @Autowired
    IPacienteRepository pacienteRepository;

    @Autowired
    IOdontologoRepository odontologoRepository;

    @Autowired
    IDomicilioRepository domicilioRepository;

    @Autowired
    ObjectMapper mapper;


    private void saveTurno(TurnoDTO turnoDTO) {
        turnoRepository.save(manejarDTO(turnoDTO));
    }

    private Turno manejarDTO(TurnoDTO turnoDTO) {
        Domicilio domicilio = obtenerDomicilio(turnoDTO.getPaciente().getDomicilio());
        Paciente paciente = mapper.convertValue(turnoDTO.getPaciente(), Paciente.class);

        paciente.setDomicilio(domicilio);
        pacienteRepository.save(paciente);

        Odontologo odontologo = mapper.convertValue(turnoDTO.getOdontologo(), Odontologo.class);
        odontologoRepository.save(odontologo);

        System.out.println(paciente);
        System.out.println(odontologo);

        if(turnoDTO.getId() != null){
            turnoDTO.setPaciente(mapper.convertValue(paciente, PacienteDTO.class));
            turnoDTO.setOdontologo(mapper.convertValue(odontologo, OdontologoDTO.class));
            return mapper.convertValue(turnoDTO, Turno.class);
        }
        return new Turno(paciente, odontologo, turnoDTO.getDate());
    }

    private Domicilio obtenerDomicilio(DomicilioDTO domicilioDTO) {
        List<Domicilio> domicilios = domicilioRepository.findAll();

        for (Domicilio domicilio : domicilios) {
            if(domicilioDTO.getCalle().equals(domicilio.getCalle()) &&
                    domicilioDTO.getNumero().equals(domicilio.getNumero()) &&
                    domicilioDTO.getLocalidad().equals(domicilio.getLocalidad()) &&
                    domicilioDTO.getProvincia().equals(domicilio.getProvincia()))
                    return domicilio;
        }
        return domicilioRepository.save(mapper.convertValue(domicilioDTO, Domicilio.class));
    }

    public void createTurno(TurnoDTO odontologo) {
        saveTurno(odontologo);
    }

    public TurnoDTO readTurno(Long id) {
        TurnoDTO turnoDTO = null;
        Optional<Turno> turno = turnoRepository.findById(id);
        if (turno.isPresent()) {
            turnoDTO = mapper.convertValue(turno, TurnoDTO.class);
        }
        return turnoDTO;
    }

    public void updateTurno(TurnoDTO turno) {
        saveTurno(turno);
    }

    public void deleteTurno(Long id) {
        turnoRepository.deleteById(id);
    }

    public Collection<TurnoDTO> getAll() {
        List<Turno> turnos = turnoRepository.findAll();
        return transformToDTO(turnos);
    }

    private Set<TurnoDTO> transformToDTO(Collection<Turno> turnos) {
        Set<TurnoDTO> turnosDTO = new HashSet<>();
        for (Turno turno : turnos) {
            TurnoDTO turnoDTO = mapper.convertValue(turno, TurnoDTO.class);
            turnosDTO.add(turnoDTO);
        }
        return turnosDTO;
    }

}
