package juggling.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import juggling.repositories.PatternRepository;
import juggling.models.Pattern;
import java.util.List;
import java.util.Optional;

@Service
public class PatternService {
    private final PatternRepository patternRepository;

    @Autowired
    public PatternService(PatternRepository patternRepository) {
        this.patternRepository = patternRepository;
    }

    // Get all patterns
    public List<Pattern> getAllPatterns() {
        return patternRepository.findAll();
    }

    // Add a new pattern
    public Pattern addPattern(Pattern pattern) {
        // You can add additional logic here before saving
        return patternRepository.save(pattern);
    }

    // Find a pattern by ID
    public Optional<Pattern> getPatternById(Integer id) {
        return patternRepository.findById(id);
    }

    // Update a pattern
    public Pattern updatePattern(Integer id, Pattern patternDetails) {
        Pattern pattern = patternRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pattern not found for this id :: " + id));
        pattern.setPatternName(patternDetails.getPatternName());
        pattern.setPatternCode(patternDetails.getPatternCode());
        pattern.setPatternDifficulty(patternDetails.getPatternDifficulty());
        // Additional logic before saving
        return patternRepository.save(pattern);
    }

    // Delete a pattern
    public void deletePattern(Integer id) {
        Pattern pattern = patternRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pattern not found for this id :: " + id));
        patternRepository.delete(pattern);
    }
    // further business logic for patterns: validation, data transformation,

}
