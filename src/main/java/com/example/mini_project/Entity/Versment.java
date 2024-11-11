package com.example.mini_project.Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@DiscriminatorValue("V")
@NoArgsConstructor
public class Versment extends Operation{
    public Versment(Date date, double amount, Compte compte) {

         super(date, amount , compte)  ;

    }
}
