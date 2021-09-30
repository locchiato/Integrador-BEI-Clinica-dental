package com.dh.clinicadental.service;

import com.dh.clinicadental.dao.IOdontologoRepository;
import com.dh.clinicadental.model.Odontologo;
import com.dh.clinicadental.model.dto.OdontologoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OdontologoService {

    @Autowired
    IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;

    public OdontologoDTO createOdontologo(OdontologoDTO odontologo) {
        return saveOdontologo(odontologo);
    }

    private OdontologoDTO saveOdontologo(OdontologoDTO odontologoDTO) {
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        return mapper.convertValue(odontologoRepository.save(odontologo), OdontologoDTO.class);
    }

    public OdontologoDTO readOdontologo(Long id) {
        Odontologo o = odontologoRepository.findById(id).orElse(null);
        return mapper.convertValue(o, OdontologoDTO.class);
    }

    public void updateOdontologo(OdontologoDTO odontologo) {
        saveOdontologo(odontologo);
    }

    public void deleteOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }

    public Collection<OdontologoDTO> getAll() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        return transformToDTO(odontologos);
    }

    public Set<OdontologoDTO> getOdontologoWithApellidoLike(String apellido) {
        Set<Odontologo> odontologos = odontologoRepository.getOdontologoByApellidoLike(apellido);
        return transformToDTO(odontologos);

    }

    private Set<OdontologoDTO> transformToDTO(Collection<Odontologo> odontologos) {
        Set<OdontologoDTO> odontologosDTO = new HashSet<>();
        for (Odontologo odontologo : odontologos) {
            OdontologoDTO odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);
            odontologosDTO.add(odontologoDTO);
        }
        return odontologosDTO;
    }

}
