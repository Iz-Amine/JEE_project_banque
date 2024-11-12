package com.example.mini_project.Metier;

import com.example.mini_project.Dao.CompteRepository;
import com.example.mini_project.Dao.OperationRepository;
import com.example.mini_project.Entity.Compte;
import com.example.mini_project.Entity.Retrait;
import com.example.mini_project.Entity.Versment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompteMetierImpl implements CompteMetier{
    @Autowired
    private CompteRepository compteRepository;


    @Autowired
    private OperationService operationService;
    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Compte saveCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    @Override
    public List<Compte> listComptes() {
        return compteRepository.findAll();
    }

    @Override
    public Optional<Compte> getCompteById(String id) {
        return compteRepository.findById(id);
    }

    @Override
    public Compte updateCompte(String id, Compte compte) {
        if (compteRepository.existsById(id)) {
            compte.setCodeCompte(id); // ensure the ID remains the same
            return compteRepository.save(compte);
        }
        return null; // Or throw an exception
    }

    @Override
    public void deleteCompte(String id) {
        compteRepository.deleteById(id);
    }

    @Override
    public Compte getCompteBycodeCompte(String code) {
        return compteRepository.getCompteBycodeCompte(code);
    }


    @Override
    public void versement(String codeCompte , double montant) {
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

    public Compte getCompte(String codeCompte) {
        return compteRepository.findById(codeCompte).orElse(null);
    }


    @Override
    public Compte findById(String compteId) {
        return compteRepository.findBycodeCompte(compteId);
    }



    @Override
    public List<Compte> findByClientId(Long clientId) {
        return compteRepository.findByClientCodeClient(clientId);

    }
}

