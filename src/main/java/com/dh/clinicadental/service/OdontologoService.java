package com.dh.clinicadental.service;

import com.dh.clinicadental.dao.IOdontologoRepository;
import com.dh.clinicadental.model.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    @Autowired
    private IOdontologoRepository odontologoRepository;

    // guarda uno
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return saveOdontologo(odontologo);
    }

    private Odontologo saveOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    // borra uno por id
    public void eliminar(Long id) {
        odontologoRepository.delete(odontologoRepository.getById(id));
    }

    // trae uno por id
    public Optional<Odontologo> buscar(Long id) {
        return Optional.of(odontologoRepository.getById(id));
    }

    // trae todos
    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }
}
