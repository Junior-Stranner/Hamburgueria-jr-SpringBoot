package com.jujubaprojects.hamburgeriajr.Controller;

import com.jujubaprojects.hamburgeriajr.Service.FormaPagamentoService;
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
    FormaPagamentoService formaPagamentoService;

    @PostMapping("/cadastrarFormaPagamento")
    public ResponseEntity<?> cadastrar(@RequestBody FormaPagamento formaPagamento){
        return new ResponseEntity<>(formaPagamentoService.cadastrar(formaPagamento), HttpStatus.CREATED);
       
    }

}
