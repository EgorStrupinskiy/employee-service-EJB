package com.strupinski.employeeserviceee.controller.impl;

import com.strupinski.employeeserviceee.controller.Command;
import com.strupinski.employeeserviceee.dto.UserDTO;
import com.strupinski.employeeserviceee.exception.AuthenticationException;
import com.strupinski.employeeserviceee.security.TokenService;
import com.strupinski.employeeserviceee.service.UserService;
import com.strupinski.employeeserviceee.util.JsonConverter;
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
