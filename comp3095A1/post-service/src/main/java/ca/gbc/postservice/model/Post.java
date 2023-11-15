package ca.gbc.postservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(value = "post")
public class Post {
    @Id
    private String id;
    private String content; //the text of the post
    private LocalDateTime timestamp; //when the post was created
    private String userId; //reference to the user who created the post
}
