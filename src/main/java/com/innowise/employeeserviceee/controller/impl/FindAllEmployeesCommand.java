package com.innowise.employeeserviceee.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.employeeserviceee.controller.Command;
import com.innowise.employeeserviceee.dto.EmployeeDTO;
import com.innowise.employeeserviceee.service.EmployeeService;
import com.innowise.employeeserviceee.util.JsonConverter;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.io.IOException;
import java.util.List;

@Data
@Stateless
public class FindAllEmployeesCommand implements Command {

    @EJB
    private EmployeeService employeeService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<EmployeeDTO> allEmployees = employeeService.findAll();
        response.getWriter().write(JsonConverter.toJson(allEmployees));
    }
}
