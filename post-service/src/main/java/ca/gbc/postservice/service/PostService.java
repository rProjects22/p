package ca.gbc.postservice.service;

import ca.gbc.postservice.dto.PostRequest;
import ca.gbc.postservice.dto.PostResponse;
import ca.gbc.postservice.dto.UserDTO;

import java.util.List;

//the interface defining methods related to post management.
public interface PostService {

    //create methods that handle the relationship between users and posts.
    //service layer sitting between repository and model.
    //using it for business operations.
    UserDTO getUserDetails(String userId);

    void createPost(PostRequest postRequest);

    String updatePost(String postId, PostRequest postRequest);

    void deletePost(String postId);

    List<PostResponse> getAllPosts();
}
