package com.innowise.employeeserviceee.controller.impl;

import com.innowise.employeeserviceee.controller.Command;
import com.innowise.employeeserviceee.dto.EmployeeDTO;
import com.innowise.employeeserviceee.dto.UserDTO;
import com.innowise.employeeserviceee.model.RegistrationRequest;
import com.innowise.employeeserviceee.service.EmployeeService;
import com.innowise.employeeserviceee.service.UserService;
import com.innowise.employeeserviceee.util.JsonConverter;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.io.IOException;

@Data
@Stateless
public class AddUserCommand implements Command {
    @EJB
    private UserService userService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RegistrationRequest registrationRequest = JsonConverter.convert(request, RegistrationRequest.class);
        UserDTO actual = userService.addUser(registrationRequest.toDTO());
        response.getWriter().write(JsonConverter.toJson(actual));
    }
}
