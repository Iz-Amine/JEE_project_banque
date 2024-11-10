package com.example.demo.service;

import com.example.demo.dao.CompteRepository;
import com.example.demo.entity.Compte;
import com.example.demo.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private CompteRepository compteRepository;

    @Override
    public List<Operation> consultOperations(String codeCompte) {
        return compteRepository.findById(codeCompte)
                .map(compte -> new ArrayList<>(compte.getOperations())) // Convert Collection to List
                .orElseGet(() -> new ArrayList<Operation>()); // Return an empty List<Operation> if not found
    }
}
