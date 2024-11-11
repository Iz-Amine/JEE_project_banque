package com.example.mini_project.Metier;

import com.example.mini_project.Entity.Employe;
import com.example.mini_project.Dao.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeMtierImpl implements EmployeMetier{
    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public Employe saveEmploye(Employe e) {
        // TODO Auto-generated method stub
        return employeRepository.save(e);
    }
    @Override
    public List<Employe> listEmployes() {
// TODO Auto-generated method stub
        return employeRepository.findAll();
    }

    @Override
    public Optional<Employe> getEmployeById(Long id) {
        return employeRepository.findById(id);
    }

    @Override
    public Employe updateEmploye(Long id, Employe employe) {
        if (employeRepository.existsById(id)) {
            employe.setCodeEmploye(id); // ensure the ID is maintained
            return employeRepository.save(employe);
        }
        return null; // Or throw an exception
    }

    @Override
    public void deleteEmploye(Long id) {
        employeRepository.deleteById(id);
    }
}
