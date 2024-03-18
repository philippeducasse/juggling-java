package juggling;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity

public class Pattern {

    @Id
    @SequenceGenerator(name = "pattern_id_sequence", sequenceName = "pattern_id_sequence", allocationSize = 1

    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pattern_id_sequence")

    private Long id;
    private String patternName;

    public Pattern(String patternName) {
        super();
        this.patternName = patternName;
    }

    public Pattern() {
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

    @Override
    public String toString() {
        return "Pattern [id= " + id + ", patternName=" + patternName + "]";
    }

}
