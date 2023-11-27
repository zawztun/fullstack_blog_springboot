package blog_rest_jpa.blog_rest_jpa.service;

import blog_rest_jpa.blog_rest_jpa.Mapper.UserMapper;
import blog_rest_jpa.blog_rest_jpa.dto.UserDto;
import blog_rest_jpa.blog_rest_jpa.model.User;
import blog_rest_jpa.blog_rest_jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
   private UserRepository userRepository;

    public UserDto addUser(@RequestBody UserDto userDto){
        User user = UserMapper.DtoToModel(userDto);
        User editdUser = userRepository.save(user);
        return UserMapper.modelToDto(editdUser);
    }
    public List<UserDto> usersList(){
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> UserMapper.modelToDto(user)).collect(Collectors.toList());
    }
    public UserDto getUser(@PathVariable("id")Long id){
        User user = userRepository.findById(id).get();
        return UserMapper.modelToDto(user);
    }

    public String deleteUser(@PathVariable("id")Long id){
        userRepository.deleteById(id);
        return "user is deleted";
    }

    public UserDto updatePost(@RequestBody UserDto userDto) {
        User user =  UserMapper.DtoToModel(userDto);
        User user1 = userRepository.save(user);
        return UserMapper.modelToDto(user1);
    }

}
