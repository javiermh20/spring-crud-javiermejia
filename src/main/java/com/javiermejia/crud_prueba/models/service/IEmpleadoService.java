package com.javiermejia.crud_prueba.models.service;

import java.util.List;

import com.javiermejia.crud_prueba.models.entity.Empleado;

public interface IEmpleadoService {
    public List<Empleado> findAll();

    public void save(Empleado empleado);

    public Empleado findOne(Long id);

    public void delete(Long id);
}
