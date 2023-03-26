package com.innowise.employeeserviceee.repository;

import com.innowise.employeeserviceee.entity.Department;
import jakarta.ejb.Local;

import java.util.List;


@Local
public interface DepartmentRepository {
    List<Department> findAll();

    Department save(Department department);

    Department findById(Long id);

    void deleteById(Long id);

    boolean existsById(Long id);
}
