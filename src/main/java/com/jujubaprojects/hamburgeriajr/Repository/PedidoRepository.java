package com.jujubaprojects.hamburgeriajr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jujubaprojects.hamburgeriajr.Model.Pedido;

public interface PedidoRepository extends JpaRepository <Pedido,Long>{
    
long countById(long id);
}
