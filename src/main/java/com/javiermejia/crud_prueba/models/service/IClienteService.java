package com.javiermejia.crud_prueba.models.service;

import java.util.List;

import com.javiermejia.crud_prueba.models.entity.Cliente;

public interface IClienteService {
    public List<Cliente> findAll();

    public void save(Cliente cliente);

    public Cliente findOne(Long id);

    public void delete(Long id);
}
