package com.jujubaprojects.hamburgeriajr.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Cardapio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String comida;
    private String bebida;

    
    @ComboSize(max = 3, message = "O combo não pode ter mais de 3 itens.")
    @Min(2)
    @NotBlank
    private String combo;
    @Min(0)
    private double preco;
 //   private double desconto;
    private double precoTotal;

    @OneToMany(mappedBy = "cardapio", cascade = CascadeType.ALL)
    private List<Pedido> pedidos ;

    

    public Cardapio(long id, String comida, String bebida, String combo, double preco, double precoTotal) {
        this.id = id;
        this.comida = comida;
        this.bebida = bebida;
        this.combo = combo;
        this.preco = preco;
        this.precoTotal = precoTotal;
    //    this.desconto = desconto;
     
    }


    public Cardapio() {
        
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComida() {
        return comida;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getCombo() {
        return combo;
    }

    public void setCombo(String combo) {
        this.combo = combo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }


    public List<Pedido> getPedidos() {
        return pedidos;
    }


    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    
    
}
