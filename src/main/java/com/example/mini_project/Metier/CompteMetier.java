package com.example.mini_project.Metier;

import com.example.mini_project.Entity.Compte;

import java.util.List;
import java.util.Optional;

public interface CompteMetier {
    Compte saveCompte(Compte compte);            // Create
    List<Compte> listComptes();                // Read All
    Optional<Compte> getCompteById(String id);     // Read by ID
    Compte updateCompte(String id, Compte compte); // Update
    void deleteCompte(String id);
}
