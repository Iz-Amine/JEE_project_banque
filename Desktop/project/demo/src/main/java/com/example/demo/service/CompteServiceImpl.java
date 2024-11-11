package com.example.demo.service;


import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.EmployeRepository;
import com.example.demo.dao.OperationRepository;
import com.example.demo.entity.Compte;
import com.example.demo.entity.Retrait;
import com.example.demo.entity.Versment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;

@Service
@Transactional
public class CompteServiceImpl implements CompteService {

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Compte addCompte(Compte compte, Long clientId, Long employeId) {
        compte.setClient(clientRepository.findById(clientId).orElse(null));
        compte.setEmploye(employeRepository.findById(employeId).orElse(null));
        return compteRepository.save(compte);
    }

    @Override
    public Compte getCompte(String codeCompte) {
        return compteRepository.findById(codeCompte).orElse(null);
    }

    @Override
    public List<Compte> listComptesByClient(Long clientId) {
        return clientRepository.findById(clientId)
                .map(client -> new ArrayList<>(client.getComptes()))  // Convert Collection to List
                .orElseGet(() -> new ArrayList<Compte>()); // Explicitly create an empty ArrayList<Compte>
    }

    @Override
    public List<Compte> listAll() {
        return compteRepository.findAll()  ;
    }

    @Override
    public List<Compte> findByClientId(Long clientId) {
        return compteRepository.findByClientCodeClient(clientId);

    }


    @Override
    public void versement(String codeCompte, double montant) {
        Compte compte = getCompte(codeCompte);
        if (compte != null) {
            compte.setSolde(compte.getSolde() + montant);
            Versment versment = new Versment(new Date(), montant , compte);
            versment.setCompte(compte);
            operationRepository.save(versment);
        }
    }

    @Override
    public void retrait(String codeCompte, double montant) {
        Compte compte = getCompte(codeCompte);
        if (compte != null) {
            compte.setSolde(compte.getSolde() - montant);
            Retrait retrait = new Retrait(new Date(), montant , compte);
            retrait.setCompte(compte);
            operationRepository.save(retrait);
        }
    }

    @Override
    public void virement(String codeCompteSource, String codeCompteDest, double montant) {
        retrait(codeCompteSource, montant);
        versement(codeCompteDest, montant);
    }

    @Override
    public Compte findById(String compteId) {
        return compteRepository.findBycodeCompte(compteId);
    }
}

