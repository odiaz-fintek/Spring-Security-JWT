package com.auth.jwt.app.filter;
// Este no se esta usando por ahora
// import com.auth.jwt.app.payload.AutenticacionApiKey;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
// import org.springframework.security.web.util.matcher.RequestMatcher;

// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;

// public class AuthFiltroApiKey extends AbstractAuthenticationProcessingFilter {
//     // Cambiarlo estas cadenas
//     private static final String API_KEY_HEADER = "API-Key";
//     private static final String API_SECRET_HEADER = "API-Secret";

//     public AuthFiltroApiKey(RequestMatcher requiresAuth) {
//         super(requiresAuth);
//     }

//     @Override
//     public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//             throws AuthenticationException, IOException {
//         String apiKey = request.getHeader(API_KEY_HEADER);
//         String apiSecret = request.getHeader(API_SECRET_HEADER);

//         if (apiKey == null || apiSecret == null) {
//             throw new RuntimeException("Missing API Key or Secret");
//         }

//         // You can customize the username and password extraction logic here
//         String username = "defaultUsername"; // or extract from request
//         String password = "defaultPassword"; // or extract from request

//         AutenticacionApiKey auth = new AutenticacionApiKey(username, password, apiKey);
//         return getAuthenticationManager().authenticate((Authentication) auth);
//     }

//     @Override
//     protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//         super.successfulAuthentication(request, response, chain, authResult);
//         chain.doFilter(request, response);
//     }
// }