package com.dh.clinicadental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String home() {
        return "Bienvenidos al sistema";
    }

    @GetMapping("/user")
    public String user() {
        return "Hola User";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hola Admin";
    }



}
