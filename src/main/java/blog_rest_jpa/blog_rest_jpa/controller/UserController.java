package blog_rest_jpa.blog_rest_jpa.controller;

import blog_rest_jpa.blog_rest_jpa.Mapper.UserMapper;
import blog_rest_jpa.blog_rest_jpa.dto.UserDto;
import blog_rest_jpa.blog_rest_jpa.model.User;
import blog_rest_jpa.blog_rest_jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/api/user")
    public User newUser(@RequestBody UserDto userDto){
        User user = UserMapper.DtoToModel(userDto);
        return userRepository.save(user);
    }
    @GetMapping("/api/user")
    public List<UserDto> userList(){
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> UserMapper.modelToDto(user)).collect(Collectors.toList());
    }
    @GetMapping("api/user/{id}")
    public UserDto getUser(@PathVariable("id")Long id){
        User user = userRepository.findById(id).get();
        return UserMapper.modelToDto(user);
    }
    @DeleteMapping("api/user/{id}")
    public String deleteUser(@PathVariable("id")Long id){
         userRepository.deleteById(id);
        return "user is deleted";
    }
    @PutMapping("api/user/{id}")
    public User editUser(@RequestBody User user){
    User editedUser =  userRepository.save(user);
        return editedUser;
    }
}
