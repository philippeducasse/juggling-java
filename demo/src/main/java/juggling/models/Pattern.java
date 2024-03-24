package juggling.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity

public class Pattern {

    @Id
    @SequenceGenerator(name = "pattern_id_sequence", sequenceName = "pattern_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pattern_id_sequence")

    private Long id;
    private String patternCode;
    private String patternName;
    private String patternDifficulty;

    public Pattern(String patternName, String patternCode, String patternDifficulty) {
        super();
        this.patternName = patternName;
        this.patternCode = patternCode;
        this.patternDifficulty = patternDifficulty;
    }

    public Pattern() {
    }

    public String getPatternCode() {
        return patternCode;
    }

    public String getPatternDifficulty() {
        return patternDifficulty;
    }

    public Long getId() {
        return id;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public void setPatternDifficulty(String patternDifficulty) {
        this.patternDifficulty = patternDifficulty;
    }

    public void setPatternCode(String patternCode) {
        this.patternCode = patternCode;
    }

    @Override
    public String toString() {
        return "Pattern [id= " + id + ", patternName=" + patternName + "]";
    }

}
