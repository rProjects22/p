package ca.gbc.postservice.service;

import ca.gbc.postservice.dto.PostRequest;
import ca.gbc.postservice.dto.PostResponse;
import ca.gbc.postservice.model.Post;
import ca.gbc.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//The implementation of the "PostService" interface, handling post-related operations.
@Service //letting know that this is the service implementation.
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService{
    //what i want to inject to my service constructor?
    //injecting repository to access it.

    private final PostRepository postRepository;
    private final MongoTemplate mongoTemplate;
    private final RestTemplate restTemplate;

    @Override
    //creating the post in the memory.
    public void createPost(PostRequest postRequest){
        //log.debug("Creating a new post{}", PostRequest.getContent());

        Post post = Post.builder()
                .id(postRequest.getId())
                .content(postRequest.getContent())
                .timestamp(postRequest.getTimestamp())
                .build();
        //passing the product to the product document.
        postRepository.save(post);

        log.info("Post {} is saved.", post.getId());
    }

    //mongoDB provides built-in methods for updating...
    @Override
    public String updatePost(String postId, PostRequest postRequest) {
        log.info("Updating a post with Id {}", postId);

        //custom update query:
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(postId));
        Post post = mongoTemplate.findOne(query, Post.class);

        //getting user info if not empty and modifying user fields:
        if(post != null){
            post.setContent(postRequest.getContent());
            post.setTimestamp(postRequest.getTimestamp());

            //update it to the actual database.
            log.info("User {} is updated.", postId);
            return postRepository.save(post).getId();
        }
        return postId.toString();
    }

    @Override
    public void deletePost(String postId) {
        log.info("Post {} is deleted", postId);
        postRepository.deleteById(postId);
    }

    @Override
    public List<PostResponse> getAllPosts() {
        log.info("Returning a list of posts");
        List<Post> posts = postRepository.findAll();

        //map calls the mapToProductResponse function to generate (in this case) a list of productResponses.
        //stream works on a collection of data and stores it into the list(similar to for/if loops).
        return posts.stream().map(this::mapToPostResponse).toList();
    }
    private PostResponse mapToPostResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .timestamp(post.getTimestamp())
                .build();
    }
}

