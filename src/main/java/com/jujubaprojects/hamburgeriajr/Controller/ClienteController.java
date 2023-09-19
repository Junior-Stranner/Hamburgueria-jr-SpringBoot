package com.jujubaprojects.hamburgeriajr.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jujubaprojects.hamburgeriajr.Model.Cliente;
import com.jujubaprojects.hamburgeriajr.Model.Mensagem;
import com.jujubaprojects.hamburgeriajr.Repository.ClienteRepository;

@RestController
public class ClienteController {
    
    @Autowired
    ClienteRepository clienteRepository;

     @Autowired
    Mensagem mensagem;

    @PostMapping("/cadastrarCliente")
   public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {
    // Primeiro, verifique se o cliente já existe no banco de dados usando o CPF
    List<Cliente> clienteExistente = clienteRepository.findByCpf(cliente.getCpf());
    
    if (clienteExistente != null && clienteExistente.equals(cliente.getCpf())) {        // Cliente com o mesmo CPF já existe, retorne um erro
        return new ResponseEntity<String>("CPF já existente", HttpStatus.BAD_REQUEST);

    } else if(cliente.getEndereco().equals("")){
        return new ResponseEntity<>("Digite o seu endereço",HttpStatus.BAD_REQUEST);

    }else if(cliente.getRua().equals("")){
         return new ResponseEntity<>("Digite a sua Rua",HttpStatus.BAD_REQUEST);

    }else if(cliente.getBairro().equals("")){
         return new ResponseEntity<>("Digite o seu Bairro",HttpStatus.BAD_REQUEST);

    }
        return new ResponseEntity<Cliente>(clienteRepository.save(cliente), HttpStatus.CREATED);
    }
}
