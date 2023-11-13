package com.jujubaprojects.hamburgeriajr.Controller;

import java.util.List;

import com.jujubaprojects.hamburgeriajr.Service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jujubaprojects.hamburgeriajr.Model.Cardapio;
import com.jujubaprojects.hamburgeriajr.Model.Mensagem;
import com.jujubaprojects.hamburgeriajr.Repository.CardapioRepository;

@RestController

public class CardapioController {
    
    @Autowired
    CardapioService cardapioService;


    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Cardapio cardapio){
    return cardapioService.cadastrarAlterarCardapio(cardapio,"cadastrar");
}

    @GetMapping("/listar")
    public List<Cardapio> listar(){
      return cardapioService.listarCardapio();
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirCardapio(@PathVariable Long id){
        return cardapioService.excluirCardapio(id);

    }
 
    @GetMapping("/buscarCardapio/{id}")
    public ResponseEntity<?> pesquisaCardapio(@PathVariable long id ){
        return new ResponseEntity<>(cardapioService.pesquisaCardapio(id), HttpStatus.OK);

    }

}
