package com.dh.clinicadental.service;


import com.dh.clinicadental.dao.IDomicilioRepository;
import com.dh.clinicadental.model.Domicilio;
import com.dh.clinicadental.model.dto.DomicilioDto;
import com.dh.clinicadental.model.dto.PacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DomicilioService {

    @Autowired
    private IDomicilioRepository domicilioRepository;

    // guarda uno
    public DomicilioDto guardar(Domicilio d) {
        return saveDomicilio(d);
    }

    private DomicilioDto saveDomicilio(Domicilio d) {
        return DomicilioDto.from(
                domicilioRepository.save(d)
        );
    }

    // trae uno por id
    public Optional<DomicilioDto> buscar(Long id) {
        return Optional.of(
                DomicilioDto.from(
                        domicilioRepository.getById(id)
                )
        );
    }

    // trae todos
    public List<DomicilioDto> buscarTodos() {
        return domicilioRepository.findAll().stream().map(DomicilioDto::from).collect(Collectors.toList());
    }

    // borra uno por id
    public void eliminar(Long id) {
        domicilioRepository.getById(id);
    }

    // trae solos los que tengan ese numero
    public List<Domicilio> buscarPorNumero(String numero) {
        return domicilioRepository.buscarDomicilio(numero);
    }
}
