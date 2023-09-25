package com.jujubaprojects.hamburgeriajr.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jujubaprojects.hamburgeriajr.Model.Pedido;
import com.jujubaprojects.hamburgeriajr.Repository.PedidoRepository;

@RestController
public class PedidoController {
    
   @Autowired
   PedidoRepository pedidoRepository;

   @PostMapping("/cadastrarPedido")
   public Pedido cadastrar(@RequestBody Pedido pedido){
    return pedidoRepository.save(pedido);

   }

   @GetMapping("/listarPedido")
   public ResponseEntity<?> listar(){
      return new ResponseEntity<>(pedidoRepository.findAll(), HttpStatus.OK);

   }

   @PutMapping("/atualizarPedido")
   public ResponseEntity<?> atualizarPedido(@RequestBody Pedido pedido){
   
        pedidoRepository.saveAndFlush(pedido);
         return new ResponseEntity<>("Pedido atualizado com Sucesso", HttpStatus.OK);
   }

   @DeleteMapping("/deletarPedido/{id}")
   public void deletar(@PathVariable long id){
        pedidoRepository.deleteById(id);

   }

}
