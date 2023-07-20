package com.strupinski.employeeserviceee.controller.impl;

import com.strupinski.employeeserviceee.controller.Command;
import com.strupinski.employeeserviceee.dto.EmployeeDTO;
import com.strupinski.employeeserviceee.service.EmployeeService;
import com.strupinski.employeeserviceee.util.JsonConverter;
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
        EmployeeDTO employee = JsonConverter.toObject(request, EmployeeDTO.class);
        EmployeeDTO actual = employeeService.saveEmployee(employee);
        response.getWriter().write(JsonConverter.toJson(actual));
    }
}
