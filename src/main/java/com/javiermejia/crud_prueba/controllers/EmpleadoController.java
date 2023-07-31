package com.javiermejia.crud_prueba.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javiermejia.crud_prueba.models.entity.Empleado;
import com.javiermejia.crud_prueba.models.service.IEmpleadoService;

@Controller
@SessionAttributes("empleado")
public class EmpleadoController {
    
    @Autowired
    private IEmpleadoService empleadoService;

    @RequestMapping(value = "/listarEmpleados", method = RequestMethod.GET)
    public String listarEmpleados(Model model){
        List<Empleado> empleados = empleadoService.findAll();

        model.addAttribute("titulo", "Listado Empleados 👷‍♂️");
        model.addAttribute("empleados", empleados);
        return "listarEmpleados";
    }

    @RequestMapping(value = "/formEmpleado")
    public String crear(Model model){
        Empleado empleado = new Empleado();
        model.addAttribute("titulo", "Formulatio Empleado");
        model.addAttribute("empleado", empleado);
        return "formEmpleado";
    }

    @RequestMapping(value = "/formEmpleado/{id}")
    public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash){
        Empleado empleado = null;
        if(id>0){
            empleado = empleadoService.findOne(id);
            if(empleado == null){
                flash.addFlashAttribute("error", "El ID no existe");
                return "redirect://listarEmpleados";
            }
        } else {
            flash.addFlashAttribute("error", "El ID no puede ser 0");
            return "redirect:/listarEmpleados";
        }
        model.addAttribute("empleado", empleado);
        model.addAttribute("titulo", "Editar Cliente");
        return "formEmpleado";
    }

    @RequestMapping(value = "/formEmpleado", method = RequestMethod.POST)
    public String guardar(@Valid Empleado empleado, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status){
        if(result.hasErrors()){
            model.addAttribute("titulo", "Formulario Empleado");
            return "formEmpleado";
        }

        String mensajeFlash = (empleado.getId() != null) ? "Empleado editadoc con éxito!!" : "Empleado creado con éxito!!";
        empleadoService.save(empleado);
        status.setComplete();
        flash.addFlashAttribute("info", mensajeFlash);
        return "redirect:listarEmpleados";
    }
    @RequestMapping(value = "/eliminarEmpleado/{id}")
    public String eliminar(@PathVariable(value = "id") Long id , RedirectAttributes flash){
        if(id > 0 ){
            empleadoService.delete(id);
            flash.addFlashAttribute("success", "Empleado eliminado con éxito!!");
        }
        return "redirect:/listarEmpleados";
    }
}
