package com.javiermejia.crud_prueba.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javiermejia.crud_prueba.models.dao.EmpleadoDAO;
import com.javiermejia.crud_prueba.models.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{
    
    @Autowired
    private EmpleadoDAO empleadoDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> findAll(){
        return (List<Empleado>) empleadoDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Empleado empleado){
        empleadoDAO.save(empleado);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado findOne(Long id){
        return empleadoDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id){
        empleadoDAO.deleteById(id);
    }
}
