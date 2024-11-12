package com.example.mini_project.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Groupe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeGroupe;

    private String nomGroupe;

    @ManyToMany(mappedBy = "groupes")
    private Collection<Employe> employe;

    private boolean isDeleted = false;  // New field for soft delete

    public void addEmploye(Employe employee) {
        this.employe.add(employee);
        employee.getGroupes().add(this);
    }

    // Method to remove an Employe from the Groupe
    public void removeEmploye(Employe employee) {
        if (this.employe.contains(employee)) {
            this.employe.remove(employee);  // Remove from this Groupe
            employee.getGroupes().remove(this);  // Remove this Groupe from the Employe
        }
    }

    public Groupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

}
