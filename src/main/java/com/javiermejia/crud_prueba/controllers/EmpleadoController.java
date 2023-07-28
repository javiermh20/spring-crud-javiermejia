package com.javiermejia.crud_prueba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javiermejia.crud_prueba.models.entity.Empleado;
import com.javiermejia.crud_prueba.models.service.IEmpleadoService;

@Controller
@SessionAttributes("empleado")
public class EmpleadoController {
    
    @Autowired
    private IEmpleadoService empleadoService;

    @RequestMapping(value = "/listarEmpleados")
    public String listarEmpleados(Model model){
        Empleado empleado = new Empleado();

        model.addAttribute("titulo", "Listado Empleados 👷‍♂️");
        model.addAttribute("empleados", empleado);
        return "listarEmpleados";
    }

    @RequestMapping(value="/formEmpleado")
    public String crear(Model model){
        Empleado empleado = new Empleado();
        model.addAttribute("titulo", "Formulario Empleado");
        model.addAttribute("empleado", empleado);
        return "formEmpleado";
    }

    @RequestMapping(value = "/formEmpleado/{id}")
    public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash){
        Empleado empleado = null;

        if(id >0){
            empleado = empleadoService.findOne(id);
            if(empleado == null){
                flash.addFlashAttribute("error", "El ID no existe");
                return "redirect:/listarEmpleados";
            }
        } else {
            flash.addFlashAttribute("error", "El ID no puede ser cero");
            return "redirect:/listarEmpleados";
        }
        model.addAttribute("empleado", empleado);
        model.addAttribute("titulo", "Editar Empleado");;
        return "formEmpleado";
    }

    @RequestMapping(value = "/eliminarEmpleado/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flahs){
        if(id > 0){
            empleadoService.delete(id);
            flahs.addFlashAttribute("success", "Empleado eliminado con exito!");
        }
        return "redirect:/listarEmpleados";
    }
}
