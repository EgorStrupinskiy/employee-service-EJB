package com.innowise.employeeserviceee.security;

import jakarta.annotation.Priority;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.lang.reflect.Method;

@Provider
@Priority(Priorities.AUTHORIZATION)
@WebFilter("/api/*")
public class AuthorizationFilter implements Filter {


//    private static void refuseRequest(ContainerRequestContext requestContext) {
//        requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
//                .header(HttpHeaders.AUTHORIZATION, "You role is forbidden for this action")
//                .build());
//    }
//
//
//    private void performAuthorization(String[] rolesAllowed,
//                                      ContainerRequestContext requestContext) {
//
//        if (rolesAllowed.length > 0 && isNotAuthenticated(requestContext)) {
//            refuseRequest(requestContext);
//        }
//
//        for (String roleAllowed : rolesAllowed) {
//            if (requestContext.getSecurityContext().isUserInRole(roleAllowed)) {
//                return;
//            }
//        }
//
//        refuseRequest(requestContext);
//    }
@Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    Class<?> resourceClass = getResourceClass(httpRequest);
    if (resourceClass.isAnnotationPresent(DenyAll.class)) {
        refuseRequest(httpResponse);
        return;
    } else if (resourceClass.isAnnotationPresent(RolesAllowed.class)) {
        RolesAllowed rolesAllowed = resourceClass.getAnnotation(RolesAllowed.class);
        performAuthorization(rolesAllowed.value(), httpRequest, httpResponse, chain);
        return;
    } else if (resourceClass.isAnnotationPresent(PermitAll.class)) {
        chain.doFilter(request, response);
        return;
    }

    Method resourceMethod = getResourceMethod(httpRequest);
    if (resourceMethod.isAnnotationPresent(DenyAll.class)) {
        refuseRequest(httpResponse);
        return;
    } else if (resourceMethod.isAnnotationPresent(RolesAllowed.class)) {
        RolesAllowed rolesAllowed = resourceMethod.getAnnotation(RolesAllowed.class);
        performAuthorization(rolesAllowed.value(), httpRequest, httpResponse, chain);
        return;
    } else if (resourceMethod.isAnnotationPresent(PermitAll.class)) {
        chain.doFilter(request, response);
        return;
    }

    if (isNotAuthenticated(httpRequest)) {
        refuseRequest(httpResponse);
        return;
    }

    chain.doFilter(request, response);
}

    private Class<?> getResourceClass(HttpServletRequest request) {
        return (Class<?>) request.getAttribute("jakarta.ws.rs.container.ResourceClass");
    }

    private Method getResourceMethod(HttpServletRequest request) {
        return (Method) request.getAttribute("jakarta.ws.rs.container.ResourceMethod");
    }

    private void performAuthorization(String[] rolesAllowed, HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (rolesAllowed.length > 0 && isNotAuthenticated(request)) {
            refuseRequest(response);
            return;
        }

        for (String role : rolesAllowed) {
            if (request.isUserInRole(role)) {
                chain.doFilter(request, response);
                return;
            }
        }

        refuseRequest(response);
    }

    private boolean isNotAuthenticated(HttpServletRequest request) {
        return request.getUserPrincipal() == null;
    }

    private void refuseRequest(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().println("Access denied");
        response.getWriter().flush();
        response.getWriter().close();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
