package suren.heaven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suren.heaven.exception.UserNotFoundException;
import suren.heaven.model.Post;
import suren.heaven.model.User;
import suren.heaven.repository.PostRepo;
import suren.heaven.repository.UserRepo;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/post/{userId}")
    public Post newPost(@PathVariable Long userId, @RequestBody Post newPost) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        newPost.setUser(user);
        return postRepo.save(newPost);
    }

    @GetMapping("/user/{userId}/posts")
    public List<Post> getAllPostsByUserId(@PathVariable Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return user.getPosts();
    }
}