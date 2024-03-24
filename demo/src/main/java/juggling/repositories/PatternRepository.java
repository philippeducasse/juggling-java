package juggling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import juggling.models.Pattern;

public interface PatternRepository
        // integer corresponds to pattern ID
        // here we are extending the jparepository which includes all crud operations
        extends JpaRepository<Pattern, Integer> {
}
