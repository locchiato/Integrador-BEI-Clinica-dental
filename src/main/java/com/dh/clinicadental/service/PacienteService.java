package com.dh.clinicadental.service;

import com.dh.clinicadental.dao.IPacienteRepository;
import com.dh.clinicadental.model.Paciente;
import com.dh.clinicadental.model.dto.DomicilioDTO;
import com.dh.clinicadental.model.dto.PacienteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PacienteService {

    @Autowired
    IPacienteRepository pacienteRepository;

    @Autowired
    DomicilioService domicilioService;

    @Autowired
    ObjectMapper mapper;

    public PacienteDTO createPaciente(PacienteDTO paciente) {
        return savePaciente(paciente);
    }

    private PacienteDTO savePaciente(PacienteDTO paciente) {
        obtenerDomicilio(paciente);
        return transformToDTO(pacienteRepository.save(transformToEntity(paciente)));
    }

    private void obtenerDomicilio(PacienteDTO pacienteDTO) {
        DomicilioDTO domicilio = pacienteDTO.getDomicilio();
        pacienteDTO.setDomicilio(domicilioService.createDomicilio(domicilio));
    }


    public PacienteDTO readPaciente(Long id) {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        return transformToDTO(paciente);
    }

    public void updatePaciente(PacienteDTO paciente) {
        savePaciente(paciente);
    }

    public void deletePaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    public Collection<PacienteDTO> getAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return transformAllToDTO(pacientes);
    }

    public Set<PacienteDTO> getPacienteWithApellidoLike(String apellido) {
        Set<Paciente> pacientes = pacienteRepository.getPacienteByApellidoLike(apellido);
        return transformAllToDTO(pacientes);
    }

    private Set<PacienteDTO> transformAllToDTO(Collection<Paciente> pacientes) {
        Set<PacienteDTO> pacientesDTO = new HashSet<>();
        for (Paciente paciente : pacientes) {
            PacienteDTO pacienteDTO = transformToDTO(paciente);
            pacientesDTO.add(pacienteDTO);
        }
        return pacientesDTO;
    }

    private Paciente transformToEntity(PacienteDTO p) {
        return mapper.convertValue(p, Paciente.class);
    }

    private PacienteDTO transformToDTO(Paciente p) {
        return mapper.convertValue(p, PacienteDTO.class);
    }

}
