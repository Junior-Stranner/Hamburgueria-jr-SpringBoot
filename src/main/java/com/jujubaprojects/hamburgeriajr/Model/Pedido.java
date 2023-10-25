package com.jujubaprojects.hamburgeriajr.Model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
@SequenceGenerator(name = "seq_pedido", sequenceName = "seq_pedido",  allocationSize = 1,initialValue = 1)
public class Pedido implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardapio_id",nullable = false)
    private Cardapio cardapio;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pedido_cliente",  // Nome da tabela intermediária
            joinColumns = @JoinColumn(name = "pedido_id"),  // Coluna de ligação de Estágio
            inverseJoinColumns = @JoinColumn(name = "cliente_id"))  // Coluna de ligação de clientes
    private List<Cliente> clientes;


    @ManyToOne(fetch = FetchType.LAZY)
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
