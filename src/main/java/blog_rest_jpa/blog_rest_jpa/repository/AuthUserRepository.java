package blog_rest_jpa.blog_rest_jpa.repository;

import blog_rest_jpa.blog_rest_jpa.model.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUserEntity,Integer> {
	Optional<AuthUserEntity> findByUsername(String username);
	Boolean existsByUsername(String username);
}
