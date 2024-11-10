package com.example.demo.service;

import com.example.demo.dao.EmployeRepository;
import com.example.demo.dao.GroupeRepository;
import com.example.demo.entity.Employe;
import com.example.demo.entity.Groupe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeServiceImpl implements EmployeService {

    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private GroupeRepository groupeRepository;

    @Override
    public Employe addEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public void assignEmployeToGroupe(Long employeId, Long groupeId) {
        Employe employe = employeRepository.findById(employeId).orElse(null);
        Groupe groupe = groupeRepository.findById(groupeId).orElse(null);
        if (employe != null && groupe != null) {
            employe.getGroupes().add(groupe);
            employeRepository.save(employe);
        }
    }

    @Override
    public List<Employe> listAll() {
        return employeRepository.findAll();
    }
}
