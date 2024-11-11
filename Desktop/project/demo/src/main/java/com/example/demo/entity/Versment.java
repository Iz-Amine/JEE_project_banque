package com.example.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
@DiscriminatorValue("V")
public class Versment extends Operation{


    public Versment(Date dateOperation, double montant , Compte c) {
        super(dateOperation, montant , c);
    }

    public Versment() {

    }


}