package com.innowise.employeeserviceee.controller.impl;

import com.innowise.employeeserviceee.controller.Command;
import com.innowise.employeeserviceee.dto.DepartmentDTO;
import com.innowise.employeeserviceee.dto.UserCard;
import com.innowise.employeeserviceee.dto.UserDTO;
import com.innowise.employeeserviceee.service.DepartmentService;
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
public class FindUserByIdCommand implements Command {
    @EJB
    private UserService userService;

    //todo same
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        Long id = Long.valueOf(pathParts[pathParts.length - 1]);
        UserCard userDTO = userService.findById(id);
        response.getWriter().write(JsonConverter.toJson(userDTO));
    }
}
