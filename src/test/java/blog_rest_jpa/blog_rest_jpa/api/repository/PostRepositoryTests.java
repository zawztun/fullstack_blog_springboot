package blog_rest_jpa.blog_rest_jpa.api.repository;

import blog_rest_jpa.blog_rest_jpa.model.Post;
import blog_rest_jpa.blog_rest_jpa.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PostRepositoryTests {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void PostRepository_SaveAll_ReturnSavePost(){
        // Arrange
    Post hello = Post.builder()
            .title("hello20")
            .body("hello 20 test")
            .description("hello desc 22")
            .build();
        //Act
        Post savedPost = postRepository.save(hello);

        // Assert
    Assertions.assertThat(savedPost).isNotNull();
    Assertions.assertThat(savedPost.getId()).isGreaterThan(0);
    }

    @Test
    public void PostRepository_GetAll_returnMoreThanOnePost(){
        //Arrange
        Post hello2 = Post.builder()
                .title("hello21")
                .description("hello21 desc")
                .body("Hello21body")
                .build();
        Post hello3 = Post.builder()
                .title("hello23")
                .description("hello23 desc")
                .body("Hello23 body")
                .build();
        //Act
        postRepository.save(hello2);
        postRepository.save(hello3);
        List<Post> postList = postRepository.findAll();


    Assertions.assertThat(postList).isNotNull();
    Assertions.assertThat(postList.size()).isEqualTo(2);
    }

    @Test
    public void PostRepository_GetByID_ReturnFindById(){

        Post post = Post.builder()
                .title("post title 1")
                .description("post 1 description 1")
                .body("post 1 body")
                .build();
        postRepository.save(post);
      Post resPost = postRepository.findById(post.getId()).get();
      //

//Assertions
        Assertions.assertThat(resPost).isNotNull();

    }
}
