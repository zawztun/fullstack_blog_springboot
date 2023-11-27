package blog_rest_jpa.blog_rest_jpa.controller;

import blog_rest_jpa.blog_rest_jpa.Mapper.PostMapper;
import blog_rest_jpa.blog_rest_jpa.Mapper.UserMapper;
import blog_rest_jpa.blog_rest_jpa.dto.PostDto;
import blog_rest_jpa.blog_rest_jpa.dto.UserDto;
import blog_rest_jpa.blog_rest_jpa.model.Post;
import blog_rest_jpa.blog_rest_jpa.model.User;
import blog_rest_jpa.blog_rest_jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @CrossOrigin
    @PostMapping("/api/user")
    public UserDto newUser(@RequestBody UserDto userDto){
        User user = UserMapper.DtoToModel(userDto);
        User editdUser = userRepository.save(user);
        return UserMapper.modelToDto(editdUser);
    }
    @CrossOrigin
    @GetMapping("/api/user")
    public List<UserDto> userList(){
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> UserMapper.modelToDto(user)).collect(Collectors.toList());
    }
    @CrossOrigin
    @GetMapping("api/user/{id}")
    public UserDto getUser(@PathVariable("id")Long id){
        User user = userRepository.findById(id).get();
        return UserMapper.modelToDto(user);
    }
    @CrossOrigin
    @DeleteMapping("api/user/{id}")
    public String deleteUser(@PathVariable("id")Long id){
         userRepository.deleteById(id);
        return "user is deleted";
    }
    @CrossOrigin
    @PutMapping("/api/user/{id}")
    public UserDto updatePost(@RequestBody UserDto userDto) {
        User user =  UserMapper.DtoToModel(userDto);
        User user1 = userRepository.save(user);
        return UserMapper.modelToDto(user1);
    }

}
