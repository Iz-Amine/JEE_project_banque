package com.example.demo.dao;

import com.example.demo.entity.* ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
}