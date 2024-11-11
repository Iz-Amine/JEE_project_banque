package com.example.demo.service;

import com.example.demo.entity.Employe;

import java.util.List;

public interface EmployeService {
    Employe addEmploye(Employe employe);

    List<Employe> findAll();
    Employe save(Employe employee);
    Employe findById(Long id);
    void delete(int id);


    void delete(Long id);

    void assignEmployeToGroupe(Long employeId, Long groupeId);

    List<Employe> listAll()  ;

}
