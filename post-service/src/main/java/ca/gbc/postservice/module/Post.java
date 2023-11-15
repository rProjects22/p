package ca.gbc.postservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Data
@Builder
@Document(value = "post")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String content; //the text of the post
    private LocalDateTime timestamp; //when the post was created
    private String userId; //reference to the user who created the post
}

