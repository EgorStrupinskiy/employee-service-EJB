package com.innowise.employeeserviceee.controller.impl;

import com.innowise.employeeserviceee.controller.Command;
import com.innowise.employeeserviceee.service.DepartmentService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.io.IOException;

@Data
@Stateless
public class DeleteDepartmentCommand implements Command {
    @EJB
    private DepartmentService departmentService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        Long id = Long.valueOf(pathParts[pathParts.length - 1]);
        departmentService.deleteById(id);

        response.getWriter().write("Department with id " + id + " was deleted");
    }
}
