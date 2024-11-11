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

    public Groupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }
}
