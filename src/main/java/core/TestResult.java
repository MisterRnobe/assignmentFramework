package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TestResult {
    private String testName;
    private List<SubTestResult> subTestResults;

    public TestResult(String testName) {
        this.testName = testName;
        subTestResults = new LinkedList<>();
    }

    public void addSubTestResult(SubTestResult subTestResult) {
        this.subTestResults.add(subTestResult);
    }

    public List<SubTestResult> getSubTestResults() {
        return new ArrayList<>(subTestResults);
    }

    @Override
    public String toString() {
        String stringSubTestResults = subTestResults.stream()
                .map(SubTestResult::toString)
                .collect(Collectors.joining(",", "[", "]"));
        return String.format("{\"%s\": \"%s\", \"%s\":%s}", "testName", testName,
                "subTestResults", stringSubTestResults);
    }
}
