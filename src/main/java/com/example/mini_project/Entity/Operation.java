package com.example.mini_project.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(length = 1)
public class Operation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroOperation;

    private Date dateOperation;
    private double montant;

    @ManyToOne
    @JoinColumn(name = "CODE_CPTE")
    private Compte compte;

    @ManyToOne
    @JoinColumn(name = "CODE_EMP")
    private Employe employe;



    public String getType(){

        if (this instanceof Retrait) {
            return "retrait" ;
        }
        else {
            return  "versement"  ;
        }
    }





    public Operation(Date dateOperation, double montant , Compte c)  {
        this.dateOperation = dateOperation;
        this.montant = montant;
        compte = c ;
    }
}
