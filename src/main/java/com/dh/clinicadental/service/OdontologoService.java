package com.dh.clinicadental.service;

import com.dh.clinicadental.dao.IOdontologoRepository;
import com.dh.clinicadental.model.Odontologo;
import com.dh.clinicadental.model.dto.OdontologoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OdontologoService {

    private static final Logger logger = Logger.getLogger(OdontologoService.class);

    @Autowired
    IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;

    public OdontologoDTO createOdontologo(OdontologoDTO odontologo) {
        logger.info("Guardando odontologo...");
        return saveOdontologo(odontologo);
    }

    private OdontologoDTO saveOdontologo(OdontologoDTO odontologoDTO) {
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        return mapper.convertValue(odontologoRepository.save(odontologo), OdontologoDTO.class);
    }

    public OdontologoDTO readOdontologo(Long id) {
        logger.info("Buscando odontologo...");
        Odontologo o = odontologoRepository.findById(id).orElse(null);
        return mapper.convertValue(o, OdontologoDTO.class);
    }

    public void updateOdontologo(OdontologoDTO odontologo) {
        logger.info("Actualizando odontologo...");
        saveOdontologo(odontologo);
    }

    public void deleteOdontologo(Long id) {
        logger.info("Borrando odontologo...");
        odontologoRepository.deleteById(id);
    }

    public Collection<OdontologoDTO> getAll() {
        logger.info("Trayendo odontologos...");
        List<Odontologo> odontologos = odontologoRepository.findAll();
        return transformToDTO(odontologos);
    }

    public Set<OdontologoDTO> getOdontologoWithApellidoLike(String apellido) {
        logger.info("Trayendo odontologos por apellido...");
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
