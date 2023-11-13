package com.jujubaprojects.hamburgeriajr.Controller;

import java.util.ArrayList;
import java.util.List;

import com.jujubaprojects.hamburgeriajr.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jujubaprojects.hamburgeriajr.Model.Cliente;
import com.jujubaprojects.hamburgeriajr.Model.Mensagem;
import com.jujubaprojects.hamburgeriajr.Model.Pedido;
import com.jujubaprojects.hamburgeriajr.Repository.ClienteRepository;

@RestController
public class ClienteController {
    
    @Autowired
    ClienteRepository clienteRepository;
    ClienteService clienteService;

     @Autowired
    Mensagem mensagem;

    @PostMapping("/cadastrarCliente")
   public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.cadastrarCliente(cliente), HttpStatus.CREATED);

  }

  @GetMapping("/listarCliente")
  public List<Cliente>listar(){
    return clienteService.listarCliente();


  }
  @GetMapping("/buscarCliente/{id}")
  public ResponseEntity<?> buscaCliente( @PathVariable long id){
    return new ResponseEntity<>(clienteService.buscaCliente(id),HttpStatus.OK);

  }

  @PutMapping("/atualizaCliente")
public ResponseEntity<?> atualizaCliente(@RequestBody Cliente cliente,long id) {

      return new ResponseEntity<>(clienteService.atualizaCliente(cliente,id), HttpStatus.OK);
} 



  @GetMapping("/contadorClientes")
  public long contador(){
    return clienteRepository.count();

  }

  @GetMapping("/clientesPorOrdemalfabetica")
  public List<Cliente> clientesPorOrdemalfabetica(){
    return clienteRepository.findByOrderByNome();

  }
}
