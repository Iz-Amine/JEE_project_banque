package com.example.mini_project.Service;

import com.example.mini_project.Entity.Compte;
import com.example.mini_project.Metier.CompteMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comptes")
public class CompteRestService {
    @Autowired
    private CompteMetier compteMetier;

    @PostMapping
    public Compte createCompte(@RequestBody Compte compte) {
        return compteMetier.saveCompte(compte);
    }

    @GetMapping
    public List<Compte> listComptes() {
        return compteMetier.listComptes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compte> getCompteById(@PathVariable String id) {
        Optional<Compte> compte = compteMetier.getCompteById(id);
        return compte.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compte> updateCompte(@PathVariable String id, @RequestBody Compte compte) {
        Compte updatedCompte = compteMetier.updateCompte(id, compte);
        if (updatedCompte != null) {
            return ResponseEntity.ok(updatedCompte);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable String id) {
        compteMetier.deleteCompte(id);
        return ResponseEntity.noContent().build();
    }
}
