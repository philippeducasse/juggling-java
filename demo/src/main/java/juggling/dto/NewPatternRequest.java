package juggling.dto;

//DTO: Data Transfer Object: a data structure for transferring data between different layers of the application, particularly for incoming requests.

public record NewPatternRequest(String patternName, String patternCode, String patternDifficulty) {
}
