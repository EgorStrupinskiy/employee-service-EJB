package com.innowise.employeeserviceee.security;

import com.innowise.employeeserviceee.exception.handler.ExceptionMessage;
import com.innowise.employeeserviceee.util.JsonConverter;
import io.jsonwebtoken.JwtException;
import jakarta.annotation.Priority;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Provider
@Priority(Priorities.AUTHENTICATION)
@WebFilter("/api/*")
@Provider
public class AuthenticationFilter implements Filter {
    private static final String AUTHENTICATION_SCHEME = "Bearer";
    private static final Set<String> EXCLUDED_PATHS = new HashSet<>(List.of("/employee-service-EE-1.0-SNAPSHOT/api/login"));
    @EJB
    private TokenService jwtTokenService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
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
            abortWithUnauthorized(httpResponse);
            return;
        }
        String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            String principal = jwtTokenService.parseToken(token).getBody().getSubject();
            String authority = jwtTokenService.parseToken(token).getBody().get("authority", String.class);
            httpRequest.getSession().setAttribute("principal", principal);
            httpRequest.getSession().setAttribute("authority", authority);
            chain.doFilter(request, response);
        } catch (Exception e) {
            abortWithUnauthorized(httpResponse);
        }
    }

    private void abortWithUnauthorized(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME);
        response.getWriter().write(JsonConverter.toJson(new ExceptionMessage(HttpServletResponse.SC_BAD_REQUEST, "Unauthorized")));
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}