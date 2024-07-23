package com.auth.jwt.app.filter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * This class is a filter that handles the authentication token for JWT (JSON Web Token) and performs token refresh if necessary.
 * It extends the OncePerRequestFilter class from Spring Security.
 */
@Component
public class JWTKeepAliveFilter extends OncePerRequestFilter  {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /**
     * Filters the incoming request and response, performing JWT token validation and authentication.
     * If the token is about to expire, it refreshes the token and updates the response header with the refreshed token.
     * Finally, it sets the authentication in the security context holder.
     *
     * @param request  the incoming HTTP servlet request
     * @param response the outgoing HTTP servlet response
     * @param chain    the filter chain
     * @throws ServletException if there is a servlet error
     * @throws IOException      if there is an I/O error
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        if (token != null && validateToken(token)) {
            Claims claims = getClaimsFromToken(token);
            if (isTokenAboutToExpire(claims)) {
                String refreshedToken = refreshJwtToken(claims);
                response.setHeader("Authorization", "Bearer " + refreshedToken);
            }

            Authentication authentication = getAuthentication(claims);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    /**
     * Retrieves the JWT token from the request's Authorization header.
     *
     * @param request the HttpServletRequest object representing the incoming request
     * @return the JWT token extracted from the Authorization header, or null if not found
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Validates the given JWT token.
     *
     * @param token The JWT token to be validated.
     * @return true if the token is valid, false otherwise.
     */
    private boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Retrieves the claims from the provided JWT token.
     *
     * @param token The JWT token from which to retrieve the claims.
     * @return The claims extracted from the token.
     */
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    /**
     * Checks if the token is about to expire.
     * 
     * @param claims The claims extracted from the JWT token.
     * @return true if the token is about to expire, false otherwise.
     */
    private boolean isTokenAboutToExpire(Claims claims) {
        Date expirationDate = claims.getExpiration();
        long timeToExpire = expirationDate.getTime() - System.currentTimeMillis();
        return timeToExpire < jwtExpiration / 2;
    }

    /**
     * Refreshes the JWT token by generating a new token with an updated expiration date.
     *
     * @param claims The claims of the original JWT token.
     * @return The refreshed JWT token.
     */
    private String refreshJwtToken(Claims claims) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(claims.getSubject())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Retrieves the authentication object based on the provided claims.
     *
     * @param claims The claims object containing the necessary information for authentication.
     * @return The authentication object.
     */
    private Authentication getAuthentication(Claims claims) {
        return null;
    }
}
