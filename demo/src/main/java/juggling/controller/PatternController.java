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

import juggling.models.Pattern;
import juggling.services.PatternService;
import juggling.dto.NewPatternRequest;

@RestController
@RequestMapping("/patterns")
public class PatternController {

    private final PatternService patternService;

    @Autowired
    public PatternController(PatternService patternService) {
        this.patternService = patternService;
    }

    // these are other annotations which refer to springMVC. Used for writting
    // servlets
    // its an abbreviation of @requestMapping

    @GetMapping
    public ResponseEntity<?> getPatterns() {
        List<Pattern> patterns = patternService.getAllPatterns();
        if (patterns.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No patterns found");
        } else {
            // Return the list of patterns with a 200 OK status if patterns are found
            return ResponseEntity.ok(patterns);
        }
    }

    @PostMapping
    public ResponseEntity<?> addPattern(@RequestBody NewPatternRequest request) {
        try {

            Pattern pattern = new Pattern();
            pattern.setPatternName(request.patternName());
            pattern.setPatternCode(request.patternCode());
            pattern.setPatternDifficulty(request.patternDifficulty());
            patternService.addPattern(pattern);
            return ResponseEntity.ok("Pattern successfully created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating the pattern: " + e.getMessage());
        }
    }

    @DeleteMapping("{patternId}")

    public ResponseEntity<?> deletePattern(@PathVariable("patternId") Integer id) {
        try {
            patternService.deletePattern(id);
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

            Pattern pattern = patternService.getPatternById(id).get();
            pattern.setPatternName(request.patternName());
            pattern.setPatternCode(request.patternCode());
            pattern.setPatternDifficulty(request.patternDifficulty());
            patternService.updatePattern(id, pattern);
            return ResponseEntity.ok("Pattern successfully updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating the pattern: " + e.getMessage());
        }
    }
}
