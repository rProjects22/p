package ca.gbc.postservice.service;

import ca.gbc.postservice.dto.PostRequest;
import ca.gbc.postservice.dto.PostResponse;

import java.util.List;

//the interface defining methods related to post management.
public interface PostService {

    //create methods that handle the relationship between users and posts.
    //service layer sitting between repository and model.
    //using it for business operations.

    void createPost(PostRequest postRequest);

    String updatePost(String postId, PostRequest postRequest);

    void deletePost(String postId);

    List<PostResponse> getAllPosts();
}
