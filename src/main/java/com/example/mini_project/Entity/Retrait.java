package com.example.mini_project.Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("R")
@NoArgsConstructor
public class Retrait extends Operation{
}
