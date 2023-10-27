package com.jujubaprojects.hamburgeriajr.Model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")

public class Pedido{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cardapio_id",nullable = false)
    private Cardapio cardapio;

    @OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL)
    private List<Cliente> clientes;


    @ManyToOne
    @JoinColumn(name = "formaPagamento_id",nullable = false)
    private FormaPagamento formaPagamento;

  

    public Pedido(long id, Cardapio cardapio, List<Cliente> clientes/* , List<FormaPagamento> formaPagamentos*/) {
        this.id = id;
        this.cardapio = cardapio;
        this.clientes = clientes;
   //     this.formaPagamentos = formaPagamentos;
   }


    public Pedido() {
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
  

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }


    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }


    public void setFormaPagamentos(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

   

    


}
