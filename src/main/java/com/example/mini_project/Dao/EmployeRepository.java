package com.example.mini_project.Dao;

import com.example.mini_project.Entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
}
