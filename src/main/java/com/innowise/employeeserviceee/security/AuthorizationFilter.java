package com.innowise.employeeserviceee.security;

import jakarta.annotation.Priority;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.lang.reflect.Method;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    private static void refuseRequest(ContainerRequestContext requestContext) {
        requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                .header(HttpHeaders.AUTHORIZATION, "You role is forbidden for this action")
                .build());
    }

    @Override
    public void filter(ContainerRequestContext requestContext) {

        Method method = resourceInfo.getResourceMethod();

        if (method.isAnnotationPresent(DenyAll.class)) {
            refuseRequest(requestContext);
        }

        RolesAllowed rolesAllowed = method.getAnnotation(RolesAllowed.class);
        if (rolesAllowed != null) {
            performAuthorization(rolesAllowed.value(), requestContext);
            return;
        }

        if (method.isAnnotationPresent(PermitAll.class)) {
            return;
        }

        Class<?> resourceClass = resourceInfo.getResourceClass();

        rolesAllowed = resourceClass.getAnnotation(RolesAllowed.class);
        if (rolesAllowed != null) {
            performAuthorization(rolesAllowed.value(), requestContext);
            return;
        }

        if (resourceClass.isAnnotationPresent(PermitAll.class)) {
            return;
        }

        if (isNotAuthenticated(requestContext)) {
            refuseRequest(requestContext);
        }
    }

    private void performAuthorization(String[] rolesAllowed,
                                      ContainerRequestContext requestContext) {

        if (rolesAllowed.length > 0 && isNotAuthenticated(requestContext)) {
            refuseRequest(requestContext);
        }

        for (String roleAllowed : rolesAllowed) {
            if (requestContext.getSecurityContext().isUserInRole(roleAllowed)) {
                return;
            }
        }

        refuseRequest(requestContext);
    }

    private boolean isNotAuthenticated(ContainerRequestContext requestContext) {
        return requestContext.getSecurityContext().getUserPrincipal() == null;
    }
}
