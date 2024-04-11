package suren.heaven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suren.heaven.exception.UserNotFoundException;
import suren.heaven.model.User;
import suren.heaven.repository.UserRepo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/client")
    public User newUser(@RequestBody User newUser) {
        return userRepo.save(newUser);
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id){
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/user/username/{username}")
    public List<User> getUsersByUsername(@PathVariable String username){
        return userRepo.findByUsername(username);
    }

    @GetMapping("/user/fullName/{fullName}")
    public List<User> getUsersByFullName(@PathVariable String fullName){
        return userRepo.findByFullName(fullName);
    }

    @GetMapping("/users/withPosts")
    public List<User> getUsersWithPosts(){
        return userRepo.findUsersWithPosts();
    }

    @GetMapping("/getUsers")
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        Page<User> users = userRepo.findAll(pageable);
        if(users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }



    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginRequest) {
        User user = userRepo.findByEmail(loginRequest.getEmail());

        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok("Login successful"+"["+user.getUsername()+","+user.getId()+"]");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
