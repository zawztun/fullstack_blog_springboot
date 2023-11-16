package blog_rest_jpa.blog_rest_jpa.controller;

import blog_rest_jpa.blog_rest_jpa.Mapper.PostMapper;
import blog_rest_jpa.blog_rest_jpa.dto.PostDto;
import blog_rest_jpa.blog_rest_jpa.model.Post;
import blog_rest_jpa.blog_rest_jpa.repository.PostRepository;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
public class PostController {
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
    List<Post> posts = postRepository.findAll();
    return posts.stream().map(post -> PostMapper.modelToDto(post)).collect(Collectors.toList());
  }

  @CrossOrigin
  @GetMapping("/api/post/{id}")
  public PostDto getPost(@PathVariable("id") Long id) {
    Post p = postRepository.findById(id).get();
    PostDto postDto = PostMapper.modelToDto(p);
    return postDto;
  }
  @CrossOrigin
  @DeleteMapping("/api/post/{id}")
  public String deletePost(@PathVariable("id") Long id) {
    postRepository.deleteById(id);
    return "Your post is Successful deleted";
  }
  @CrossOrigin
  @PutMapping("/api/post/{id}")
  public PostDto updatePost(@RequestBody PostDto p) {
    Post editPost = PostMapper.dtoToModel(p);
    Post post = postRepository.save(editPost);
    return PostMapper.modelToDto(post);
  }
}
