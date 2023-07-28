package com.javiermejia.crud_prueba.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.javiermejia.crud_prueba.models.entity.Producto;

public interface ProductoDAO extends PagingAndSortingRepository<Producto, Long> {
    
}
