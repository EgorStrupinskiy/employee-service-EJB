package com.innowise.employeeserviceee.service;


import com.innowise.employeeserviceee.dto.DepartmentDTO;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface DepartmentService {
    DepartmentDTO saveDepartment(DepartmentDTO department);

    List<DepartmentDTO> getAll();

    DepartmentDTO findById(Long id);

    void deleteById(Long id);
}
