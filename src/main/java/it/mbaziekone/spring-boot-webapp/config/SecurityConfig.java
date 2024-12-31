package it.mbaziekone.book_e_commerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import it.mbaziekone.book_e_commerce.service.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	CustomUserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/public/**", "/register", "/api/**", "/css/**", "/js/**", "/images/**").permitAll()
						.anyRequest().authenticated())
				.formLogin(form -> form
						.loginPage("/loginAdmin")
						.loginProcessingUrl("/perform_login")
						.defaultSuccessUrl("/dashboard",true)
						.failureUrl("/loginAdmin?error=true")
						.permitAll())
				 		.headers(headers -> headers
				        .cacheControl(cache -> cache.disable()) // Disable Cache-Control Header
				        )
				.logout(logout -> logout
						.logoutUrl("/perform_logout")
						.logoutSuccessUrl("/loginAdmin?logout=true")
						.deleteCookies("JSESSIONID")
						.invalidateHttpSession(true)
						.permitAll()
				)
				.build();
	}
	
}