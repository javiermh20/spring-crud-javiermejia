package com.javiermejia.crud_prueba.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javiermejia.crud_prueba.models.dao.ProductoDAO;
import com.javiermejia.crud_prueba.models.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService {
    
    @Autowired
    private ProductoDAO productoDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll(){
        return (List<Producto>) productoDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Producto producto){
        productoDAO.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findOne(Long id) {
        return productoDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id){
        productoDAO.deleteById(id);
    }
}
