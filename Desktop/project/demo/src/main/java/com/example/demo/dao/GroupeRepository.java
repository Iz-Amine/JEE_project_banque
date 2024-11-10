package com.example.demo.dao;
import com.example.demo.entity.* ;

import org.springframework.data.jpa.repository.JpaRepository;
public interface GroupeRepository extends JpaRepository<Groupe, Long> {
}