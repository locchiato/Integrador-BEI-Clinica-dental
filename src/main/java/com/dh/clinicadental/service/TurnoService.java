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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TurnoService {

    private static final Logger logger = Logger.getLogger(TurnoService.class);

    @Autowired
    ITurnoRepository turnoRepository;

    @Autowired
    PacienteService pacienteService;

    @Autowired
    OdontologoService odontologoService;

    @Autowired
    ObjectMapper mapper;

    public void createTurno(TurnoDTO turno) {
        logger.info("Guardando turno...");
        saveTurno(turno);
    }

    private void saveTurno(TurnoDTO turnoDTO) {
        obtenerPaciente(turnoDTO);
        obtenerOdontologo(turnoDTO);
        turnoRepository.save(mapper.convertValue(turnoDTO, Turno.class));
    }

    private void obtenerPaciente(TurnoDTO pacienteDTO) {
        PacienteDTO paciente = pacienteDTO.getPaciente();
        pacienteDTO.setPaciente(pacienteService.createPaciente(paciente));
    }

    private void obtenerOdontologo(TurnoDTO pacienteDTO) {
        OdontologoDTO odontologo = pacienteDTO.getOdontologo();
        pacienteDTO.setOdontologo(odontologoService.createOdontologo(odontologo));
    }

    public TurnoDTO readTurno(Long id) {
        logger.info("Buscando turno...");
        Turno turno = turnoRepository.findById(id).orElse(null);
        return mapper.convertValue(turno, TurnoDTO.class);
    }

    public void updateTurno(TurnoDTO turno) {
        logger.info("Actualizando turno...");
        saveTurno(turno);
    }

    public void deleteTurno(Long id) {
        logger.info("Borrando turno...");
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
