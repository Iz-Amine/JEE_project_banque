package com.example.mini_project.Metier;


import com.example.mini_project.Entity.Compte;
import com.example.mini_project.Entity.Operation;
import com.example.mini_project.Entity.Versment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.mini_project.Dao.* ;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public List<Operation> consultOperations(String codeCompte) {
        return compteRepository.findById(codeCompte)
                .map(compte -> new ArrayList<>(compte.getOperations())) // Convert Collection to List
                .orElseGet(ArrayList::new); // Return an empty List<Operation> if not found
    }

    @Override
    public List<Operation> findByCompteId(String compteId) {
        return operationRepository.findByCompteCodeCompte(compteId);

    }

    @Transactional
    public void deposit(String codeCompte, double amount) {
        Compte compte = compteRepository.findById(codeCompte).orElseThrow(() -> new RuntimeException("Compte not found"));
        Versment deposit = new Versment(new Date(), amount, compte);

        compte.setSolde(compte.getSolde() + amount); // Update the account balance
        compteRepository.save(compte); // Save updated account balance
        operationRepository.save(deposit); // Save the deposit operation
    }

}

