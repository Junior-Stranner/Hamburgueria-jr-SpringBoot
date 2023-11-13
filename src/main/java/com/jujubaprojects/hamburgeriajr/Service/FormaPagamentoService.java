package com.jujubaprojects.hamburgeriajr.Service;

import com.jujubaprojects.hamburgeriajr.Model.FormaPagamento;
import com.jujubaprojects.hamburgeriajr.Repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class FormaPagamentoService {

    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;

    public ResponseEntity<?> cadastrar(@RequestBody FormaPagamento formaPagamento){

        if(!formaPagamento.equals("Pix,Crédito,Débito")){

            return new ResponseEntity<String>("Falha ....", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(formaPagamentoRepository.save(formaPagamento), HttpStatus.CREATED);

    }
}
