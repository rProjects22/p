package ca.gbc.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //from Lombok to generate constructors, getters and setter...
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    //include the attributes needed for creating or updating a user profile.

    private String username;
    private String email;
    private String password;
}

