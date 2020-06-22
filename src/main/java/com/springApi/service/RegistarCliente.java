package com.springApi.service;

import com.springApi.Model.Cliente;
import com.springApi.Repository.ClienteRepository;
import com.springApi.exceptionhandler.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistarCliente {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente registar(Cliente cliente){

        Cliente clienteExistente =  clienteRepository.findByEmail(cliente.getEmail());

        if(clienteExistente != null && !clienteExistente.equals(cliente))
        {
            throw new NegocioException("Já Existe um cliente com esse email");
        }
        return clienteRepository.save(cliente);
    }

    public void excluir (Long clientId){
        clienteRepository.deleteById(clientId);
    }

}
