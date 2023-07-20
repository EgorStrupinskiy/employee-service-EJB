package com.strupinski.employeeserviceee.dto.converter;

import com.strupinski.employeeserviceee.dto.DepartmentDTO;
import com.strupinski.employeeserviceee.entity.Department;
import com.strupinski.employeeserviceee.exception.NoSuchRecordException;
import com.strupinski.employeeserviceee.repository.EmployeeRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentConverter {

    @EJB
    private EmployeeRepository employeeRepository;

    public Department toEntity(DepartmentDTO departmentDTO) {
        Department department = Department.builder()
                .name(departmentDTO.getName())
                .id(departmentDTO.getId())
                .build();
        Optional.ofNullable(departmentDTO.getEmployeeIds())
                .ifPresent(employeeIds -> employeeIds.forEach(id -> department.addEmployee(employeeRepository.findById(id)
                        .orElseThrow(() -> new NoSuchRecordException
                                ("", String.format("Employee with id=%s not found", id)))
                )));

        return department;
    }

    public DepartmentDTO toDTO(Department department) {
        DepartmentDTO departmentDTO = DepartmentDTO.builder()
                .name(department.getName())
                .id(department.getId())
                .build();
        Optional.ofNullable(department.getEmployees())
                .ifPresent(employees -> employees.forEach(employee -> departmentDTO.addEmployeeId(employee.getId())));

        return departmentDTO;
    }
}
