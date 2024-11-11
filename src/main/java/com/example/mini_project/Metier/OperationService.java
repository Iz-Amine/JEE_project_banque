package com.example.mini_project.Metier;


import com.example.mini_project.Entity.Operation;

import java.util.List;

public interface OperationService {
    List<Operation> consultOperations(String codeCompte);
    List<Operation> findByCompteId(String compteId);

}
