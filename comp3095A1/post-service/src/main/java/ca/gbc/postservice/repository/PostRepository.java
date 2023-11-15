package ca.gbc.postservice.repository;

import ca.gbc.postservice.model.Post;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
    //Custom query methods
    //CRUD operations.

    @DeleteQuery
    void deleteById(String PostId);
}

