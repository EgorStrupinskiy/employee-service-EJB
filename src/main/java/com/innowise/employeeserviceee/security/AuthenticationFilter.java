package com.innowise.employeeserviceee.security;

import jakarta.annotation.Priority;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Provider
@Priority(Priorities.AUTHENTICATION)
@WebFilter("/api/*")
public class AuthenticationFilter implements Filter {

    private static final String REALM = "example";
    private static final String AUTHENTICATION_SCHEME = "Bearer";
    private static final Set<String> EXCLUDED_PATHS = new HashSet<>(List.of("/employee-service-EE-1.0-SNAPSHOT/api/login"));
    @Context
    private UriInfo uriInfo;

    @EJB
    private TokenService jwtTokenService;

//    @Override
//    public void filter(ContainerRequestContext requestContext) {
//        if (EXCLUDED_PATHS.contains(uriInfo.getPath())) {
//            return;
//        }
//
//        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
//
//        if (authorizationHeader == null || !authorizationHeader.startsWith(AUTHENTICATION_SCHEME)) {
//            abortWithUnauthorized(requestContext);
//            return;
//        }
//
//        String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();
//
//        try {
//            String principal = jwtTokenService.parseToken(token).getBody().getSubject();
//            String authority = jwtTokenService.parseToken(token).getBody().get("authority", String.class);
//            requestContext.setSecurityContext(
//                    new SecurityContext(principal, requestContext.getSecurityContext().isSecure(), authority));
//        } catch (Exception e) {
//            abortWithUnauthorized(requestContext);
//        }
//    }

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
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME);
        response.getWriter().write("Unauthorized");
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}