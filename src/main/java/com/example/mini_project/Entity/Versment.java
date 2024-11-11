package com.example.mini_project.Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("V")
@NoArgsConstructor
public class Versment extends Operation{
}
