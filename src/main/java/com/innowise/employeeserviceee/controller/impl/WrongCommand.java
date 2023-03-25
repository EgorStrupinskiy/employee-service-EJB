package com.innowise.employeeserviceee.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.employeeserviceee.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class WrongCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValueAsString("No such command");
    }
}