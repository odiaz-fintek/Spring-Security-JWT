package com.auth.jwt.app.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    // Get the API key and secret from application.properties
    @Value("${api.key}")
    private String apiKey;
//    @Value("${api.secret}")
//    private String apiSecret;
    
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Get the API key and secret from request headers
        String requestApiKey = request.getHeader("X-API-KEY");
//        String requestApiSecret = request.getHeader("X-API-SECRET");
        if (request.getRequestURI().startsWith("/apikey") || request.getRequestURI().startsWith("/home")) {
            if (apiKey.equals(requestApiKey)) {
                // Continue processing the request
                filterChain.doFilter(request, response);
            } else {
                // Reject the request and send an unauthorized error
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Unauthorized");
            }
        } else {
            // Continue processing the request for public routes
            filterChain.doFilter(request, response);
        }
    }
}