package com.dh.clinicadental.controller;

import com.dh.clinicadental.model.dto.OdontologoDTO;
import com.dh.clinicadental.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    OdontologoService odontologoService;


    @PostMapping
    public ResponseEntity<?> addOdontologo(@RequestBody OdontologoDTO odontologoDTO)
    {
        odontologoService.createOdontologo(odontologoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public OdontologoDTO getOdontologo(@PathVariable Long id)
    {
        return odontologoService.readOdontologo(id);
    }

    @PutMapping()
    public ResponseEntity<?> updateOdontologo(@RequestBody OdontologoDTO odontologoDTO)
    {
        odontologoService.updateOdontologo(odontologoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable long id)
    {
        odontologoService.deleteOdontologo(id);
        return ResponseEntity.status(HttpStatus.OK).body("Odontologo eliminado");

    }

    @GetMapping
    public Collection<OdontologoDTO> listOdontologo()
    {
        return odontologoService.getAll();
    }

    @GetMapping("/ape")
    public Set<OdontologoDTO> listOdontologo(@RequestParam String lastname)
    {
        return odontologoService.getOdontologoWithApellidoLike(lastname);
    }


}
