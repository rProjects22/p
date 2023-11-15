package ca.gbc.postservice.datasource;

import ca.gbc.postservice.model.User;

public interface UserDataSource {
    User getUserDetails(String userId);
}