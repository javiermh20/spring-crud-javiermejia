package com.javiermejia.crud_prueba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
    
    @GetMapping({"","/","/index"})
    public String inicio(Model model) {
        model.addAttribute("titulo", "Inicio Crud 🤧");;
        return "index";
    }

    @GetMapping("/listadoCliente")
    public String listadoClientes(Model model) {
        model.addAttribute("titulo", "Listado de Clientes 🤧");
        return "listadoCliente";
    }

    @GetMapping("/listadoEmpleado")
    public String listadoEmpleados(Model model) {
        model.addAttribute("titulo", "Listado de Empleados 👷‍♂️");
        return "listadoEmpleado";
    }

    @GetMapping("/listadoProducto")
    public String listadoProductos(Model model) {
        model.addAttribute("titulo", "Listado de Productos ⚽");
        return "listadoProducto";
    }

    @GetMapping("/listadoSucursal")
    public String listadoSucursal(Model model) {
        model.addAttribute("titulo", "Listado de Sucursales 🏢");
        return "listadoSucursal";
    }
}
