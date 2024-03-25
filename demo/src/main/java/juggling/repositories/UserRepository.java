package juggling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import juggling.models.User;

public interface UserRepository
                extends JpaRepository<User, Integer> {
}
