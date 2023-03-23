package com.innowise.employeeserviceee.controller;

import com.innowise.employeeserviceee.dto.EmployeeDTO;
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

@WebServlet("/employees")
public class ControllerServlet extends HttpServlet {
    @EJB
    private EmployeeService employeeService;

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
        System.out.println(allEmployees);
    }
}