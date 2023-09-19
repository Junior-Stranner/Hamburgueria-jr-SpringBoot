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
import org.springframework.web.bind.annotation.RequestParam;
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

        if(cardapio.getComida().equals("")){  
         mensagem.setMensagem("preencha o Cardápio !");
         return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

        }else if (cardapio.getComida().equals(pedido)){
  
            mensagem.setMensagem("Você deve adicionar um item comestivel relacionado a Hamburgeres !");
            return new ResponseEntity<>(mensagem , HttpStatus.BAD_REQUEST);
          
        }
         if(cardapio.getPreco() > 50 && cardapio.getPreco() < 70 ){
        
            cardapio.setPrecoTotal(cardapio.getPreco() * 0.95);
            mensagem.setMensagem("desconto de 5% ativo");
            return new ResponseEntity<>(cardapioRepository.save(cardapio), HttpStatus.OK);
            
        }else if(cardapio.getPreco() > 70 && cardapio.getPreco() < 90){

                 cardapio.setPrecoTotal(cardapio.getPreco() * 0.90);
                  mensagem.setMensagem("desconto de 10% ativo");
                  return new ResponseEntity<>(cardapioRepository.save(cardapio), HttpStatus.OK);
               }
          return new ResponseEntity<>(cardapioRepository.save(cardapio),HttpStatus.CREATED);
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
}
