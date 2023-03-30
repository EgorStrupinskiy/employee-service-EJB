package com.innowise.employeeserviceee.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.employeeserviceee.controller.Command;
import com.innowise.employeeserviceee.dto.UserCard;
import com.innowise.employeeserviceee.service.UserService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.io.IOException;
import java.util.List;

@Data
@Stateless
public class FindAllUsersCommand implements Command {
    @EJB
    private UserService userService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<UserCard> allUsers = userService.findAll();
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(allUsers));
    }
}
