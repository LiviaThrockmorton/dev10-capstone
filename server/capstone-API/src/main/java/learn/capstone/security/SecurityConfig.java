package learn.capstone.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    @Bean
    public SecurityFilterChain getChain(HttpSecurity http, AuthenticationConfiguration config) throws Exception {
        http.csrf().disable();
        http.cors();
//        http.authorizeRequests().antMatchers("/**").permitAll();

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/authenticate").permitAll()
                .antMatchers(HttpMethod.POST, "/refresh").authenticated()
                .antMatchers(HttpMethod.GET, "/api/duck/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/duck/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/duck/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/duck/**").hasAuthority("ADMIN")

          
                .antMatchers(HttpMethod.GET, "/api/item/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/item/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/item/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/item/**").hasAuthority("ADMIN")


                .antMatchers(HttpMethod.GET, "/api/outfit/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/outfit/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/outfit/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/outfit/**").hasAuthority("ADMIN")


                .antMatchers(HttpMethod.GET, "/api/comment/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/comment/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/comment/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/comment/**").hasAuthority("ADMIN")


                
                .antMatchers("/**").denyAll()
                .and()
                .addFilter(new JwtRequestFilter(manager(config), converter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
