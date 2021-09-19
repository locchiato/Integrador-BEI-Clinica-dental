package com.dh.clinicadental.service;

import com.dh.clinicadental.dao.IDomicilioRepository;
import com.dh.clinicadental.dao.IPacienteRepository;
import com.dh.clinicadental.model.Domicilio;
import com.dh.clinicadental.model.Paciente;
import com.dh.clinicadental.model.dto.DomicilioDto;
import com.dh.clinicadental.model.dto.PacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private IPacienteRepository pacienteRepository;

    @Autowired
    private IDomicilioRepository domicilioRepository;

    // guarda uno
    public PacienteDto guardar(Paciente p) {
        p.setFechaIngreso(new Date());

        actualizarDomicilio(p);

        return savePaciente(p);
    }

    private void actualizarDomicilio(Paciente paciente) {
        Domicilio domicilioBuscado = paciente.getDomicilio();
        Optional<Domicilio> domicilio = domicilioRepository.buscarDomicilio(domicilioBuscado.getNumero()).stream()
                .filter(domicilioActual -> domicilioBuscado.getCalle().equals(domicilioActual.getCalle())).findFirst();

        if (domicilio.isPresent()) {
            paciente.setDomicilio(domicilio.get());
        } else {
            Domicilio dom = domicilioRepository.save(domicilioBuscado);
            paciente.setDomicilio(dom);
        }
    }

    // trae uno por id
    public Optional<PacienteDto> buscar(Long id) {
        return Optional.of(PacienteDto.from(pacienteRepository.getById(id)));
    }

    // trae todos
    public List<PacienteDto> buscarTodos() {
        return pacienteRepository.findAll().stream().map(PacienteDto::from).collect(Collectors.toList());
    }

    // borra uno por id
    public void eliminar(Long id) {
        pacienteRepository.delete(pacienteRepository.getById(id));
    }

    // edita uno
    public PacienteDto actualizar(Paciente p) {
        return savePaciente(p);
    }

    private PacienteDto savePaciente(Paciente p) {
        return PacienteDto.from(
                pacienteRepository.save(p)
        );
    }
}
