package com.innowise.employeeserviceee.security;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SecurityContext implements jakarta.ws.rs.core.SecurityContext {

    private final String username;
    private final boolean isSecure;
    private final List<String> roles;

    public SecurityContext(String username, boolean isSecure, String... roles) {
        System.out.println("SecurityContext.SecurityContext");
        this.username = username;
        this.isSecure = isSecure;
        this.roles = Collections.unmodifiableList(Arrays.asList(roles));
    }

    @Override
    public Principal getUserPrincipal() {
        return () -> username;
    }

    @Override
    public boolean isUserInRole(String role) {
        return roles.contains(role);
    }

    @Override
    public boolean isSecure() {
        return isSecure;
    }

    @Override
    public String getAuthenticationScheme() {
        return "Bearer";
    }


}
