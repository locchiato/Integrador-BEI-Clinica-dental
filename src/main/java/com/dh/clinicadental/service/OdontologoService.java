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

    public void createOdontologo(OdontologoDTO odontologo) {
        saveOdontologo(odontologo);
    }

    private void saveOdontologo(OdontologoDTO odontologoDTO) {
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        odontologoRepository.save(odontologo);
    }

    public OdontologoDTO readOdontologo(Long id) {
        OdontologoDTO odontologoDTO = null;
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if (odontologo.isPresent()) {
            odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);
        }
        return odontologoDTO;
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
