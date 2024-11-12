package com.example.mini_project.Dao;

import com.example.mini_project.Entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
    // Retrieve all active employees
    @Query("SELECT e FROM Employe e WHERE e.isDeleted = false")
    List<Employe> findAllActive();

    // Find an active employee by ID
    @Query("SELECT e FROM Employe e WHERE e.codeEmploye = :id AND e.isDeleted = false")
    Optional<Employe> findActiveById(Long id);
}
