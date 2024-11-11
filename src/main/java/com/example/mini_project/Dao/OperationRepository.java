package com.example.mini_project.Dao;

import com.example.mini_project.Entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
