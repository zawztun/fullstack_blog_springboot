package blog_rest_jpa.blog_rest_jpa.controller;
import blog_rest_jpa.blog_rest_jpa.dto.UserDto;
import blog_rest_jpa.blog_rest_jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {

  @Autowired private UserService userService;

  @CrossOrigin
  @PostMapping("/api/user")
  public UserDto newUser(@RequestBody UserDto userDto) {
    return userService.addUser(userDto);
  }

  @CrossOrigin
  @GetMapping("/api/user")
  public List<UserDto> userList() {

    return userService.usersList();
  }

  @CrossOrigin
  @GetMapping("api/user/{id}")
  public UserDto getUser(@PathVariable("id") Long id) {
    return userService.getUser(id);
  }

  @CrossOrigin
  @DeleteMapping("api/user/{id}")
  public String deleteUser(@PathVariable("id") Long id) {
    return userService.deleteUser(id);
  }

  @CrossOrigin
  @PutMapping("/api/user/{id}")
  public UserDto updatePost(@RequestBody UserDto userDto) {
    return userService.updatePost(userDto);
  }
}
