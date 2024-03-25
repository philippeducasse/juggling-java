package juggling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// these are wrappers which encapsulates various configurations
@SpringBootApplication
// this marks the class as a web controller, and that all methods in the marked
// class will return a JSON response.
// needed to create REST endpoints
// restcontrollers returns a domain object instead of a view

public class Main {

    // final means the vlaue cannot be changes (const)

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
}
