package com.dh.clinicadental.service;

import com.dh.clinicadental.dao.IDomicilioRepository;
import com.dh.clinicadental.model.Domicilio;
import com.dh.clinicadental.model.dto.DomicilioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DomicilioService {

    @Autowired
    IDomicilioRepository domicilioRepository;

    @Autowired
    ObjectMapper mapper;

    public DomicilioDTO createDomicilio(DomicilioDTO domicilioDTO) {
        return saveDomicilio(domicilioDTO);
    }

    private DomicilioDTO saveDomicilio(DomicilioDTO d) {
        return mapper.convertValue(domicilioRepository.save(transformToEntity(d)), DomicilioDTO.class);
    }

    private Domicilio transformToEntity(DomicilioDTO d) {
        return mapper.convertValue(d, Domicilio.class);
    }


}
