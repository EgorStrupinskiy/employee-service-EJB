package com.strupinski.employeeserviceee.controller.impl;

import com.strupinski.employeeserviceee.controller.Command;
import com.strupinski.employeeserviceee.dto.DepartmentDTO;
import com.strupinski.employeeserviceee.service.DepartmentService;
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
public class AddDepartmentCommand implements Command {
    @EJB
    private DepartmentService departmentService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DepartmentDTO departmentDTO = JsonConverter.toObject(request, DepartmentDTO.class);
        DepartmentDTO actual = departmentService.saveDepartment(departmentDTO);
        response.getWriter().write(JsonConverter.toJson(actual));
    }
}
