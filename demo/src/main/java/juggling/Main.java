package juggling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import juggling.models.Pattern;
import juggling.models.User;
import juggling.repositories.PatternRepository;
import juggling.repositories.UserRepository;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

// these are wrappers which encapsulates various configurations
@SpringBootApplication
// this marks the class as a web controller, and that all methods in the marked
// class will return a JSON response.
// needed to create REST endpoints
// restcontrollers returns a domain object instead of a view

public class Main {

    // final means the vlaue cannot be changes (const)
    private final PatternRepository patternRepository;
    private final UserRepository userRepository;

    // public is the modifier. It makes the class main available to any part of the
    // code
    public Main(PatternRepository patternRepository, UserRepository userRepository) {
        this.patternRepository = patternRepository;
        this.userRepository = userRepository;
    }

    // static means it is independent from the modifier "public"
    // void is the type, means that the method doesnt return anything
    // String [] args means that this method takes an array of arguments of the type
    // String
    public static void main(String[] args) {
        // this refers back to @SpringBootApplication
        SpringApplication.run(Main.class, args);
    }

    // these are other annotations which refer to springMVC. Used for writting
    // servlets
    // its an abbreviation of @requestMapping

    @GetMapping
    public ResponseEntity<?> getPatterns() {
        List<Pattern> patterns = patternRepository.findAll();
        if (patterns.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No patterns found");
        } else {
            // Return the list of patterns with a 200 OK status if patterns are found
            return ResponseEntity.ok(patterns);
        }
    }

    record NewPatternRequest(
            String patternName,
            String patternCode,
            String patternDifficulty) {
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found");
        } else {
            return ResponseEntity.ok(users);
        }
    }

    record NewUserRequest(
            String userName,
            String password,
            String email) {
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody NewUserRequest request) {
        try {

            User user = new User();
            user.setUserName(request.userName());
            user.setPassword(request.password());
            user.setEmail(request.email());
            userRepository.save(user);
            return ResponseEntity.ok("user successfully created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating the user: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addPattern(@RequestBody NewPatternRequest request) {
        try {

            Pattern pattern = new Pattern();
            pattern.setPatternName(request.patternName());
            pattern.setPatternCode(request.patternCode());
            pattern.setPatternDifficulty(request.patternDifficulty());
            patternRepository.save(pattern);
            return ResponseEntity.ok("Pattern successfully created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating the pattern: " + e.getMessage());
        }
    }

    @DeleteMapping("{patternId}")

    public ResponseEntity<?> deletePattern(@PathVariable("patternId") Integer id) {
        try {
            patternRepository.deleteById(id);
            return ResponseEntity.ok("Pattern successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating the pattern: " + e.getMessage());
        }
    }

    @PutMapping("{patternId}")
    public ResponseEntity<?> updatePattern(@PathVariable("patternId") Integer id,
            @RequestBody NewPatternRequest request) {
        try {

            Pattern pattern = patternRepository.findById(id).get();
            pattern.setPatternName(request.patternName());
            pattern.setPatternCode(request.patternCode());
            pattern.setPatternDifficulty(request.patternDifficulty());
            patternRepository.save(pattern);
            return ResponseEntity.ok("Pattern successfully updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating the pattern: " + e.getMessage());
        }
    }
}
