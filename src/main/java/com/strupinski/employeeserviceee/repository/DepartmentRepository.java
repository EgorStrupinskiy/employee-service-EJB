package com.strupinski.employeeserviceee.repository;

import com.strupinski.employeeserviceee.entity.Department;
import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;


@Local
public interface DepartmentRepository {
    List<Department> findAll();

    Optional<Department> save(Department department);

    Optional<Department> findById(Long id);

    void deleteById(Long id);

    boolean existsById(Long id);
}
