package com.example.mini_project.Metier;

import com.example.mini_project.Dao.GroupeRepository;
import com.example.mini_project.Entity.Employe;
import com.example.mini_project.Entity.Groupe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GroupeMetierImpl implements GroupeMetier {

    @Autowired
    private GroupeRepository groupeRepository;

    @Override
    public Groupe saveGroupe(Groupe groupe) {
        return groupeRepository.save(groupe);
    }

    @Override
    public Groupe getGroupeById(Long id) {
        return groupeRepository.findActiveById(id).orElse(null);  // Use active (non-deleted) method
    }

    @Override
    public List<Groupe> getAllGroupes() {
        return groupeRepository.findAllActive();  // Fetch only active groups
    }

    @Override
    public Groupe updateGroupe(Long id, Groupe groupe) {
        Optional<Groupe> existingGroupe = groupeRepository.findActiveById(id);  // Use active (non-deleted) method
        if (existingGroupe.isPresent()) {
            Groupe updatedGroupe = existingGroupe.get();
            updatedGroupe.setNomGroupe(groupe.getNomGroupe());
            return groupeRepository.save(updatedGroupe);
        }
        return null;
    }

    @Override
    public void deleteGroupe(Long id) {
        Optional<Groupe> existingGroupe = groupeRepository.findById(id);
        existingGroupe.ifPresent(groupe -> {
            groupe.setDeleted(true);  // Set isDeleted to true for soft delete
            groupeRepository.save(groupe);
        });
    }

    @Transactional
    @Override
    public Groupe addEmployeeToGroup(Long groupId, Employe employee) {
        Groupe groupe = groupeRepository.findActiveById(groupId).orElse(null);
        if (groupe != null && !groupe.getEmploye().contains(employee)) {
            groupe.addEmploye(employee);
            return groupeRepository.save(groupe);
        }
        return groupe;
    }
}
