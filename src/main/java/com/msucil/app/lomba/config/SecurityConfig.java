package com.msucil.app.lomba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login.loginPage("/security/auth/login").permitAll()
				.loginProcessingUrl("/security/auth/login").permitAll())
				.logout(logout -> logout.logoutUrl("/security/auth/logout").clearAuthentication(true)
						.invalidateHttpSession(true))
				.authorizeHttpRequests(req -> req.requestMatchers("/security/account/system/**", "/security/auth/**")
						.permitAll().anyRequest().authenticated());

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/library/**", "/theme/**");
	}
}
