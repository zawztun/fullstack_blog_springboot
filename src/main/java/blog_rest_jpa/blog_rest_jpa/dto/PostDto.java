package blog_rest_jpa.blog_rest_jpa.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDto {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String title;
        private  String description;
        private  String body;
        private String photoUrl;
        @CreationTimestamp
        private LocalDateTime createdOn;
        @UpdateTimestamp
        private LocalDateTime updatedOn;
    }

