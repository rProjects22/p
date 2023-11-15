package ca.gbc.postservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data //from Lombok to generate constructors, getters and setter...
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequest {
    private String id;
    private String content;
    private LocalDateTime timestamp;
}
