package com.innowise.employeeserviceee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.employeeserviceee.dto.DepartmentDTO;
import com.innowise.employeeserviceee.dto.EmployeeDTO;
import com.innowise.employeeserviceee.dto.UserDTO;
import com.innowise.employeeserviceee.entity.User;
import com.innowise.employeeserviceee.service.UserService;
import com.innowise.employeeserviceee.service.DepartmentService;
import com.innowise.employeeserviceee.service.EmployeeService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyServlet", urlPatterns = {"/api/*"})
public class ControllerServlet extends HttpServlet {
    @EJB
    private EmployeeService employeeService;

    @EJB
    private DepartmentService departmentService;

    @EJB
    private UserService authorizationService;

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        System.out.println(path);
        ObjectMapper mapper = new ObjectMapper();
        switch (path) {
            case "/employees":
                List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
                response.getWriter().write(mapper.writeValueAsString(allEmployees));
                break;
            case "/departments":
                List<DepartmentDTO> allDepartments = departmentService.getAll();
                response.getWriter().write(mapper.writeValueAsString(allDepartments));
                break;
            case "/users":
                List<UserDTO> allUsers = authorizationService.findAll();
                response.getWriter().write(mapper.writeValueAsString(allUsers));
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }
}