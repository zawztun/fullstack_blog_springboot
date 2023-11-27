package blog_rest_jpa.blog_rest_jpa.repository;

import blog_rest_jpa.blog_rest_jpa.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
	Optional<Role> findByName(String name);
}
