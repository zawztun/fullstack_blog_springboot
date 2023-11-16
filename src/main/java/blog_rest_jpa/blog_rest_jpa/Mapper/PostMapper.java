package blog_rest_jpa.blog_rest_jpa.Mapper;

import blog_rest_jpa.blog_rest_jpa.dto.PostDto;
import blog_rest_jpa.blog_rest_jpa.model.Post;

public class PostMapper {
    public static PostDto modelToDto(Post p) {
        return PostDto.builder()
                .id(p.getId())
                .title(p.getTitle())
                .body(p.getBody())
                .description(p.getDescription()).createdOn(p.getCreatedOn()).updatedOn(p.getUpdatedOn())
                .photoUrl(p.getPhotoUrl())
                .build();
    }
    public static Post dtoToModel(PostDto p) {
        return Post.builder()
                .id(p.getId())
                .title(p.getTitle()).body(p.getBody()).description(p.getDescription()).createdOn(p.getCreatedOn()).updatedOn(p.getUpdatedOn()).photoUrl(p.getPhotoUrl()).build();
    }
}
