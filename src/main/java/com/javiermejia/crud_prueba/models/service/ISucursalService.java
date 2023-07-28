package com.javiermejia.crud_prueba.models.service;

import java.util.List;

import com.javiermejia.crud_prueba.models.entity.Sucursal;

public interface ISucursalService {
    public List<Sucursal> findAll();

    public void save(Sucursal sucursal);

    public Sucursal findOne(Long id);

    public void delete(Long id);
}
