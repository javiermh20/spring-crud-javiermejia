package com.javiermejia.crud_prueba.controllers;

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

import com.javiermejia.crud_prueba.models.entity.Cliente;
import com.javiermejia.crud_prueba.models.service.IClienteService;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
    
    @Autowired
    private IClienteService clienteService;

    @RequestMapping(value = "/listarClientes", method = RequestMethod.GET)
    public String listarClientes(Model model){
        Cliente cliente = new Cliente();
        
        model.addAttribute("titulo", "Listado de Clientes 🤧");
        model.addAttribute("clientes", cliente);
        return "listarClientes";
    }

    @RequestMapping(value="/formCliente")
    public String crear(Model model){
        Cliente cliente = new Cliente();
        model.addAttribute("titulo", "Formulario Cliente");
        model.addAttribute("cliente", cliente);
        return "formCliente";
    }

    @RequestMapping(value="/formCliente/{id}")
    public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash){
        Cliente cliente = null;

        if(id >0){
            cliente = clienteService.findOne(id);
            if(cliente == null){
                flash.addFlashAttribute("error", "El ID no existe");
                return "redirect:/listarClientes";
            }
        } else {
            flash.addFlashAttribute("error", "El ID no puede ser cero");
            return "redirect:/listarClientes";
        }
        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Editar Cliente");;
        return "formCliente";
    }

    @RequestMapping(value="/formCliente", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status){
        if(result.hasErrors()){
            model.addAttribute("titulo", "Formulario Cliente");
            return "formCliente";
        }

        String mensajeFlash = (cliente.getId() != null) ? "Cliente editado con éxito!" : "Cliente creado con éxito!";
        clienteService.save(cliente);
        status.setComplete();
        flash.addFlashAttribute("info", mensajeFlash);
        return "redirect:listarClientes";
    }

    @RequestMapping(value="/eliminarCliente/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash){
        if(id >0){
            clienteService.delete(id);
            flash.addFlashAttribute("success", "Cliente eliminado con éxito!");
        }
        return "redirect:/listarClientes";
    }

}
