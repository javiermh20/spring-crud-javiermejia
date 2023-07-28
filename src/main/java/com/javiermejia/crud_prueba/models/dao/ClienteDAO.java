package com.javiermejia.crud_prueba.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.javiermejia.crud_prueba.models.entity.Cliente;

public interface ClienteDAO extends PagingAndSortingRepository<Cliente, Long> {
    
}
