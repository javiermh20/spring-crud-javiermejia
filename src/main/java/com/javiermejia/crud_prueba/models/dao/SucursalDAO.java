package com.javiermejia.crud_prueba.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.javiermejia.crud_prueba.models.entity.Sucursal;

public interface SucursalDAO extends PagingAndSortingRepository<Sucursal, Long> {
    
}
