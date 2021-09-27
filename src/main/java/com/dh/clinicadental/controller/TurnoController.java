package com.dh.clinicadental.controller;

import com.dh.clinicadental.model.dto.TurnoDTO;
import com.dh.clinicadental.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    TurnoService turnoService;

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody TurnoDTO turno)
    {
        turnoService.createTurno(turno);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public TurnoDTO getStudent(@PathVariable Long id)
    {
        return turnoService.readTurno(id);
    }

    @PutMapping()
    public ResponseEntity<?> updateStudent(@RequestBody TurnoDTO turnoDTO)
    {
        turnoService.updateTurno(turnoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable long id)
    {
        turnoService.deleteTurno(id);
        return ResponseEntity.status(HttpStatus.OK).body("eliminado");

    }

    @GetMapping("/list")
    public Collection<TurnoDTO> listStudents()
    {
        return turnoService.getAll();
    }



}
