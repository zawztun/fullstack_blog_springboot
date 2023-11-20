package blog_rest_jpa.blog_rest_jpa.repository;

import blog_rest_jpa.blog_rest_jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
