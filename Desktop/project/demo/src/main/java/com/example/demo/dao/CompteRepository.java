package com.example.demo.dao;
import com.example.demo.entity.* ;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte, String> {


    List<Compte> findByClientCodeClient(Long clientId);
    public Compte findBycodeCompte(String codeCompte);

}
