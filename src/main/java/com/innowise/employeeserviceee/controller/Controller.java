package com.innowise.employeeserviceee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.employeeserviceee.dto.DepartmentDTO;
import com.innowise.employeeserviceee.dto.EmployeeDTO;
import com.innowise.employeeserviceee.dto.UserDTO;
import com.innowise.employeeserviceee.service.DepartmentService;
import com.innowise.employeeserviceee.service.EmployeeService;
import com.innowise.employeeserviceee.service.UserService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
import java.util.List;

@MultipartConfig
@WebServlet(name = "Controller", urlPatterns = {"/api/*"})
public class Controller extends HttpServlet {

    @EJB
    CommandProvider commandProvider;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        commandProvider.provideCommand(request).execute(request, response);
    }
}