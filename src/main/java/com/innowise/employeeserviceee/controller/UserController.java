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
//@RequestMapping("/aboba")
//public class UserController {
//
//    @Autowired
//    private final AuthorizationService service;
//
//    public UserController(AuthorizationService service) {
//        this.service = service;
//    }
//
//    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody List<User> getAll() {
//        return this.service.findAll();
//    }
//
//    @PostMapping("/")
//    public void addNewUser(@RequestBody User user) {
//        service.addUser(user);
//    }
//}
