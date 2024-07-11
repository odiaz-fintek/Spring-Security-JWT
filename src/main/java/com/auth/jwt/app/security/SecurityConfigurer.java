package com.auth.jwt.app.security;

import com.auth.jwt.app.filter.ApiKeyAuthFilter;
import com.auth.jwt.app.filter.AuthFiltroToken;
import com.auth.jwt.app.filter.JWTKeepAliveFilter;
import com.auth.jwt.app.security.service.MiUserDetailsService;
import com.auth.jwt.app.security.service.AuthBlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    /* ~ Autowired
    -------------------------------------------------------------- */
    @Autowired
    private MiUserDetailsService userDetailsService;

    @Autowired
    private AuthFiltroToken authFiltroToken;

    @Autowired
    private AuthBlock authBlock;
    @Autowired
    private ApiKeyAuthFilter apiKeyAuthFilter;

    @Autowired
    private JWTKeepAliveFilter jwtKeepAliveFilter;

    /* ~ BEANS
    -------------------------------------------------------------- */
    @Bean
    public BCryptPasswordEncoder passEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*~~(Migrate manually based on https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)~~>*/
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    /**
     * Indicamos que queremos una autenticacion personalizada en este caso definimos el comportamiento
     * del <b>serDetailsService</b> en nuestra clase {@link MiUserDetailsService}, esto permite personalizar
     * la autenticacion.
     * Tambien indicamos que debe cifrar la contraseña que se cree o que se analice.
     * @param auth usado para indicar la autenticacion por medio de la BD.
     * @throws Exception si existe un problema con la autenticacion.
     */
    /*~~(Migrate manually based on https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)~~>*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authBlock);
        auth.userDetailsService(userDetailsService).passwordEncoder(passEncoder());
    }

    // Establecemos que rutas y/o recursos estaran protegidos

    /**
     * Configuramos las rutas y/o recursos que queremos proteger y cuales establacer de modo publico, ademas de
     * configurar nuestros propios filtros, login o logout.
     * @param http URL usada para comparar el acceso
     * @throws Exception Si no tiene acceso a los recursos
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/registrarse", "/iniciar", "/public")
                    .permitAll()
                    .antMatchers("/basic/**").authenticated()
                    .anyRequest()
                    .permitAll()
                .and()
                 .httpBasic()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .invalidSessionUrl("/logoutforced")
                    .maximumSessions(1)
                    .expiredUrl("/logoutforced");

        // Indicamos que usaremos un filtro
        http.addFilterBefore(jwtKeepAliveFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiKeyAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(authFiltroToken, UsernamePasswordAuthenticationFilter.class);
    }

    
} // fin de la clase de configuracion
