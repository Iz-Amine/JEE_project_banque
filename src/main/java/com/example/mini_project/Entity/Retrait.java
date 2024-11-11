package com.example.mini_project.Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@DiscriminatorValue("R")
@NoArgsConstructor
public class Retrait extends Operation{


    public Retrait( Date dateOperation, double montant, Compte compte) {
        super( dateOperation, montant, compte);
    }
}
