package blog_rest_jpa.blog_rest_jpa.repository;
import blog_rest_jpa.blog_rest_jpa.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT u FROM Post u ORDER BY u.id DESC Limit 1")
    Post findAllOrderedByLastId();
}