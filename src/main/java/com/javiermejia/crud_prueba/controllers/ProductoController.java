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

import com.javiermejia.crud_prueba.models.entity.Producto;
import com.javiermejia.crud_prueba.models.service.IProductoService;

@Controller
@SessionAttributes("producto")
public class ProductoController {
    
    @Autowired
    private IProductoService productoService;

    @RequestMapping(value = "/listarProductos", method = RequestMethod.GET)
    public String listarProductos(Model model){

        List<Producto> productos = productoService.findAll();

        model.addAttribute("titulo", "Listado de Productos ⚽");
        model.addAttribute("productos", productos);
        return "listarProductos";
    }

    @RequestMapping(value="/formProducto")
    public String crear(Model model){
        Producto producto = new Producto();
        model.addAttribute("titulo", "Formulario Producto");
        model.addAttribute("producto", producto);
        return "formProducto";
    }

    @RequestMapping(value="/formProducto/{id}")
    public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash){
        Producto producto = null;

        if(id >0){
            producto = productoService.findOne(id);
            if(producto == null){
                flash.addFlashAttribute("error", "El ID no existe");
                return "redirect:/listarProductos";
            }
        } else {
            flash.addFlashAttribute("error", "El ID no puede ser cero");
            return "redirect:/listarProductos";
        }
        model.addAttribute("producto", producto);
        model.addAttribute("titulo", "Editar Producto");;
        return "formProducto";
    }

    @RequestMapping(value="/formProducto", method = RequestMethod.POST)
    public String guardar(@Valid Producto producto, BindingResult result, Model model, RedirectAttributes flash,  SessionStatus status){
        if(result.hasErrors()){
            model.addAttribute("titulo", "Formulario Producto");
            return "formProducto";
        }
        String mensajeFlash = (producto.getId() != null)? "Producto editado con éxito" : "Producto creado con éxito";
        productoService.save(producto);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:listarProductos";
    }

    @RequestMapping(value = "/eliminarProducto/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash){
        if(id >0){
            productoService.delete(id);
            flash.addFlashAttribute("success", "Producto eliminado con éxito");
        }
        return "redirect:/listarProductos";
    }
}
