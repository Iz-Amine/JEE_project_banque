package com.example.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
@DiscriminatorValue("V")
public class Versment extends Operation{


    public Versment(Date dateOperation, double montant) {
        super(dateOperation, montant);
    }

    public Versment() {

    }
}