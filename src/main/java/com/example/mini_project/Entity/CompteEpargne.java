package com.example.mini_project.Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@DiscriminatorValue("CE")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompteEpargne extends Compte {

    private double taux;

    public CompteEpargne(String codeCompte, Date dateCreation, double solde, double taux , Long ClientId) {
        super(codeCompte, dateCreation, solde , ClientId);
        this.taux = taux;
    }
}
