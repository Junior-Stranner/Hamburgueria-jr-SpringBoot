package com.jujubaprojects.hamburgeriajr.Service;

import com.jujubaprojects.hamburgeriajr.Model.Cliente;
import com.jujubaprojects.hamburgeriajr.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {

        List<Cliente> clientes = new ArrayList<>();
        boolean clienteExistente = clientes.stream().anyMatch(c -> c.getCpf().equals(cliente.getCpf()));
        /*Verifica se o Cliente existe comprando o seu  CPF */

        if (clienteExistente) {
            return new ResponseEntity<>("CPF já existente", HttpStatus.BAD_REQUEST);

        }
            if (cliente.getEndereco().isEmpty()) {
                return new ResponseEntity<>("Digite o seu endereço", HttpStatus.BAD_REQUEST);

            } else if (cliente.getRua().isEmpty()) {
                return new ResponseEntity<>("Digite a sua Rua", HttpStatus.BAD_REQUEST);

            } else if (cliente.getBairro().isEmpty()) {
                return new ResponseEntity<>("Digite o seu Bairro", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(clienteRepository.save(cliente), HttpStatus.CREATED);
    }
    public List<Cliente>listarCliente() {
        return clienteRepository.findAll();

    }
    public ResponseEntity<?> buscaCliente(@PathVariable long id){

       if(!clienteRepository.existsById(id)){
           return new ResponseEntity<>("Cliente não encontrado ",HttpStatus.BAD_REQUEST);
       }else{
           return new ResponseEntity<>(clienteRepository.findById(id),HttpStatus.OK);
       }

    }

    public Cliente atualizaCliente(Cliente clienteAtualizado , long id) {

        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if(clienteOptional.isPresent()){
            Cliente clienteExistente = clienteOptional.get();
            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setEndereco(clienteAtualizado.getEndereco());
            clienteExistente.setCpf(clienteAtualizado.getCpf());
            clienteExistente.setBairro(clienteAtualizado.getBairro());

            return clienteRepository.save(clienteExistente);
        }else{
            return null;
        }

    }


}
