package com.auth.jwt.app.filter;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.authority.AuthorityUtils;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;

// import com.auth.jwt.app.payload.AutenticacionApiKey;

// import java.util.Collections;
// import javax.servlet.http.HttpServletRequest;

// public class AuthenticationService {

//     @Value("${token.palabra.secreta}")
//     private static String apiSecret;
//     private static final String AUTH_TOKEN_HEADER_NAME = "API-Key";

//     public static Authentication getAuthentication(HttpServletRequest request) {
//         String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);

//         // Aquí puedes implementar tu lógica para validar la API Key
//         if (apiKey == null || !isValidApiKey(apiKey)) {
//             throw new BadCredentialsException("Invalid API Key");
//         }

//         return new AutenticacionApiKey(apiKey, apiSecret, AuthorityUtils.NO_AUTHORITIES);
//     }

//     private static boolean isValidApiKey(String apiKey) {
//         // Implementa tu lógica de validación de la API Key aquí
//         // Por ejemplo, busca en la base de datos si la API Key es válida
//         return "expected-api-key".equals(apiKey);
//     }
// }