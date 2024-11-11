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
        return groupeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Groupe> getAllGroupes() {
        return groupeRepository.findAll();
    }

    @Override
    public Groupe updateGroupe(Long id, Groupe groupe) {
        Optional<Groupe> existingGroupe = groupeRepository.findById(id);
        if (existingGroupe.isPresent()) {
            Groupe updatedGroupe = existingGroupe.get();
            // Set fields to update here
            updatedGroupe.setNomGroupe(groupe.getNomGroupe());
            // Add other fields if necessary
            return groupeRepository.save(updatedGroupe);
        }
        return null;
    }

    @Override
    public void deleteGroupe(Long id) {
        groupeRepository.deleteById(id);
    }


    @Transactional
    @Override
    public Groupe addEmployeeToGroup(Long groupId, Employe employee) {
        // Fetch the group by ID
        Groupe groupe = groupeRepository.findById(groupId).orElse(null);
        if (groupe != null) {
            // Add employee to the group's collection if not already present
            if (!groupe.getEmploye().contains(employee)) {
                groupe.addEmploye(employee);
                return groupeRepository.save(groupe); // Save the updated group
            }
        }
        return groupe;
    }
}
