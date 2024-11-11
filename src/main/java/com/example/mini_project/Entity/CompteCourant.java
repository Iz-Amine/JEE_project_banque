package com.example.mini_project.Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@DiscriminatorValue("CC")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompteCourant extends Compte {

    private double decouvert;

    public CompteCourant(String codeCompte, Date dateCreation, double solde, double decouvert) {
        super(codeCompte, dateCreation, solde);
        this.decouvert = decouvert;
    }
}
