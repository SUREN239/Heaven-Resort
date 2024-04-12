package suren.heaven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import suren.heaven.exception.UserNotFoundException;
import suren.heaven.model.Profile;
import suren.heaven.model.User;
import suren.heaven.repository.ProfileRepo;
import suren.heaven.repository.UserRepo;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProfileController {

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/profile/{userId}")
    public Profile getProfileByUserId(@PathVariable Long userId){
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return user.getProfile();
    }
    @DeleteMapping("/profile/{id}")
    public ResponseEntity<String> deleteProfileById(@PathVariable Long id) {
        if(profileRepo.existsById(id)) {
            profileRepo.deleteById(id);
            return ResponseEntity.ok("Profile with ID " + id + " deleted successfully.");
        } else {
            throw new UserNotFoundException(id);
        }
    }


    @PostMapping("/profile")
    public Profile newProfile(@RequestBody Profile newProfile) {
        return profileRepo.save(newProfile);
    }

    @GetMapping("/profiles")
    public List<Profile> getAllProfiles() {
        return profileRepo.findAll();
    }

    @GetMapping("/profile/{id}")
    public Profile getProfileById(@PathVariable Long id){
        return profileRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
