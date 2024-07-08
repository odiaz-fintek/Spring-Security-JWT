package com.auth.jwt.app.security.utils;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;

// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;

// @Component
// public class ApiKeyUtil {
//     @Value("${token.palabra.secreta}")
//     private String SECRETO;

//     public Claims extraerContenidoClaims(String apikey) {
//         return Jwts.parser().setSigningKey(SECRETO).parseClaimsJws(apikey).getBody();
//     }

//     public String extraerUsername(String apikey) {
//         return extraerContenidoClaims(apikey).getSubject();
//     }

//     public Date extraerTiempoVencimiento(String apikey) {
//         return extraerContenidoClaims(apikey).getExpiration();
//     }

//     public boolean isTokenExpiration(String apikey) {
//         return extraerTiempoVencimiento(apikey).before(new Date());
//     }

//     public String prepararEstructuraApiKey(Map<String, Object> payload, String subject) {
//         return Jwts.builder()
//                 .setClaims(payload)
//                 .setSubject(subject)
//                 .setIssuedAt(new Date(System.currentTimeMillis()))
//                 .setExpiration(new Date(System.currentTimeMillis() + 5000 * 60))
//                 .signWith(SignatureAlgorithm.HS256, SECRETO)
//                 .compact();
//     }

//     public String createApiKey(UserDetails userDetails) {
//         Map<String, Object> claims = new HashMap<>();
//         return prepararEstructuraApiKey(claims, userDetails.getUsername());
//     }

//     public boolean validarApiKey(String apikey, UserDetails userDetails) {
//         final String username = extraerUsername(apikey);
//         return (username.equals(userDetails.getUsername()) && !isTokenExpiration(apikey));
//     }
// }
