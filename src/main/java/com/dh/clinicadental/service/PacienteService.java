package com.dh.clinicadental.service;

import com.dh.clinicadental.dao.IDomicilioRepository;
import com.dh.clinicadental.dao.IPacienteRepository;
import com.dh.clinicadental.model.Domicilio;
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
    private IPacienteRepository pacienteRepository;

    @Autowired
    private IDomicilioRepository domicilioRepository;

    @Autowired
    ObjectMapper mapper;

    public void createPaciente(PacienteDTO paciente) {
        savePaciente(paciente);
    }

    public PacienteDTO readPaciente(Long id) {
        PacienteDTO pacienteDTO = null;
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()) {
            pacienteDTO = transformToDTO(paciente.get());
        }
        return pacienteDTO;
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

    private void savePaciente(PacienteDTO p) {
        Paciente paciente = transformToEntity(p);
        Domicilio domicilio = obtenerDomicilio(p.getDomicilio());
        paciente.setDomicilio(domicilio);
        pacienteRepository.save(paciente);
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

    public Set<PacienteDTO> getPacienteWithApellidoLike(String apellido) {
        Set<Paciente> pacientes = pacienteRepository.getPacienteByApellidoLike(apellido);
        return transformAllToDTO(pacientes);
    }

}
