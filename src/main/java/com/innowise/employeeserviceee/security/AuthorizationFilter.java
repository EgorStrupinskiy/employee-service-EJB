package com.innowise.employeeserviceee.security;

import com.innowise.employeeserviceee.exception.AuthenticationException;
import com.innowise.employeeserviceee.exception.handler.ExceptionMessage;
import com.innowise.employeeserviceee.util.JsonConverter;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.*;

@Provider
@WebFilter("/api/*")
public class AuthorizationFilter implements Filter {
    private static final String AUTHENTICATION_SCHEME = "Bearer";
    private static final Set<String> EXCLUDED_PATHS = new HashSet<>(List.of("/employee-service-EE-1.0-SNAPSHOT/api/login"));

    @EJB
    private TokenService jwtTokenService;

    private final Map<String, List<String>> map = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        List<String> innerMap = new ArrayList<>();

        innerMap.add("ALL");
        map.put("GET", innerMap);

        innerMap = new ArrayList<>();
        innerMap.add("ADMIN");
        map.put("POST", innerMap);

        innerMap = new ArrayList<>();
        innerMap.add("ADMIN");
        map.put("DELETE", innerMap);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestUri = httpRequest.getRequestURI();
        if (EXCLUDED_PATHS.contains(requestUri)) {
            chain.doFilter(request, response);
            return;
        }

        String authorizationHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith(AUTHENTICATION_SCHEME)) {
            throw new NotAuthorizedException(requestUri);
        }

        String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();


        String authority = jwtTokenService.parseToken(token).getBody().get("authority", String.class);

        String methodType = httpRequest.getMethod();

        if (!map.containsKey(methodType)) {
            throw new NotAuthorizedException(requestUri);
        }
        List<String> allowedRoles = map.get(methodType);

        if (allowedRoles != null) {
            if (allowedRoles.size() == 0) {
                throw new NotAuthorizedException(requestUri);
            }
            if (isAllowed(allowedRoles, authority)) {
                chain.doFilter(request, response);
                return;
            }
        }
        throw new NotAuthorizedException(requestUri);
    }

    private boolean isAllowed(List<String> rolesAllowed, String userRole) {
        if (rolesAllowed.size() == 0 || userRole == null) {
            return false;
        }
        if (rolesAllowed.contains("ALL")) {
            return true;
        }
        for (String roleAllowed : rolesAllowed) {
            if (roleAllowed.equals(userRole)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void destroy() {
    }
}
