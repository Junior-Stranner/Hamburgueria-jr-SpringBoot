package com.jujubaprojects.hamburgeriajr.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jujubaprojects.hamburgeriajr.Model.FormaPagamento;
import com.jujubaprojects.hamburgeriajr.Repository.FormaPagamentoRepository;

@RestController
public class FormaPagamentoController {
    

    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;

    @PostMapping("/cadastrarFormaPagamento")
    public ResponseEntity<?> cadastrar(@RequestBody FormaPagamento formaPagamento){

        if(!formaPagamento.equals("Pix,Crédito,Débito")){

            return new ResponseEntity<String>("Falha ....", HttpStatus.BAD_REQUEST);
        }
            return new ResponseEntity<>(formaPagamentoRepository.save(formaPagamento), HttpStatus.CREATED);
       
    }

}
