package com.example.mini_project.Dao;

import com.example.mini_project.Entity.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroupeRepository extends JpaRepository<Groupe, Long> {
    // Retrieve all active groups
    @Query("SELECT g FROM Groupe g WHERE g.isDeleted = false")
    List<Groupe> findAllActive();

    // Find an active group by ID
    @Query("SELECT g FROM Groupe g WHERE g.codeGroupe = :id AND g.isDeleted = false")
    Optional<Groupe> findActiveById(Long id);

}
