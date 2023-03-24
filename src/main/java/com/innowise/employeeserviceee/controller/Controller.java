package com.innowise.employeeserviceee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.employeeserviceee.dto.DepartmentDTO;
import com.innowise.employeeserviceee.dto.EmployeeDTO;
import com.innowise.employeeserviceee.dto.UserDTO;
import com.innowise.employeeserviceee.service.DepartmentService;
import com.innowise.employeeserviceee.service.EmployeeService;
import com.innowise.employeeserviceee.service.UserService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Controller", urlPatterns = {"/api/*"})
public class Controller extends HttpServlet {
    @EJB
    private EmployeeService employeeService;

    @EJB
    private DepartmentService departmentService;

    @EJB
    private UserService authorizationService;

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        System.out.println(path);
        ObjectMapper mapper = new ObjectMapper();
        switch (path) {
            case "/employees":
                List<EmployeeDTO> allEmployees = employeeService.findAll();
                response.getWriter().write(mapper.writeValueAsString(allEmployees));
                break;
            case "/departments":
                List<DepartmentDTO> allDepartments = departmentService.findAll();
                response.getWriter().write(mapper.writeValueAsString(allDepartments));
                break;
            case "/users":
                List<UserDTO> allUsers = authorizationService.findAll();
                response.getWriter().write(mapper.writeValueAsString(allUsers));
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        processRequest(request, response);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        processRequest(request, response);
//    }

//    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        String commandName = request.getParameter(COMMAND);
//        Optional<Command> command = COMMAND_PROVIDER.getCommand(commandName);
//        Router router;
//        try {
//            router = command.isPresent() ? command.get().execute(request) : new Router(ERROR_404_PAGE, Router.RouterType.ERROR);
//        } catch (CommandException e) {
//            router = new Router(ERROR_404_PAGE, Router.RouterType.REDIRECT);
//        }
//        switch (router.getRouterType()) {
//            case REDIRECT:
//                response.sendRedirect(router.getPagePath());
//                break;
//            case FORWARD:
//                RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
//                dispatcher.forward(request, response);
//                break;
//            case ERROR:
//                response.sendRedirect(router.getPagePath());
//                break;
//            default:
//                response.sendRedirect(ERROR_404_PAGE);
//                break;
//        }
//    }
}