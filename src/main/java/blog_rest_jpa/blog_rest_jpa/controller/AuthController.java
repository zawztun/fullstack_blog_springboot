package blog_rest_jpa.blog_rest_jpa.controller;

import blog_rest_jpa.blog_rest_jpa.dto.LoginDto;
import blog_rest_jpa.blog_rest_jpa.dto.LoginResponseDto;
import blog_rest_jpa.blog_rest_jpa.dto.RegisterDto;
import blog_rest_jpa.blog_rest_jpa.model.AuthUserEntity;
import blog_rest_jpa.blog_rest_jpa.model.Role;
import blog_rest_jpa.blog_rest_jpa.repository.AuthUserRepository;
import blog_rest_jpa.blog_rest_jpa.repository.RoleRepository;
import blog_rest_jpa.blog_rest_jpa.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private AuthenticationManager authenticationManager;
	private AuthUserRepository authUserRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	private TokenGenerator tokenGenerator;

	@Autowired
	public AuthController(
			AuthenticationManager authenticationManager,
			AuthUserRepository authUserRepository,
			RoleRepository roleRepository,
			PasswordEncoder passwordEncoder,TokenGenerator tokenGenerator) {
		this.authenticationManager = authenticationManager;
		this.authUserRepository = authUserRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenGenerator = tokenGenerator;
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
		if (authUserRepository.existsByUsername(registerDto.getUsername()))
			return ResponseEntity.badRequest().body("Username is taken");
		AuthUserEntity authUserEntity = new AuthUserEntity();
		authUserEntity.setUsername(registerDto.getUsername());
		authUserEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		Role role = roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		authUserEntity.setRoles(Collections.singletonList(role));
		authUserRepository.save(authUserEntity);
		return ResponseEntity.ok("Register");
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto logindto){
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logindto.getUsername(),logindto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String jwtToken = tokenGenerator.generateToken(logindto.getUsername());
		return ResponseEntity.ok(new LoginResponseDto(jwtToken,logindto.getUsername()));
	}
}
