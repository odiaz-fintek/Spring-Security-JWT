package com.auth.jwt.app.payload;

// import java.io.Serializable;
// import org.springframework.security.authentication.AbstractAuthenticationToken;
// import org.springframework.security.core.GrantedAuthority;

// import java.util.Collection;

// public class AutenticacionApiKey implements Serializable {
//     private static final long serialVersionUID = 1L;
//     private String apiKey;
//     private String username;
//     private String password;
//     // private String apiSecret;


//     public AutenticacionApiKey() {}

//     public AutenticacionApiKey(String username, String password, String apiKey) {
//         this.apiKey = apiKey;
//         this.username = username;
//         this.password = password;
//         // this.apiSecret = apiSecret;
//     }

//     public String getApiKey() {
//         return apiKey;
//     }

//     public void setApiKey(String apiKey) {
//         this.apiKey = apiKey;
//     }

//     public String getUsername() {
//         return username;
//     }

//     public void setUsername(String username) {
//         this.username = username;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }
// }

// public class AutenticacionApiKey extends AbstractAuthenticationToken {

//     private final String apiKey;
//     private final String apiSecret;

//     public AutenticacionApiKey(String apiKey, String apiSecret) {
//         super(null);
//         this.apiKey = apiKey;
//         this.apiSecret = apiSecret;
//         setAuthenticated(false);
//     }

//     public AutenticacionApiKey(String apiKey, String apiSecret, Collection<? extends GrantedAuthority> authorities) {
//         super(authorities);
//         this.apiKey = apiKey;
//         this.apiSecret = apiSecret;
//         super.setAuthenticated(true);
//     }

//     @Override
//     public Object getCredentials() {
//         return this.apiSecret;
//     }

//     @Override
//     public Object getPrincipal() {
//         return this.apiKey;
//     }

//     @Override
//     public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
//         if (isAuthenticated) {
//             throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
//         }
//         super.setAuthenticated(false);
//     }

//     @Override
//     public void eraseCredentials() {
//         super.eraseCredentials();
//     }
// }