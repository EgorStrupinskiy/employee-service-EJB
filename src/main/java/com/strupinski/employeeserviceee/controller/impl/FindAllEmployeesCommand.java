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
