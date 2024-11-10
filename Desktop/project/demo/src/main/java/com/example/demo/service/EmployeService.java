package com.example.demo.service;

import com.example.demo.entity.Employe;

import java.util.List;

public interface EmployeService {
    Employe addEmploye(Employe employe);
    void assignEmployeToGroupe(Long employeId, Long groupeId);

    List<Employe> listAll()  ;
}
