package com.strupinski.employeeserviceee.service;


import com.strupinski.employeeserviceee.dto.DepartmentDTO;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface DepartmentService {
    DepartmentDTO saveDepartment(DepartmentDTO department);

    List<DepartmentDTO> findAll();

    DepartmentDTO findById(Long id);

    void deleteById(Long id);

}
