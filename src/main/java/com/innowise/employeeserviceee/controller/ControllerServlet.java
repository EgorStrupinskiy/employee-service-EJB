package com.innowise.employeeserviceee.controller;


import com.innowise.employeeserviceee.dto.EmployeeDTO;
import com.innowise.employeeserviceee.service.EmployeeService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class ControllerServlet extends HttpServlet {
    @EJB
    private final EmployeeService employeeService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "employees":
                // вызов метода, отвечающего за авторизацию пользователя
                findAllEmployees(request, response);
                break;
            case "users":
                // вызов метода, отвечающего за выход пользователя из системы
                findAllUsers(request, response);
                break;
            case "departments":
                // вызов метода, отвечающего за регистрацию нового пользователя
                findAllDepartments(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                break;
        }

    }

    private void findAllEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // логика авторизации пользователя
    }

    private List<EmployeeDTO> findAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    private void findAllDepartments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // логика регистрации нового пользователя
    }
}
