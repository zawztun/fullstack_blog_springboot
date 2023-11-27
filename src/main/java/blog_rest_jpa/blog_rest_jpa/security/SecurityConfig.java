package blog_rest_jpa.blog_rest_jpa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	private  CustomUserDetailsService customUserDetailsService;
	private  JwtAuthEntry jwtAuthEntry;

	@Autowired
	public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAuthEntry jwtAuthEntry) {
		this.customUserDetailsService = customUserDetailsService;
		this.jwtAuthEntry = jwtAuthEntry;
	}

	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception{
		http
				.csrf()
				.disable()
				.exceptionHandling()
				.authenticationEntryPoint(jwtAuthEntry)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers("/api/auth/**").permitAll()
				.anyRequest().authenticated());
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	@Bean
	public JWTAuthenticationFilter jwtAuthenticationFilter(){
		return new JWTAuthenticationFilter();
	}

	/**
	@BEAN
	PUBLIC USERDetailsService users(){
		UserDetails admin = User.builder()
				.username("admin")
				.password("password")
				.roles("ADMIN")
				.build();
		UserDetails user = User.builder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(admin, user);
	}
	 */

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
