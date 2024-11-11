package com.example.mini_project.Dao;

import com.example.mini_project.Entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {


    List<Operation> findByCompteCodeCompte(String compteId);
}
