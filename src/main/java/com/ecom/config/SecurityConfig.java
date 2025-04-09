package com.ecom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ecom.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	http.csrf(e -> e.disable());
//	http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated());
////	http.formLogin(Customizer.withDefaults());
//	http.httpBasic(Customizer.withDefaults());
//	http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//	return http.build();
		return http.csrf(e -> e.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/auth/register", "/api/v1/auth/login")
						.permitAll().anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();

//	http.formLogin(Customizer.withDefaults());
	}

//@Bean
//public UserDetails userDetailsService() {
//	UserDetails user1=User.withDefaultPasswordEncoder().username("Men").password("1025").roles("USER").build();
//	return new InMemoryUserDetailManager(user1);
//}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

//	@Bean
//	public InMemoryUserDetailsManager userDetailService(PasswordEncoder encoder) {
//		UserDetails user = User.withUsername("men").password(encoder.encode("1024")).roles("USER").build();
//		return new InMemoryUserDetailsManager(user);
//	}

	@Bean
	public AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsServiceImpl,
			PasswordEncoder encoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsServiceImpl);
		authenticationProvider.setPasswordEncoder(encoder);
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
