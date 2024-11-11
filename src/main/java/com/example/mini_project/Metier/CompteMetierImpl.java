package com.example.mini_project.Metier;

import com.example.mini_project.Dao.CompteRepository;
import com.example.mini_project.Entity.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompteMetierImpl implements CompteMetier{
    @Autowired
    private CompteRepository compteRepository;

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
}
