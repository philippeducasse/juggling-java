package juggling.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import juggling.models.User;
import juggling.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Add a new user
    public User addUser(User user) {
        // You can add additional logic here before saving
        return userRepository.save(user);
    }

    // Find a user by ID
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    // Update a user
    public User updateUser(Integer id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found for this id :: " + id));
        user.setUserName(userDetails.getUserName());
        user.setEmail(userDetails.getEmail());
        // Additional logic before saving
        return userRepository.save(user);
    }

    // Delete a user
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found for this id :: " + id));
        userRepository.delete(user);
    }
    // further business logic for users: validation, data transformation,

    // further business logic for users: validation, data transformation,

}