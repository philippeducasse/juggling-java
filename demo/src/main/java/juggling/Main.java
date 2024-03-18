package juggling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.Id;

import org.springframework.web.bind.annotation.RequestParam;

// these are wrappers which encapsulates various configurations
@SpringBootApplication
// this marks the class as a web controller, and that all methods in the marked
// class will return a JSON response.
// needed to create REST endpoints
@RestController

public class Main {
    // public is the modifier. It makes the class main available to any part of the
    // code
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
    @GetMapping("/")
    public GreetResponse greet() {
        return new GreetResponse("Hello There");
    }

    record GreetResponse(String greet) {
    }
}
