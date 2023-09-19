package com.jujubaprojects.hamburgeriajr.Model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    @ManyToOne(cascade = CascadeType.ALL)
    private Cardapio cardapio;

    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.ALL) // Verifique se o nome da coluna corresponde à sua tabela
    private FormaPagamento formaPagamento;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}
