package com.example.demo.dao;

import com.example.demo.entity.* ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {


    List<Operation> findByCompteCodeCompte(String compteId);

}
