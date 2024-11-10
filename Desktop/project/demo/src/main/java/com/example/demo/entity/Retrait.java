package com.example.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation {
    public Retrait(Date dateOperation, double montant) {
        super(dateOperation, montant);
    }

    public Retrait() {
    }
}