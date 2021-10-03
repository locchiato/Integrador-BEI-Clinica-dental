package com.dh.clinicadental.service;

import com.dh.clinicadental.GlobalExceptionHandler;
import com.dh.clinicadental.dao.IDomicilioRepository;
import com.dh.clinicadental.model.Domicilio;
import com.dh.clinicadental.model.dto.DomicilioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DomicilioService {

    private static final Logger logger = Logger.getLogger(DomicilioService.class);

    @Autowired
    IDomicilioRepository domicilioRepository;

    @Autowired
    ObjectMapper mapper;

    public DomicilioDTO createDomicilio(DomicilioDTO domicilioDTO) {
        return saveDomicilio(domicilioDTO);
    }

    private DomicilioDTO saveDomicilio(DomicilioDTO d) {
        logger.info("Guardando domicilio...");
        return mapper.convertValue(domicilioRepository.save(transformToEntity(d)), DomicilioDTO.class);
    }

    private Domicilio transformToEntity(DomicilioDTO d) {
        return mapper.convertValue(d, Domicilio.class);
    }


}
