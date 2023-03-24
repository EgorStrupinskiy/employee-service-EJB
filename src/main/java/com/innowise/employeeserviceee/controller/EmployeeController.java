//package com.innowise.employeeserviceee.controller;
//
//import com.innowise.employeeserviceee.dto.EmployeeDTO;
//import com.innowise.employeeserviceee.service.EmployeeService;
//import jakarta.ejb.EJB;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.List;
//
//public class EmployeeController {
//    @EJB
//    private EmployeeService employeeService;
//
//
//    public List<EmployeeDTO> showAllEmployees() {
//        List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
//        return allEmployees;
//    }
//
//
//    public EmployeeDTO addNewEmployee(EmployeeDTO employee) {
//
//        return employeeService.saveEmployee(employee);
//
//    }
//
//    public EmployeeDTO updateEmployee(EmployeeDTO employee) {
//        employeeService.saveEmployee(employee);
//
//        return employee;
//    }
//
//    public String deleteEmployee(Long id) {
//        employeeService.deleteById(id);
//
//        return "Employee with id " + id + " was deleted";
//    }
//
//    public EmployeeDTO getEmployee(Long id) {
//        EmployeeDTO employee = employeeService.findById(id);
//        return employee;
//    }
//
//    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//
//        if (action == null) {
//            // Если параметр "action" отсутствует, генерируем ошибку
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//            return;
//        }
//
//        // Выбираем нужный метод в зависимости от значения параметра "action"
//        switch (action) {
//            case "list":
//                listUsers(request, response);
//                break;
//            case "create":
//                createUser(request, response);
//                break;
//            case "edit":
//                editUser(request, response);
//                break;
//            case "delete":
//                deleteUser(request, response);
//                break;
//            default:
//                // Если значение параметра "action" неизвестно, генерируем ошибку
//                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//                break;
//        }
//    }
//}
