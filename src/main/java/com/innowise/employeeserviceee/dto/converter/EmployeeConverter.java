package com.innowise.employeeserviceee.dto.converter;


import com.innowise.employeeserviceee.dto.EmployeeDTO;
import com.innowise.employeeserviceee.entity.Department;
import com.innowise.employeeserviceee.entity.Employee;
import com.innowise.employeeserviceee.exception.NoSuchRecordException;
import com.innowise.employeeserviceee.repository.DepartmentRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeConverter {
    @EJB
    private DepartmentRepository departmentRepository;

    public Employee toEntity(EmployeeDTO employeeDTO) {
        Employee employee = Employee.builder()
                .id(employeeDTO.getId())
                .name(employeeDTO.getName())
                .surname(employeeDTO.getSurname())
                .salary(employeeDTO.getSalary())
                .build();
        Optional.ofNullable(employeeDTO.getDepartmentId())
                .ifPresent(id -> {
                            Department department = Optional.ofNullable(departmentRepository.findById(employeeDTO.getDepartmentId()))
                                    .orElseThrow(() -> new NoSuchRecordException("", "Department with id=%s not found" + id));
                            employee.setDepartment(department);
                        }
                );
        return employee;
    }

    public void updateEmployeeFields(EmployeeDTO employeeDTO, Employee employee) {
        employee.setName(employeeDTO.getName());
        employee.setSurname(employeeDTO.getSurname());
        employee.setSalary(employeeDTO.getSalary());
        Optional.ofNullable(employeeDTO.getDepartmentId())
                .ifPresent(id -> {
                            Department department = Optional.ofNullable(departmentRepository.findById(employeeDTO.getDepartmentId()))
                                    .orElseThrow(() -> new NoSuchRecordException("", "Department with id=%s not found" + id));
                            employee.setDepartment(department);
                        }
                );
//        if (employeeDTO.getDepartmentId() != null) {
//            Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
////                    .orElseThrow(() -> new NoSuchRecordException
////                            (String.format("Department with id=%s not found", employeeDTO.getDepartmentId()))
////                    )
//                    ;
//    } else
//
//    {
//        employee.setDepartment(null);
//    }

    }

    public EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .salary(employee.getSalary())
                .build();
        System.out.println(employeeDTO);
        Optional.ofNullable(employee.getDepartment())
                .ifPresent(department -> employeeDTO.setDepartmentId(department.getId()));

        return employeeDTO;
    }
}
