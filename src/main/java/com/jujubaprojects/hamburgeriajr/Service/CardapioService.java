package com.jujubaprojects.hamburgeriajr.Service;

import com.jujubaprojects.hamburgeriajr.Model.Cardapio;
import com.jujubaprojects.hamburgeriajr.Model.Mensagem;
import com.jujubaprojects.hamburgeriajr.Repository.CardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CardapioService {

    @Autowired
    CardapioRepository cardapioRepository;

    Mensagem mensagem;

    public ResponseEntity<?> cadastrarAlterarCardapio(Cardapio cardapio, String acao) {
        String pedido = "bebida";

        if (cardapio.getComida().isEmpty()) {
            mensagem.setMensagem("preencha o Cardápio !");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

        }
        if (pedido.equals(cardapio.getComida())) {

            mensagem.setMensagem("Você deve adicionar um item comestivel relacionado a Hamburgeres !");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

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
        if (acao.equals("cadastro")) {

            // cardapio.setPrecoTotal(cardapio.getPrecoTotal() * desconto);
            return new ResponseEntity<>(cardapioRepository.save(cardapio), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(cardapioRepository.saveAndFlush(cardapio), HttpStatus.OK);
        }
    }

    public List<Cardapio> listarCardapio() {
        return cardapioRepository.findAll();
    }

    public ResponseEntity<?> excluirCardapio(@PathVariable Long id) {

        if (cardapioRepository.countById(id) == 0) {
            cardapioRepository.deleteById(id);
            return new ResponseEntity<String>("Tipo de Pedido excluido !", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Não foi Possível excluir Cardápio !", HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> pesquisaCardapio(@PathVariable long id) {
        if (cardapioRepository.countById(id) == 0) {
            return new ResponseEntity<String>("Cardápio não encontrado , Digite um Id Existente!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(cardapioRepository.findById(id), HttpStatus.OK);
        }
    }
}
