package com.strupinski.employeeserviceee.controller;

import com.strupinski.employeeserviceee.exception.AbstractException;
import com.strupinski.employeeserviceee.exception.handler.ExceptionHandler;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;

@MultipartConfig
@WebServlet(name = "Controller", urlPatterns = {"/api/*"})
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Controller extends HttpServlet {

    @EJB
    CommandProvider commandProvider;

    @PermitAll
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @PermitAll
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        try {
            commandProvider.provideCommand(request).execute(request, response);
//            throw new NoSuchRecordException("department/", "Test exception");
        } catch (AbstractException e) {
            ExceptionHandler.handle(e, response);
        }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }
}