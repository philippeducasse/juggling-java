package juggling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

// these are wrappers which encapsulates various configurations
@SpringBootApplication
// this marks the class as a web controller, and that all methods in the marked
// class will return a JSON response.
// needed to create REST endpoints
@RestController
@RequestMapping("api/v1/patterns")

public class Main {

    private final PatternRepository patternRepository;

    // public is the modifier. It makes the class main available to any part of the
    // code
    public Main(PatternRepository patternRepository) {
        this.patternRepository = patternRepository;
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
    public List<Pattern> getPatterns() {
        return patternRepository.findAll();
    }

    record NewPatternRequest(
            String patternName,
            String patternCode,
            String patternDifficulty) {
    }

    @PostMapping
    public void addPattern(@RequestBody NewPatternRequest request) {
        Pattern pattern = new Pattern();
        pattern.setPatternName(request.patternName());
        pattern.setPatternCode(request.patternCode());
        pattern.setPatternDifficulty(request.patternDifficulty());
        patternRepository.save(pattern);
    }

    @DeleteMapping("{patternId}")
    public void deletePattern(@PathVariable("patternId") Integer id) {
        patternRepository.deleteById(id);
    }

    @PutMapping("{patternId}")
    public void updatePattern(@RequestBody @PathVariable("patternId") NewPatternRequest request) {
        Pattern pattern = new Pattern();
        pattern.setPatternName(request.patternName());
        pattern.setPatternCode(request.patternCode());
        pattern.setPatternDifficulty(request.patternDifficulty());
        patternRepository.save(pattern);
    }
}
