package com.javiermejia.crud_prueba.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javiermejia.crud_prueba.models.dao.ClienteDAO;
import com.javiermejia.crud_prueba.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {
    
    @Autowired
    private ClienteDAO clienteDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Cliente cliente){
        clienteDAO.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        return clienteDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id){
        clienteDAO.deleteById(id);
    } 
}
