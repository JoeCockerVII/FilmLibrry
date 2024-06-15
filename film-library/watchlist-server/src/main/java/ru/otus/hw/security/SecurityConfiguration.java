package ru.otus.hw.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.otus.hw.services.UserService;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").authenticated()
                        .requestMatchers("/watchlists","/watch").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/watchlists/**","/watch/**").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/film/list").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/authors", "/genres").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/films").hasRole("ADMIN")
                        .requestMatchers("/films").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/films/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/films/**").hasAnyRole("ADMIN")
                        .requestMatchers("/**").hasAnyRole("USER","ADMIN")

                        .anyRequest().denyAll()
                )
                .userDetailsService(userService)
                .formLogin(Customizer.withDefaults());
        return http.build();
    }


//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userService);
//        provider.setPasswordEncoder(passwordEncoder);
//        return provider;
//    }

}
