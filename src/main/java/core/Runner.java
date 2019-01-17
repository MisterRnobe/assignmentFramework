package core;

import core.marker.Assignment;
import core.marker.AssignmentTest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {
    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        //Extract test
        List<Class> assignmentClasses = loadClassesFromPackage("assignment").stream()
                .filter(c -> c.isAnnotationPresent(Assignment.class))
                .collect(Collectors.toList());
        if (assignmentClasses.isEmpty()) {
            throw new RuntimeException("No assignment classes found!");
        }

        List<Object> assignments = assignmentClasses.stream()
                .map(c -> {
                    try {
                        return c.getConstructor();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .map(constructor -> {
                    try {
                        return constructor.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        //Extract methods
        Map<Object, List<Method>> tests = assignments.stream()
                .map(o -> new AbstractMap.SimpleEntry<>(
                        o,
                        Stream.of(o.getClass().getMethods())
                                .filter(m -> m.isAnnotationPresent(AssignmentTest.class))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        List<TestResult> testResults = tests.entrySet().stream()
                .map((e) -> {
                    Object testInstance = e.getKey();
                    List<Method> testMethods = e.getValue();

                    TestResult testResult = new TestResult(testInstance.getClass().getAnnotation(Assignment.class).name());
                    testMethods.forEach(m -> {
                        AssignmentTest assignmentTest = m.getAnnotation(AssignmentTest.class);
                        int score = 0;
                        String error = null;
                        try {
                            m.invoke(testInstance);
                            score = assignmentTest.score();
                        } catch (InvocationTargetException exception) {
                            exception.getTargetException().printStackTrace();
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            PrintStream printStream = new PrintStream(byteArrayOutputStream);
                            exception.getTargetException().printStackTrace(printStream);
                            error = byteArrayOutputStream.toString();
                            printStream.close();

                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        }
                        SubTestResult subTestResult = new SubTestResult();
                        subTestResult.setMaxScore(assignmentTest.score());
                        subTestResult.setSubTestName(assignmentTest.name());
                        subTestResult.setActualScore(score);
                        subTestResult.setErrorMsg(error);
                        testResult.addSubTestResult(subTestResult);
                    });
                    return testResult;
                })
                .collect(Collectors.toList());
        String result = testResults.stream()
                .map(TestResult::toString)
                .collect(Collectors.joining(",", "[", "]"));
        System.out.println(result);
    }

    private static List<Class> loadClassesFromPackage(String packageName) throws IOException {
        String path = System.getProperty("user.dir") + "\\target\\classes\\" + packageName;
        List<Class> assignmentClasses;
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            assignmentClasses = paths
                    .filter(Files::isRegularFile)
                    .map(file -> {
                        try {
                            String fileName = file.getFileName().toString();
                            String className = packageName + "." + fileName.replace(".class", "");
                            return Class.forName(className);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        return assignmentClasses;
    }


}
