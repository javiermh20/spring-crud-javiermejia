package com.javiermejia.crud_prueba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javiermejia.crud_prueba.models.entity.Sucursal;
import com.javiermejia.crud_prueba.models.service.ISucursalService;

@Controller
@SessionAttributes("sucursal")
public class SucursalController {
    
    @Autowired
    private ISucursalService sucursalService;

    @RequestMapping(value = "/listarSucursales", method = RequestMethod.GET)
    public String listarSucursales(Model model){
        List<Sucursal> sucursales = sucursalService.findAll();

        model.addAttribute("titulo", "Listado de Sucursales 🏢");
        model.addAttribute("sucursales", sucursales);
        return "listarSucursales";
    }

    @RequestMapping(value="/formSucursal")
    public String crear(Model model){
        Sucursal sucursal = new Sucursal();
        model.addAttribute("titulo", "Formulario Sucursal");
        model.addAttribute("sucursal", sucursal);
        return "formSucursal";
    }

    @RequestMapping(value="/formSucursal/{id}")
    public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash){
        Sucursal sucursal = null;

        if(id >0){
            sucursal = sucursalService.findOne(id);
            if(sucursal == null){
                flash.addFlashAttribute("error", "El ID no existe");
                return "redirect:/listarSucursales";
            }
        } else {
            flash.addFlashAttribute("error", "El ID no puede ser cero");
            return "redirect:/listarSucursales";
        }
        model.addAttribute("sucursal", sucursal);
        model.addAttribute("titulo", "Editar Sucursal");;
        return "formSucursal";
    }

    @RequestMapping(value="/formSucursal", method = RequestMethod.POST)
    public String guardar(Sucursal sucursal, Model model, RedirectAttributes flash, SessionStatus status){
        String mensajeFlash = (sucursal.getId() != null)? "Sucursal editada con éxito" : "Sucursal creada con éxito";
        sucursalService.save(sucursal);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:listarSucursales";
    }

    @RequestMapping(value="/eliminarSucursal/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash){
        if(id > 0){
            sucursalService.delete(id);
            flash.addFlashAttribute("success", "Sucursal eliminada con éxito");
        }
        return "redirect:/listarSucursales";
    }

}
