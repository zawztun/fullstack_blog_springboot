package blog_rest_jpa.blog_rest_jpa.service;

import blog_rest_jpa.blog_rest_jpa.Mapper.PostMapper;
import blog_rest_jpa.blog_rest_jpa.dto.PostDto;
import blog_rest_jpa.blog_rest_jpa.model.Post;
import blog_rest_jpa.blog_rest_jpa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
  @Autowired private PostRepository postRepository;

  public PostDto newPost(@RequestBody PostDto p) {
    Post post = PostMapper.dtoToModel(p);
    Post newPost = postRepository.save(post);
    return PostMapper.modelToDto(newPost);
  }

  public List<PostDto> postsList() {
    List<Post> posts = postRepository.findAll();
    return posts.stream().map(post -> PostMapper.modelToDto(post)).collect(Collectors.toList());
  }

  public List<PostDto> serachPostByTitle(String title) {
    if (title.length() < 2) {
      return null;
    }
    List<Post> posts = postRepository.searchByTitle(title);
    return posts.stream().map(p -> PostMapper.modelToDto(p)).collect(Collectors.toList());
  }

  public PostDto recommendedPost(){
    Post post = postRepository.findAllOrderedByLastId();
    PostDto postDto = PostMapper.modelToDto(post);
    return postDto;
  }
  public PostDto getPostById(Long id) {
    Post p = postRepository.findById(id).get();
    System.out.println(p);
    PostDto postDto = PostMapper.modelToDto(p);
    return postDto;
  }

  public PostDto updatedPost(PostDto p) {
    Post editPost = PostMapper.dtoToModel(p);
    System.out.println(p);
    Post post = postRepository.save(editPost);
    return PostMapper.modelToDto(post);
  }

  public String deletedPost(Long id) {
    postRepository.deleteById(id);
    return "Your post is Successful deleted";
  }

}
