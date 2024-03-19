package juggling;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatternRepository
        // integer corresponds to customer ID
        // here we are extending the jparepository which includes all crud operations
        extends JpaRepository<Pattern, Integer> {
}
