package com.javiermejia.crud_prueba.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javiermejia.crud_prueba.models.dao.SucursalDAO;
import com.javiermejia.crud_prueba.models.entity.Sucursal;

@Service
public class SucursalServiceImpl implements ISucursalService {
    
    @Autowired
    private SucursalDAO sucursalDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Sucursal> findAll(){
        return (List<Sucursal>) sucursalDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Sucursal sucursal){
        sucursalDAO.save(sucursal);
    }

    @Override
    @Transactional(readOnly = true)
    public Sucursal findOne(Long id) {
        return sucursalDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id){
        sucursalDAO.deleteById(id);
    }
}
