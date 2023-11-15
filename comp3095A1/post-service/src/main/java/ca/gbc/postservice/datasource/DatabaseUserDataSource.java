package ca.gbc.postservice.datasource;

import ca.gbc.postservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseUserDataSource implements UserDataSource {

    private final UserRepository userRepository;

    @Autowired
    public DatabaseUserDataSource(@Qualifier("userRepo") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserDetails(String userId) {
        return userRepository.findUserByUserId(userId);
    }
}
