package com.strupinski.employeeserviceee.controller.impl;

import com.strupinski.employeeserviceee.controller.Command;
import com.strupinski.employeeserviceee.dto.UserDTO;
import com.strupinski.employeeserviceee.exception.UsernameNotFoundException;
import com.strupinski.employeeserviceee.model.RegistrationRequest;
import com.strupinski.employeeserviceee.service.UserService;
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
public class AddUserCommand implements Command {
    @EJB
    private UserService userService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, UsernameNotFoundException {
        RegistrationRequest registrationRequest = JsonConverter.toObject(request, RegistrationRequest.class);
        UserDTO actual = userService.addUser(registrationRequest.toDTO());
        response.getWriter().write(JsonConverter.toJson(actual));
    }
}
