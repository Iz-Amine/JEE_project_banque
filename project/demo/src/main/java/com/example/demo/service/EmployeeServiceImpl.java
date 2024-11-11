package com.example.demo.service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

private EmployeeDao employeeDao;

     @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() {

    return employeeDao.findAllByOrderByLastNameAsc();


    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeDao.findById(id);

        // Check if the result is present
        return result.orElse(null); // or throw an exception if not found
    }

    @Override
    @Transactional
    public void delete(int id) {
employeeDao.deleteById(id);
    }
}
