package com.example.mini_project.Dao;

import com.example.mini_project.Entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte, String> {

    List<Compte> findByClientCodeClient(Long clientId);
    public Compte findBycodeCompte(String codeCompte);

 public   Compte getCompteBycodeCompte(String code ) ;


}
