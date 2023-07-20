package com.strupinski.employeeserviceee.controller.impl;

import com.strupinski.employeeserviceee.controller.Command;
import com.strupinski.employeeserviceee.service.UserService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.io.IOException;

@Data
@Stateless
public class DeleteUserCommand implements Command {
    @EJB
    private UserService userService;

    //todo same
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        Long id = Long.valueOf(pathParts[pathParts.length - 1]);
        userService.deleteById(id);

        response.getWriter().write("User with id " + id + " was deleted");
    }
}
