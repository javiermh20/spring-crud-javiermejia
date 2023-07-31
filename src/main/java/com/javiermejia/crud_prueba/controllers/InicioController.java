package com.javiermejia.crud_prueba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
    
    @GetMapping({"","/","/index"})
    public String inicio(Model model) {
        model.addAttribute("titulo", "Inicio CRUD 🤧");;
        return "index";
    }
}
