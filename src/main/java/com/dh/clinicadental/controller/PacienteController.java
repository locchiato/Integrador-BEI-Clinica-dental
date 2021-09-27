package com.dh.clinicadental.controller;

import com.dh.clinicadental.model.dto.PacienteDTO;
import com.dh.clinicadental.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;


    @PostMapping
    public ResponseEntity<?> addPaciente(@RequestBody PacienteDTO pacienteDTO)
    {
        System.out.println(pacienteDTO);
        pacienteService.createPaciente(pacienteDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public PacienteDTO getPaciente(@PathVariable Long id)
    {
        return pacienteService.readPaciente(id);
    }

    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody PacienteDTO pacienteDTO)
    {
        System.out.println(pacienteDTO);
        pacienteService.updatePaciente(pacienteDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable long id)
    {
        pacienteService.deletePaciente(id);
        return ResponseEntity.status(HttpStatus.OK).body("Paciente eliminado");

    }

    @GetMapping
    public Collection<PacienteDTO> listPacientes()
    {
        return pacienteService.getAll();
    }

    @GetMapping("/ape")
    public Set<PacienteDTO> listPacientes(@RequestParam String lastname)
    {
        return pacienteService.getPacienteWithApellidoLike(lastname);
    }


}
