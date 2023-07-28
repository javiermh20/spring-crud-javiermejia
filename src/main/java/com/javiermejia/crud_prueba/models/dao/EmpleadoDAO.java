package com.javiermejia.crud_prueba.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.javiermejia.crud_prueba.models.entity.Empleado;

public interface EmpleadoDAO extends PagingAndSortingRepository<Empleado, Long> {
    
}
