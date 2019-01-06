package core;

public class SubTestResult {
    private String subTestName;
    private Integer maxScore;
    private Integer actualScore;

    public SubTestResult() {
    }

    public String getSubTestName() {
        return subTestName;
    }

    public SubTestResult setSubTestName(String subTestName) {
        this.subTestName = subTestName;
        return this;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public SubTestResult setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
        return this;
    }

    public Integer getActualScore() {
        return actualScore;
    }

    public SubTestResult setActualScore(Integer actualScore) {
        this.actualScore = actualScore;
        return this;
    }

    @Override
    public String toString() {
        return String.format("{\"%s\":\"%s\", \"%s\": %d, \"%s\": %d}", "subTestName", subTestName,
                "maxScore", maxScore, "actualScore", actualScore);
    }
}
