package com.innowise.employeeserviceee.controller.impl;

import com.innowise.employeeserviceee.controller.Command;
import com.innowise.employeeserviceee.dto.EmployeeDTO;
import com.innowise.employeeserviceee.service.EmployeeService;
import com.innowise.employeeserviceee.util.JsonConverter;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.io.IOException;

@Data
@Stateless
public class AddEmployeeCommand implements Command {
    @EJB
    private EmployeeService employeeService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EmployeeDTO employee = JsonConverter.convert(request, EmployeeDTO.class);
        EmployeeDTO actual = employeeService.saveEmployee(employee);
        response.getWriter().write(JsonConverter.toJson(actual));
    }
}
