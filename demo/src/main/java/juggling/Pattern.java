package juggling;

public class Pattern {
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

    @Override
    public String toString() {
        return "Pattern [id= " + id + ", patternName=" + patternName + "]";
    }

}
