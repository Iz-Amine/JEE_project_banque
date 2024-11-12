package com.example.mini_project.Metier;

import com.example.mini_project.Entity.Employe;
import com.example.mini_project.Dao.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeMtierImpl implements EmployeMetier {

    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public Employe saveEmploye(Employe e) {
        return employeRepository.save(e);
    }

    @Override
    public List<Employe> listEmployes() {
        return employeRepository.findAllActive();  // Fetch only active employees
    }

    @Override
    public Optional<Employe> getEmployeById(Long id) {
        return employeRepository.findActiveById(id);  // Use active (non-deleted) method
    }

    @Override
    public Employe updateEmploye(Long id, Employe employe) {
        if (employeRepository.existsById(id)) {
            employe.setCodeEmploye(id);  // Ensure ID is maintained
            return employeRepository.save(employe);
        }
        return null;
    }

    @Override
    public void deleteEmploye(Long id) {
        Optional<Employe> existingEmploye = employeRepository.findById(id);
        existingEmploye.ifPresent(employe -> {
            employe.setDeleted(true);  // Set isDeleted to true for soft delete
            employeRepository.save(employe);
        });
    }
}
