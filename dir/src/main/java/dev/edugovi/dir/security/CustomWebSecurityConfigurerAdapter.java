package dev.edugovi.dir.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter {

    /*
    Must change default users and passwords!
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("User")
            .password("{bcrypt}$2y$05$MPsTYf3dWtdr70In7Xp0MeU4khKPzcl4WbY7nTfuLQb5Nvgqzsjde")
            .roles("USER")
            .build();
        UserDetails external = User.builder()
            .username("External")
            .password("{bcrypt}$2y$05$cC6TJWnvh/gb6x3FTwFPmuKkzqsCg9u5pIhAFkkd6yveco23KZFPO")
            .roles("EXTERNAL")
            .build();
        UserDetails admin = User.builder()
            .username("Admin")
            .password("{bcrypt}$2y$05$56xO8esPfWaDxWYIlGppb.ihG6Wy0.Ja2enpTpvgQXyL0C3N1F/ye")
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(user, external, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .httpBasic().and()
            .securityMatcher("/**")
            .authorizeHttpRequests((authorize) -> 
                authorize       
                    .requestMatchers(RegexRequestMatcher.regexMatcher(HttpMethod.POST, "^/users/check$")).hasAnyRole("ADMIN", "EXTERNAL")
                    .requestMatchers(RegexRequestMatcher.regexMatcher(HttpMethod.GET, "^/users/[0-9]+\\?projection=userProtected$")).hasAnyRole("EXTERNAL", "ADMIN")    
                    .requestMatchers(RegexRequestMatcher.regexMatcher(HttpMethod.PUT, "^/users/[0-9]+$")).hasAnyRole("ADMIN")
                    .requestMatchers(RegexRequestMatcher.regexMatcher(HttpMethod.PATCH, "^/users/[0-9]+$")).hasAnyRole("ADMIN")
                    .requestMatchers(RegexRequestMatcher.regexMatcher(HttpMethod.POST, "^/users$")).hasAnyRole("ADMIN")
                    .requestMatchers(RegexRequestMatcher.regexMatcher(HttpMethod.DELETE, "^/users/[0-9]+$")).hasAnyRole("ADMIN")
                    .requestMatchers(RegexRequestMatcher.regexMatcher(HttpMethod.DELETE, "^/users$")).hasAnyRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN")
                    .requestMatchers("/**").hasAnyRole("ADMIN")
                    .anyRequest().denyAll()
            );
            http.headers().frameOptions().disable();
            http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
