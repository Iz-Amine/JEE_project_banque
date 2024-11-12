package com.example.mini_project.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
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
public class Employe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeEmploye;

    private String nomEmploye;

    @ManyToOne
    @JoinColumn(name = "code_emp_sup")
    private Employe employeSup;

    private boolean isDeleted = false;  // New field for soft delete

    @ManyToMany
    @JoinTable(name = "EMP_GR")
    private Collection<Groupe> groupes;

    public Employe(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    @JsonIgnore
    public Employe getEmployeSup() {
        return employeSup;
    }

    @JsonSetter
    public void setEmployeSup(Employe employeSup) {
        this.employeSup = employeSup;
    }

    @JsonIgnore
    public Collection<Groupe> getGroupes() {
        return groupes;
    }
}
