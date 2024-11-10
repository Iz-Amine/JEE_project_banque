package com.example.demo.service;


import com.example.demo.entity.Compte;

import java.util.List;

public interface CompteService {
    Compte addCompte(Compte compte, Long clientId, Long employeId);
    Compte getCompte(String codeCompte);
    List<Compte> listComptesByClient(Long clientId);
    List<Compte> listAll();

    void versement(String codeCompte, double montant);
    void retrait(String codeCompte, double montant);
    void virement(String codeCompteSource, String codeCompteDest, double montant);
}
