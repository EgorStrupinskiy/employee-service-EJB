package com.strupinski.employeeserviceee.controller.impl;

import com.strupinski.employeeserviceee.controller.Command;
import com.strupinski.employeeserviceee.exception.NoSuchCommandException;
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
        throw new NoSuchCommandException(request.getRequestURI());
    }
}