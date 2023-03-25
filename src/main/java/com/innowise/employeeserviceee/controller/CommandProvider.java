package com.innowise.employeeserviceee.controller;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@Stateless
public class CommandProvider {
    private final String COMMAND_SEPARATOR = "/";
    @EJB
    private Command findAllEmployeesCommand;
    @EJB
    private Command addEmployeeCommand;

    @EJB
    private Command findAllDepartmentsCommand;
    @EJB
    private Command addDepartmentCommand;

    @EJB
    private Command findAllUsersCommand;
    @EJB
    private Command addUserCommand;

    @EJB
    private Command wrongCommand;

    private Map<String, Map<String, Command>> map = new HashMap<>();

    @PostConstruct
    private void init() {
        Map<String, Command> innerMap = new HashMap<>();
        innerMap.put("GET", findAllEmployeesCommand);
        innerMap.put("POST", addEmployeeCommand);
        map.put("employees", innerMap);

        innerMap = new HashMap<>();
        innerMap.put("GET", findAllDepartmentsCommand);
        innerMap.put("POST", addDepartmentCommand);
        map.put("departments", innerMap);

        innerMap = new HashMap<>();
        innerMap.put("GET", findAllUsersCommand);
        innerMap.put("POST", addUserCommand);
        map.put("users", innerMap);
    }

    public Command provideCommand(HttpServletRequest request) {
        String path = request.getRequestURI();
        String[] pathParts = path.split(COMMAND_SEPARATOR);
        String commandName = pathParts.length > 2 ? pathParts[3] : "wrongCommand";
//        switch (commandName) {
//            case "employees" -> {
//                switch (request.getMethod()) {
//                    case "GET" -> {
//                        return findAllEmployeesCommand;
//                    }
//                    case "POST" -> {
//                        return addEmployeeCommand;
//                    }
//                    default -> {
//                        return wrongCommand;
//                    }
//                }
//            }
//            case "departments" -> {
//                switch (request.getMethod()) {
//                    case "GET" -> {
//                        return findAllDepartmentsCommand;
//                    }
//                    case "POST" -> {
//                        return addDepartmentCommand;
//                    }
//                    default -> {
//                        return wrongCommand;
//                    }
//                }            }
//            case "users" -> {
//                switch (request.getMethod()) {
//                    case "GET" -> {
//                        return findAllUsersCommand;
//                    }
//                    case "POST" -> {
//                        return addUserCommand;
//                    }
//                    default -> {
//                        return wrongCommand;
//                    }
//                }
//            }
//            default -> {
//                return wrongCommand;
//            }
        return map.get(commandName).get(request.getMethod());

    }
}
