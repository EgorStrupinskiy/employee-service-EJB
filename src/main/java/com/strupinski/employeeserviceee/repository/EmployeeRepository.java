package com.strupinski.employeeserviceee.repository;

import com.strupinski.employeeserviceee.entity.Employee;
import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface EmployeeRepository {
    List<Employee> findAll();

    Optional<Employee> save(Employee employee);

    Optional<Employee> findById(Long id);

    void deleteById(Long id);
}
