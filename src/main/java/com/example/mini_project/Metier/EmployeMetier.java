package com.example.mini_project.Metier;

import com.example.mini_project.Entity.Employe;

import java.util.List;
import java.util.Optional;

public interface EmployeMetier {
    public Employe saveEmploye(Employe e);
    public List<Employe> listEmployes();

    public Optional<Employe> getEmployeById(Long id); // Read by ID
    public Employe updateEmploye(Long id, Employe employe); // Update
    public void deleteEmploye(Long id);           // Delete
}
