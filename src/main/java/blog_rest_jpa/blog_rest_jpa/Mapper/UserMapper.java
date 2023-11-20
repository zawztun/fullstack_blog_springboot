package blog_rest_jpa.blog_rest_jpa.Mapper;

import blog_rest_jpa.blog_rest_jpa.dto.UserDto;
import blog_rest_jpa.blog_rest_jpa.model.User;

public class UserMapper {
  public static UserDto modelToDto(User user){

        return UserDto.builder()
                .id(user.getId())
                .password(user.getPassword())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public static User DtoToModel(UserDto userDto){
      return User.builder()
              .id(userDto.getId())
              .username(userDto.getUsername())
              .password(userDto.getPassword())
              .email(userDto.getEmail())
              .build();
    }

}
