package com.innowise.employeeserviceee.dto.converter;

import com.innowise.employeeserviceee.dto.DepartmentDTO;
import com.innowise.employeeserviceee.entity.Department;
import com.innowise.employeeserviceee.exception.NoSuchRecordException;
import com.innowise.employeeserviceee.repository.EmployeeRepository;
import jakarta.ejb.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class DepartmentConverter {

    private final EmployeeRepository employeeRepository;

    public Department toEntity(DepartmentDTO departmentDTO) {
        Department department = Department.builder()
                .name(departmentDTO.getName())
                .id(departmentDTO.getId())
                .build();

        Optional.ofNullable(departmentDTO.getEmployeeIds())
                .ifPresent(employeeIds -> employeeIds.forEach(id -> {
                    department.addEmployee(employeeRepository.findById(id)
//                            .orElseThrow(() -> new NoSuchRecordException
//                                    (String.format("Employee with id=%s not found", id)))
                    );
                }));

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
