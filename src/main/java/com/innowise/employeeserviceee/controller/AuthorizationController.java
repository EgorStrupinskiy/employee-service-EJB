//package com.innowise.employeeserviceee.controller;
//
//import com.strupinski.employeeservice.entity.User;
//import com.strupinski.employeeservice.service.AuthorizationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/")
//public class AuthorizationController {
//
//    @Autowired
//    private final AuthorizationService service;
//
//    public AuthorizationController(AuthorizationService service) {
//        this.service = service;
//    }
//
//    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody List<User> findAllUsers() {
//        return this.service.findAll();
//    }
//
//    @DeleteMapping("/users/{id}")
//    public String deleteUserById(@PathVariable Long id) {
//        service.deleteById(id);
//
//        return "Department with id " + id + " was deleted";
//    }
//
//    @PostMapping("/register")
//    public User addNewDepartment(@RequestBody User user) {
//        return service.addUser(user);
//    }
//}
