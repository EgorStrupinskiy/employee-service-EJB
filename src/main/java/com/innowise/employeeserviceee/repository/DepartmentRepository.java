package com.innowise.employeeserviceee.repository;

import com.innowise.employeeserviceee.entity.Department;
import jakarta.ejb.Stateless;


@Stateless
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
}
