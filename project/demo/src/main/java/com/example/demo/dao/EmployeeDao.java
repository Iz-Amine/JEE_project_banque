package com.example.demo.dao;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

public List<Employee>  findAllByOrderByLastNameAsc( );


}
