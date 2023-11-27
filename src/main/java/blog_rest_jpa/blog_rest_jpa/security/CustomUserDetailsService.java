package blog_rest_jpa.blog_rest_jpa.security;

import blog_rest_jpa.blog_rest_jpa.model.AuthUserEntity;
import blog_rest_jpa.blog_rest_jpa.model.Role;
import blog_rest_jpa.blog_rest_jpa.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements  UserDetailsService {

	private AuthUserRepository authUserRepository;

	@Autowired
	public CustomUserDetailsService(AuthUserRepository authUserRepository) {
		this.authUserRepository = authUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		AuthUserEntity authUser=  authUserRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not found: " + username));
		return new User(authUser.getUsername(),authUser.getPassword(),mapRolesToAuthorities(authUser.getRoles()));
	}

	private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
