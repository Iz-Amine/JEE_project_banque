package com.example.demo.dao;
import com.example.demo.entity.* ;

import org.springframework.data.jpa.repository.JpaRepository;
public interface CompteRepository extends JpaRepository<Compte, String> {
}
