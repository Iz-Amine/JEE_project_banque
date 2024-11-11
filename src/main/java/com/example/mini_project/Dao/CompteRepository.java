package com.example.mini_project.Dao;

import com.example.mini_project.Entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, String> {
}
