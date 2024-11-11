package com.example.mini_project.Service;


import com.example.mini_project.Entity.Groupe;
import com.example.mini_project.Metier.GroupeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/groupes")
public class GroupeRestService {
    @Autowired
    private GroupeMetier groupeMetier;

    // Create a new groupe
    @PostMapping
    public ResponseEntity<Groupe> createGroupe(@RequestBody Groupe groupe) {
        Groupe savedGroupe = groupeMetier.saveGroupe(groupe);
        return ResponseEntity.ok(savedGroupe);
    }

    // Get a groupe by ID
    @GetMapping("/{id}")
    public ResponseEntity<Groupe> getGroupeById(@PathVariable Long id) {
        Groupe groupe = groupeMetier.getGroupeById(id);
        return groupe != null ? ResponseEntity.ok(groupe) : ResponseEntity.notFound().build();
    }

    // Get all groupes
    @GetMapping
    public List<Groupe> getAllGroupes() {
        return groupeMetier.getAllGroupes();
    }

    // Update a groupe by ID
    @PutMapping("/{id}")
    public ResponseEntity<Groupe> updateGroupe(@PathVariable Long id, @RequestBody Groupe groupe) {
        Groupe updatedGroupe = groupeMetier.updateGroupe(id, groupe);
        return updatedGroupe != null ? ResponseEntity.ok(updatedGroupe) : ResponseEntity.notFound().build();
    }

    // Delete a groupe by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupe(@PathVariable Long id) {
        groupeMetier.deleteGroupe(id);
        return ResponseEntity.noContent().build();
    }
}
