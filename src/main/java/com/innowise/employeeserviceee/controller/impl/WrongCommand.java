package com.innowise.employeeserviceee.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.employeeserviceee.controller.Command;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.io.IOException;
@Data
@Stateless
public class WrongCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
    }
}