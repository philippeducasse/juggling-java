package juggling.dto;

public record NewUserRequest(
        String userName,
        String password,
        String email) {
}
