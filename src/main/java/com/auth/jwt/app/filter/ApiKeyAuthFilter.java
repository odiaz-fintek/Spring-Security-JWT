package com.auth.jwt.app.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth.jwt.app.service.UsuarioService;

import java.io.IOException;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    // Get the API key and secret from application.properties
    @Autowired
    private UsuarioService usuarioService;
    
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Get the API key and secret from request headers
        String requestApiKey = request.getHeader("X-API-KEY");
        if (request.getRequestURI().startsWith("/apikey")) {
                if (requestApiKey != null && usuarioService.buscarApikeyPorApikey(requestApiKey)) {
                // Continue processing the request
                filterChain.doFilter(request, response);
            } else {
                // Reject the request and send an unauthorized error
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Unauthorized: Missing or invalid apikey header");
            }
        } else {
            // Continue processing the request for public routes
            filterChain.doFilter(request, response);
        }
    }
}