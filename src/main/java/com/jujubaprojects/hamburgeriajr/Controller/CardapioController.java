package com.jujubaprojects.hamburgeriajr.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jujubaprojects.hamburgeriajr.Model.Cardapio;
import com.jujubaprojects.hamburgeriajr.Model.Mensagem;
import com.jujubaprojects.hamburgeriajr.Repository.CardapioRepository;

@RestController
//@CrossOrigin("/cardapio")
public class CardapioController {
    
    @Autowired
    CardapioRepository cardapioRepository;

    @Autowired
    Mensagem mensagem;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Cardapio cardapio){
        String pedido = "bebida";

        if(cardapio.getComida().isEmpty()){  
         mensagem.setMensagem("preencha o Cardápio !");
         return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

        }
         if (pedido.equals(cardapio.getComida())){
  
            mensagem.setMensagem("Você deve adicionar um item comestivel relacionado a Hamburgeres !");
           
        }
    double desconto = 0.0;

    if (pedido.equals(cardapio.getComida())) {
        mensagem.setMensagem("Você deve adicionar um item comestível relacionado a Hambúrgueres!");
        return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

    } else if (cardapio.getPreco() >= 50 && cardapio.getPreco() <= 70) {
        desconto = 0.95;
        cardapio.setPrecoTotal(cardapio.getPreco() * desconto);
        mensagem.setMensagem("Desconto de 5% ativo");

    } else if (cardapio.getPreco() > 70 && cardapio.getPreco() <= 90) {
        desconto = 0.90;
        cardapio.setPrecoTotal(cardapio.getPreco() * desconto);
        mensagem.setMensagem("Desconto de 10% ativo");

    } else if (cardapio.getPreco() > 100) {
        desconto = 0.70;
        cardapio.setPrecoTotal(cardapio.getPreco() * desconto);
        mensagem.setMensagem("Desconto de 30% ativo");
    } else if (cardapio.getPreco() < 50) {
        mensagem.setMensagem("Você não terá desconto no Pedido");

    }

    return new ResponseEntity<>( cardapioRepository.save(cardapio), HttpStatus.OK);
}

    @GetMapping("/listar")
    public ResponseEntity< List<Cardapio>>  listar(){
        List<Cardapio> cardapios = cardapioRepository.findAll();/*Executa a consulta no banco de dados*/
       return new ResponseEntity< List<Cardapio>> (cardapios, HttpStatus.OK); /*Retorna a lista JSON */

    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirCardapio(@PathVariable Long id){

         cardapioRepository.deleteById(id);
        return new ResponseEntity<String>("Tipo de Pedido excluido !", HttpStatus.OK);

    }
 
    @GetMapping("/buscarCardapio/{id}")
    public ResponseEntity<?> pesquisaCardapio(@PathVariable long id ){

        if(cardapioRepository.countById(id) == 0){
              return new ResponseEntity<String>("Cardápio não encontrado , Digite um Id Existente!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cardapioRepository.findById(id), HttpStatus.OK);
      
    }

   @GetMapping("/contadorCardapio")
   public long contador(){
    return cardapioRepository.count();

  }
}
