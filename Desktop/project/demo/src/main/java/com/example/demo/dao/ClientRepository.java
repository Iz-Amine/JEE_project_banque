package com.example.demo.dao;
import com.example.demo.entity.* ;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClientRepository extends JpaRepository<Client, Long> {
}