package com.dh.clinicadental.service;

import com.dh.clinicadental.dao.IPacienteRepository;
import com.dh.clinicadental.dao.ITurnoRepository;
import com.dh.clinicadental.model.Paciente;
import com.dh.clinicadental.model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {


    @Autowired
    private ITurnoRepository turnoRepository;

    // guarda uno
    public Turno guardar(Turno t) {
        return turnoRepository.save(t);
    }

    // trae uno por id
    public Optional<Turno> buscar(Long id) {
        return Optional.of(turnoRepository.getById(id));
    }

    // trae todos
    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }

    // borra uno por id
    public void eliminar(Long id) {
        turnoRepository.delete(turnoRepository.getById(id));
    }

    // edita uno
    public Turno actualizar(Turno t) {
        return turnoRepository.save(t);
    }

}
