package com.innowise.employeeserviceee.controller.impl;

import com.innowise.employeeserviceee.controller.Command;
import com.innowise.employeeserviceee.dto.DepartmentDTO;
import com.innowise.employeeserviceee.service.DepartmentService;
import com.innowise.employeeserviceee.util.JsonConverter;
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
public class AddDepartmentCommand implements Command {
    @EJB
    private DepartmentService departmentService;

    @Override
    @RolesAllowed("HR")
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DepartmentDTO departmentDTO = JsonConverter.convert(request, DepartmentDTO.class);
        DepartmentDTO actual = departmentService.saveDepartment(departmentDTO);
        response.getWriter().write(JsonConverter.toJson(actual));
    }
}
