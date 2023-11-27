package blog_rest_jpa.blog_rest_jpa.controller;

import blog_rest_jpa.blog_rest_jpa.Mapper.PostMapper;
import blog_rest_jpa.blog_rest_jpa.dto.PostDto;
import blog_rest_jpa.blog_rest_jpa.model.Post;
import blog_rest_jpa.blog_rest_jpa.repository.PostRepository;
import blog_rest_jpa.blog_rest_jpa.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
public class PostController {
  @Autowired
  private PostService postService;
  @Autowired private PostRepository postRepository;
  @CrossOrigin
  @PostMapping("/api/post")
  public PostDto newPost(@RequestBody PostDto p) {
    Post post = PostMapper.dtoToModel(p);
    Post newPost = postRepository.save(post);
    return PostMapper.modelToDto(newPost);
  }
  @CrossOrigin
  @GetMapping("/api/post/recommended")
  public PostDto recommendedPost(){
    Post post = postRepository.findAllOrderedByLastId();
    PostDto postDto = PostMapper.modelToDto(post);
    return postDto;
  }

  @CrossOrigin
  @GetMapping("/api/post")
  public List<PostDto> postList() {
   return postService.postsList();
  }
  @CrossOrigin
  @GetMapping("/api/post/{id}")
  public PostDto getPost(@PathVariable("id") Long id) {
    System.out.println(id);
    return postService.getPostById(id);
  }
  @CrossOrigin
  @DeleteMapping("/api/post/{id}")
  public String deletePost(@PathVariable("id") Long id) {
    return postService.deletedPost(id);
  }
  @CrossOrigin
  @PutMapping("/api/post/{id}")
  public PostDto postUpdate(@RequestBody PostDto p) {
    System.out.println(p);
  return postService.updatedPost(p);
  }
  @CrossOrigin
  @GetMapping("/api/post/search")
  public List<PostDto> searchPost(@RequestParam("title") String title) {
    return postService.serachPostByTitle(title);
  }
}
