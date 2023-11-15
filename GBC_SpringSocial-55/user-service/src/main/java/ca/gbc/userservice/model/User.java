package ca.gbc.userservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//we always start from modelling. Then move on to service and repository.
@Data
@Builder
@Document(value = "user")
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
}
