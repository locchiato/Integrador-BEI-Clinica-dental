package com.dh.clinicadental.controller;

import com.dh.clinicadental.model.Domicilio;
import com.dh.clinicadental.model.Paciente;
import com.dh.clinicadental.model.dto.DomicilioDto;
import com.dh.clinicadental.model.dto.PacienteDto;
import com.dh.clinicadental.service.DomicilioService;
import com.dh.clinicadental.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class PacienteController {

    PacienteService pacienteService;

    @Autowired
    DomicilioService domicilioService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        List<PacienteDto> todos = pacienteService.buscarTodos();
        if(todos.size() > 0){

        modelAndView.addObject("pacientes", todos);
            todos.forEach(System.out::println);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/crear", method = RequestMethod.GET)
    public String crear() {
        return "crear-data";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute PacienteDto paciente) {
        System.out.println(paciente);

        pacienteService.guardar(Paciente.from(paciente));
        return index();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return index();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Long id) {
        Optional<PacienteDto> paciente = pacienteService.buscar(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit-data");
        modelAndView.addObject("paciente", paciente.get());
        return modelAndView;
    }

    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute Paciente paciente) {
        pacienteService.actualizar(paciente);
        return index();
    }
}
