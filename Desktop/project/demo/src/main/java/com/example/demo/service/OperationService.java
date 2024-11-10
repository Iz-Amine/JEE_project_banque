package com.example.demo.service;


import com.example.demo.entity.Operation;

import java.util.List;

public interface OperationService {
    List<Operation> consultOperations(String codeCompte);
}