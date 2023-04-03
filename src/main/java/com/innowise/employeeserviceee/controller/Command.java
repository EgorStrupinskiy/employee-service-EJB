package com.innowise.employeeserviceee.controller;

import com.innowise.employeeserviceee.exception.NoSuchRecordException;
import com.innowise.employeeserviceee.exception.UsernameNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, UsernameNotFoundException;
}