package com.innowise.employeeserviceee.controller.impl;

import com.innowise.employeeserviceee.controller.Command;
import com.innowise.employeeserviceee.dto.UserDTO;
import com.innowise.employeeserviceee.exception.AuthenticationException;
import com.innowise.employeeserviceee.security.TokenService;
import com.innowise.employeeserviceee.service.UserService;
import com.innowise.employeeserviceee.util.JsonConverter;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;
import lombok.Data;

import java.io.IOException;

@Data
@Stateless
public class LogInCommand implements Command {

    @EJB
    TokenService tokenService;

    @EJB
    UserService userService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDTO userDTO = JsonConverter.toObject(request, UserDTO.class);

        if (userService.isAuthenticated(userDTO)) {
            String token = userService.generateToken(userDTO);
            response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            response.getWriter().write("Bearer " + token);
        } else {
            throw new AuthenticationException(request.getRequestURI());
        }
    }
}
