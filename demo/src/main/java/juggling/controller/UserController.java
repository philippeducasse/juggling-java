package juggling.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import juggling.models.User;
import juggling.services.UserService;
import juggling.dto.NewUserRequest;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found");
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody NewUserRequest request) {
        try {

            User user = new User();
            user.setUserName(request.userName());
            user.setPassword(request.password());
            user.setEmail(request.email());
            userService.addUser(user);
            return ResponseEntity.ok("user successfully created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating the user: " + e.getMessage());
        }
    }

    @DeleteMapping("{userId}")

    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("user successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating the user: " + e.getMessage());
        }
    }

    @PutMapping("{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Integer id,
            @RequestBody NewUserRequest request) {
        try {

            User user = userService.getUserById(id).get();
            user.setUserName(request.userName());
            user.setPassword(request.password());
            user.setEmail(request.email());

            userService.updateUser(id, user);
            return ResponseEntity.ok("user successfully updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating the user: " + e.getMessage());
        }
    }
}
