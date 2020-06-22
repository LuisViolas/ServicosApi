package com.springApi.Controller;

import com.springApi.Model.Cliente;
import com.springApi.Repository.ClienteRepository;
import com.springApi.service.RegistarCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RegistarCliente registarCliente;

    @GetMapping
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar ( @Valid @RequestBody  Cliente cliente){
       return registarCliente.registar(cliente);
    }
    @PutMapping("{clienteId}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId , @RequestBody Cliente cliente){

        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteId);
        return ResponseEntity.ok(registarCliente.registar(cliente));
    }
    @DeleteMapping("/{clientesId}")
    public ResponseEntity<Void> remover(@PathVariable Long clienteId){
        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }
        registarCliente.excluir(clienteId);
        return ResponseEntity.noContent().build();
    }

}
