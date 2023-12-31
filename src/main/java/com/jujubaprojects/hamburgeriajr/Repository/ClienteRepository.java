package com.jujubaprojects.hamburgeriajr.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jujubaprojects.hamburgeriajr.Model.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente,Long>{
    
    long countById(long id);

    List<Cliente> findByOrderByNome();


//    List<Cliente> findByNomeStartsWith(String termo);

}
