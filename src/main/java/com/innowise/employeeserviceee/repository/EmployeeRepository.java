package com.innowise.employeeserviceee.repository;

import com.innowise.employeeserviceee.entity.Employee;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface EmployeeRepository {
    List<Employee> findAll();

    Employee save(Employee employee);

    Employee findById(Long id);

    void deleteById(Long id);
}
