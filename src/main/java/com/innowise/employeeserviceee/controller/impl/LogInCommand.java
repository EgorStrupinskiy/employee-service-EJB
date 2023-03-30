package com.innowise.employeeserviceee.controller.impl;

import com.innowise.employeeserviceee.controller.Command;
import com.innowise.employeeserviceee.dto.UserDTO;
import com.innowise.employeeserviceee.security.TokenService;
import com.innowise.employeeserviceee.service.UserService;
import com.innowise.employeeserviceee.util.JsonConverter;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
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
    @PermitAll
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDTO userDTO = JsonConverter.convert(request, UserDTO.class);
//        User user = tokenService.authenticate(
//                userDTO.getUsername(), userDTO.getPassword());
//
//        String token = tokenService.generateToken(user.getUsername(), user.getAuthority());
//
//        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
//        response.getWriter().write("You are logged in");
        if (userService.checkCredentials(userDTO)) {
            String token = userService.generateToken(userDTO);
            response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            response.getWriter().write("You are logged in");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Response.Status.UNAUTHORIZED.toString());
        }
    }
}
