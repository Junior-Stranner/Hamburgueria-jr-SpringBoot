package com.jujubaprojects.hamburgeriajr.Controller;

import java.util.ArrayList;
import java.util.List;
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

     @Autowired
    Mensagem mensagem;

    @PostMapping("/cadastrarCliente")
   public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {
    
   List<Cliente> clientes = new ArrayList<>();

   for (Cliente clienteExistente : clientes) {
    if (clienteExistente.getCpf().equalsIgnoreCase(cliente.getCpf())) {
        return new ResponseEntity<>("CPF já existente", HttpStatus.BAD_REQUEST);
    }
}

    if (cliente.getEndereco().isEmpty()) {
        return new ResponseEntity<>("Digite o seu endereço", HttpStatus.BAD_REQUEST);

   }else if (cliente.getRua().isEmpty()) {
         return new ResponseEntity<>("Digite a sua Rua", HttpStatus.BAD_REQUEST);
         
   }else if (cliente.getBairro().isEmpty()) {
         return new ResponseEntity<>("Digite o seu Bairro", HttpStatus.BAD_REQUEST);
   }

        return new ResponseEntity<>(clienteRepository.save(cliente), HttpStatus.CREATED);

  }

  @GetMapping("/listarCliente")
  public ResponseEntity<List<Cliente>> listar(){

   List<Cliente> clientes = clienteRepository.findAll();
    return new ResponseEntity<List<Cliente>>(clientes ,HttpStatus.OK);


  }
  @GetMapping("/buscarCliente/{id}")
  public ResponseEntity<?> buscaCliente( @PathVariable long id){

    if(clienteRepository.countById(id) == 0){
        return new ResponseEntity<String>("nenhum cliente encontrado , digite outro id", HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(clienteRepository.findById(id),HttpStatus.OK);

  }

  @PutMapping("/atualizaCliente")
public ResponseEntity<?> atualizaCliente(@RequestBody Cliente cliente) {

      clienteRepository.saveAndFlush(cliente);
      return new ResponseEntity<>(cliente, HttpStatus.OK);
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
