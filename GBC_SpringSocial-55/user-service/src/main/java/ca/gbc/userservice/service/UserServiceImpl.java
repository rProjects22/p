package ca.gbc.userservice.service;

import ca.gbc.userservice.dto.UserRequest;
import ca.gbc.userservice.dto.UserResponse;
import ca.gbc.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ca.gbc.userservice.model.User;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //letting know that this is the service implementation.
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    //what i want to inject to my service constructor?
    //injecting repository to access it.

   private final UserRepository userRepository;
   private final MongoTemplate mongoTemplate;

    @Override
    //creating the product in the memory.
    public void createUser(UserRequest userRequest){
        log.debug("Creating a new user{}", userRequest.getUsername());

        User user = User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
        //passing the product to the product document.
        userRepository.save(user);

        log.info("User {} is saved.", user.getId());
    }

    //mongoDB provides built-in methods for updating...
    @Override
    public String updateUser(String userId, UserRequest userRequest) {
        log.info("Updating an user with Id {}", userId);

        //custom update query:
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        User user = mongoTemplate.findOne(query, User.class);

        //getting user info if not empty and modifying user fields:
        if(user != null){
            user.setUsername(userRequest.getUsername());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());

            //update it to the actual database.
            log.info("User {} is updated.", userId);
            return userRepository.save(user).getId();
        }
        return userId.toString();
    }

    @Override
    public void deleteUser(String userId) {
        log.info("User {} is deleted", userId);
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        log.info("Returning a list of users");
        List<User> users = userRepository.findAll();

        //map calls the mapToProductResponse function to generate (in this case) a list of productResponses.
        //stream works on a collection of data and stores it into the list(similar to for/if loops).
        return users.stream().map(this::mapToUserResponse).toList();
    }
    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
